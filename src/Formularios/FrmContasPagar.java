/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Formularios;

import Banco.Conexao;
import Controle.ContasPagarDAO;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ContasPagar;

/**
 *
 * @author robson
 */
public class FrmContasPagar extends javax.swing.JFrame {
    
    
    Connection conexao;
    DateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat df = new DecimalFormat("###,##0.00");
    
    int id_venda, nparc; //Atributo usado para armazenar o id da venda
    double vlrDesc, vlrJuros; //Atributo usado para armazenar o valor do desconto

    /**
     * Creates new form FrmContasPagar
     */
    public FrmContasPagar() {
        initComponents();
        
        carregaCBOFornecedores();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtJuros = new javax.swing.JTextField();
        btnRelatorio = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtValorFinal = new javax.swing.JTextField();
        cboTipoPgto = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtDesconto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtValorParc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboFornecedores = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableContas = new javax.swing.JTable();
        btnBaixarParcs = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNParc = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Contas à Pagar");

        txtJuros.setFont(new java.awt.Font("Dialog", 0, 18));
        txtJuros.setText("0");
        txtJuros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJurosActionPerformed(evt);
            }
        });

        btnRelatorio.setText("Relatório");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel4.setText("%");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel10.setText("Valor Final:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel5.setText("Tipo Pgto:");

        txtValorFinal.setEditable(false);
        txtValorFinal.setFont(new java.awt.Font("Dialog", 0, 18));
        txtValorFinal.setForeground(new java.awt.Color(-65536,true));

        cboTipoPgto.setFont(new java.awt.Font("Dialog", 0, 18));
        cboTipoPgto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dinheiro", "Cartão" }));
        cboTipoPgto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoPgtoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel6.setText("Desconto:");

        txtDesconto.setFont(new java.awt.Font("Dialog", 0, 18));
        txtDesconto.setText("0");
        txtDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescontoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel7.setText("%");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel8.setText("Valor Parcela:");

        txtValorParc.setEditable(false);
        txtValorParc.setFont(new java.awt.Font("Dialog", 0, 18));
        txtValorParc.setForeground(new java.awt.Color(-65536,true));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 24));
        jLabel1.setText("ID:");

        txtID.setFont(new java.awt.Font("SansSerif", 0, 24));
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 24));
        jLabel2.setText("Fornecedor:");

        cboFornecedores.setFont(new java.awt.Font("SansSerif", 0, 24));
        cboFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboFornecedoresMouseClicked(evt);
            }
        });
        cboFornecedores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFornecedoresItemStateChanged(evt);
            }
        });
        cboFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFornecedoresActionPerformed(evt);
            }
        });

        JTableContas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Compra", "Nome", "Data Compra", "Parcela", "Valor", "Juros", "Data Vencimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTableContas.setShowHorizontalLines(false);
        JTableContas.setShowVerticalLines(false);
        JTableContas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableContasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableContas);

        btnBaixarParcs.setText("Baixar Parcelas");
        btnBaixarParcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaixarParcsActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel9.setText("Nº Parcela:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel3.setText("Juros:");

        txtNParc.setEditable(false);
        txtNParc.setFont(new java.awt.Font("Dialog", 0, 18));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboFornecedores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBaixarParcs, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDesconto, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtJuros, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNParc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addGap(22, 22, 22))
                            .addComponent(cboTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorParc)
                            .addComponent(txtValorFinal, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cboFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtJuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtNParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtValorParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBaixarParcs, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtJurosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJurosActionPerformed
            juros();
    }//GEN-LAST:event_txtJurosActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
      
    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void cboTipoPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoPgtoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoPgtoActionPerformed

    private void txtDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescontoActionPerformed
            desconto();
    }//GEN-LAST:event_txtDescontoActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
            
        setaCBOID(Integer.parseInt(txtID.getText()));
        preencherJTable();

        
    }//GEN-LAST:event_txtIDActionPerformed

    private void cboFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFornecedoresActionPerformed
            try{  
        
                    if("Todas Vencidas".equals(cboFornecedores.getSelectedItem())){
                        vencidas();
                        return;
                    }
                     this.conexao = Conexao.getConexao();

                     String sql = "Select id from fornecedores where fantasia = ?";
                     PreparedStatement ps = this.conexao.prepareStatement(sql);
                     ps.setString(1, String.valueOf(cboFornecedores.getSelectedItem()));
                     ResultSet rsts = ps.executeQuery();

                         rsts.next();

                             txtID.setText(String.valueOf(rsts.getInt("id")));

                         }catch(SQLException err){
                             JOptionPane.showMessageDialog(null, "Erro" + err);
                         }
   
            preencherJTable();
    }//GEN-LAST:event_cboFornecedoresActionPerformed

    private void JTableContasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableContasMouseClicked
            if(evt.getClickCount() == 1){
            
            Point p =  evt.getPoint();
            
            int row = JTableContas.rowAtPoint(p);
            txtValorParc.setText(String.valueOf(JTableContas.getModel().getValueAt(row, 4)));
            txtValorFinal.setText(String.valueOf(JTableContas.getModel().getValueAt(row, 4)));
            id_venda = Integer.parseInt(String.valueOf(JTableContas.getModel().getValueAt(row, 0)));
            nparc = Integer.parseInt(String.valueOf(JTableContas.getModel().getValueAt(row, 3)));
            txtNParc.setText(String.valueOf(nparc));
            
            
            
        }
    }//GEN-LAST:event_JTableContasMouseClicked

    private void btnBaixarParcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaixarParcsActionPerformed
        
                try{

                ContasPagar cp = new ContasPagar();
                ContasPagarDAO cpDAO = new ContasPagarDAO();

                cp.setVlr_juros(vlrJuros);
                cp.setVlr_desc(vlrDesc);
                cp.setTipo_pgto(String.valueOf(cboTipoPgto.getSelectedItem()));
                cp.setId_venda(id_venda);
                cp.setNparc(Integer.parseInt(txtNParc.getText()));
                cp.setVlr_final(Double.parseDouble(String.valueOf(df.parse(txtValorFinal.getText()))));
                cp.setId_cliente(Integer.parseInt(txtID.getText()));


                //Dinheiro
                if(0 == cboTipoPgto.getSelectedIndex()){


                    FrmContasReceberVista contasReceberVista = new FrmContasReceberVista();

                    contasReceberVista.retornaTotal(Double.parseDouble(String.valueOf(df.parse(txtValorFinal.getText()))),
                                  Integer.parseInt(txtID.getText()), id_venda, nparc, vlrDesc, vlrJuros);


                }else if(1 == cboTipoPgto.getSelectedIndex()){


                    cpDAO.baixarContasaPagar(cp);




                }

                preencherJTable();    
                limparCampos();

             }catch(ParseException err){
                      JOptionPane.showMessageDialog(null, "Erro ao converter moeda " + err);
             }
       

    }//GEN-LAST:event_btnBaixarParcsActionPerformed

    private void cboFornecedoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFornecedoresItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFornecedoresItemStateChanged

    private void cboFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboFornecedoresMouseClicked
      
    }//GEN-LAST:event_cboFornecedoresMouseClicked

    
    private void carregaCBOFornecedores(){
        
        try {
            this.conexao = Conexao.getConexao();
            
            String sql = "Select fantasia from fornecedores";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet rsts = ps.executeQuery();
            
            while(rsts.next()){
                
                cboFornecedores.addItem(rsts.getString("fantasia"));
                
                
                
            }
            cboFornecedores.addItem("Todas Vencidas");
            
        } catch (SQLException ex) {
            Logger.getLogger(FrmContasPagar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private void setaCBOID(int id){
        
          try {
            this.conexao = Conexao.getConexao();
            
            String sql = "Select fantasia from fornecedores where id = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rsts = ps.executeQuery();
            
        
                rsts.next();
                cboFornecedores.setSelectedItem(rsts.getString("fantasia"));
             
            
            
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Fonecedor não encontrado!!!", null, WIDTH);
        }
        
        
    }
    
     private void preencherJTable(){
        
        JTableContas.getColumnModel().getColumn(0).setPreferredWidth(50);
        JTableContas.getColumnModel().getColumn(1).setPreferredWidth(50);
        JTableContas.getColumnModel().getColumn(2).setPreferredWidth(50);
        JTableContas.getColumnModel().getColumn(3).setPreferredWidth(25);
        JTableContas.getColumnModel().getColumn(4).setPreferredWidth(25);
        JTableContas.getColumnModel().getColumn(5).setPreferredWidth(25);
        JTableContas.getColumnModel().getColumn(6).setPreferredWidth(50);
        
        DefaultTableModel modelo = (DefaultTableModel)JTableContas.getModel();
        modelo.setNumRows(0);
       
        try{
           
            
            this.conexao = Conexao.getConexao();
            
            String sql = "Select id_vencom, fo.fantasia, data_venda, nparc, vlr_parc, vlr_juros, data_vencto from " +
                         "fluxocaixa cr inner join fornecedores fo on cr.id_fornecedor = fo.id where id_fornecedor = ? and pago = 'N';";
                         
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtID.getText()));
            ResultSet rsts = ps.executeQuery();
            
            while(rsts.next()){

                modelo.addRow(new Object[] {rsts.getInt("id_vencom"),
                                            rsts.getString("fo.fantasia"),
                                            dateFor.format(rsts.getDate("data_venda")),
                                            rsts.getInt("nparc"),
                                            df.format(rsts.getDouble("vlr_parc")),
                                            df.format(rsts.getDouble("vlr_juros")),
                                            dateFor.format(rsts.getDate("data_vencto"))});
               
 
                
            }

        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Erro ao preencher jtable" + err);
                  
        }
    }
     
     private void vencidas(){
        try {
            
         
            
              //Formata a data para consultas em banco de dados
            DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
            
             //Uso da classe calendar para trabalhar com as datas
            Calendar c = Calendar.getInstance();
           
         
            DefaultTableModel modelo = (DefaultTableModel)JTableContas.getModel();
            modelo.setNumRows(0);
            
            
            this.conexao = Conexao.getConexao();
            
            String sql = "Select id_vencom, fo.fantasia, data_venda, nparc, vlr_parc, vlr_juros, data_vencto from " +
                         "fluxocaixa cr inner join fornecedores fo on cr.id_fornecedor = fo.id where data_vencto < ? and pago = 'N'";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, data.format(c.getTime()));
            ResultSet rsts = ps.executeQuery();
            
            while(rsts.next()){
 
                modelo.addRow(new Object[] {rsts.getInt("id_vencom"),
                                            rsts.getString("fo.fantasia"),
                                            dateFor.format(rsts.getDate("data_venda")),
                                            rsts.getInt("nparc"),
                                            df.format(rsts.getDouble("vlr_parc")),
                                            df.format(rsts.getDouble("vlr_juros")),
                                            dateFor.format(rsts.getDate("data_vencto"))});                                            

            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao trazer  todas vencidas " + ex);
        }
           
          
       }
     
     
     private void desconto(){
        
      try{
          
        double total = Double.parseDouble(String.valueOf(df.parse(txtValorParc.getText())));
        double desc = Integer.parseInt(txtDesconto.getText());
        
       
        
        vlrDesc = total * (desc/100);
        
       
        total = total - (total * (desc / 100));
        
       
        txtValorFinal.setText(df.format(total));
        
      }catch(ParseException err){
          JOptionPane.showMessageDialog(null, "Erro no método desconto " + err);                    
      }
    }
       
       private void juros(){
        try {
            
            
            double total = Double.parseDouble(String.valueOf(df.parse(txtValorParc.getText())));
            double juros = Integer.parseInt(txtJuros.getText());
            
            vlrJuros = total * (juros/100);
            
            total = total + (total * (juros / 100));
            
            txtValorFinal.setText(df.format(total));
            
            
            
        } catch (ParseException ex) {
            Logger.getLogger(FrmContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
        private void limparCampos(){
        
        txtJuros.setText("0");
        txtNParc.setText("");
        txtValorParc.setText("0,00");
        txtDesconto.setText("0");
        
    }
    
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
            java.util.logging.Logger.getLogger(FrmContasPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmContasPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmContasPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmContasPagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmContasPagar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableContas;
    private javax.swing.JButton btnBaixarParcs;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JComboBox cboFornecedores;
    private javax.swing.JComboBox cboTipoPgto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDesconto;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtJuros;
    private javax.swing.JTextField txtNParc;
    private javax.swing.JTextField txtValorFinal;
    private javax.swing.JTextField txtValorParc;
    // End of variables declaration//GEN-END:variables
}
