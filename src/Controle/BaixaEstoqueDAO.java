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
import modelo.BaixaEstoque;

/**
 *
 * @author Formigao
 */
public class BaixaEstoqueDAO {
    
    Connection conexao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    
    
    public void removerEstoque(BaixaEstoque baixaestoque)
    {
        try{
            this.conexao = Conexao.getConexao();
            
            String sql = "Update produtos set estoque = estoque - ? where cod_barra = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, baixaestoque.getQtdeEstoque());
            ps.setString(2, baixaestoque.getCod_barra());
            ps.execute();      
            
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Erro ao remover estoque 'removerEstoque' " + err);
 
        }

    }
    
    public void baixaEstoque(BaixaEstoque baixaEstoque){
        
        Calendar c = Calendar.getInstance();
       
        
        try{
            
            this.conexao = Conexao.getConexao();
            
            String sql = "insert into baixa_estoque (cod_barra, id_funcionario, descricao, qtde, data) " +
                         "values (?, ?, ?, ?, ?)";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, baixaEstoque.getCod_barra());
            ps.setInt(2, baixaEstoque.getId_funcionario());
            ps.setString(3, baixaEstoque.getDescricao());
            ps.setInt(4, baixaEstoque.getQtdeEstoque());
            ps.setString(5, df.format(c.getTime()));
            ps.execute();
            
        }catch(SQLException err){
            
        }
        
        
    }
    
}
