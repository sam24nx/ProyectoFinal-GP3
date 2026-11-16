package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Lugar;
import modelo.Pasaje;
import modelo.Pasajero;
import modelo.Viaje;

public class PasajeData {
    
    private Connection con;

    public PasajeData() {
        try {
            con = Conexion.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }

    public boolean registrarPasaje(Pasaje pasaje) {
        String sql = "INSERT INTO pasajes (ID_Pasajero, ID_Lugar, ID_Ruta, FechaVenta, HoraVenta, Precio, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, pasaje.getPasajero().getIdPasajero());
            ps.setInt(2, pasaje.getLugar().getIdAsiento());
            ps.setInt(3, pasaje.getViaje().getIdViaje()); 
            ps.setDate(4, Date.valueOf(pasaje.getFechaVenta()));
            ps.setTime(5, Time.valueOf(pasaje.getHoraVenta()));
            ps.setDouble(6, pasaje.getPrecio());
            ps.setBoolean(7, pasaje.isEstado());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pasaje.setIdPasaje(rs.getInt(1));
                Asientodata asientoData = new Asientodata();
                asientoData.bajaLogica(pasaje.getLugar().getIdAsiento());
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar pasaje: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean modificarPasaje(Pasaje pasaje) {
        String sql = "UPDATE pasajes SET ID_Pasajero = ?, ID_Lugar = ?, ID_Ruta = ?, FechaVenta = ?, HoraVenta = ?, Precio = ?, Estado = ? WHERE ID_Pasaje = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pasaje.getPasajero().getIdPasajero());
            ps.setInt(2, pasaje.getLugar().getIdAsiento());
            ps.setInt(3, pasaje.getViaje().getIdViaje());
            ps.setDate(4, Date.valueOf(pasaje.getFechaVenta()));
            ps.setTime(5, Time.valueOf(pasaje.getHoraVenta()));
            ps.setDouble(6, pasaje.getPrecio());
            ps.setBoolean(7, pasaje.isEstado());
            ps.setInt(8, pasaje.getIdPasaje());
            
            int exito = ps.executeUpdate();
            return exito == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar pasaje: " + ex.getMessage());
            return false;
        }
    }

    public Pasaje buscarPasaje(int idPasaje) {
        Pasaje pasaje = null;
        String sql = "SELECT * FROM pasajes WHERE ID_Pasaje = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPasaje);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                PasajeroData pasajeroData = new PasajeroData();
                Asientodata lugarData = new Asientodata();
                ViajeData viajeData = new ViajeData();

                Pasajero pasajero = pasajeroData.buscar(rs.getInt("ID_Pasajero"));
                Lugar lugar = lugarData.buscar(rs.getInt("ID_Lugar"));
                Viaje viaje = viajeData.buscarViaje(rs.getInt("ID_Ruta"));

                pasaje = new Pasaje(
                    rs.getInt("ID_Pasaje"),
                    pasajero,
                    lugar,
                    viaje,
                    rs.getDate("FechaVenta").toLocalDate(),
                    rs.getTime("HoraVenta").toLocalTime(),
                    rs.getDouble("Precio"),
                    rs.getBoolean("Estado")
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar pasaje: " + ex.getMessage());
        }
        return pasaje;
    }
    
    public void anularPasaje(int idPasaje) {
        Pasaje p = buscarPasaje(idPasaje);
        String sql = "UPDATE pasajes SET Estado = false WHERE ID_Pasaje = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPasaje);
            int exito = ps.executeUpdate();
            if (exito == 1) {

                if (p != null && p.getLugar() != null) {
                    p.getLugar().setEstado(true);
                    Asientodata asientoData = new Asientodata();
                    asientoData.actualizar(p.getLugar());
                }
                JOptionPane.showMessageDialog(null, "Pasaje anulado y asiento liberado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al anular: " + ex.getMessage());
        }
    }
    
    public List<Pasaje> listarPasajes() {
        List<Pasaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM pasajes";
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            PasajeroData pasajeroData = new PasajeroData();
            Asientodata lugarData = new Asientodata();
            ViajeData viajeData = new ViajeData();

            while (rs.next()) {
                int idPasaje = rs.getInt("ID_Pasaje");
                int idPasajero = rs.getInt("ID_Pasajero");
                int idLugar = rs.getInt("ID_Lugar");
                int idRuta = rs.getInt("ID_Ruta");
                
                java.time.LocalDate fechaVenta = rs.getDate("FechaVenta").toLocalDate();
                java.time.LocalTime horaVenta = rs.getTime("HoraVenta").toLocalTime();
                double precio = rs.getDouble("Precio");
                boolean estado = rs.getBoolean("Estado");

                Pasajero pasajero = pasajeroData.buscar(idPasajero);
                Lugar lugar = lugarData.buscar(idLugar);
                Viaje viaje = viajeData.buscarViaje(idRuta);

                Pasaje pasaje = new Pasaje(idPasaje, pasajero, lugar, viaje, fechaVenta, horaVenta, precio, estado);
                lista.add(pasaje);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + ex.getMessage());
        }
        return lista;
    }
}