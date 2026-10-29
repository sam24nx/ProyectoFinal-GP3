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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Colectivo;
import modelo.Lugar;
import modelo.Conexion;

public class LugarData {

    private Connection con;

    public LugarData() {
        try {
            con = Conexion.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
        }
    }

    public void insertar(Lugar l) {
        String sql = "INSERT INTO lugares (id_colectivo, numero_asiento, estado) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, l.getColectivo().getIdColectivo());
            ps.setInt(2, l.getNumeroAsiento());
            ps.setBoolean(3, l.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) l.setIdAsiento(rs.getInt(1));
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        }
    }

    public void actualizar(Lugar l) {
        String sql = "UPDATE lugares SET id_colectivo=?, numero_asiento=?, estado=? WHERE id_lugar=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, l.getColectivo().getIdColectivo());
            ps.setInt(2, l.getNumeroAsiento());
            ps.setBoolean(3, l.isEstado());
            ps.setInt(4, l.getIdAsiento());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
    }

    public void bajaLogica(int id) {
        String sql = "UPDATE lugares SET estado=false WHERE id_lugar=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al dar de baja: " + ex.getMessage());
        }
    }

    public Lugar buscar(int id) {
        String sql = "SELECT l.*, c.matricula FROM lugares l "
                   + "JOIN colectivos c ON l.id_colectivo = c.id_colectivo "
                   + "WHERE l.id_lugar=? AND l.estado=true";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Lugar l = new Lugar();
                l.setIdAsiento(rs.getInt("id_lugar"));
                l.setNumeroAsiento(rs.getInt("numero_asiento"));
                l.setEstado(rs.getBoolean("estado"));
                Colectivo c = new Colectivo();
                c.setIdColectivo(rs.getInt("id_colectivo"));
                c.setMatricula(rs.getString("matricula"));
                l.setColectivo(c);
                return l;
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return null;
    }

    public List<Lugar> listar() {
        List<Lugar> lista = new ArrayList<>();
        String sql = "SELECT l.*, c.matricula FROM lugares l "
                   + "JOIN colectivos c ON l.id_colectivo = c.id_colectivo "
                   + "WHERE l.estado=true ORDER BY l.id_colectivo, l.numero_asiento";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lugar l = new Lugar();
                l.setIdAsiento(rs.getInt("id_lugar"));
                l.setNumeroAsiento(rs.getInt("numero_asiento"));
                l.setEstado(rs.getBoolean("estado"));
                Colectivo c = new Colectivo();
                c.setIdColectivo(rs.getInt("id_colectivo"));
                c.setMatricula(rs.getString("matricula"));
                l.setColectivo(c);
                lista.add(l);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }
}
