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
import modelo.Compras;

/**
 *
 * @author Formigao
 */
public class ComprasDAO 
{
    Connection conexao;
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    public void ConfirmaCompraCab(Compras compras)
    {
        try
        {
            
            this.conexao = Conexao.getConexao();
            String sql = "insert into Compra_Cab (id_fornecedor, data, total) values (?, ?, ?) ";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, compras.getId_fornecedor());
            ps.setString(2, df.format(compras.getDatavenda()));
            ps.setDouble(3, compras.getTotal());
            ps.execute();
   
        }
        catch(SQLException err)
        {
        JOptionPane.showMessageDialog(null, "Erro ao inserir Compra_Cab " + err);
        }
    }
    
    public void atualizaCompraCab(Compras compras)
    {    
        try
        {  
            
            this.conexao = Conexao.getConexao();
            String sql = "Update compra_cab set total = ?, status = ? where id = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setDouble(1, compras.getTotal());
            ps.setString(2, compras.getStatusComprasCab());
            ps.setInt(3, compras.getId_compra());
        
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Erro no método atualizaCompraCab " + ex);
        }
     
    }
    
    public void cancelarComprasCab(Compras compras){
        
        try
        {
            
            this.conexao = Conexao.getConexao();
            
            String sql = "Delete from Compra_Cab where id = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, compras.getId_compra());
            ps.execute();
            
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "Erro no VendasDAO, não foi possivel excluir vendaCab " + err);
        }
        
    }
    
        public void addCompraDet(Compras compras){
        try{
            
            this.conexao = Conexao.getConexao();
            
            String sql = "Insert into Compra_det (id_compra, cod_barra, qtde, vlr_unit) values(?, ?, ?, ?)";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, compras.getId_compra());
            ps.setString(2, compras.getCod_barra());
            ps.setInt(3, compras.getQtdeEstoque());
            ps.setDouble(4, compras.getVlr_unit());
            ps.execute();
                    
            
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Erro no ComprasDAO, não foi possivel corfirmar addCompraDet " + err);
            
        }
    }
    
     public void adicionaEstoque(Compras compras)
     {
      try
      {  
        this.conexao = Conexao.getConexao();
        
        String sql = "Update produtos set estoque = estoque + ? where cod_barra = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, compras.getQtdeEstoque());
        ps.setString(2, compras.getCod_barra());
        ps.execute();
      }
      catch(SQLException err)
      {
          JOptionPane.showMessageDialog(null, "Erro ao adicionar estoque 'adicionaEstoque' " + err);
      }
   
    }
     
      public void removerEstoque(Compras compras){
        try{
            this.conexao = Conexao.getConexao();
            
            String sql = "Update produtos set estoque = estoque - ? where cod_barra = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, compras.getQtdeEstoque());
            ps.setString(2, compras.getCod_barra());
            ps.execute();      
            
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Erro ao remover estoque 'removerEstoque' " + err);
 
        }
            

    }   
     
      public void confirmaFluxoCaixaParcelas(Compras compras){
        try{
           
                    Calendar c = Calendar.getInstance();
            
                    String sql = "Insert into fluxocaixa (id_vencom, id_fornecedor, nparc, vlr_parc, data_venda, data_vencto, operacao, pago) values(?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = this.conexao.prepareStatement(sql);
                    ps.setInt(1, compras.getId_compra());
                    ps.setInt(2, compras.getId_fornecedor());
                    ps.setInt(3, compras.getNparc());
                    ps.setDouble(4, compras.getVlr_parc());
                    ps.setString(5, df.format(c.getTime()));
                    ps.setString(6, df.format(compras.getDataVencto()));
                    ps.setString(7, "C");
                    ps.setString(8, "N");
                    ps.execute();
                    ps.close();       
                    
                            
                
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, "Erro nas parcela 'confirmaFluxoCaixa' " + err);
            //}catch(ParseException err){
            //    JOptionPane.showMessageDialog(null, "Parse 'contasreceber' " + err);
            }
        
    
    }
      
       public void confirmaFluxoCaixa(Compras compras){
        
        try{
           
                    Calendar c = Calendar.getInstance();
            
                    String sql = "Insert into fluxocaixa (id_vencom, id_fornecedor, vlr_final, data_venda, data_pgto, operacao, pago, tipo_pgto) values(?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = this.conexao.prepareStatement(sql);
                    ps.setInt(1, compras.getId_compra());
                    ps.setInt(2, compras.getId_fornecedor());
                    ps.setDouble(3, compras.getTotal());
                    ps.setString(4, df.format(c.getTime()));
                    ps.setString(5, df.format(c.getTime()));
                    ps.setString(6, "C");
                    ps.setString(7, "S");
                    ps.setString(8, compras.getTipo_pgto());
                    ps.execute();
                    ps.close();       
                    
                            
                
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, "Erro fluxoCaixa 'confirmaFluxoCaixa' " + err);
            //}catch(ParseException err){
            //    JOptionPane.showMessageDialog(null, "Parse 'contasreceber' " + err);
            }
        
        
        
    }
     
 
    
}
