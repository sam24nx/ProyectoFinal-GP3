/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Lugar;
import modelo.Pasaje;
import modelo.Pasajero;
import modelo.Viaje;
import modelo.Conexion;

public class PasajeData {

    private Connection con = null;

    public PasajeData() {
        try {
            con = Conexion.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }

    public void insertar(Pasaje pasaje) {
        String sql = "INSERT INTO pasajes(ID_Pasajero, ID_Lugar, ID_Ruta, FechaVenta, HoraVenta, Precio, Estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pasaje.getPasajero().getIdPasajero());
            ps.setInt(2, pasaje.getLugar().getIdAsiento());
            ps.setInt(3, pasaje.getViaje().getIdViaje());
            ps.setDate(4, java.sql.Date.valueOf(pasaje.getFechaVenta()));
            ps.setTime(5, java.sql.Time.valueOf(pasaje.getHoraVenta()));
            ps.setDouble(6, pasaje.getPrecio());
            ps.setBoolean(7, pasaje.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pasaje.setIdPasaje(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Pasaje guardado");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }

    public void modificar(Pasaje pasaje) {
        String sql = "UPDATE pasajes SET ID_Pasajero=?, ID_Lugar=?, ID_Ruta=?, FechaVenta=?, HoraVenta=?, Precio=?, Estado=? "
                   + "WHERE ID_Pasaje=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pasaje.getPasajero().getIdPasajero());
            ps.setInt(2, pasaje.getLugar().getIdAsiento());
            ps.setInt(3, pasaje.getViaje().getIdViaje());
            ps.setDate(4, java.sql.Date.valueOf(pasaje.getFechaVenta()));
            ps.setTime(5, java.sql.Time.valueOf(pasaje.getHoraVenta()));
            ps.setDouble(6, pasaje.getPrecio());
            ps.setBoolean(7, pasaje.isEstado());
            ps.setInt(8, pasaje.getIdPasaje());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Pasaje modificado");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "UPDATE pasajes SET Estado = 0 WHERE ID_Pasaje = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Pasaje anulado");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
    }

    public Pasaje buscar(int id) {
        String sql = "SELECT * FROM pasajes WHERE ID_Pasaje = ? AND Estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pasaje p = new Pasaje();
                p.setIdPasaje(rs.getInt("ID_Pasaje"));
                p.setFechaVenta(rs.getDate("FechaVenta").toLocalDate());
                p.setHoraVenta(rs.getTime("HoraVenta").toLocalTime());
                p.setPrecio(rs.getDouble("Precio"));
                p.setEstado(rs.getBoolean("Estado"));

                Pasajero pasajero = new Pasajero();
                pasajero.setIdPasajero(rs.getInt("ID_Pasajero"));
                p.setPasajero(pasajero);

                Lugar lugar = new Lugar();
                lugar.setIdAsiento(rs.getInt("ID_Lugar"));
                p.setLugar(lugar);

                Viaje viaje = new Viaje();
                viaje.setIdViaje(rs.getInt("ID_Ruta"));
                p.setViaje(viaje);

                return p;
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return null;
    }

    public List<Pasaje> listar() {
        List<Pasaje> pasajes = new ArrayList<>();
        String sql = "SELECT * FROM pasajes WHERE Estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasaje p = new Pasaje();
                p.setIdPasaje(rs.getInt("ID_Pasaje"));
                p.setFechaVenta(rs.getDate("FechaVenta").toLocalDate());
                p.setHoraVenta(rs.getTime("HoraVenta").toLocalTime());
                p.setPrecio(rs.getDouble("Precio"));
                p.setEstado(rs.getBoolean("Estado"));

                Pasajero pasajero = new Pasajero();
                pasajero.setIdPasajero(rs.getInt("ID_Pasajero"));
                p.setPasajero(pasajero);

                Lugar lugar = new Lugar();
                lugar.setIdAsiento(rs.getInt("ID_Lugar"));
                p.setLugar(lugar);

                Viaje viaje = new Viaje();
                viaje.setIdViaje(rs.getInt("ID_Ruta"));
                p.setViaje(viaje);

                pasajes.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        return pasajes;
    }
}
