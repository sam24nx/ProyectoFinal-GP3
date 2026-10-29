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

public class ColectivoData {

    private Connection con;

    public ColectivoData() {
        try {
            con=Conexion.getConnection();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
        }
    }

    public void insertar(Colectivo c) {
        String sql = "INSERT INTO colectivos (matricula, marca, modelo, capacidad, chofer, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getMatricula());
            ps.setString(2, c.getMarca());
            ps.setString(3, c.getModelo());
            ps.setInt(4, c.getCapacidad());
            ps.setString(5, c.getChofer());
            ps.setBoolean(6, c.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) c.setIdColectivo(rs.getInt(1));
            // Generar los asientos automáticamente según la capacidad
            generarAsientos(c.getIdColectivo(), c.getCapacidad());
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        }
    }

    private void generarAsientos(int idColectivo, int capacidad) {
        String sql = "INSERT INTO lugares (id_colectivo, numero_asiento, estado) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= capacidad; i++) {
                ps.setInt(1, idColectivo);
                ps.setInt(2, i);
                ps.setBoolean(3, true);
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Se generaron " + capacidad + " asientos para el colectivo " + idColectivo);
        } catch (SQLException ex) {
            System.out.println("Error al generar asientos: " + ex.getMessage());
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
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
    }

    public void bajaLogica(int id) {
        String sql = "UPDATE colectivos SET estado=false WHERE id_colectivo=?";
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

    public List<Colectivo> listar() {
        List<Colectivo> lista = new ArrayList<>();
        String sql = "SELECT * FROM colectivos WHERE estado=true ORDER BY id_colectivo";
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
            System.out.println("Error al listar: " + ex.getMessage());
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
