/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmNotificacoes.java
 *
 * Created on 25/03/2014, 16:35:39
 */
package Formularios;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

/**
 *
 * @author Abel
 */
public class FrmNotificacoes extends javax.swing.JFrame {

    /** Creates new form FrmNotificacoes */
    public FrmNotificacoes() {
        initComponents();
        
        
        Dimension srcSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
        this.setLocation(srcSize.width - this.getWidth(), srcSize.height - toolHeight.bottom - this.getHeight());
        timerTempo.start();
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timerTempo = new org.netbeans.examples.lib.timerbean.Timer();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblTexto = new javax.swing.JLabel();
        lblLink = new javax.swing.JLabel();

        timerTempo.setDelay(10000L);
        timerTempo.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timerTempoOnTime(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTitulo.setText("Aviso !!!");

        jButton1.setBackground(new java.awt.Color(-4144960,true));
        jButton1.setText("X");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblTexto.setFont(new java.awt.Font("High Tower Text", 0, 14)); // NOI18N
        lblTexto.setText("jLabel2");

        lblLink.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblLink.setForeground(new java.awt.Color(-16776961,true));
        lblLink.setText("jLabel1");
        lblLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLinkMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(lblTexto)
                    .addComponent(lblLink))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(lblTexto)
                .addGap(30, 30, 30)
                .addComponent(lblLink)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lblLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkMouseClicked
        new FrmContasReceber().setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_lblLinkMouseClicked

    private void timerTempoOnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timerTempoOnTime
        dispose();
    }//GEN-LAST:event_timerTempoOnTime

    
    public void mensagem (String titulo, String msg, String link){
        
        lblTitulo.setText(titulo);
        lblTexto.setText(msg);
        lblLink.setText(link);
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmNotificacoes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLink;
    private javax.swing.JLabel lblTexto;
    private javax.swing.JLabel lblTitulo;
    private org.netbeans.examples.lib.timerbean.Timer timerTempo;
    // End of variables declaration//GEN-END:variables
}