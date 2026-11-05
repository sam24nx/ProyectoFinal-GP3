package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Pasaje;

public class PasajeData {
    
    private Connection con;

    public PasajeData() {
        try {
            con = Conexion.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }

    public void registrarPasaje(Pasaje pasaje) {
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
                JOptionPane.showMessageDialog(null, "Pasaje registrado" + pasaje.getIdPasaje());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }
    
    public void anularPasaje(int idPasaje) {
        String sql = "UPDATE pasajes SET Estado=false WHERE ID_Pasaje=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPasaje);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Pasaje anulado (baja lógica)");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }
}