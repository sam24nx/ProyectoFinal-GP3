/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.sql.*;
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
    private Connection con=null;
    
    
public ViajeData() {
        try {
        con=Conexion.getConnection();
        }catch(SQLException ex) {
        JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
        }
    }
    
 
public void guardarViaje(Viaje viaje) {
        String sql="INSERT INTO viaje(Origen, Destino, HoraSalida, HoraLlegada, ID_Colectivo, Precio, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,viaje.getOrigen());
            ps.setString(2,viaje.getDestino());
            ps.setTime(3,Time.valueOf(viaje.getHoraSalida()));
            ps.setTime(4,Time.valueOf(viaje.getHoraLlegada()));
            ps.setInt(5,viaje.getColectivo().getIdColectivo());
            ps.setDouble(6,viaje.getPrecio());
            ps.setBoolean(7,viaje.isEstado());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()){
                viaje.setIdViaje(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"Viaje guardado");
            }else{
            ps.close();}
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
        }
    }
    
    
public void modificarViaje(Viaje viaje){
        String sql="UPDATE viaje SET Origen = ?, Destino = ?, HoraSalida = ?, HoraLlegada = ?, ID_Colectivo = ?, Precio = ?, Estado = ? WHERE ID_Viaje = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,viaje.getOrigen());
            ps.setString(2,viaje.getDestino());
            ps.setTime(3,Time.valueOf(viaje.getHoraSalida()));
            ps.setTime(4,Time.valueOf(viaje.getHoraLlegada()));
            ps.setInt(5,viaje.getColectivo().getIdColectivo());
            ps.setDouble(6,viaje.getPrecio());
            ps.setBoolean(7,viaje.isEstado());
            ps.setInt(8,viaje.getIdViaje());
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Viaje modificado");
            }else{
            ps.close();}
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
        }}    
    
public void eliminarViaje(int id) {
                                                                                //es para la baja lgik baja_logica
        
        String sql="UPDATE viaje SET Estado = 0 WHERE ID_Viaje = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            int exito=ps.executeUpdate();
            if (exito==1){
                JOptionPane.showMessageDialog(null,"Viaje dado de baja");
            }else{
            ps.close();}
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
        }}  

public List<Viaje>listarViajes() {
        List<Viaje>viajes=new ArrayList<>();
        String sql="SELECT * FROM viaje WHERE Estado = 1";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Viaje v=new Viaje();
                v.setIdViaje(rs.getInt("ID_Viaje"));
                v.setOrigen(rs.getString("Origen"));
                v.setDestino(rs.getString("Destino"));
                v.setHoraSalida(rs.getTime("HoraSalida").toLocalTime());
                v.setHoraLlegada(rs.getTime("HoraLlegada").toLocalTime());
                v.setPrecio(rs.getDouble("Precio"));
                v.setEstado(rs.getBoolean("Estado"));
                
                // se asigna el id
                Colectivo c=new Colectivo();
                c.setIdColectivo(rs.getInt("ID_Colectivo"));
                v.setColectivo(c);
                
                viajes.add(v);
            }
            ps.close();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
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
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
    }
    return null;
}

    
}
