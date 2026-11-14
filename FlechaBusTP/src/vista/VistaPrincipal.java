/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import javax.swing.*;


public class VistaPrincipal extends javax.swing.JFrame {
    
private persistencia.Asientodata asientoData = new persistencia.Asientodata();
private javax.swing.table.DefaultTableModel modeloAsientos = new javax.swing.table.DefaultTableModel();
    
private persistencia.PasajeroData pasajeroData=new persistencia.PasajeroData();
private javax.swing.table.DefaultTableModel modeloPasajeros = new javax.swing.table.DefaultTableModel();
  
private persistencia.Colectivodata colectivoData=new persistencia.Colectivodata();
private javax.swing.table.DefaultTableModel modeloColectivos=new javax.swing.table.DefaultTableModel();


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
                a.getColectivo().getIdColectivo(), // Sacamos el ID del objeto Colectivo anidado
                a.getNumeroAsiento(),
                a.isEstado() ? "Disponible" : "Ocupado" // Usamos isEstado()
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
        modeloPasajeros.setRowCount(0); // Vacia la tabla
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
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
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
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

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

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel10.setText("COLECTIVOS");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setText("MATRICULA:");
        jLabel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setText("MARCA:");
        jLabel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setText("MODELO:");
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setText("CAPACIDAD:");
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel16.setText("CHOFER:");
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel17.setText("ESTADO:");
        jLabel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBox2.setText("ACTIVO");

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

        btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo_colectivo.png"))); // NOI18N
        btnNuevo2.setText("NUEVO");
        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo2ActionPerformed(evt);
            }
        });

        btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar_colectivo.png"))); // NOI18N
        btnGuardar2.setText("GUARDAR");
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        btnModificar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/editar_colectivo.png"))); // NOI18N
        btnModificar2.setText("MODIFICAR");
        btnModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar2ActionPerformed(evt);
            }
        });

        btnEliminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/borrar_colectivo.png"))); // NOI18N
        btnEliminar2.setText("ELIMINAR");
        btnEliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar2ActionPerformed(evt);
            }
        });

        btnBuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar_colectivo.png"))); // NOI18N
        btnBuscar2.setText("BUSCAR");
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });

        btnListar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/listar_colectivo.png"))); // NOI18N
        btnListar2.setText("LISTAR");
        btnListar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(jTextField7)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnModificar2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnListar2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar2)))
                        .addContainerGap(12, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jCheckBox2))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(btnEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnListar2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabbedPane2.addTab("COLECTIVOS", jPanel2);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel3.setText("INFORMACION DEL PASAJERO:");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setText("DNI ");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel5.setText("NOMBRE:");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel6.setText("APELLIDO:");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setText("CORREO:");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setText("TELEFONO:");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel9.setText("ESTADO:");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("ACTIVO");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo_pasajero.png"))); // NOI18N
        btnNuevo1.setText("NUEVO");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar_pasajero.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        btnModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/editar_pasajero.png"))); // NOI18N
        btnModificar1.setText("MODIFICAR");
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });

        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar_pasajero.png"))); // NOI18N
        btnEliminar1.setText("ELIMINAR");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar_pasajero.png"))); // NOI18N
        btnBuscar1.setText("BUSCAR");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        btnListar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/listar_pasajero.png"))); // NOI18N
        btnListar1.setText("LISTAR");
        btnListar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListar1ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNuevo1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnListar1))
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jCheckBox1))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jCheckBox1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("PASAJEROS", jPanel1);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel18.setText("ASIENTOS:");
        jLabel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel20.setText("ID COLECTIVO:");
        jLabel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel21.setText("N° DE ASIENTO:");
        jLabel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel22.setText("DISPONIBLE:");
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnNuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo_asiento.png"))); // NOI18N
        btnNuevo3.setText("NUEVO");
        btnNuevo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo3ActionPerformed(evt);
            }
        });

        btnGuardar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar_asiento.png"))); // NOI18N
        btnGuardar3.setText("GUARDAR");
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });

        btnModificar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modifiar_asiento.png"))); // NOI18N
        btnModificar3.setText("MODIFICAR");
        btnModificar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar3ActionPerformed(evt);
            }
        });

        btnEliminar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar_asiento.png"))); // NOI18N
        btnEliminar3.setText("ELIMINAR");
        btnEliminar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar3ActionPerformed(evt);
            }
        });

        btnBuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar_asiento.png"))); // NOI18N
        btnBuscar3.setText("BUSCAR");
        btnBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar3ActionPerformed(evt);
            }
        });

        btnListar3.setText("LISTAR");
        btnListar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListar3ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21)
                                    .addComponent(btnNuevo3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(btnGuardar3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jCheckBox3))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(btnModificar3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnEliminar3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscar3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnListar3))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(101, 101, 101)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jLabel18)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel18)
                .addGap(69, 69, 69)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar3))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jTabbedPane2.addTab("ASIENTOS", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel23.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel23.setText("FORMULARIO DE RUTAS:");
        jLabel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel24.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel24.setText("ID:");
        jLabel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel25.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel25.setText("ORIGEN:");
        jLabel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel26.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel26.setText("DESTINO:");
        jLabel26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel27.setText("HORA DE SALIDA:");
        jLabel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel28.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel28.setText("HORA DE LLEGADA:");
        jLabel28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel29.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel29.setText("COLECTIVO:");
        jLabel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel30.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel30.setText("PRECIO:");
        jLabel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField15.setText("jTextField15");

        jTextField16.setText("jTextField16");

        jTextField17.setText("jTextField17");

        jFormattedTextField1.setText("jFormattedTextField1");

        jFormattedTextField2.setText("jFormattedTextField2");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField18.setText("jTextField18");

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
        jScrollPane5.setViewportView(jTable5);

        btnNuevo4.setText("NUEVO");

        btnGuardar4.setText("GUARDAR");

        btnModificar4.setText("MODIFICAR");

        btnEliminar4.setText("ELIMINAR");

        btnBuscar4.setText("BUSCAR");

        btnListar4.setText("LISTAR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnEliminar4)
                                        .addGap(56, 56, 56)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscar4)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnNuevo4)
                        .addGap(26, 26, 26)
                        .addComponent(btnGuardar4)
                        .addGap(463, 463, 463)
                        .addComponent(btnListar4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnModificar4)
                        .addGap(401, 401, 401)))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel23)
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel28)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel29)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo4)
                    .addComponent(btnGuardar4)
                    .addComponent(btnModificar4)
                    .addComponent(btnEliminar4)
                    .addComponent(btnBuscar4)
                    .addComponent(btnListar4))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jTabbedPane2.addTab("RUTAS", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setText("FORMULARIO DE PASAJES (VENTA)");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel31.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel31.setText("ID PASAJE:");
        jLabel31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel32.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel32.setText("PASAJERO:");
        jLabel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel33.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel33.setText("ASIENTO/LUGAR:");
        jLabel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel34.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel34.setText("RUTA:");
        jLabel34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel35.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel35.setText("FECHA DE VENTA:");
        jLabel35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel36.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel36.setText("HORA DE VENTA:");
        jLabel36.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel37.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel37.setText("PRECIO:");
        jLabel37.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField19.setText("jTextField19");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField20.setText("jTextField20");

        jTextField21.setText("jTextField21");

        jTextField22.setText("jTextField22");

        jTextField23.setText("jTextField23");

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
        jScrollPane6.setViewportView(jTable6);

        btnNuevo5.setText("NUEVO");

        btnGuardar5.setText("GUARDAR");

        btnModificar5.setText("MODIFICAR");

        btnEliminar5.setText("ELIMINAR");

        btnBuscar5.setText("BUSCAR");

        btnListar.setText("LISTAR");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel32)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(btnNuevo5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btnGuardar5)
                                        .addGap(59, 59, 59)
                                        .addComponent(btnModificar5)))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel36)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField22))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel35)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(btnEliminar5)
                                .addGap(56, 56, 56)
                                .addComponent(btnBuscar5)
                                .addGap(67, 67, 67)
                                .addComponent(btnListar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel34)
                    .addComponent(jLabel37)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel35)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel36)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo5)
                    .addComponent(btnGuardar5)
                    .addComponent(btnModificar5)
                    .addComponent(btnEliminar5)
                    .addComponent(btnBuscar5)
                    .addComponent(btnListar))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("PASAJES", jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel2.setText("LISTADO DE PASAJES");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(616, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("LISTADOS", jPanel6);

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
            int idColectivo = Integer.parseInt(jTextField13.getText());
            int nroAsiento = Integer.parseInt(jTextField14.getText());
            boolean estado = jCheckBox3.isSelected();

            modelo.Colectivo col = new modelo.Colectivo();
            col.setIdColectivo(idColectivo);

            modelo.Lugar nuevoLugar = new modelo.Lugar(col, nroAsiento, estado);
            asientoData.insertar(nuevoLugar);
            javax.swing.JOptionPane.showMessageDialog(this, "Lugar guardado exitosamente");
            limpiarCamposAsiento();
            cargarTablaAsientos();

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Los campos ID Colectivo y N° Asiento deben ser numeros", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
        // TODO add your handling code here:

        limpiarCamposAsiento();
    }//GEN-LAST:event_btnNuevo3ActionPerformed

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

        if (jTextField7.getText().isEmpty()||jTextField8.getText().isEmpty()||jTextField9.getText().isEmpty()||jTextField10.getText().isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete los campos","Campos vacios", javax.swing.JOptionPane.WARNING_MESSAGE);
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

            colectivoData.insertar(nuevo);

            javax.swing.JOptionPane.showMessageDialog(this,"Colectivo guardado");
            limpiarCamposColectivo();
            cargarTablaColectivos();

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"La capacidad debe ser un numero","Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"Ocurrio un error al guardar" + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
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
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
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
