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
import modelo.Produtos;


/**
 *
 * @author Formigao
 */
public class ProdutosDAO {
    
    private Connection conexao;
   
     
    // Este método insere os dados na tabela
    public void inserirDados(Produtos produtos) 
    
    {
       
        try{
        
       Calendar c = Calendar.getInstance();
       DateFormat df= new SimpleDateFormat("yyyy-MM-dd"); 
       
       this.conexao = Conexao.getConexao();
        
       PreparedStatement ps;
       String sql = "INSERT into produtos(cod_barra, descricao, id_fornecedor, estoque, estoque_min, valor_custo, valor_venda, data) "
               +    "values(?, ?, ?, ?, ?, ?, ?, ?)";
       ps = this.conexao.prepareStatement(sql);
       ps.setString(1, produtos.getCod_barra());
       ps.setString(2, produtos.getDescricao());
       ps.setInt(3, produtos.getId_fornecedor());
       ps.setInt(4, produtos.getEstoque());
       ps.setInt(5, produtos.getEstoque_min());
       ps.setDouble(6, produtos.getValor_custo());
       ps.setDouble(7, produtos.getValor_venda());
       ps.setString(8, df.format(c.getTime()));
       ps.execute();
       ps.close();
       
        }catch(SQLException err){
            System.out.println("Erro ao cadastrar produtos 'inserirDados' " + err);
        }
                       
    }
    
    public void deletarDados(Produtos produtos) throws SQLException
    {
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
        String sql = "Delete from produtos where id= ?";
        ps= this.conexao.prepareStatement(sql);
        ps.setInt(1, produtos.getId());
        ps.execute();
        ps.close();
    }
    
    public void atualizarDados(Produtos produtos) throws SQLException
    {
        
        this.conexao = Conexao.getConexao();
        String sql = "Update produtos set cod_barra = ?, descricao = ?, id_fornecedor = ?, estoque = ?, "
                   + " estoque_min = ?, valor_custo = ?, valor_venda = ? where id = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setString(1, produtos.getCod_barra());
        ps.setString(2, produtos.getDescricao());
        ps.setInt(3, produtos.getId_fornecedor());
        ps.setInt(4, produtos.getEstoque());
        ps.setInt(5, produtos.getEstoque_min());
        ps.setDouble(6, produtos.getValor_custo());
        ps.setDouble(7, produtos.getValor_venda());
        ps.setInt(8, produtos.getId());
        ps.execute();
        ps.close();
     }
     
    
}

