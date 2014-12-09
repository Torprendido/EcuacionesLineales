import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import Modelo.Modelo;

public class Frame extends javax.swing.JFrame {
    
    private ArrayList<Lexema> lexemas;
    
    public Frame() {
        initComponents();
        lineasJSP.getVerticalScrollBar().addAdjustmentListener(
                new AdjustmentListener() {
                        @Override
                        public void adjustmentValueChanged(AdjustmentEvent e) {
                            areaJSP.getVerticalScrollBar().setValue(e.getValue());
                        }
                }
        );
        areaJSP.getVerticalScrollBar().addAdjustmentListener(
                new AdjustmentListener() {
                        @Override
                        public void adjustmentValueChanged(AdjustmentEvent e) {
                            lineasJSP.getVerticalScrollBar().setValue(e.getValue());
                        }
                }
        );
        lineasJSP.getVerticalScrollBar().setEnabled(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        areaJSP = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        analizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaJT = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        mensaje = new javax.swing.JTextArea();
        resolver = new javax.swing.JButton();
        lineasJSP = new javax.swing.JScrollPane();
        areaLineas = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        area.setColumns(20);
        area.setRows(5);
        area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                areaKeyTyped(evt);
            }
        });
        areaJSP.setViewportView(area);

        analizar.setText("Analizar");
        analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarActionPerformed(evt);
            }
        });

        tablaJT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lexama", "Token", "ID", "Linea"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaJT);

        mensaje.setColumns(20);
        mensaje.setRows(5);
        jScrollPane3.setViewportView(mensaje);

        resolver.setText("Resolver");
        resolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resolverActionPerformed(evt);
            }
        });

        areaLineas.setColumns(20);
        areaLineas.setRows(5);
        lineasJSP.setViewportView(areaLineas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(analizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)))
                        .addComponent(lineasJSP, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(areaJSP, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resolver)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(areaJSP, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(analizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addComponent(lineasJSP))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resolver)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarActionPerformed
        lexemas = new ArrayList();
        tablaJT.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"Lexama", "Token", "ID", "Linea"}
        ));
        Analizar();
        enumerarLineas(area.getText());
    }//GEN-LAST:event_analizarActionPerformed

    private void resolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resolverActionPerformed
        mensaje.setText(Sintaxis.mensajeSintaxis(lexemas));
    }//GEN-LAST:event_resolverActionPerformed

    private void areaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaKeyTyped
        if (
                !(evt.getKeyChar() > 47 & evt.getKeyChar() < 58) &
                !(evt.getKeyChar() > 96 & evt.getKeyChar() < 127) &
                !(evt.getKeyChar() == 46) &
                !(evt.getKeyChar() == 61) &
                !(evt.getKeyChar() == 45) &
                !(evt.getKeyChar() == 32) &
                !(evt.getKeyChar() == 43)
        ) evt.consume();
    }//GEN-LAST:event_areaKeyTyped

    public static void main(String args[]) {
        Frame ventana = new Frame();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analizar;
    private javax.swing.JTextArea area;
    private javax.swing.JScrollPane areaJSP;
    private javax.swing.JTextArea areaLineas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane lineasJSP;
    private javax.swing.JTextArea mensaje;
    private javax.swing.JButton resolver;
    private javax.swing.JTable tablaJT;
    // End of variables declaration//GEN-END:variables


    private void SepararCodigo(String codigo) {
        codigo = codigo + "\n";
        String[][] aux = Modelo.ExtraerSeparadores();
        String lexema = "";
        int linea = 1;
        for (int i = 0; i < codigo.length(); i++)
            for (int j = 0; j < aux.length; j++) {
                Lexema lex = new Lexema();
                Lexema lex2 = new Lexema();
                if (
                        aux[j][0].compareTo(codigo.charAt(i) + "") == 0 ||
                        codigo.charAt(i) == ' ' ||
                        codigo.charAt(i) == '\n' ||
                        codigo.charAt(i) == '\t'
                ) {
                    if (lexema.compareTo("") != 0) {
                        lex.setLexema(lexema);
                        lex.setLinea(linea + "");
                        lexema = "";
                        lexemas.add(lex);
                    }
                    if (codigo.charAt(i) == '\n') linea ++;
                    if (codigo.charAt(i) != ' ' & codigo.charAt(i) != '\n' & codigo.charAt(i) != '\t') {
                        lex2.setLexema(codigo.charAt(i) + "");
                        lex2.setId(aux[j][1]);
                        lex2.setToken(aux[j][2]);
                        lex2.setLinea(linea + "");
                        lexemas.add(lex2);
                    }
                    break;
                } else if (j == aux.length - 1) lexema = lexema + codigo.charAt(i);
            }
    }

    private void Analizar() {
        SepararCodigo(area.getText());
        char[][] array = Modelo.AbrirTablaTrancicion();
        for (Lexema l: lexemas)
            if (l.getId().compareTo("") == 0)
                Automata.EsValido(l.getLexema(), array, l);
        //finalmente imprimir resultados
        for (Lexema l: lexemas)
            ((DefaultTableModel) tablaJT.getModel()).addRow(
                new Object[] {l.getLexema(), l.getToken(), l.getId(), l.getLinea()}
            );
    }
    
    private void enumerarLineas(String codigo) {
        int k = 0;
        codigo = codigo + '\n';
        areaLineas.setText("");
        for (int i = 0; i < codigo.length(); i++) {
            if (codigo.codePointAt(i) == '\n')
                areaLineas.setText(areaLineas.getText() + ++k + '\n');
        }
    }
}