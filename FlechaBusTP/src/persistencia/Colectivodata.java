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
import modelo.Conexion;
import java.sql.SQLIntegrityConstraintViolationException;

public class Colectivodata {

    private Connection con;

    public Colectivodata() {
        try {
            con=Conexion.getConnection();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
        }
    }

    public boolean insertarColectivo(Colectivo colectivo) {
        String sql = "INSERT INTO colectivos (matricula, marca, modelo, capacidad, chofer, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, colectivo.getMatricula());
            ps.setString(2, colectivo.getMarca());
            ps.setString(3, colectivo.getModelo());
            ps.setInt(4, colectivo.getCapacidad());
            ps.setString(5, colectivo.getChofer());
            ps.setBoolean(6, colectivo.isEstado());
            ps.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Ya existe un colectivo registrado con la patente: " + colectivo.getMatricula(), "Patente Duplicada", JOptionPane.WARNING_MESSAGE);
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el colectivo: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }



    public void actualizar(Colectivo c) {
        String sql = "UPDATE colectivos SET matricula=?, marca=?, modelo=?, capacidad=?, chofer=?, estado=? WHERE id_colectivo=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getMatricula());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());
            ps.setInt(4, c.getCapacidad());
            ps.setString(5, c.getChofer());
            ps.setBoolean(6, c.isEstado());
            ps.setInt(7, c.getIdColectivo());
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
    }

    public void bajaLogica(int id) {
        String sql = "UPDATE colectivos SET estado=0 WHERE id_colectivo=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al dar de baja: " + ex.getMessage());
        }
    }

    public Colectivo buscar(int id) {
        String sql = "SELECT * FROM colectivos WHERE id_colectivo=? AND estado=true";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Colectivo c = new Colectivo();
                c.setIdColectivo(rs.getInt("id_colectivo"));
                c.setMatricula(rs.getString("matricula"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setCapacidad(rs.getInt("capacidad"));
                c.setChofer(rs.getString("chofer"));
                c.setEstado(rs.getBoolean("estado"));
                return c;
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return null;
    }

    public List<Colectivo> listar(){
        List<Colectivo> lista = new ArrayList<>();
        //String sql = "SELECT * FROM colectivos WHERE estado=true ORDER BY id_colectivo";// MUESTRA SOLO LOS ACTIVOS
        String sql = "SELECT * FROM colectivos ORDER BY id_colectivo";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Colectivo c = new Colectivo();
                c.setIdColectivo(rs.getInt("id_colectivo"));
                c.setMatricula(rs.getString("matricula"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setCapacidad(rs.getInt("capacidad"));
                c.setChofer(rs.getString("chofer"));
                c.setEstado(rs.getBoolean("estado"));
                lista.add(c);
            }
        } catch (SQLException ex) {
           javax.swing.JOptionPane.showMessageDialog(null, "Error al listar: " + ex.getMessage(), "Error BD", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

public Colectivo buscarPorMatricula(String matricula) {
    String sql = "SELECT * FROM colectivos WHERE matricula=? AND estado=true";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, matricula);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Colectivo c = new Colectivo();
            c.setIdColectivo(rs.getInt("id_colectivo"));
            c.setMatricula(rs.getString("matricula"));
            c.setMarca(rs.getString("marca"));
            c.setModelo(rs.getString("modelo"));
            c.setCapacidad(rs.getInt("capacidad"));
            c.setChofer(rs.getString("chofer"));
            c.setEstado(rs.getBoolean("estado"));
            return c;
        }
    } catch (SQLException ex) {
        System.out.println("Error al buscar por matrícula: " + ex.getMessage());
    }
    return null;
}


}