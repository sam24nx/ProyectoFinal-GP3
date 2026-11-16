/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import javax.swing.*;


public class VistaPrincipal extends javax.swing.JFrame {
    
private persistencia.PasajeData pasajeData=new persistencia.PasajeData();
private javax.swing.table.DefaultTableModel modeloPasajes=new javax.swing.table.DefaultTableModel();
    
private persistencia.Asientodata asientoData=new persistencia.Asientodata();
private javax.swing.table.DefaultTableModel modeloAsientos=new javax.swing.table.DefaultTableModel();
    
private persistencia.PasajeroData pasajeroData=new persistencia.PasajeroData();
private javax.swing.table.DefaultTableModel modeloPasajeros=new javax.swing.table.DefaultTableModel();
  
private persistencia.Colectivodata colectivoData=new persistencia.Colectivodata();
private javax.swing.table.DefaultTableModel modeloColectivos=new javax.swing.table.DefaultTableModel();

private persistencia.ViajeData viajeData=new persistencia.ViajeData();
private javax.swing.table.DefaultTableModel modeloViajes=new javax.swing.table.DefaultTableModel();


    public VistaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        
        //pasajeros
        armarCabeceraPasajeros();
        cargarTablaPasajeros();
        
        //colectivo
        armarCabeceraColectivos();
        cargarTablaColectivos();
        limpiarCamposColectivo();
        
        //asiento
        armarCabeceraAsientos();
        cargarTablaAsientos();
        limpiarCamposAsiento();
        
        //iconos actualizados
        
        //viajes/rutas
        armarCabeceraViajes();
        cargarComboColectivos();
        cargarTablaViajes();
        limpiarCamposViaje();
        
        // pasajes
        armarCabeceraPasajes();
jComboBox4.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        modelo.Viaje viajeSeleccionado = (modelo.Viaje) jComboBox4.getSelectedItem();
        if (viajeSeleccionado != null) {
            jTextField23.setText(Double.toString(viajeSeleccionado.getPrecio()));
            jTextField23.setEditable(false);
        }
    }
});


jComboBox5.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        modelo.Colectivo colSeleccionado = (modelo.Colectivo) jComboBox5.getSelectedItem();

        jComboBox3.removeAllItems();
        jComboBox4.removeAllItems();
        jTextField23.setText("");

        if (colSeleccionado != null) {
            int idCol = colSeleccionado.getIdColectivo();

            for (modelo.Lugar l : asientoData.listar()) {
                if (l.isEstado() && l.getColectivo().getIdColectivo() == idCol) {
                    jComboBox3.addItem(l);
                }
            }

            for (modelo.Viaje v : viajeData.listarViajes()) {
                if (v.isEstado() && v.getColectivo().getIdColectivo() == idCol) {
                    jComboBox4.addItem(v);
                }
            }
        }
    }
});

cargarCombosPasajes();
cargarTablaPasajes();
limpiarCamposPasajeVenta();
        
        

        
        
    }
    
    
    
private void cargarTablaPasajes() {
    modeloPasajes.setRowCount(0);
    for (modelo.Pasaje p:pasajeData.listarPasajes()) {
        modeloPasajes.addRow(new Object[]{
            p.getIdPasaje(),
            p.getPasajero() != null ? p.getPasajero().getApellido()+", " +p.getPasajero().getNombre():"N/D",
            p.getLugar() != null ? "Asiento N° "+p.getLugar().getNumeroAsiento():"N/D",
            p.getViaje() != null ? p.getViaje().getOrigen()+" -> " +p.getViaje().getDestino():"N/D",
            p.getFechaVenta(),
            p.getHoraVenta(),
            p.getPrecio(),
            p.isEstado() ? "Activo" : "Anulado"
        });
    }
}
    
    
private void limpiarCamposPasajeVenta() {
    jDateChooser2.setDate(null);
    jTextField22.setText("");
    
    if (jComboBox2.getItemCount()>0)jComboBox2.setSelectedIndex(0);
    if (jComboBox5.getItemCount()>0)jComboBox5.setSelectedIndex(0);
    
    jTable6.clearSelection();
}
    
    
private void cargarCombosPasajes() {
    jComboBox2.removeAllItems();
    for (modelo.Pasajero p : pasajeroData.listar()) {
        if (p.isEstado()) {
            jComboBox2.addItem(p);
        }
    }

    jComboBox5.removeAllItems();
    for (modelo.Colectivo c : colectivoData.listar()) {
        if (c.isEstado()) {
            jComboBox5.addItem(c);
        }
    }
}
    


private void cargarDatosPasajeEnFormulario(modelo.Pasaje p) {
    
    if (p.getPasajero() != null) {
        for (int i = 0; i < jComboBox2.getItemCount(); i++) {
            if (jComboBox2.getItemAt(i).getIdPasajero() == p.getPasajero().getIdPasajero()) {
                jComboBox2.setSelectedIndex(i);
                break;
            }
        }
    }

    
    if (p.getViaje() != null && p.getViaje().getColectivo() != null) {
        int idCol = p.getViaje().getColectivo().getIdColectivo();
        for (int i = 0; i < jComboBox5.getItemCount(); i++) {
            if (jComboBox5.getItemAt(i).getIdColectivo() == idCol) {
                jComboBox5.setSelectedIndex(i);
                break;
            }
        }
    }

    
    if (p.getViaje() != null) {
        for (int i = 0; i < jComboBox4.getItemCount(); i++) {
            if (jComboBox4.getItemAt(i).getIdViaje() == p.getViaje().getIdViaje()) {
                jComboBox4.setSelectedIndex(i);
                break;
            }
        }
    }

    
    if (p.getLugar() != null) {
        boolean encontrado = false;
        for (int i = 0; i < jComboBox3.getItemCount(); i++) {
            if (jComboBox3.getItemAt(i).getIdAsiento() == p.getLugar().getIdAsiento()) {
                jComboBox3.setSelectedIndex(i);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            jComboBox3.addItem(p.getLugar());
            jComboBox3.setSelectedItem(p.getLugar());
        }
    }

    
    if (p.getFechaVenta() != null) {
        jDateChooser2.setDate(java.sql.Date.valueOf(p.getFechaVenta()));
    }
    if (p.getHoraVenta() != null) {
        jTextField22.setText(p.getHoraVenta().toString());
    }
    jTextField23.setText(Double.toString(p.getPrecio()));
}
    
    private void armarCabeceraPasajes() {
    modeloPasajes=new javax.swing.table.DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    modeloPasajes.addColumn("ID PASAJE");
    modeloPasajes.addColumn("PASAJERO");
    modeloPasajes.addColumn("ASIENTO");
    modeloPasajes.addColumn("RUTA (VIAJE)");
    modeloPasajes.addColumn("FECHA VENTA");
    modeloPasajes.addColumn("HORA VENTA");
    modeloPasajes.addColumn("PRECIO");
    modeloPasajes.addColumn("ESTADO");
    jTable6.setModel(modeloPasajes);
}
    
    
    
    private void limpiarCamposViaje() {
    jTextField16.setText("");
    jTextField17.setText("");
    jTextField18.setText("");
    jFormattedTextField1.setText("");
    jFormattedTextField2.setText("");
    jDateChooser1.setDate(null);
    if (jComboBox1.getItemCount()>0){
    jComboBox1.setSelectedIndex(0);
    }
    jTable5.clearSelection();
}
    
    
    private void cargarTablaViajes() {
    modeloViajes.setRowCount(0);
    for (modelo.Viaje v:viajeData.listarViajes()) {
        modeloViajes.addRow(new Object[]{
            v.getIdViaje(),
            v.getOrigen(),
            v.getDestino(),
            v.getFecha(),
            v.getHoraSalida(),
            v.getHoraLlegada(),
            v.getColectivo().getIdColectivo(),
            v.getPrecio()
        });
    }
}
    
    
    private void cargarComboColectivos() {
    jComboBox1.removeAllItems();
    for (modelo.Colectivo c:colectivoData.listar()) {
        if (c.isEstado()){
        jComboBox1.addItem(c);
        }
    }
}
    
    
private void armarCabeceraViajes() {
    modeloViajes = new javax.swing.table.DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    modeloViajes.addColumn("ID");
    modeloViajes.addColumn("ORIGEN");
    modeloViajes.addColumn("DESTINO");
    modeloViajes.addColumn("FECHA");
    modeloViajes.addColumn("HORA SALIDA");
    modeloViajes.addColumn("HORA LLEGADA");
    modeloViajes.addColumn("ID COLECTIVO");
    modeloViajes.addColumn("PRECIO");
    jTable5.setModel(modeloViajes);
}
    
    private void limpiarCamposAsiento() {
        jTextField13.setText("");
        jTextField14.setText("");
        jCheckBox3.setSelected(false);
        jTable4.clearSelection();
    }
    
    
    private void cargarTablaAsientos() {
        modeloAsientos.setRowCount(0);
        for (modelo.Lugar a : asientoData.listar()) { 
            modeloAsientos.addRow(new Object[]{
                a.getIdAsiento(),
                a.getColectivo().getIdColectivo(), 
                a.getNumeroAsiento(),
                a.isEstado() ? "Disponible" : "Ocupado" 
            });
        }
    }
    
    
    private void armarCabeceraAsientos() {
        modeloAsientos = new javax.swing.table.DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloAsientos.addColumn("ID LUGAR");
        modeloAsientos.addColumn("ID COLECTIVO");
        modeloAsientos.addColumn("N° ASIENTO");
        modeloAsientos.addColumn("DISPONIBILIDAD");
        jTable4.setModel(modeloAsientos);
    }
    
    
    
    private void limpiarCamposColectivo() {
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jCheckBox2.setSelected(false);
    }
    
    
    private void cargarTablaColectivos() {
        modeloColectivos.setRowCount(0);
        for (modelo.Colectivo c:colectivoData.listar()) {
            modeloColectivos.addRow(new Object[]{
                c.getIdColectivo(),
                c.getMatricula(),
                c.getMarca(), 
                c.getModelo(), 
                c.getCapacidad(), 
                c.getChofer(), 
                c.isEstado() ? "Activo" : "Inactivo"
            });
        }
    }
    
    
    private void armarCabeceraColectivos() {
        modeloColectivos=new javax.swing.table.DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloColectivos.addColumn("ID");
        modeloColectivos.addColumn("MATRICULA");
        modeloColectivos.addColumn("MARCA");
        modeloColectivos.addColumn("MODELO");
        modeloColectivos.addColumn("CAPACIDAD");
        modeloColectivos.addColumn("CHOFER");
        modeloColectivos.addColumn("ESTADO");
        jTable3.setModel(modeloColectivos);
    }
    
    

    private void armarCabeceraPasajeros() {
        modeloPasajeros = new javax.swing.table.DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Bloquea todas las celdas contra doble clic / edición directa
            }
        };
        modeloPasajeros.addColumn("ID");
        modeloPasajeros.addColumn("DNI");
        modeloPasajeros.addColumn("NOMBRE");
        modeloPasajeros.addColumn("APELLIDO");
        modeloPasajeros.addColumn("CORREO");
        modeloPasajeros.addColumn("TELEFONO");
        modeloPasajeros.addColumn("ESTADO");
        jTable2.setModel(modeloPasajeros);
    }
    
    private void cargarTablaPasajeros() {
        modeloPasajeros.setRowCount(0);
        for (modelo.Pasajero p:pasajeroData.listar()) {
            modeloPasajeros.addRow(new Object[]{
                p.getIdPasajero(),
                p.getDni(),
                p.getNombre(), 
                p.getApellido(), 
                p.getCorreo(), 
                p.getTelefono(), 
                p.isEstado() ? "Activo" : "Inactivo"
            });
        }
    }
    
    private void limpiarCamposPasajero() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jCheckBox1.setSelected(false);
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnNuevo2 = new javax.swing.JButton();
        btnGuardar2 = new javax.swing.JButton();
        btnModificar2 = new javax.swing.JButton();
        btnEliminar2 = new javax.swing.JButton();
        btnBuscar2 = new javax.swing.JButton();
        btnListar2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnNuevo1 = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        btnModificar1 = new javax.swing.JButton();
        btnEliminar1 = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();
        btnListar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        btnNuevo3 = new javax.swing.JButton();
        btnGuardar3 = new javax.swing.JButton();
        btnModificar3 = new javax.swing.JButton();
        btnEliminar3 = new javax.swing.JButton();
        btnBuscar3 = new javax.swing.JButton();
        btnListar3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField18 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        btnNuevo4 = new javax.swing.JButton();
        btnGuardar4 = new javax.swing.JButton();
        btnModificar4 = new javax.swing.JButton();
        btnEliminar4 = new javax.swing.JButton();
        btnBuscar4 = new javax.swing.JButton();
        btnListar4 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel38 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        btnNuevo5 = new javax.swing.JButton();
        btnGuardar5 = new javax.swing.JButton();
        btnModificar5 = new javax.swing.JButton();
        btnEliminar5 = new javax.swing.JButton();
        btnBuscar5 = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame1.setTitle("FECHA FLASH - MENU");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("COLECTIVOS", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("ASIENTOS", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("RUTAS", jPanel9);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("PASAJES", jPanel10);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("LISTADOS", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("PASAJEROS", jPanel12);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FECHA FLASH - MENU");

        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("ENTRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, -1, -1));

        jButton2.setText("ENTRAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, -1, -1));

        jButton3.setText("ENTRAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 500, -1, -1));

        jButton4.setText("ENTRAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, -1, -1));

        jButton10.setText("ENTRAR");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 500, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/inicioV222333 (1).jpg"))); // NOI18N
        jPanel13.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 1050, 670));

        jTabbedPane2.addTab("INICIO", jPanel13);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel10.setText("COLECTIVOS");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setText("MATRICULA:");
        jLabel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 86, -1, -1));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setText("MARCA:");
        jLabel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 137, -1, -1));

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setText("MODELO:");
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 86, -1, -1));

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setText("CAPACIDAD:");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 137, -1, -1));

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel16.setText("CHOFER:");
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 182, -1, -1));

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel17.setText("ESTADO:");
        jLabel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 182, -1, -1));
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 86, 152, -1));
        jPanel2.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 137, 152, -1));
        jPanel2.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 86, 153, -1));
        jPanel2.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 137, 153, -1));
        jPanel2.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 182, 153, -1));

        jCheckBox2.setText("ACTIVO");
        jPanel2.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 183, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MATRICULA", "MARCA", "MODELO", "CAPACIDAD", "CHOFER", "ESTADO"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 341, 933, 305));

        btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo_colectivo.png"))); // NOI18N
        btnNuevo2.setText("NUEVO");
        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, 40));

        btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar_colectivo.png"))); // NOI18N
        btnGuardar2.setText("GUARDAR");
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, -1, 42));

        btnModificar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/editar_colectivo.png"))); // NOI18N
        btnModificar2.setText("MODIFICAR");
        btnModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, -1, 40));

        btnEliminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/borrar_colectivo.png"))); // NOI18N
        btnEliminar2.setText("ELIMINAR");
        btnEliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 280, -1, 40));

        btnBuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar_colectivo.png"))); // NOI18N
        btnBuscar2.setText("BUSCAR");
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, -1, 40));

        btnListar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/listar_colectivo.png"))); // NOI18N
        btnListar2.setText("ACTUALIZAR");
        btnListar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btnListar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, -1, 40));

        jButton5.setText("VOLVER AL INICIO");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 150, 50));

        jTabbedPane2.addTab("COLECTIVOS", jPanel2);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel3.setText("PASAJEROS");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setText("DNI ");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 129, -1, -1));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel5.setText("NOMBRE:");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 173, -1, -1));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel6.setText("APELLIDO:");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 230, -1, -1));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setText("CORREO:");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, -1, -1));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setText("TELEFONO:");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, -1, -1));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel9.setText("ESTADO:");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 129, 150, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 173, 150, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 230, 150, -1));
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 150, -1));
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 150, -1));

        jCheckBox1.setText("ACTIVO");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, -1, -1));

        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo_pasajero.png"))); // NOI18N
        btnNuevo1.setText("NUEVO");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 331, -1, 41));

        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar_pasajero.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, -1, 41));

        btnModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/editar_pasajero.png"))); // NOI18N
        btnModificar1.setText("MODIFICAR");
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, -1, 41));

        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar_pasajero.png"))); // NOI18N
        btnEliminar1.setText("ELIMINAR");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 330, -1, 41));

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar_pasajero.png"))); // NOI18N
        btnBuscar1.setText("BUSCAR");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 330, 152, 41));

        btnListar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/listar_pasajero.png"))); // NOI18N
        btnListar1.setText("ACTUALZAR");
        btnListar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnListar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, 41));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "CORREO", "TELEFONO", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 384, 959, 280));

        jButton6.setText("VOLVER AL INICIO");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 150, 50));

        jTabbedPane2.addTab("PASAJEROS", jPanel1);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel18.setText("ASIENTOS");
        jLabel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jLabel20.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel20.setText("ID COLECTIVO:");
        jLabel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, -1, -1));

        jLabel21.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel21.setText("N° DE ASIENTO:");
        jLabel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 142, -1, -1));

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel22.setText("DISPONIBLE:");
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, -1, -1));
        jPanel3.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 151, -1));
        jPanel3.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 150, -1));
        jPanel3.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, -1, -1));

        btnNuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo_asiento.png"))); // NOI18N
        btnNuevo3.setText("NUEVO");
        btnNuevo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo3ActionPerformed(evt);
            }
        });
        jPanel3.add(btnNuevo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 302, -1, 41));

        btnGuardar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar_asiento.png"))); // NOI18N
        btnGuardar3.setText("GUARDAR");
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 302, -1, 41));

        btnModificar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modifiar_asiento.png"))); // NOI18N
        btnModificar3.setText("MODIFICAR");
        btnModificar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar3ActionPerformed(evt);
            }
        });
        jPanel3.add(btnModificar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 302, -1, 41));

        btnEliminar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar_asiento.png"))); // NOI18N
        btnEliminar3.setText("ELIMINAR");
        btnEliminar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar3ActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 302, -1, 41));

        btnBuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar_asiento.png"))); // NOI18N
        btnBuscar3.setText("BUSCAR");
        btnBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar3ActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, -1, 41));

        btnListar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/listar_asiento.png"))); // NOI18N
        btnListar3.setText("ACTUALZAR");
        btnListar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListar3ActionPerformed(evt);
            }
        });
        jPanel3.add(btnListar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 302, -1, 41));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "COLECTIVO", "N° ASIENTO", "DISPONIBLE"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 361, 978, 294));

        jButton7.setText("VOLVER AL INICIO");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 150, 50));

        jTabbedPane2.addTab("ASIENTOS", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel23.setText("FORMULARIO DE RUTAS:");
        jLabel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jLabel25.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel25.setText("ORIGEN:");
        jLabel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel26.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel26.setText("DESTINO:");
        jLabel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel27.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel27.setText("HORA DE SALIDA:");
        jLabel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, -1));

        jLabel28.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel28.setText("HORA DE LLEGADA:");
        jLabel28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

        jLabel29.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel29.setText("COLECTIVO:");
        jLabel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, -1, -1));

        jLabel30.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel30.setText("FECHA");
        jLabel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, -1, -1));

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 150, -1));
        jPanel4.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 149, -1));
        jPanel4.add(jFormattedTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 110, -1));
        jPanel4.add(jFormattedTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 110, -1));

        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 174, -1));
        jPanel4.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 70, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ORIGEN", "DESTINO", "HORA DE SALIDA", "HORA DE LLEGADA", "COLECTIVO", "PRECIO"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 386, 1008, 270));

        btnNuevo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nueva_ruta.png"))); // NOI18N
        btnNuevo4.setText("NUEVO");
        btnNuevo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, 40));

        btnGuardar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar_ruta.png"))); // NOI18N
        btnGuardar4.setText("GUARDAR");
        btnGuardar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, -1, 40));

        btnModificar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar_ruta.png"))); // NOI18N
        btnModificar4.setText("MODIFICAR");
        btnModificar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnModificar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 340, 150, 40));

        btnEliminar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar_ruta.png"))); // NOI18N
        btnEliminar4.setText("ELIMINAR");
        btnEliminar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnEliminar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 340, -1, 40));

        btnBuscar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar_ruta.png"))); // NOI18N
        btnBuscar4.setText("BUSCAR");
        btnBuscar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnBuscar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, -1, 40));

        btnListar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lsitar_ruta.png"))); // NOI18N
        btnListar4.setText("ACTUALIZAR");
        btnListar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListar4ActionPerformed(evt);
            }
        });
        jPanel4.add(btnListar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, 40));
        jPanel4.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 130, 40));

        jLabel38.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel38.setText("PRECIO:");
        jLabel38.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, -1, -1));

        jButton8.setText("VOLVER AL INICIO");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 150, 50));

        jTabbedPane2.addTab("RUTAS", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setText("FORMULARIO DE PASAJES (VENTA)");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        jLabel32.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel32.setText("PASAJERO:");
        jLabel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 150, -1, -1));

        jLabel33.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel33.setText("ASIENTO/LUGAR:");
        jLabel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 198, -1, -1));

        jLabel34.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel34.setText("RUTA:");
        jLabel34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel35.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel35.setText("FECHA DE VENTA:");
        jLabel35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 150, -1, -1));

        jLabel36.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel36.setText("COLECTIVO:");
        jLabel36.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel37.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel37.setText("PRECIO:");
        jLabel37.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, -1));

        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 150, 220, -1));

        jPanel5.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 198, 149, -1));
        jPanel5.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 154, -1));

        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 137, -1));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID PASAJE", "PASAJERO", "ASIENTO", "RUTA", "FECHA VENTA", "HORA VENTA", "PRECIO"
            }
        ));
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 433, 1008, 231));

        btnNuevo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo_pasaje.png"))); // NOI18N
        btnNuevo5.setText("NUEVO");
        btnNuevo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo5ActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, 40));

        btnGuardar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar_pasaje.png"))); // NOI18N
        btnGuardar5.setText("GUARDAR");
        btnGuardar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar5ActionPerformed(evt);
            }
        });
        jPanel5.add(btnGuardar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, -1, 40));

        btnModificar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar_pasaje.png"))); // NOI18N
        btnModificar5.setText("MODIFICAR");
        btnModificar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar5ActionPerformed(evt);
            }
        });
        jPanel5.add(btnModificar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, -1, 40));

        btnEliminar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar_pasaje.png"))); // NOI18N
        btnEliminar5.setText("ELIMINAR");
        btnEliminar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar5ActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, -1, 40));

        btnBuscar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar_pasaje.png"))); // NOI18N
        btnBuscar5.setText("BUSCAR");
        btnBuscar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar5ActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, -1, 40));

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/listar_pasaje.png"))); // NOI18N
        btnListar.setText("ACTUALIZAR");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel5.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, -1, 40));

        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 260, -1));
        jPanel5.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 130, -1));

        jPanel5.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        jLabel39.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel39.setText("HORA DE VENTA:");
        jLabel39.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, -1, -1));

        jButton9.setText("VOLVER AL INICIO");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 150, 50));

        jButton11.setText("REACTIVAR");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 390, -1, -1));

        jTabbedPane2.addTab("PASAJES", jPanel5);

        jDesktopPane1.setLayer(jTabbedPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        cargarCombosPasajes();
        cargarTablaPasajes();
        limpiarCamposPasajeVenta();

    }//GEN-LAST:event_btnListarActionPerformed

    private void btnBuscar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar5ActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this, "Ingrese el ID del pasaje a buscar:", "Buscar Pasaje", JOptionPane.QUESTION_MESSAGE);
        if (input != null && !input.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(input.trim());
                modelo.Pasaje p = pasajeData.buscarPasaje(id);
                if (p != null) {
                    cargarDatosPasajeEnFormulario(p);
                    JOptionPane.showMessageDialog(this, "Pasaje encontrado.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró pasaje con ID: " + id, "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBuscar5ActionPerformed

    private void btnEliminar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar5ActionPerformed
        // TODO add your handling code here:

        int fila=jTable6.getSelectedRow();
        if (fila==-1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pasaje de la tabla para anular", "Atencion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPasaje = (Integer) modeloPasajes.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Esta seguro de anular este pasaje?", "Confirmar anulación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            modelo.Pasaje pAnular = pasajeData.buscarPasaje(idPasaje);
            if (pAnular != null && pAnular.getLugar() != null) {
                pAnular.getLugar().setEstado(true);
                asientoData.actualizar(pAnular.getLugar());
            }
            pasajeData.anularPasaje(idPasaje);
            cargarCombosPasajes();
            cargarTablaPasajes();
            cargarTablaAsientos();
            limpiarCamposPasajeVenta();
        }

    }//GEN-LAST:event_btnEliminar5ActionPerformed

    private void btnModificar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar5ActionPerformed
        // TODO add your handling code here:

        int fila = jTable6.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pasaje de la tabla para modificar.", "Atencion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modelo.Pasajero pasajeroSel = (modelo.Pasajero) jComboBox2.getSelectedItem();
        modelo.Lugar lugarSel = (modelo.Lugar) jComboBox3.getSelectedItem();
        modelo.Viaje viajeSel = (modelo.Viaje) jComboBox4.getSelectedItem();

        if (pasajeroSel == null || lugarSel == null || viajeSel == null || jDateChooser2.getDate() == null || jTextField22.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idPasaje = (Integer) modeloPasajes.getValueAt(fila, 0);
            double precio = Double.parseDouble(jTextField23.getText().trim());
            java.time.LocalDate fechaVenta = jDateChooser2.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.time.LocalTime horaVenta = java.time.LocalTime.parse(jTextField22.getText().trim());
            boolean estadoActual = modeloPasajes.getValueAt(fila, 7).toString().equals("Activo");

            modelo.Pasaje pasajeAnterior = pasajeData.buscarPasaje(idPasaje);
            if (pasajeAnterior != null && pasajeAnterior.getLugar() != null
                && pasajeAnterior.getLugar().getIdAsiento() != lugarSel.getIdAsiento()) {
                pasajeAnterior.getLugar().setEstado(true);
                asientoData.actualizar(pasajeAnterior.getLugar());
                asientoData.bajaLogica(lugarSel.getIdAsiento());
            }

            modelo.Pasaje pasajeMod = new modelo.Pasaje(idPasaje, pasajeroSel, lugarSel, viajeSel, fechaVenta, horaVenta, precio, estadoActual);

            if (pasajeData.modificarPasaje(pasajeMod)) {
                JOptionPane.showMessageDialog(this, "Pasaje modificado correctamente.");
                limpiarCamposPasajeVenta();
                cargarCombosPasajes();
                cargarTablaPasajes();
                cargarTablaAsientos();
            }
        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de hora invalido, Use HH:mm (ej: 14:30)", "Error de Hora", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un numero valido", "Error de Precio", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificar5ActionPerformed

    private void btnGuardar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar5ActionPerformed
        // TODO add your handling code here:
        modelo.Pasajero pasajeroSel = (modelo.Pasajero) jComboBox2.getSelectedItem();
        modelo.Lugar lugarSel = (modelo.Lugar) jComboBox3.getSelectedItem();
        modelo.Viaje viajeSel = (modelo.Viaje) jComboBox4.getSelectedItem();

        if (pasajeroSel == null || lugarSel == null || viajeSel == null || jDateChooser2.getDate() == null || jTextField22.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(jTextField23.getText().trim());
            java.time.LocalDate fechaVenta = jDateChooser2.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.time.LocalTime horaVenta = java.time.LocalTime.parse(jTextField22.getText().trim());

            modelo.Pasaje nuevoPasaje = new modelo.Pasaje(pasajeroSel, lugarSel, viajeSel, fechaVenta, horaVenta, precio, true);

            if (pasajeData.registrarPasaje(nuevoPasaje)) {
                asientoData.bajaLogica(lugarSel.getIdAsiento());
                JOptionPane.showMessageDialog(this, "Pasaje vendido y guardado correctamente.");
                limpiarCamposPasajeVenta();
                cargarCombosPasajes();
                cargarTablaPasajes();
                cargarTablaAsientos();
            }
        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de hora invalido. Use HH:mm (ej: 14:30)", "Error de Hora", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un numero valido.", "Error de Precio", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardar5ActionPerformed

    private void btnNuevo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo5ActionPerformed
        // TODO add your handling code here:

        limpiarCamposPasajeVenta();
    }//GEN-LAST:event_btnNuevo5ActionPerformed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
        int fila = jTable6.getSelectedRow();
        if (fila != -1) {
            int idPasaje = (Integer) modeloPasajes.getValueAt(fila, 0);
            modelo.Pasaje p = pasajeData.buscarPasaje(idPasaje);
            if (p != null) {
                cargarDatosPasajeEnFormulario(p);
            }
        }
    }//GEN-LAST:event_jTable6MouseClicked

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnListar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListar4ActionPerformed
        // TODO add your handling code here:
        cargarComboColectivos();
        cargarTablaViajes();
        limpiarCamposViaje();

    }//GEN-LAST:event_btnListar4ActionPerformed

    private void btnBuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar4ActionPerformed
        // TODO add your handling code here:

        String input = JOptionPane.showInputDialog(this,"Ingrese el ID de la ruta a buscar","Buscar Ruta",JOptionPane.QUESTION_MESSAGE);
        if (input!=null&&!input.trim().isEmpty()) {
            try {
                int id=Integer.parseInt(input.trim());
                modelo.Viaje v=viajeData.buscarViaje(id);
                if (v != null) {

                    jTextField16.setText(v.getOrigen());
                    jTextField17.setText(v.getDestino());
                    if (v.getFecha() != null) {
                        jDateChooser1.setDate(java.sql.Date.valueOf(v.getFecha()));
                    }
                    jFormattedTextField2.setText(v.getHoraSalida().toString());
                    jFormattedTextField1.setText(v.getHoraLlegada().toString());
                    jTextField18.setText(String.valueOf(v.getPrecio()));

                    for (int i = 0;i<jComboBox1.getItemCount();i++) {
                        if (jComboBox1.getItemAt(i).getIdColectivo()==v.getColectivo().getIdColectivo()) {
                            jComboBox1.setSelectedIndex(i);
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(this,"Ruta encontrada");
                } else {
                    JOptionPane.showMessageDialog(this,"No se encontro ninguna ruta"+id,"Sin resultados",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"El ID debe ser un  numero valido","Error",JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnBuscar4ActionPerformed

    private void btnEliminar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar4ActionPerformed
        // TODO add your handling code here:
        int fila=jTable5.getSelectedRow();
        if (fila==-1){
            JOptionPane.showMessageDialog(this,"Seleccione una ruta de la tabla para darla de baja","Atención",JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idViaje=(Integer) modeloViajes.getValueAt(fila, 0);
        int confirm=JOptionPane.showConfirmDialog(this,"Seguro que desea dar de baja esta ruta?","Confirmar",JOptionPane.YES_NO_OPTION);

        if (confirm==JOptionPane.YES_OPTION) {
            if (viajeData.eliminarViaje(idViaje)) {
                limpiarCamposViaje();
                cargarTablaViajes();
            }
        }

    }//GEN-LAST:event_btnEliminar4ActionPerformed

    private void btnModificar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar4ActionPerformed
        // TODO add your handling code here:

        int fila=jTable5.getSelectedRow();
        if (fila==-1){
            JOptionPane.showMessageDialog(this,"Seleccione una ruta de la tabla para modificar","Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (jTextField16.getText().trim().isEmpty()||jTextField17.getText().trim().isEmpty()||jTextField18.getText().trim().isEmpty()||jDateChooser1.getDate()==null){
            JOptionPane.showMessageDialog(this,"Complete todos los campos obligatorios", "Campos Vacíos",JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idViaje=(Integer) modeloViajes.getValueAt(fila, 0);
            String origen=jTextField16.getText().trim();
            String destino=jTextField17.getText().trim();
            java.time.LocalDate fecha=jDateChooser1.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.time.LocalTime horaSalida=java.time.LocalTime.parse(jFormattedTextField2.getText().trim());
            java.time.LocalTime horaLlegada=java.time.LocalTime.parse(jFormattedTextField1.getText().trim());
            double precio=Double.parseDouble(jTextField18.getText().trim());
            modelo.Colectivo col=(modelo.Colectivo) jComboBox1.getSelectedItem();

            modelo.Viaje viajeMod=new modelo.Viaje(idViaje,origen,destino,fecha,horaSalida,horaLlegada,col,precio,true);

            if (viajeData.modificarViaje(viajeMod)) {
                limpiarCamposViaje();
                cargarTablaViajes();
            }
        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this,"Formato de hora invalido(ej: 14:30)", "Error de Hora",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Verifique que el ID y el precio sean numeros válidos","Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnModificar4ActionPerformed

    private void btnGuardar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar4ActionPerformed
        // TODO add your handling code here:

        if (jTextField16.getText().trim().isEmpty()||jTextField17.getText().trim().isEmpty()||jTextField18.getText().trim().isEmpty()||jDateChooser1.getDate()==null) {
            JOptionPane.showMessageDialog(this, "Complete origen,destino,fecha y precio","Campos obligatorios",JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String origen=jTextField16.getText().trim();
            String destino=jTextField17.getText().trim();
            java.time.LocalDate fecha=jDateChooser1.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.time.LocalTime horaSalida=java.time.LocalTime.parse(jFormattedTextField2.getText().trim());
            java.time.LocalTime horaLlegada=java.time.LocalTime.parse(jFormattedTextField1.getText().trim());
            double precio=Double.parseDouble(jTextField18.getText().trim());
            modelo.Colectivo col=(modelo.Colectivo) jComboBox1.getSelectedItem();

            modelo.Viaje nuevo=new modelo.Viaje(origen,destino,fecha,horaSalida,horaLlegada,col,precio,true);

            if (viajeData.guardarViaje(nuevo)) {
                limpiarCamposViaje();
                cargarTablaViajes();
            }
        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de hora invalido,(ej: 14:30)", "Error de Hora", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un numero válido", "Error de Precio", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardar4ActionPerformed

    private void btnNuevo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo4ActionPerformed
        // TODO add your handling code here:
        limpiarCamposViaje();

    }//GEN-LAST:event_btnNuevo4ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:

        int fila=jTable5.getSelectedRow();
        if (fila != -1){
            jTextField16.setText(modeloViajes.getValueAt(fila,1).toString());
            jTextField17.setText(modeloViajes.getValueAt(fila,2).toString());

            Object fechaObj=modeloViajes.getValueAt(fila, 3);
            if (fechaObj != null) {
                jDateChooser1.setDate(java.sql.Date.valueOf(fechaObj.toString()));
            }
            jFormattedTextField2.setText(modeloViajes.getValueAt(fila,4).toString());
            jFormattedTextField1.setText(modeloViajes.getValueAt(fila,5).toString());
            int idCol = Integer.parseInt(modeloViajes.getValueAt(fila,6).toString());
            for(int i=0;i<jComboBox1.getItemCount();i++) {
                if (jComboBox1.getItemAt(i).getIdColectivo()==idCol) {
                    jComboBox1.setSelectedIndex(i);
                    break;
                }
            }

            jTextField18.setText(modeloViajes.getValueAt(fila,7).toString());
        }

    }//GEN-LAST:event_jTable5MouseClicked

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:

        int filaSeleccionada = jTable4.getSelectedRow();
        if (filaSeleccionada != -1) {

            jTextField13.setText(modeloAsientos.getValueAt(filaSeleccionada, 1).toString());

            jTextField14.setText(modeloAsientos.getValueAt(filaSeleccionada, 2).toString());

            String estadoTexto = modeloAsientos.getValueAt(filaSeleccionada, 3).toString();
            jCheckBox3.setSelected(estadoTexto.equals("Disponible"));
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void btnListar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListar3ActionPerformed
        // TODO add your handling code here:

        cargarTablaAsientos();
        limpiarCamposAsiento();
    }//GEN-LAST:event_btnListar3ActionPerformed

    private void btnBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar3ActionPerformed
        // TODO add your handling code here:
        try {

            String inputId = javax.swing.JOptionPane.showInputDialog(this, "Ingrese el ID del lugar que desea buscar:", "Buscar Asiento", javax.swing.JOptionPane.QUESTION_MESSAGE);

            if (inputId != null && !inputId.trim().isEmpty()) {
                int idBuscado = Integer.parseInt(inputId);

                modelo.Lugar lugarEncontrado = asientoData.buscar(idBuscado);

                if (lugarEncontrado != null) {

                    jTextField13.setText(String.valueOf(lugarEncontrado.getColectivo().getIdColectivo()));
                    jTextField14.setText(String.valueOf(lugarEncontrado.getNumeroAsiento()));
                    jCheckBox3.setSelected(lugarEncontrado.isEstado());

                    javax.swing.JOptionPane.showMessageDialog(this, "Lugar encontrado");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "No existe ningún lugar con el ID: " + idBuscado, "No encontrado", javax.swing.JOptionPane.WARNING_MESSAGE);
                    limpiarCamposAsiento();
                }
            }
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese un numero de ID valido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscar3ActionPerformed

    private void btnEliminar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar3ActionPerformed
        // TODO add your handling code here:

        int filaSeleccionada = jTable4.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un asiento de la tabla para darlo de baja.");
            return;
        }

        int idLugar = (Integer) modeloAsientos.getValueAt(filaSeleccionada, 0);

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this, "Seguro que desea pasar este asiento a inactivo/ocupado?", "Confirmar", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            asientoData.bajaLogica(idLugar);
            javax.swing.JOptionPane.showMessageDialog(this, "Asiento dado de baja");
            limpiarCamposAsiento();
            cargarTablaAsientos();
        }
    }//GEN-LAST:event_btnEliminar3ActionPerformed

    private void btnModificar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar3ActionPerformed
        // TODO add your handling code here:

        int filaSeleccionada = jTable4.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un asiento de la tabla para modificarlo.");
            return;
        }

        try {

            int idLugar = (Integer) modeloAsientos.getValueAt(filaSeleccionada, 0);

            int idColectivo = Integer.parseInt(jTextField13.getText());
            int nroAsiento = Integer.parseInt(jTextField14.getText());
            boolean estado = jCheckBox3.isSelected();

            modelo.Colectivo col = new modelo.Colectivo();
            col.setIdColectivo(idColectivo);

            modelo.Lugar lugarModificado = new modelo.Lugar(idLugar, col, nroAsiento, estado);

            asientoData.actualizar(lugarModificado);
            javax.swing.JOptionPane.showMessageDialog(this, "Lugar modificado exitosamente");
            limpiarCamposAsiento();
            cargarTablaAsientos();

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Revise que los numeros sean validos", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificar3ActionPerformed

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        // TODO add your handling code here:

        try {
            int idColectivo=Integer.parseInt(jTextField13.getText());
            int nroAsiento=Integer.parseInt(jTextField14.getText());
            boolean estado=jCheckBox3.isSelected();
            modelo.Colectivo col=new modelo.Colectivo();
            col.setIdColectivo(idColectivo);
            modelo.Lugar nuevoLugar=new modelo.Lugar(col,nroAsiento,estado);
            boolean guardado=asientoData.insertar(nuevoLugar);
            if (guardado){
                javax.swing.JOptionPane.showMessageDialog(this, "Asiento guardado");
                limpiarCamposAsiento();
                cargarTablaAsientos();
            }
        } catch(NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Los campos ID Colectivo y N° Asiento deben ser numeros", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
        // TODO add your handling code here:

        limpiarCamposAsiento();
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

        int filaSeleccionada=jTable2.getSelectedRow();
        if (filaSeleccionada!=-1) {
            jTextField1.setText(modeloPasajeros.getValueAt(filaSeleccionada,1).toString());
            jTextField2.setText(modeloPasajeros.getValueAt(filaSeleccionada,2).toString());
            jTextField3.setText(modeloPasajeros.getValueAt(filaSeleccionada,3).toString());
            jTextField4.setText(modeloPasajeros.getValueAt(filaSeleccionada,4).toString());
            jTextField5.setText(modeloPasajeros.getValueAt(filaSeleccionada,5).toString());

            String estadoStr = modeloPasajeros.getValueAt(filaSeleccionada, 6).toString();
            jCheckBox1.setSelected(estadoStr.equals("Activo"));
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnListar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListar1ActionPerformed
        // TODO add your handling code here:
        limpiarCamposPasajero();
        cargarTablaPasajeros();
    }//GEN-LAST:event_btnListar1ActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:

        String input=javax.swing.JOptionPane.showInputDialog(this, "Ingrese el ID del pasajero a buscar");

        if (input!=null&&!input.trim().isEmpty()) {
            try {
                int id=Integer.parseInt(input);
                modelo.Pasajero p=pasajeroData.buscar(id);

                if (p != null){
                    jTextField1.setText(p.getDni());
                    jTextField2.setText(p.getNombre());
                    jTextField3.setText(p.getApellido());
                    jTextField4.setText(p.getCorreo());
                    jTextField5.setText(p.getTelefono());
                    jCheckBox1.setSelected(p.isEstado());
                    javax.swing.JOptionPane.showMessageDialog(this, "Pasajero encontrado");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Pasajero no encontrado", "Busqueda", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "El ID debe ser un numero entero","Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        // TODO add your handling code here:

        int filaSeleccionada = jTable2.getSelectedRow();
        if (filaSeleccionada != -1) {
            int id = (Integer) modeloPasajeros.getValueAt(filaSeleccionada, 0);
            pasajeroData.bajaLogica(id);
            cargarTablaPasajeros();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un pasajero de la tabla para eliminar");
        }
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        // TODO add your handling code here:

        int filaSeleccionada = jTable2.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un pasajero de la tabla para modificar.", "Atención", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (jTextField1.getText().isEmpty()||jTextField2.getText().isEmpty()||jTextField3.getText().isEmpty() || jTextField4.getText().isEmpty()||jTextField5.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = (Integer) modeloPasajeros.getValueAt(filaSeleccionada, 0);
            String dni = jTextField1.getText();
            String nombre = jTextField2.getText();
            String apellido = jTextField3.getText();
            String correo = jTextField4.getText();
            String telefono = jTextField5.getText();
            boolean estado = jCheckBox1.isSelected();

            Integer.parseInt(dni);

            modelo.Pasajero pasajeroModificado = new modelo.Pasajero(id, dni, nombre, apellido, correo, telefono, estado);
            pasajeroData.actualizar(pasajeroModificado);

            javax.swing.JOptionPane.showMessageDialog(this,"Pasajero modificado");
            limpiarCamposPasajero();
            cargarTablaPasajeros();

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "El DNI debe contener solo numeros", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al modificar" + ex.getMessage(),"Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // TODO add your handling code here:
        if (jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty() || jTextField4.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nombre, Apellido y Correo son obligatorios.");
            return;
        }

        try {
            String dni=jTextField1.getText();
            String nombre = jTextField2.getText();
            String apellido = jTextField3.getText();
            String correo = jTextField4.getText();
            String telefono = jTextField5.getText();
            boolean estado = jCheckBox1.isSelected();

            Integer.parseInt(dni);//solo numeros para dni

            modelo.Pasajero nuevo = new modelo.Pasajero(dni, nombre, apellido, correo, telefono, estado);

            pasajeroData.insertar(nuevo);

            javax.swing.JOptionPane.showMessageDialog(this, "Pasajero guardado");
            limpiarCamposPasajero();
            cargarTablaPasajeros();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El DNI debe contener solo numeros","Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error"+ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        // TODO add your handling code here:

        limpiarCamposPasajero();
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnListar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListar2ActionPerformed
        // TODO add your handling code here:

        limpiarCamposColectivo();
        cargarTablaColectivos();
    }//GEN-LAST:event_btnListar2ActionPerformed

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        // TODO add your handling code here:

        String input = javax.swing.JOptionPane.showInputDialog(this, "Ingrese el ID del colectivo");

        if (input!=null&&!input.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(input);
                modelo.Colectivo c = colectivoData.buscar(id);

                if (c != null) {
                    jTextField7.setText(c.getMatricula());
                    jTextField8.setText(c.getMarca());
                    jTextField9.setText(c.getModelo());
                    jTextField10.setText(String.valueOf(c.getCapacidad()));
                    jTextField11.setText(c.getChofer());
                    jCheckBox2.setSelected(c.isEstado());

                    javax.swing.JOptionPane.showMessageDialog(this, "Colectivo encontrado");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se encontro ningun colectivo", "Búsqueda", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "El ID debe ser un numero entero.","Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBuscar2ActionPerformed

    private void btnEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar2ActionPerformed
        // TODO add your handling code here:

        int filaSeleccionada = jTable3.getSelectedRow();
        if (filaSeleccionada != -1) {

            int id = (Integer) modeloColectivos.getValueAt(filaSeleccionada, 0);

            colectivoData.bajaLogica(id);

            javax.swing.JOptionPane.showMessageDialog(this, "Colectivo dado de baja");
            cargarTablaColectivos();
            limpiarCamposColectivo();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un colectivo de la tabla para dar de baja");
        }
    }//GEN-LAST:event_btnEliminar2ActionPerformed

    private void btnModificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar2ActionPerformed
        // TODO add your handling code here:

        int filaSeleccionada = jTable3.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,"Seleccione un colectivo de la tabla","Atención", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (jTextField7.getText().isEmpty()||jTextField8.getText().isEmpty()||jTextField9.getText().isEmpty()||jTextField10.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,"complete los campos","Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id=(Integer) modeloColectivos.getValueAt(filaSeleccionada,0);
            String matricula = jTextField7.getText();
            String marca = jTextField8.getText();
            String modelo = jTextField9.getText();
            int capacidad = Integer.parseInt(jTextField10.getText());
            String chofer = jTextField11.getText();
            boolean estado = jCheckBox2.isSelected();

            modelo.Colectivo colectivoModificado=new modelo.Colectivo(id,matricula, marca, modelo, capacidad, chofer, estado);
            colectivoData.actualizar(colectivoModificado);

            javax.swing.JOptionPane.showMessageDialog(this,"Colectivo modificado");
            limpiarCamposColectivo();
            cargarTablaColectivos();

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"Verifique que el ID y la capacidad sean numeros","Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"Error al modificar" + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificar2ActionPerformed

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        // TODO add your handling code here:

        if (jTextField7.getText().isEmpty() || jTextField8.getText().isEmpty() || jTextField9.getText().isEmpty() || jTextField10.getText().isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "complete los campos", "Campos vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String matricula=jTextField7.getText();
            String marca=jTextField8.getText();
            String modelo=jTextField9.getText();
            int capacidad=Integer.parseInt(jTextField10.getText());
            String chofer=jTextField11.getText();
            boolean estado=jCheckBox2.isSelected();

            modelo.Colectivo nuevo=new modelo.Colectivo(matricula, marca, modelo, capacidad, chofer, estado);

            boolean guardado=colectivoData.insertarColectivo(nuevo);
            if (guardado){
                javax.swing.JOptionPane.showMessageDialog(this,"Colectivo guardado");
                limpiarCamposColectivo();
                cargarTablaColectivos();
            }

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "La capacidad debe ser un numero", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        // TODO add your handling code here:

        limpiarCamposColectivo();
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = jTable3.getSelectedRow();
        if (filaSeleccionada != -1) {

            jTextField7.setText(modeloColectivos.getValueAt(filaSeleccionada, 1).toString());
            jTextField8.setText(modeloColectivos.getValueAt(filaSeleccionada, 2).toString());
            jTextField9.setText(modeloColectivos.getValueAt(filaSeleccionada, 3).toString());
            jTextField10.setText(modeloColectivos.getValueAt(filaSeleccionada, 4).toString());
            jTextField11.setText(modeloColectivos.getValueAt(filaSeleccionada, 5).toString());

            String estadoStr = modeloColectivos.getValueAt(filaSeleccionada, 6).toString();
            jCheckBox2.setSelected(estadoStr.equals("Activo"));
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(5);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        
        jTabbedPane2.setSelectedIndex(4);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int fila = jTable6.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un pasaje de la tabla para cambiar su estado", "Atención", JOptionPane.WARNING_MESSAGE);
        return;
    }
    int idPasaje=(Integer) modeloPasajes.getValueAt(fila, 0);
    modelo.Pasaje p=pasajeData.buscarPasaje(idPasaje);

    if (p == null) return;

    if (p.isEstado()) {
        if (p.getLugar() != null) {
            p.getLugar().setEstado(true);
            asientoData.actualizar(p.getLugar());
        }
        pasajeData.anularPasaje(idPasaje);
        JOptionPane.showMessageDialog(this,"Pasaje anulado y asiento liberado");
    } else {
    
        modelo.Lugar asientoActual = asientoData.buscar(p.getLugar().getIdAsiento());
        if (asientoActual != null && !asientoActual.isEstado()) {
            JOptionPane.showMessageDialog(this,"No se puede reactivar: el Asiento N° "+asientoActual.getNumeroAsiento()+" ya fue ocupado por otro pasaje","Asiento ocupado", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (asientoActual != null) {
            asientoActual.setEstado(false);
            asientoData.actualizar(asientoActual);
        }
        p.setEstado(true);
        pasajeData.modificarPasaje(p);
        JOptionPane.showMessageDialog(this,"Pasaje reactivado y asiento ocupado nuevamente");
    }

    cargarCombosPasajes();
    cargarTablaPasajes();
    cargarTablaAsientos();
    limpiarCamposPasajeVenta();
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscar2;
    private javax.swing.JButton btnBuscar3;
    private javax.swing.JButton btnBuscar4;
    private javax.swing.JButton btnBuscar5;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnEliminar2;
    private javax.swing.JButton btnEliminar3;
    private javax.swing.JButton btnEliminar4;
    private javax.swing.JButton btnEliminar5;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnGuardar3;
    private javax.swing.JButton btnGuardar4;
    private javax.swing.JButton btnGuardar5;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnListar1;
    private javax.swing.JButton btnListar2;
    private javax.swing.JButton btnListar3;
    private javax.swing.JButton btnListar4;
    private javax.swing.JButton btnModificar1;
    private javax.swing.JButton btnModificar2;
    private javax.swing.JButton btnModificar3;
    private javax.swing.JButton btnModificar4;
    private javax.swing.JButton btnModificar5;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnNuevo3;
    private javax.swing.JButton btnNuevo4;
    private javax.swing.JButton btnNuevo5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<modelo.Colectivo> jComboBox1;
    private javax.swing.JComboBox<modelo.Pasajero> jComboBox2;
    private javax.swing.JComboBox<modelo.Lugar> jComboBox3;
    private javax.swing.JComboBox<modelo.Viaje> jComboBox4;
    private javax.swing.JComboBox<modelo.Colectivo> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
