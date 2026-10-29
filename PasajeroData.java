/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Conexion;
import modelo.Pasajero;

public class PasajeroData {

    // ALTA
    public void insertar(Pasajero p) {
        String sql = "INSERT INTO Pasajeros (DNI, Nombre, Apellido, Correo, Telefono, Estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getCorreo());
            ps.setString(5, p.getTelefono());
            ps.setBoolean(6, p.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) p.setIdPasajero(rs.getInt(1));
            System.out.println("Pasajero insertado con ID: " + p.getIdPasajero());
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    // MODIFICAR
    public void actualizar(Pasajero p) {
        String sql = "UPDATE Pasajeros SET DNI=?, Nombre=?, Apellido=?, Correo=?, Telefono=?, Estado=? "
                   + "WHERE ID_Pasajero=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getCorreo());
            ps.setString(5, p.getTelefono());
            ps.setBoolean(6, p.isEstado());
            ps.setInt(7, p.getIdPasajero());
            ps.executeUpdate();
            System.out.println("Pasajero actualizado.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    // BUSCAR por ID
    public Pasajero buscar(int id) {
        Pasajero p = null;
        String sql = "SELECT * FROM Pasajeros WHERE ID_Pasajero=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Pasajero();
                p.setIdPasajero(rs.getInt("ID_Pasajero"));
                p.setDni(rs.getString("DNI"));
                p.setNombre(rs.getString("Nombre"));
                p.setApellido(rs.getString("Apellido"));
                p.setCorreo(rs.getString("Correo"));
                p.setTelefono(rs.getString("Telefono"));
                p.setEstado(rs.getBoolean("Estado"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return p;
    }

    // LISTAR todos
    public List<Pasajero> listar() {
        List<Pasajero> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pasajeros";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Pasajero p = new Pasajero();
                p.setIdPasajero(rs.getInt("ID_Pasajero"));
                p.setDni(rs.getString("DNI"));
                p.setNombre(rs.getString("Nombre"));
                p.setApellido(rs.getString("Apellido"));
                p.setCorreo(rs.getString("Correo"));
                p.setTelefono(rs.getString("Telefono"));
                p.setEstado(rs.getBoolean("Estado"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // BAJA LÓGICA
    public void bajaLogica(int id) {
        cambiarEstado(id, false);
    }

    // ALTA LÓGICA
    public void altaLogica(int id) {
        cambiarEstado(id, true);
    }

    private void cambiarEstado(int id, boolean estado) {
        String sql = "UPDATE Pasajeros SET Estado=? WHERE ID_Pasajero=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Estado del pasajero " + id + " cambiado a " + estado);
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado: " + e.getMessage());
        }
    }

    // BORRADO FÍSICO
    public void borrar(int id) {
        String sql = "DELETE FROM Pasajeros WHERE ID_Pasajero=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Pasajero " + id + " eliminado.");
        } catch (SQLException e) {
            System.out.println("Error al borrar: " + e.getMessage());
        }
    }
}
