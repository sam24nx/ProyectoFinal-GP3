package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Lugar;
import modelo.Colectivo;
import modelo.Conexion;

public class Asientodata {
    
    private Connection con;

    public Asientodata() {
        try {
            con=Conexion.getConnection();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
        }
    }

    public void insertar(Lugar lugar) {
        String sql = "INSERT INTO lugares (ID_Colectivo, NumeroAsiento, Estado) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lugar.getColectivo().getIdColectivo()); 
            ps.setInt(2, lugar.getNumeroAsiento()); 
            ps.setBoolean(3, lugar.isEstado());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar el lugar: " + ex.getMessage(), "Error BD", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizar(Lugar lugar) {
        String sql = "UPDATE lugares SET ID_Colectivo = ?, NumeroAsiento = ?, Estado = ? WHERE ID_Asiento = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lugar.getColectivo().getIdColectivo()); 
            ps.setInt(2, lugar.getNumeroAsiento()); 
            ps.setBoolean(3, lugar.isEstado());
            ps.setInt(4, lugar.getIdAsiento());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al actualizar el lugar: " + ex.getMessage(), "Error BD", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void bajaLogica(int idAsiento) {
        String sql = "UPDATE lugares SET Estado = false WHERE ID_Asiento = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAsiento);
            ps.executeUpdate();
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al dar de baja: " + ex.getMessage(), "Error BD", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public Lugar buscar(int idAsiento) {
        Lugar lugar = null;
        String sql = "SELECT * FROM lugares WHERE ID_Asiento = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAsiento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lugar = new Lugar();
                lugar.setIdAsiento(rs.getInt("ID_Asiento"));
                
                Colectivo col = new Colectivo();
                col.setIdColectivo(rs.getInt("ID_Colectivo"));
                lugar.setColectivo(col);
                
                lugar.setNumeroAsiento(rs.getInt("NumeroAsiento"));
                lugar.setEstado(rs.getBoolean("Estado"));
            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al buscar: " + ex.getMessage(), "Error BD", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return lugar;
    }

    public List<Lugar> listar() {
        List<Lugar> lista = new ArrayList<>();
        String sql = "SELECT * FROM lugares ORDER BY ID_Colectivo, NumeroAsiento";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lugar l = new Lugar();
                l.setIdAsiento(rs.getInt("ID_Asiento"));
                
                Colectivo col = new Colectivo();
                col.setIdColectivo(rs.getInt("ID_Colectivo"));
                l.setColectivo(col);
                
                l.setNumeroAsiento(rs.getInt("NumeroAsiento"));
                l.setEstado(rs.getBoolean("Estado"));
                
                lista.add(l);
            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al listar: " + ex.getMessage(), "Error BD", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
}