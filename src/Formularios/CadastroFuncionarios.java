/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CadastroFuncionarios.java
 *
 * Created on 14/02/2014, 14:49:48
 */
package Formularios;

import Banco.Conexao;
import Controle.FuncionariosDAO;
import java.awt.Color;
import java.awt.Point;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Funcionarios;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import org.exolab.castor.types.Date;


/**
 *
 * @author Formigao
 */
public class CadastroFuncionarios extends javax.swing.JFrame {
    
    
    Connection conexao;
    String CheckRegistro = "";
    ResultSet rst;
    

    /** Creates new form CadastroFuncionarios */
    public CadastroFuncionarios() {
        initComponents();
        //this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        threadBtnOnTime.start();
        Preencher_JTable();
        consultarCidade();
        consultarCargos();
        
    }
    
    
    
     private void FiltrarJTable()
    {
        TableRowSorter sorter = new TableRowSorter(jTableFuncionarios.getModel());
        jTableFuncionarios.setRowSorter(sorter);
        String texto = txtNome.getText();
        
        try
        {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Valor não encontrado", "Aviso - Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     //Metodo utilizado para consultar a cidade e mostra-la na CBOCidades
         private void consultarCidade()
    {
        try
        {
         this.conexao =  Conexao.getConexao();  
         String sql = "Select nome from cidades";
         PreparedStatement ps = this.conexao.prepareStatement(sql);
         ResultSet rsts = ps.executeQuery();
         while (rsts.next())
            cboCidade.addItem(rsts.getString("Nome"));
             
             
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR A CIDADE: " + "/N" + erro);
        }
        
    }
         
      private void consultarCargos()
      {
          try
          {
              this.conexao = Conexao.getConexao();
              String sql = "select cargo from cargos";
              PreparedStatement ps = this.conexao.prepareStatement(sql);
              ResultSet rsts = ps.executeQuery();
              while (rsts.next())
                  cboCargo.addItem(rsts.getString("Cargo"));
          }
          catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR A CARGO: " + "/N" + erro);
        }
      }

 
      
      public void Preencher_JTable()
    
      {
       
      
    
     
        
     jTableFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(50); 
     jTableFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(6).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(7).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(8).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(9).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(10).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(11).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(12).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(13).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(14).setPreferredWidth(100);
     jTableFuncionarios.getColumnModel().getColumn(15).setPreferredWidth(100);
   
      DefaultTableModel modelo = (DefaultTableModel)jTableFuncionarios.getModel();
     modelo.setNumRows(0);
     
     try
     {
         this.conexao = Conexao.getConexao();
         String sql = "select fu.*, ci.nome cidade, ca.cargo cargo from funcionarios fu inner join cidades ci on fu.id_cidade = ci.id inner join cargos ca on fu.id_cargo = ca.id";
         PreparedStatement ps = this.conexao.prepareStatement(sql);
         ResultSet rsts = ps.executeQuery();
         
        while (rsts.next())
             modelo.addRow(new Object[]{rsts.getInt("ID"),
                                      rsts.getString("Nome"),
                                      rsts.getString("cidade"),
                                      rsts.getString("uf"),
                                      rsts.getString("Bairro"),
                                      rsts.getString("Endereco"),
                                      rsts.getInt("Numero"),
                                      rsts.getString("Complemento"),
                                      rsts.getString("RG"),
                                      rsts.getString("CPF"),
                                      rsts.getString("Nascimento"),
                                      rsts.getString("telefone1"),
                                      rsts.getString("telefone2"),
                                      rsts.getString("Email"),    
                                      rsts.getString("Admissao"),
                                      rsts.getString("cargo")});
         
         
     }
      
     catch(SQLException err)
     {
         
         JOptionPane.showMessageDialog(null, "Erro ao preencher JTable " + err);
         
     }
     
    }
      
     
    
  
    
      
 
     
    
    
    
    private void btnEditarAtualizar()
    {
        
        if (!"".equals(txtID.getText())){
            btnGravar.setText("ATUALIZAR");
        }
        
        else if ("".equals(txtNome.getText())){
            btnGravar.setText("GRAVAR");
        }
    }
    
    public void LimparCampos()
    {
        txtID.setText("");
        

        dispose();
        new CadastroFuncionarios().setVisible(true);
        
    }
    
   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        threadBtnOnTime = new org.netbeans.examples.lib.timerbean.Timer();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFuncionarios = new javax.swing.JTable();
        txtID = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        txtDataNasc = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFone1 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        cboCidade = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txtFone2 = new javax.swing.JFormattedTextField();
        txtIDCidade = new javax.swing.JTextField();
        txtUF = new javax.swing.JTextField();
        txtRG = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtIDCargo = new javax.swing.JTextField();
        cboCargo = new javax.swing.JComboBox();
        txtDataAdmissao = new javax.swing.JFormattedTextField();

        threadBtnOnTime.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                threadBtnOnTimeOnTime(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Funcionários");
        setResizable(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel14.setText("Email:");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setText("Código:");

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTableFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Cidade", "UF", "Bairro", "Endereco", "N", "Complemento", "RG", "CPF", "Nascimento", "Fone 1 (Res)", "Fone 2 (Cel)", "Email", "Admissão", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFuncionarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFuncionarios);
        if (jTableFuncionarios.getColumnModel().getColumnCount() > 0) {
            jTableFuncionarios.getColumnModel().getColumn(0).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(1).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(2).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(3).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(4).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(5).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(6).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(7).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(8).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(9).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(10).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(11).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(12).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(13).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(14).setResizable(false);
            jTableFuncionarios.getColumnModel().getColumn(15).setResizable(false);
        }

        txtID.setToolTipText("");
        txtID.setEnabled(false);
        txtID.setNextFocusableComponent(txtNome);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDKeyPressed(evt);
            }
        });

        txtComplemento.setToolTipText("");
        txtComplemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoActionPerformed(evt);
            }
        });
        txtComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtComplementoKeyPressed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagems/Excluir 22 x 22.png"))); // NOI18N
        btnExcluir.setText("EXCLUIR");
        btnExcluir.setToolTipText("Exclui o registro selecionado");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        try {
            txtDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataNasc.setNextFocusableComponent(txtDataAdmissao);
        txtDataNasc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDataNascMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel5.setText("Bairro:");

        try {
            txtFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel6.setText("Endereço:");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.setNextFocusableComponent(txtDataNasc);
        txtCPF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCPFMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel4.setText("Cidade:");

        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagems/Novo 22 x 22.png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.setToolTipText("Cria um novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnGravar.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagems/Gravar 22 x 22.png"))); // NOI18N
        btnGravar.setText("GRAVAR");
        btnGravar.setToolTipText("Grava o registro no banco de dados");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        txtEmail.setToolTipText("");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel8.setText("Complemento:");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel7.setText("Nº:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel10.setText("CPF:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel9.setText("RG:");

        txtBairro.setToolTipText("");
        txtBairro.setNextFocusableComponent(txtNumero);
        txtBairro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBairroMouseClicked(evt);
            }
        });
        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });
        txtBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBairroKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel11.setText("Data Nasc:");

        txtEndereco.setToolTipText("");
        txtEndereco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEnderecoMouseClicked(evt);
            }
        });
        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });
        txtEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEnderecoKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel12.setText("Fone(Res):");

        txtNome.setToolTipText("");
        txtNome.setNextFocusableComponent(txtRG);
        txtNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeMouseClicked(evt);
            }
        });
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        txtNumero.setToolTipText("");
        txtNumero.setNextFocusableComponent(txtNumero);
        txtNumero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNumeroMouseClicked(evt);
            }
        });
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroKeyPressed(evt);
            }
        });

        cboCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCidadeActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel13.setText("Fone:(Cel):");

        try {
            txtFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtIDCidade.setToolTipText("");
        txtIDCidade.setEnabled(false);
        txtIDCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDCidadeActionPerformed(evt);
            }
        });
        txtIDCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDCidadeKeyPressed(evt);
            }
        });

        txtUF.setToolTipText("");
        txtUF.setEnabled(false);
        txtUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUFActionPerformed(evt);
            }
        });
        txtUF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUFKeyPressed(evt);
            }
        });

        txtRG.setToolTipText("");
        txtRG.setNextFocusableComponent(txtCPF);
        txtRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRGActionPerformed(evt);
            }
        });
        txtRG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRGKeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel16.setText("Admissão:");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel17.setText("Cargo:");

        txtIDCargo.setToolTipText("");
        txtIDCargo.setEnabled(false);
        txtIDCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDCargoActionPerformed(evt);
            }
        });
        txtIDCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDCargoKeyPressed(evt);
            }
        });

        cboCargo.setNextFocusableComponent(txtEndereco);
        cboCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCargoActionPerformed(evt);
            }
        });

        try {
            txtDataAdmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataAdmissao.setNextFocusableComponent(cboCargo);
        txtDataAdmissao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDataAdmissaoMouseClicked(evt);
            }
        });
        txtDataAdmissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataAdmissaoActionPerformed(evt);
            }
        });

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
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(243, 243, 243))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel11)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel16)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtDataAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel17)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtIDCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(6, 6, 6))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFone2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIDCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 4, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGravar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDataAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIDCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIDCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtFone2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(5, 5, 5)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(767, 413));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionariosMouseClicked
        // TODO add your handling code here:
        
        
         if(evt.getClickCount() == 2)
        {
            
                Point p = evt.getPoint();
                int row = jTableFuncionarios.rowAtPoint(p);
                int col = jTableFuncionarios.columnAtPoint(p);
                
                txtID.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 0)));
                txtNome.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 1)));
                cboCidade.setSelectedItem(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 2)));
                txtUF.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 3)));
                txtBairro.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 4)));
                txtEndereco.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 5)));
                txtNumero.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 6)));
                txtComplemento.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 7)));
                txtRG.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 8)));
                txtCPF.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 9)));
                txtDataNasc.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 10)));
                txtFone1.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 11)));
                txtFone2.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 12)));
                txtEmail.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 13)));
                txtDataAdmissao.setText(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 14)));
                
                cboCargo.setSelectedItem(String.valueOf(jTableFuncionarios.getModel().getValueAt(row, 15)));

                
                
                            
                this.conexao = Conexao.getConexao();
                try
                {
                    String sql = "select nome from cidades where id =?";
                    String sql2 = "select cargo from cargos where id=?";
                    PreparedStatement ps = this.conexao.prepareStatement(sql);
                    PreparedStatement ps2 = this.conexao.prepareStatement(sql2);
                    ps.setInt(1, Integer.parseInt(txtIDCidade.getText()));
                    ps2.setInt(1, Integer.parseInt(txtIDCargo.getText()));
                    ResultSet rsts = ps.executeQuery();
                    ResultSet rsts2 = ps2.executeQuery();
                   
                    rsts.next();
                    
                    cboCidade.setSelectedItem(rsts.getString("nome"));
                    
                    rsts2.next();
                    cboCargo.setSelectedItem(rsts2.getString("cargo"));
                    
                    
                }
                catch(SQLException erro)
                {
                    JOptionPane.showMessageDialog(null, erro);
                }
                
            }
         

      
          
}//GEN-LAST:event_jTableFuncionariosMouseClicked

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtIDActionPerformed

    private void txtIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_txtIDKeyPressed

    private void txtComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtComplementoActionPerformed

    private void txtComplementoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplementoKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_txtComplementoKeyPressed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

         if ("".equals(txtID.getText()))
        {
            JOptionPane.showMessageDialog(null, "Erro, nenhum registro selecionado");
        }
        
        else if (!"".equals(txtID.getText()))
        {
            
           int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse registro?", "Excluir", JOptionPane.YES_NO_OPTION);
           if (resp == JOptionPane.YES_OPTION) 
           {
        
        try{
            
            Funcionarios funcionarios = new Funcionarios();
            funcionarios.setId(Integer.parseInt(txtID.getText()));
            
           
            FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
            funcionariosDAO.deletarDados(funcionarios);
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso !");
            Preencher_JTable();
         
            LimparCampos();
            
        }catch(SQLException err){
            
            JOptionPane.showMessageDialog(null, "Erro de SQL " + err);
            
        }catch(Exception err){
            
            JOptionPane.showMessageDialog(null, "Erro de Exception " + err);
            
        }// Fim Try Catch
        
       }//Fim Se
        }
         
         
       
        
        
        
      
        
        
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

           if(!"".equals(txtNome.getText()) || !"".equals(txtNumero.getText()) || !"".equals(txtRG.getText()) ||
              !"".equals(txtCPF.getText()) || !"".equals(txtDataNasc.getText()))
        {
            if(JOptionPane.showConfirmDialog(null, "Deseja realmente criar um novo?", "Novo Funcionario", 2, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                LimparCampos();
            } 
            
        }
    
        
        
        
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed

         if("".equals(txtNome.getText())  || "".equals(txtRG.getText()) || "".equals(txtNumero.getText()) ||
            "".equals(txtCPF.getText()) || "".equals(txtDataNasc.getText()) || "".equals(txtBairro.getText ()) || 
            "".equals(txtEndereco.getText()) || "".equals(txtDataAdmissao.getText().toString()))
         {
         
            btnGravar.setEnabled(false);
            jTableFuncionarios.setEnabled(false);
            
            if("".equals(txtNome.getText()))
            {
                txtNome.setText("Campo não preenchido");
                txtNome.setForeground(Color.RED);
            }
            else if("Campo não preenchido".equals(txtNome.getText()))
            {
                CheckRegistro = "Campo não Preenchido não é um registro valido";               
            }
            if("".equals(txtNumero.getText()))
            {
                txtNumero.setText("Preencher");
                txtNumero.setForeground(Color.RED);
            }
            else if("Campo não preenchido".equals(txtNumero.getText()))
            {
                CheckRegistro = "Campo não Preenchido não é um registro valido";               
            }
            
            
            if("".equals(txtBairro.getText()))
            {
                txtBairro.setText("Campo não preenchido");
                txtBairro.setForeground(Color.RED);
            }
            else if("Campo não preenchido".equals(txtBairro.getText()))
            {
                CheckRegistro = "Campo não Preenchido não é um registro valido";               
            }
            if("".equals(txtEndereco.getText()))
            {
                txtEndereco.setText("Campo não preenchido");
                txtEndereco.setForeground(Color.RED);
            }
            else if("Campo não preenchido".equals(txtEndereco.getText()))
            {
                CheckRegistro = "Campo não Preenchido não é um registro valido";               
            }
           
           
            else if("Campo não preenchido".equals(txtRG.getText()))
            {
                CheckRegistro = "Campo não Preenchido não é um registro valido";               
            }
            if("   .   .   -  ".equals(txtCPF.getText()))
            try 
            {
             txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("Campo não preenchido")));
             txtCPF.setForeground(Color.RED);
            } 
            catch (java.text.ParseException ex)
            {
             ex.printStackTrace();
            }
            else if("Campo não preenchido".equals(txtCPF.getText()))
            {
                CheckRegistro = "Campo não Preenchido não é um registro valido";               
            }
            if("  -  -    ".equals(txtDataNasc.getText()))
            try 
            {
             txtDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("Campo não preenchido")));
             txtDataNasc.setForeground(Color.RED);
            } 
            catch (java.text.ParseException ex)
            {
             ex.printStackTrace();
            }
            else if("Campo não preenchido".equals(txtDataNasc.getText()))
            {
                CheckRegistro = "Campo não Preenchido não é um registro valido";               
            }
           
            if(!"".equals(CheckRegistro))
            {
                JOptionPane.showMessageDialog(null, CheckRegistro );
            }
             
            
        }

        else 
        {
        
        try 
        {
        
            Funcionarios funcionarios = new Funcionarios();
            
            funcionarios.setNome(txtNome.getText());
            funcionarios.setId_cidade(Integer.parseInt(txtIDCidade.getText()));
            funcionarios.setUf(txtUF.getText());
            funcionarios.setBairro(txtBairro.getText());
            funcionarios.setEndereco(txtEndereco.getText());
            funcionarios.setNumero(Integer.parseInt(txtNumero.getText()));
            funcionarios.setComplemento(txtComplemento.getText());
            funcionarios.setRg(txtRG.getText());
            funcionarios.setCpf(txtCPF.getText());
            funcionarios.setNascimento(txtDataNasc.getText());
            funcionarios.setTelefone1(txtFone1.getText());
            funcionarios.setTelefone2(txtFone2.getText());
            funcionarios.setEmail(txtEmail.getText());
            funcionarios.setAdmissao(txtDataAdmissao.getText());
            funcionarios.setId_cargo(Integer.parseInt(txtIDCargo.getText()));

            
            FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
            if("".equals(String.valueOf(txtID.getText())))
            {
                funcionariosDAO.insereDados(funcionarios);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                jTableFuncionarios.setVisible(true);
                
                
            }
                    
            
            else
            {
               funcionarios.setId(Integer.parseInt(txtID.getText()));
               funcionariosDAO.atualizaDados(funcionarios);
               JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
              
            }
                
                
                
            //Preenche JTable
                    Preencher_JTable();
                    
                    
                    
                    //Limpa os campos
                    LimparCampos();
                    
                     }
        catch(SQLException err)
        {
                    
                    JOptionPane.showMessageDialog(null, "Erro ao gravar " + err);
                    
        }catch(Exception err)
        {
                    JOptionPane.showMessageDialog(null, "Erro de Exception " + err);
                    
        }//Fim Try Cacth 
            
        }
      
         
        
         
        
        
    }//GEN-LAST:event_btnGravarActionPerformed

      
    
    
    
    
    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_txtEmailKeyPressed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtBairroActionPerformed

    private void txtBairroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBairroKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_txtBairroKeyPressed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnderecoKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_txtEnderecoKeyPressed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed

        
        FiltrarJTable();
        
}//GEN-LAST:event_txtNomeKeyPressed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtNumeroActionPerformed

    private void txtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_txtNumeroKeyPressed

    private void cboCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCidadeActionPerformed
        // TODO add your handling code here:
        try
        {
            this.conexao = Conexao.getConexao();
            String sql = "Select id, uf from cidades where nome = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, String.valueOf(cboCidade.getSelectedItem()));
            ResultSet rsts = ps.executeQuery();
            rsts.next();
 
            txtIDCidade.setText(rsts.getString("id"));
            txtUF.setText(rsts.getString("uf"));
            
            
        }
        catch (SQLException ERRO)
        {
          
        }
      
}//GEN-LAST:event_cboCidadeActionPerformed

    private void cboCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCargoActionPerformed
        // TODO add your handling code here:
         try
        {
            this.conexao = Conexao.getConexao();
            String sql = "Select id from cargos where cargo = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, String.valueOf(cboCargo.getSelectedItem()));
            ResultSet rsts = ps.executeQuery();
            rsts.next();
 
            txtIDCargo.setText(rsts.getString("id"));
            
            
            
        }
        catch (SQLException ERRO)
        {
          
        }
    }//GEN-LAST:event_cboCargoActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void threadBtnOnTimeOnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threadBtnOnTimeOnTime
        // TODO add your handling code here:
        btnEditarAtualizar();
    }//GEN-LAST:event_threadBtnOnTimeOnTime

    private void txtIDCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCidadeActionPerformed

    private void txtIDCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDCidadeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCidadeKeyPressed

    private void txtIDCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCargoActionPerformed

    private void txtIDCargoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDCargoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCargoKeyPressed

    private void txtUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUFActionPerformed

    private void txtUFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUFKeyPressed

    private void txtNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeMouseClicked
        // TODO add your handling code here:
        if ("Campo não preenchido".equals(txtNome.getText()))
        {
            txtNome.setText("");
            txtNome.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            
        }
    }//GEN-LAST:event_txtNomeMouseClicked

    private void txtBairroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBairroMouseClicked
        // TODO add your handling code here:
        if ("Campo não preenchido".equals(txtBairro.getText()))
        {
            txtBairro.setText("");
            txtBairro.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            
        }
    }//GEN-LAST:event_txtBairroMouseClicked

    private void txtEnderecoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEnderecoMouseClicked
        // TODO add your handling code here:
        if ("Campo não preenchido".equals(txtEndereco.getText()))
        {
            txtEndereco.setText("");
            txtEndereco.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            
        }
    }//GEN-LAST:event_txtEnderecoMouseClicked

    private void txtCPFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCPFMouseClicked
        // TODO add your handling code here:
        try 
            {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
            txtCPF.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            } 
            catch (java.text.ParseException ex)
            {
             ex.printStackTrace();
            }
    }//GEN-LAST:event_txtCPFMouseClicked

    private void txtDataNascMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDataNascMouseClicked
        // TODO add your handling code here:
        try 
            {
            txtDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
            txtDataNasc.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            } 
            catch (java.text.ParseException ex)
            {
             ex.printStackTrace();
            }
    }//GEN-LAST:event_txtDataNascMouseClicked

    private void txtNumeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNumeroMouseClicked
        // TODO add your handling code here:
        if ("Preencher".equals(txtNumero.getText()))
        {
            txtNumero.setText("");
            txtNumero.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            
        }
        
    }//GEN-LAST:event_txtNumeroMouseClicked

    private void txtRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRGActionPerformed

    private void txtRGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRGKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRGKeyPressed

    private void txtDataAdmissaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDataAdmissaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataAdmissaoMouseClicked

    private void txtDataAdmissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataAdmissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataAdmissaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CadastroFuncionarios().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox cboCargo;
    private javax.swing.JComboBox cboCidade;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFuncionarios;
    private org.netbeans.examples.lib.timerbean.Timer threadBtnOnTime;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JFormattedTextField txtDataAdmissao;
    private javax.swing.JFormattedTextField txtDataNasc;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JFormattedTextField txtFone1;
    private javax.swing.JFormattedTextField txtFone2;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDCargo;
    private javax.swing.JTextField txtIDCidade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRG;
    private javax.swing.JTextField txtUF;
    // End of variables declaration//GEN-END:variables
}


