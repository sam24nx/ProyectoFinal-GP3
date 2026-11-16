/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Colectivo;
import modelo.Conexion;
import modelo.Viaje;

/**
 *
 * @author facun
 */
public class ViajeData {

    private Connection con = null;

    public ViajeData() {
        try {
            con = Conexion.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }

    public void guardarViaje(Viaje viaje) {
        String sql = "INSERT INTO rutas(Origen, Destino, HoraSalida, HoraLlegada, ID_Colectivo, Precio, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, viaje.getOrigen());
            ps.setString(2, viaje.getDestino());
            ps.setTime(3, Time.valueOf(viaje.getHoraSalida()));
            ps.setTime(4, Time.valueOf(viaje.getHoraLlegada()));
            ps.setInt(5, viaje.getColectivo().getIdColectivo());
            ps.setDouble(6, viaje.getPrecio());
            ps.setBoolean(7, viaje.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                viaje.setIdViaje(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Viaje guardado");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }

    public void modificarViaje(Viaje viaje) {
        String sql = "UPDATE rutas SET Origen = ?, Destino = ?, HoraSalida = ?, HoraLlegada = ?, ID_Colectivo = ?, Precio = ?, Estado = ? WHERE ID_Ruta = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, viaje.getOrigen());
            ps.setString(2, viaje.getDestino());
            ps.setTime(3, Time.valueOf(viaje.getHoraSalida()));
            ps.setTime(4, Time.valueOf(viaje.getHoraLlegada()));
            ps.setInt(5, viaje.getColectivo().getIdColectivo());
            ps.setDouble(6, viaje.getPrecio());
            ps.setBoolean(7, viaje.isEstado());
            ps.setInt(8, viaje.getIdViaje());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Viaje modificado");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }

    public void eliminarViaje(int id) {
        // baja lógica
        String sql = "UPDATE rutas SET Estado = 0 WHERE ID_Ruta = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Viaje dado de baja");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }

    public List<Viaje> listarViajes() {
        List<Viaje> viajes = new ArrayList<>();
        String sql = "SELECT * FROM rutas WHERE Estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Viaje v = new Viaje();
                v.setIdViaje(rs.getInt("ID_Ruta"));
                v.setOrigen(rs.getString("Origen"));
                v.setDestino(rs.getString("Destino"));
                v.setHoraSalida(rs.getTime("HoraSalida").toLocalTime());
                v.setHoraLlegada(rs.getTime("HoraLlegada").toLocalTime());
                v.setPrecio(rs.getDouble("Precio"));
                v.setEstado(rs.getBoolean("Estado"));

                // se asigna el id
                Colectivo c = new Colectivo();
                c.setIdColectivo(rs.getInt("ID_Colectivo"));
                v.setColectivo(c);

                viajes.add(v);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return viajes;
    }

    public Viaje buscarViaje(int id) {
        String sql = "SELECT * FROM rutas WHERE ID_Ruta = ? AND Estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Viaje v = new Viaje();
                v.setIdViaje(rs.getInt("ID_Ruta"));
                v.setOrigen(rs.getString("Origen"));
                v.setDestino(rs.getString("Destino"));
                v.setHoraSalida(rs.getTime("HoraSalida").toLocalTime());
                v.setHoraLlegada(rs.getTime("HoraLlegada").toLocalTime());
                v.setPrecio(rs.getDouble("Precio"));
                v.setEstado(rs.getBoolean("Estado"));
                Colectivo c = new Colectivo();
                c.setIdColectivo(rs.getInt("ID_Colectivo"));
                v.setColectivo(c);
                return v;
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return null;
    }
    
    public LocalTime obtenerHoraSalida(int idRuta) {
    String sql = "SELECT HoraSalida FROM rutas WHERE ID_Ruta = ? AND Estado = 1";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idRuta);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            LocalTime hora = rs.getTime("HoraSalida").toLocalTime();
            ps.close();
            return hora;
        }
        ps.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
    }
    return null;
}
public List<Object[]> listarRutasPorOrigenDestino(String texto) {
    List<Object[]> filas = new ArrayList<>();
    String sql = "SELECT r.ID_Ruta, r.Origen, r.Destino, r.HoraSalida, r.HoraLlegada, "
               + "c.Matricula, c.Chofer, r.Precio "
               + "FROM rutas r "
               + "INNER JOIN colectivos c ON r.ID_Colectivo = c.ID_Colectivo "
               + "WHERE r.Estado = 1 AND (r.Origen LIKE ? OR r.Destino LIKE ?) "
               + "ORDER BY r.HoraSalida";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        String filtro = "%" + texto + "%";
        ps.setString(1, filtro);
        ps.setString(2, filtro);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Object[] fila = new Object[8];
            fila[0] = rs.getInt("ID_Ruta");
            fila[1] = rs.getString("Origen");
            fila[2] = rs.getString("Destino");
            fila[3] = rs.getTime("HoraSalida").toLocalTime();
            fila[4] = rs.getTime("HoraLlegada").toLocalTime();
            fila[5] = rs.getString("Matricula");
            fila[6] = rs.getString("Chofer");
            fila[7] = rs.getDouble("Precio");
            filas.add(fila);
        }
        ps.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
    }
    return filas;
}

}
