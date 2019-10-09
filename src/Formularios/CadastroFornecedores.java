/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CadastroFornecedores.java
 *
 * Created on 10/03/2014, 19:26:34
 */
package Formularios;

import Banco.Conexao;
import Controle.FornecedoresDAO;
import java.awt.Color;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Fornecedores;
/**
 *
 * @author Formigao
 */
public class CadastroFornecedores extends javax.swing.JFrame {
    
    Connection conexao;
    
    ResultSet rst;
    
    String CheckRegistro = "";
  

    /** Creates new form CadastroFornecedores */
    public CadastroFornecedores() 
    {
        
        initComponents();
        //Inicilização dos Metodos e do Timer 
        consultarCidade();
        threadBtnOnTime.start(); //Metodo btnEditarAtualizar é iniciliazado dentro dele.
        Preencher_JTable();
        //Faz o menu principal ficar de tela cheia e adiciona Background
        //this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        
    }

    
    //Metodo que detecta automaticamente se é um novo cadastro ou uma atualização de um antigo registro
    private void btnEditarAtualizar()
    {
        
        if(!"".equals(txtID.getText()))
        {
            btnGravar.setText("ATUALIZAR");
        }
        
        else if("".equals(txtRazaoSocial.getText()))
        {
            btnGravar.setText("GRAVAR");
        }
        
    }
    
    //Metodo que limpa todos os campos do formulario 
    public void limparCampos()
    {

        dispose();
        new CadastroFornecedores().setVisible(true);
        
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
    
   
    
    private void excluir()
    {
         
        try {
            Fornecedores fornecedores = new Fornecedores();
            fornecedores.setId(Integer.parseInt(txtID.getText()));
            FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
            fornecedoresDAO.deletarDados(fornecedores);
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso !");
            Preencher_JTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir este fonecedor !!! \n"
             + "Existem produtos relacionados a ele, impedindo a exclusão", "Erro", JOptionPane.ERROR_MESSAGE);
        }
         
     }
    
    
    
    
    //Metodo que Preenche a JTABLE
    public void Preencher_JTable()
    {
        
        jTableFornecedores.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableFornecedores.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTableFornecedores.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTableFornecedores.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTableFornecedores.getColumnModel().getColumn(4).setPreferredWidth(30);
        jTableFornecedores.getColumnModel().getColumn(5).setPreferredWidth(150);
        jTableFornecedores.getColumnModel().getColumn(6).setPreferredWidth(150);
        jTableFornecedores.getColumnModel().getColumn(7).setPreferredWidth(110);
        jTableFornecedores.getColumnModel().getColumn(8).setPreferredWidth(100);
        jTableFornecedores.getColumnModel().getColumn(9).setPreferredWidth(100);
        jTableFornecedores.getColumnModel().getColumn(10).setPreferredWidth(100);
        jTableFornecedores.getColumnModel().getColumn(11).setPreferredWidth(150);
        jTableFornecedores.getColumnModel().getColumn(12).setPreferredWidth(200);
             
        DefaultTableModel modelo = (DefaultTableModel)jTableFornecedores.getModel();
        modelo.setNumRows(0);
        
        try
        {
            this.conexao = Conexao.getConexao();
            String sql = "select fo.*, ci.nome cidade from fornecedores fo inner join cidades ci on fo.id_cidade = ci.id";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet rsts = ps.executeQuery();
            
            while (rsts.next())
                modelo.addRow(new Object[]{rsts.getInt("ID"),
                                           rsts.getString("Razao_Social"),
                                           rsts.getString("Fantasia"),
                                           rsts.getString("cidade"),
                                           rsts.getString("uf"),
                                           rsts.getString("bairro"),
                                           rsts.getString("endereco"),
                                           rsts.getString("CNPJ"),
                                           rsts.getString("IE"),
                                           rsts.getString("Fone1"),
                                           rsts.getString("Fone2"),
                                           rsts.getString("Contato"),
                                           rsts.getString("Email")});
            
        }
        catch(SQLException err)
        {
         
         JOptionPane.showMessageDialog(null, "Erro ao preencher JTable " + err);
        
        }
        catch(Exception err)
        {
            
         JOptionPane.showMessageDialog(null, "Erro de Exception " + err);
         
        }//Fim Try Cacth
    }
    
    //Metodo para FiltrarJTable via o campo Razão Social
    private void filtrarJTable()
    {
        TableRowSorter sorter = new TableRowSorter(jTableFornecedores.getModel());
        jTableFornecedores.setRowSorter(sorter);
        String texto = txtRazaoSocial.getText();
        
        try
        {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Valor não encontrado", "Aviso - Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        threadBtnOnTime = new org.netbeans.examples.lib.timerbean.Timer();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtRazaoSocial = new javax.swing.JTextField();
        txtFantasia = new javax.swing.JTextField();
        txtIDCidade = new javax.swing.JTextField();
        txtUF = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtContato = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFornecedores = new javax.swing.JTable();
        cboCidade = new javax.swing.JComboBox();
        txtFone1 = new javax.swing.JFormattedTextField();
        txtFone2 = new javax.swing.JFormattedTextField();
        txtCNPJ = new javax.swing.JFormattedTextField();
        txtIE = new javax.swing.JFormattedTextField();

        threadBtnOnTime.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                threadBtnOnTimeOnTime(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fornecedores");
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setText("Código:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel4.setText("Razão Social:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel5.setText("Fantasia:");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel7.setText("Bairro:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel8.setText("Endereço:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel9.setText("Cidade:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel11.setText("CNPJ:");

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel12.setText("IE:");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel13.setText("Fone(Res):");

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel14.setText("Fone(Cel):");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel15.setText("Contato:");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel16.setText("Email:");

        txtID.setToolTipText("");
        txtID.setEnabled(false);
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

        txtRazaoSocial.setToolTipText("");
        txtRazaoSocial.setNextFocusableComponent(txtFantasia);
        txtRazaoSocial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRazaoSocialMouseClicked(evt);
            }
        });
        txtRazaoSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazaoSocialActionPerformed(evt);
            }
        });
        txtRazaoSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRazaoSocialKeyPressed(evt);
            }
        });

        txtFantasia.setToolTipText("");
        txtFantasia.setNextFocusableComponent(txtCNPJ);
        txtFantasia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFantasiaMouseClicked(evt);
            }
        });
        txtFantasia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFantasiaActionPerformed(evt);
            }
        });
        txtFantasia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFantasiaKeyPressed(evt);
            }
        });

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

        txtBairro.setToolTipText("");
        txtBairro.setNextFocusableComponent(cboCidade);
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

        txtEndereco.setToolTipText("");
        txtEndereco.setNextFocusableComponent(txtBairro);
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

        txtContato.setToolTipText("");
        txtContato.setNextFocusableComponent(txtFone1);
        txtContato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtContatoMouseClicked(evt);
            }
        });
        txtContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContatoActionPerformed(evt);
            }
        });
        txtContato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContatoKeyPressed(evt);
            }
        });

        txtEmail.setToolTipText("");
        txtEmail.setNextFocusableComponent(btnGravar);
        txtEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEmailMouseClicked(evt);
            }
        });
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

        btnGravar.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagems/Gravar 22 x 22.png"))); // NOI18N
        btnGravar.setText("GRAVAR");
        btnGravar.setToolTipText("Grava o registro no banco de dados");
        btnGravar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGravarMouseClicked(evt);
            }
        });
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagems/Novo 22 x 22.png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.setToolTipText("Cria um novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
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

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTableFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Razão Social", "Fantasia", "Cidade", "UF", "Bairro", "Endereço", "CNPJ", "IE", "Fone1", "Fone 2", "Contato", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFornecedores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFornecedores);
        if (jTableFornecedores.getColumnModel().getColumnCount() > 0) {
            jTableFornecedores.getColumnModel().getColumn(0).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(1).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(2).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(3).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(4).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(5).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(6).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(7).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(8).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(9).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(10).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(11).setResizable(false);
            jTableFornecedores.getColumnModel().getColumn(12).setResizable(false);
        }

        cboCidade.setNextFocusableComponent(txtContato);
        cboCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCidadeActionPerformed(evt);
            }
        });

        try {
            txtFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFone1.setNextFocusableComponent(txtFone2);

        try {
            txtFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFone2.setNextFocusableComponent(txtEmail);

        try {
            txtCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCNPJ.setNextFocusableComponent(txtIE);
        txtCNPJ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCNPJMouseClicked(evt);
            }
        });

        try {
            txtIE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtIE.setNextFocusableComponent(txtEndereco);
        txtIE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIEMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNovo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(4, 4, 4)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(5, 5, 5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIE, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtRazaoSocial))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFone2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtIDCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(8, 8, 8))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txtFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEndereco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12))
                                    .addComponent(txtIE, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtIDCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(cboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addComponent(jLabel15)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFone2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(txtFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGravar)
                        .addGap(6, 6, 6)
                        .addComponent(btnExcluir)
                        .addGap(41, 41, 41))))
        );

        setSize(new java.awt.Dimension(832, 417));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtIDActionPerformed

    private void txtIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_txtIDKeyPressed

    private void txtRazaoSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaoSocialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaoSocialActionPerformed

    private void txtRazaoSocialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaoSocialKeyPressed
        // TODO add your handling code here:
        filtrarJTable();
    }//GEN-LAST:event_txtRazaoSocialKeyPressed

    private void txtFantasiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFantasiaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtFantasiaActionPerformed

    private void txtFantasiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFantasiaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFantasiaKeyPressed

    private void txtIDCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCidadeActionPerformed

    private void txtIDCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDCidadeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCidadeKeyPressed

    private void txtUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUFActionPerformed

    private void txtUFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUFKeyPressed

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

    private void txtContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContatoActionPerformed

    private void txtContatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContatoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContatoKeyPressed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyPressed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed

               
        
        if("".equals(txtRazaoSocial.getText()) || "".equals(txtFantasia.getText()) ||
           "".equals(txtIDCidade.getText()) || "".equals(cboCidade.getSelectedItem()) || 
           "".equals(txtUF.getText()) || "".equals(txtBairro.getText()) || "".equals(txtEndereco.getText()) ||  
           "".equals(txtCNPJ.getText())  || "".equals(txtFone1.getText()) || 
           "".equals(txtFone2.getText()) || "".equals(txtContato.getText()))
            
        {
           
            btnGravar.setEnabled(false);
            jTableFornecedores.setVisible(false);
           
            
        if("".equals(txtRazaoSocial.getText()))
        {
            txtRazaoSocial.setText("Campo não preenchido");
            txtRazaoSocial.setForeground(Color.RED);
            
            
        }
        else if("Campo não preenchido".equals(txtRazaoSocial.getText()))
        {
              
             CheckRegistro = "Campo não Preenchido não é um registro valido";               
        }
        if("".equals(txtFantasia.getText()))
        {
            txtFantasia.setText("Campo não preenchido");
            txtFantasia.setForeground(Color.red);
        }
        else if("Campo não preenchido".equals(txtFantasia.getText()))
        {
            
             CheckRegistro = "Campo não Preenchido não é um registro valido";                 
        }
        if("".equals(txtBairro.getText()))
        {
            txtBairro.setText("Campo não preenchido");
            txtBairro.setForeground(Color.red);
        }
        else if("Campo não preenchido".equals(txtBairro.getText()))
        {
           
           CheckRegistro = "Campo não Preenchido não é um registro valido";                  
        }
        if("".equals(txtEndereco.getText()))
        {
            txtEndereco.setText("Campo não preenchido");
            txtEndereco.setForeground(Color.red);
        }
        else if("Campo não preenchido".equals(txtEndereco.getText()))
        {
            
            CheckRegistro = "Campo não Preenchido não é um registro valido";                 
        }
        if("  .   .   /    -  ".equals(txtCNPJ.getText()))
        {

            try 
            {
             txtCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("Campo não preenchido")));
             txtCNPJ.setForeground(Color.RED);
            } 
            catch (java.text.ParseException ex)
            {
             ex.printStackTrace();
            }
           
        }
        else if("Campo não preenchido".equals(txtCNPJ.getText()))
        {
             
             CheckRegistro = "Campo não Preenchido não é um registro valido";             
        }
        
        if("".equals(txtContato.getText()))
        {
            txtContato.setText("Campo não preenchido");
            txtContato.setForeground(Color.red);

        }
      
        else if("Campo não preenchido".equals(txtContato.getText()))
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
                Fornecedores fornecedores = new Fornecedores();
                
                fornecedores.setRazao_Social(txtRazaoSocial.getText());
                fornecedores.setFantasia(txtFantasia.getText());
                fornecedores.setID_Cidade(Integer.parseInt(txtIDCidade.getText()));
                fornecedores.setCidade(String.valueOf(cboCidade.getSelectedItem()));
                fornecedores.setUF(txtUF.getText());
                fornecedores.setBairro(txtBairro.getText());
                fornecedores.setEndereco(txtEndereco.getText());
                fornecedores.setCNPJ(txtCNPJ.getText());
                fornecedores.setIE(txtIE.getText());
                fornecedores.setFone1(txtFone1.getText());
                fornecedores.setFone2(txtFone2.getText());
                fornecedores.setContato(txtContato.getText());
                fornecedores.setEmail(txtEmail.getText());
                
                FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
                if("".equals(String.valueOf(txtID.getText())))
                {
                    fornecedoresDAO.insereDados(fornecedores);
                    JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                    jTableFornecedores.setVisible(true);
                    
                }
                else
                {
                    fornecedores.setId(Integer.parseInt(txtID.getText()));
                    fornecedoresDAO.atualizaDados(fornecedores);
                    JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
                }
                
                Preencher_JTable();
                limparCampos();
                
                
            }
            catch(SQLException erro)
            {
                JOptionPane.showMessageDialog(null, "Erro ao gravar " + erro);
                    
            }
            catch(Exception err)
            {
                    JOptionPane.showMessageDialog(null, "Erro de Exception " + err);
                    
            }//Fim Try Cacth 
            
        }
       
       
        

        
        
        
        
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        
        /* Verifica se algum campo está preenchido = sim
         * Pergunta se deseja realmente criar um novo  = sim
         * Limpa todos os campos
         */
        if(!"".equals(txtRazaoSocial.getText()) || !"".equals(txtFantasia.getText()) ||
           !"".equals(txtIDCidade.getText()) || !"".equals(cboCidade.getSelectedItem()) || 
           !"".equals(txtUF.getText()) || !"".equals(txtBairro.getText()) || !"".equals(txtEndereco.getText()) ||  
           !"".equals(txtCNPJ.getText()) || !"".equals(txtIE.getText()) || !"".equals(txtFone1.getText()) || 
           !"".equals(txtFone2.getText()) || !"".equals(txtContato.getText()) || !"".equals(txtEmail.getText()))
        {
            if(JOptionPane.showConfirmDialog(null, "Deseja realmente criar um novo?", "Novo Fornecedor", 2, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                limparCampos();
            } 
        }
       
        
        
        
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        if("".equals(txtID.getText()))
        {
            JOptionPane.showMessageDialog(null, "Erro, nenhum registro selecionado");
        }
        else if (!"".equals(txtID.getText()))
        {
            
           int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse registro?", "Excluir", JOptionPane.YES_NO_OPTION);
           if (resp == JOptionPane.YES_OPTION) 
           {
               excluir();
           }
               
               
       }
           
        
        
        
        
        
        
        
    }//GEN-LAST:event_btnExcluirActionPerformed

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

    private void threadBtnOnTimeOnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threadBtnOnTimeOnTime
        // TODO add your handling code here:
        
        btnEditarAtualizar();
        
    }//GEN-LAST:event_threadBtnOnTimeOnTime

    private void txtRazaoSocialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRazaoSocialMouseClicked
        // TODO add your handling code here:
        if ("Campo não preenchido".equals(txtRazaoSocial.getText()))
        {
            txtRazaoSocial.setText("");
            txtRazaoSocial.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
        }
    }//GEN-LAST:event_txtRazaoSocialMouseClicked

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

    private void txtContatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtContatoMouseClicked
        // TODO add your handling code here:
        if ("Campo não preenchido".equals(txtContato.getText()))
        {
            txtContato.setText("");
            txtContato.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            
        }
    }//GEN-LAST:event_txtContatoMouseClicked

    private void txtFantasiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFantasiaMouseClicked
        // TODO add your handling code here:
        if ("Campo não preenchido".equals(txtFantasia.getText()))
        {
            txtFantasia.setText("");
            txtFantasia.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
        }
    }//GEN-LAST:event_txtFantasiaMouseClicked

    private void btnGravarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGravarMouseClicked
        // TODO add your handling code here:
        

    }//GEN-LAST:event_btnGravarMouseClicked

    private void txtEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_txtEmailMouseClicked

    private void txtCNPJMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCNPJMouseClicked
        // TODO add your handling code here:
                // TODO add your handling code here:
        try 
            {
            txtCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
            txtCNPJ.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            } 
            catch (java.text.ParseException ex)
            {
             ex.printStackTrace();
            }
    }//GEN-LAST:event_txtCNPJMouseClicked

    private void txtIEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIEMouseClicked
        // TODO add your handling code here:
        if ("Campo não preenchido".equals(txtIE.getText()))
        {
            txtIE.setText("");
            txtIE.setForeground(Color.BLACK);
            btnGravar.setEnabled(true);
            
        }
    }//GEN-LAST:event_txtIEMouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTableFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFornecedoresMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2) {
            Point p = evt.getPoint();
            int row = jTableFornecedores.rowAtPoint(p);
            int col = jTableFornecedores.columnAtPoint(p);
            
            txtID.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 0)));
            txtRazaoSocial.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 1)));
            txtFantasia.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 2)));
            cboCidade.setSelectedItem(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 3)));
            txtUF.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 4)));
            txtBairro.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 5)));
            txtEndereco.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 6)));
            txtCNPJ.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 7)));
            txtIE.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 8)));
            txtFone1.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 9)));
            txtFone2.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 10)));
            txtContato.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 11)));
            txtEmail.setText(String.valueOf(jTableFornecedores.getModel().getValueAt(row, 12)));
            
            this.conexao = Conexao.getConexao();
            try {
                String sql = "select nome from cidades where id =?";
                PreparedStatement ps = this.conexao.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(txtIDCidade.getText()));
                ResultSet rsts = ps.executeQuery();
                
                rsts.next();
                
                cboCidade.setSelectedItem(rsts.getString("nome"));
                
            } catch(SQLException erro) {
                JOptionPane.showMessageDialog(null, erro);
            }
        }
}//GEN-LAST:event_jTableFornecedoresMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CadastroFornecedores().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox cboCidade;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFornecedores;
    private org.netbeans.examples.lib.timerbean.Timer threadBtnOnTime;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCNPJ;
    private javax.swing.JTextField txtContato;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtFantasia;
    private javax.swing.JFormattedTextField txtFone1;
    private javax.swing.JFormattedTextField txtFone2;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDCidade;
    private javax.swing.JFormattedTextField txtIE;
    private javax.swing.JTextField txtRazaoSocial;
    private javax.swing.JTextField txtUF;
    // End of variables declaration//GEN-END:variables
}
