package persistencia;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Colectivo;
import modelo.Conexion;
import modelo.Viaje;

public class ViajeData {
    private Connection con = null;

    public ViajeData() {
        try {
            con=Conexion.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }


    private boolean validarViaje(Viaje viaje,boolean esModificacion) {
        if(viaje.getOrigen()==null || viaje.getOrigen().trim().isEmpty()||viaje.getDestino()==null||viaje.getDestino().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"El origen y el destino son obligatorios","Campos Vacios",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (viaje.getOrigen().trim().equalsIgnoreCase(viaje.getDestino().trim())) {
            JOptionPane.showMessageDialog(null,"El origen y el destino no pueden ser el mismo lugar","Ruta Invalida",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (viaje.getFecha() == null) {
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fecha para el viaje","Fecha Vacía",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!esModificacion && viaje.getFecha().isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null,"No se puede programar un viaje en una fecha pasada","Fecha Invalida",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (viaje.getHoraSalida()==null || viaje.getHoraLlegada()==null) {
            JOptionPane.showMessageDialog(null,"Debe indicar la hora de salida y llegada", "Horarios Incompletos",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (viaje.getHoraSalida().equals(viaje.getHoraLlegada())) {
            JOptionPane.showMessageDialog(null,"La hora de salida y llegada no pueden ser iguales", "Horario Invalido",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (viaje.getPrecio()<=0) {
            JOptionPane.showMessageDialog(null,"El precio debe ser mayor a 0", "Precio Invalido",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (viaje.getColectivo()==null) {
            JOptionPane.showMessageDialog(null,"Debe asignar un colectivo al viaje","Falta Colectivo",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        
        
        
        
        
        String sqlCol="SELECT Estado FROM colectivos WHERE ID_Colectivo = ?";
        try (PreparedStatement psCol=con.prepareStatement(sqlCol)) {
            psCol.setInt(1,viaje.getColectivo().getIdColectivo());
            ResultSet rsCol=psCol.executeQuery();
            if (rsCol.next()){
                if (!rsCol.getBoolean("Estado")) {
                    JOptionPane.showMessageDialog(null,"El colectivo seleccionado esta INACTIVO y no puede asignarse", "Colectivo Inactivo", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null,"El colectivo seleccionado no existe","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al verificar colectivo"+ex.getMessage(),"Error Bsae de Ddatos",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //colectivo no tenga otro viaje activo
        
        String sqlOcupado = "SELECT COUNT(*) FROM viaje WHERE ID_Colectivo = ? AND Fecha = ? AND HoraSalida = ? AND Estado = 1 AND ID_Viaje != ?";
        try (PreparedStatement psOcup=con.prepareStatement(sqlOcupado)) {
            psOcup.setInt(1,viaje.getColectivo().getIdColectivo());
            psOcup.setDate(2, Date.valueOf(viaje.getFecha()));
            psOcup.setTime(3,Time.valueOf(viaje.getHoraSalida()));
            psOcup.setInt(4,esModificacion ? viaje.getIdViaje() : -1);
            ResultSet rsOcup=psOcup.executeQuery();
            if (rsOcup.next()&&rsOcup.getInt(1)>0){
                JOptionPane.showMessageDialog(null,"Ese colectivo ya tiene un viaje asignado en la misma fecha y hora","Colectivo Ocupado",JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al verificar disponibilidad"+ex.getMessage(),"Error Base de datos",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean guardarViaje(Viaje viaje) {
        if (!validarViaje(viaje,false)) {
            return false;
        }

        String sql="INSERT INTO viaje(Origen, Destino, Fecha, HoraSalida, HoraLlegada, ID_Colectivo, Precio, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,viaje.getOrigen().trim());
            ps.setString(2,viaje.getDestino().trim());
            ps.setDate(3,Date.valueOf(viaje.getFecha()));
            ps.setTime(4,Time.valueOf(viaje.getHoraSalida()));
            ps.setTime(5,Time.valueOf(viaje.getHoraLlegada()));
            ps.setInt(6,viaje.getColectivo().getIdColectivo());
            ps.setDouble(7,viaje.getPrecio());
            ps.setBoolean(8,viaje.isEstado());
            
            int filas=ps.executeUpdate();
            if (filas>0){
                ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()) {
                 viaje.setIdViaje(rs.getInt(1));
                }
                JOptionPane.showMessageDialog(null,"Viaje guardado correctamente");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al guardar viaje"+ex.getMessage(),"Error Base de datos",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean modificarViaje(Viaje viaje) {
        if (!validarViaje(viaje,true)){
            return false;
        }

        String sql="UPDATE viaje SET Origen = ?, Destino = ?, Fecha = ?, HoraSalida = ?, HoraLlegada = ?, ID_Colectivo = ?, Precio = ?, Estado = ? WHERE ID_Viaje = ?";
        try (PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,viaje.getOrigen().trim());
            ps.setString(2,viaje.getDestino().trim());
            ps.setDate(3,Date.valueOf(viaje.getFecha()));
            ps.setTime(4,Time.valueOf(viaje.getHoraSalida()));
            ps.setTime(5,Time.valueOf(viaje.getHoraLlegada()));
            ps.setInt(6,viaje.getColectivo().getIdColectivo());
            ps.setDouble(7,viaje.getPrecio());
            ps.setBoolean(8,viaje.isEstado());
            ps.setInt(9,viaje.getIdViaje());
            
            int exito=ps.executeUpdate();
            if (exito==1){
                JOptionPane.showMessageDialog(null,"Viaje modificado correctamente");
                return true;
        }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al modificar viaje"+ex.getMessage(),"Error Base de datos",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
// NO! dar de baja un viaje que ya tiene pasajes activos
public boolean eliminarViaje(int id) {
        String sqlCheckPasajes = "SELECT COUNT(*) FROM pasajes WHERE ID_Ruta = ? AND Estado = 1";
        try (PreparedStatement psCheck = con.prepareStatement(sqlCheckPasajes)) {
            psCheck.setInt(1, id);
            ResultSet rsCheck = psCheck.executeQuery();
            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "No se puede dar de baja este viaje porque tiene pasajes activos", "Bloqueado", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Aviso al verificar pasajes: " + ex.getMessage());
        }

        String sql = "UPDATE viaje SET Estado = 0 WHERE ID_Viaje = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Viaje dado de baja");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al dar de baja: " + ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public List<Viaje>listarViajes() {
        List<Viaje> viajes=new ArrayList<>();
        String sql="SELECT * FROM viaje WHERE Estado = 1";
        try (PreparedStatement ps=con.prepareStatement(sql);
             ResultSet rs=ps.executeQuery()) {
            while (rs.next()) {
                Viaje v=new Viaje();
                v.setIdViaje(rs.getInt("ID_Viaje"));
                v.setOrigen(rs.getString("Origen"));
                v.setDestino(rs.getString("Destino"));
                if (rs.getDate("Fecha")!=null) {
                v.setFecha(rs.getDate("Fecha").toLocalDate());
                }
                v.setHoraSalida(rs.getTime("HoraSalida").toLocalTime());
                v.setHoraLlegada(rs.getTime("HoraLlegada").toLocalTime());
                v.setPrecio(rs.getDouble("Precio"));
                v.setEstado(rs.getBoolean("Estado"));

                Colectivo c=new Colectivo();
                c.setIdColectivo(rs.getInt("ID_Colectivo"));
                v.setColectivo(c);

                viajes.add(v);
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al listar viajes"+ex.getMessage());
        }
        return viajes;
    }

    public Viaje buscarViaje(int id) {
        String sql = "SELECT * FROM viaje WHERE ID_Viaje = ? AND Estado = 1";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();

            if (rs.next()) {
                Viaje v=new Viaje();
                v.setIdViaje(rs.getInt("ID_Viaje"));
                v.setOrigen(rs.getString("Origen"));
                v.setDestino(rs.getString("Destino"));
                if(rs.getDate("Fecha")!=null) {
                v.setFecha(rs.getDate("Fecha").toLocalDate());
                }
                v.setHoraSalida(rs.getTime("HoraSalida").toLocalTime());
                v.setHoraLlegada(rs.getTime("HoraLlegada").toLocalTime());
                v.setPrecio(rs.getDouble("Precio"));
                v.setEstado(rs.getBoolean("Estado"));

                Colectivo c=new Colectivo();
                c.setIdColectivo(rs.getInt("ID_Colectivo"));
                v.setColectivo(c);

                return v;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar viaje: " + ex.getMessage());
        }
        return null;
    }
}