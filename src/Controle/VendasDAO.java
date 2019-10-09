/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Banco.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import modelo.Vendas;

/**
 *
 * @author Abel
 */
public class VendasDAO {
    
    Connection conexao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    
    public void ConfirmaVendaCab(Vendas vendas){
      try{  
            
        this.conexao = Conexao.getConexao();
        
        String sql = "Insert into Venda_Cab (id_cliente, data, total, status) values(?, ?, ?, ?)";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, vendas.getId_cliente());
        ps.setString(2, df.format(vendas.getDataVenda()));
        ps.setDouble(3, vendas.getTotal());
        ps.setString(4, vendas.getStatusCliente());
        ps.execute();
        
    }catch(SQLException err){
        JOptionPane.showMessageDialog(null, "Erro ao inserir Venda_Cab " + err);
    }
        
    }
    
    public void atualizaVendaCab(Vendas vendas){
        
        try {
            this.conexao = Conexao.getConexao();
            
            String sql = "Update venda_cab set total = ?, status = ? where id = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setDouble(1, vendas.getTotal());
            ps.setString(2, vendas.getStatusVendaCab());
            ps.setInt(3, vendas.getId_venda());
            ps.execute();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no método atualizaVendaCab " + ex);
        }
        
        
        
    }
    
    public void cancelarVendaCab(Vendas vendas){
        
        try{
            
            this.conexao = Conexao.getConexao();
            
            String sql = "Delete from Venda_Cab where id = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, vendas.getId_venda());
            ps.execute();
            
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Erro no VendasDAO, não foi possivel excluir vendaCab " + err);
        }
        
    }
    
    public void addVendaDet(Vendas vendas){
        try{
            
            this.conexao = Conexao.getConexao();
            
            String sql = "Insert into Venda_det (id_venda, cod_barra, qtde, vlr_venda) values(?, ?, ?, ?)";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, vendas.getId_venda());
            ps.setString(2, vendas.getCod_barra());
            ps.setInt(3, vendas.getQtdeEstoque());
            ps.setDouble(4, vendas.getVlr_custo());
            ps.execute();
                    
            
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Erro no VendasDAO, não foi possivel corfirmar VendaDet " + err);
            
        }
    }
    
    
    
    public void cancelaVendaDet(Vendas vendas){
        
      try{  
        this.conexao = Conexao.getConexao();
        
        String sql = "Delete from venda_det where id_venda = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, vendas.getId_venda());
        ps.execute();
      }catch(SQLException err){
          JOptionPane.showMessageDialog(null, "Erro ao cancelar a vendadet 'cancelaVendaDet' " + err);
      }        
    }
    
    public void confirmaFluxoCaixaParcelas(Vendas vendas){
        try{
           
                    Calendar c = Calendar.getInstance();
            
                    String sql = "Insert into fluxocaixa (id_vencom, id_cliente, nparc, vlr_parc, data_venda, data_vencto, operacao, pago) values(?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = this.conexao.prepareStatement(sql);
                    ps.setInt(1, vendas.getId_venda());
                    ps.setInt(2, vendas.getId_cliente());
                    ps.setInt(3, vendas.getNParc());
                    ps.setDouble(4, vendas.getVlr_Parc());
                    ps.setString(5, df.format(c.getTime()));
                    ps.setString(6, df.format(vendas.getDataVencto()));
                    ps.setString(7, "V");
                    ps.setString(8, "N");
                    ps.execute();
                    ps.close();       
                    
                            
                
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, "Erro nas parcela 'confirmaFluxoCaixa' " + err);
            //}catch(ParseException err){
            //    JOptionPane.showMessageDialog(null, "Parse 'contasreceber' " + err);
            }
        
    
    }
    
    public void confirmaFluxoCaixa(Vendas vendas){
        
        try{
           
                    Calendar c = Calendar.getInstance();
            
                    String sql = "Insert into fluxocaixa (id_vencom, id_cliente, vlr_final, data_venda, data_pgto, operacao, pago, tipo_pgto) values(?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = this.conexao.prepareStatement(sql);
                    ps.setInt(1, vendas.getId_venda());
                    ps.setInt(2, vendas.getId_cliente());
                    ps.setDouble(3, vendas.getVlr_final());
                    ps.setString(4, df.format(c.getTime()));
                    ps.setString(5, df.format(c.getTime()));
                    ps.setString(6, "V");
                    ps.setString(7, "S");
                    ps.setString(8, vendas.getTipo_pgto());
                    ps.execute();
                    ps.close();       
                    
                            
                
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, "Erro nas parcela 'confirmaFluxoCaixa' " + err);
            //}catch(ParseException err){
            //    JOptionPane.showMessageDialog(null, "Parse 'contasreceber' " + err);
            }
        
        
        
    }

    
    public void adicionaEstoque(Vendas vendas){
      try{  
        this.conexao = Conexao.getConexao();
        
        String sql = "Update produtos set estoque = estoque + ? where cod_barra = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, vendas.getQtdeEstoque());
        ps.setString(2, vendas.getCod_barra());
        ps.execute();
      }catch(SQLException err){
          JOptionPane.showMessageDialog(null, "Erro ao adicionar estoque 'adicionaEstoque' " + err);
    }
   
    }
    
       
    public void removerEstoque(Vendas vendas){
        try{
            this.conexao = Conexao.getConexao();
            
            String sql = "Update produtos set estoque = estoque - ? where cod_barra = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, vendas.getQtdeEstoque());
            ps.setString(2, vendas.getCod_barra());
            ps.execute();      
            
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Erro ao remover estoque 'removerEstoque' " + err);
 
        }
            

    }   
    
   
    
 
    
}
