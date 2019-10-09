/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;


import java.sql.Connection;
import java.sql.SQLException;
import modelo.Clientes;
import Banco.Conexao;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Formigao
 */
public class ClientesDAO {
    
    private Connection conexao;
    
    Calendar c = Calendar.getInstance();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    
    public void InsereDados(Clientes clientes) throws SQLException
    {
        
        
        
        this.conexao = Conexao.getConexao();
         
        PreparedStatement ps;
        
        String sql = "Insert into clientes (nome, id_cidade, uf, bairro, endereco, numero, complemento, rg, cpf, " + 
                                            "datanasc, fone1, fone2, email, status, data) values (?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?)";
        
        
        ps = this.conexao.prepareStatement(sql);
        ps.setString(1, clientes.getNome());
        ps.setInt(2,clientes.getID_Cidade());
        ps.setString(3, clientes.getUF());
        ps.setString(4, clientes.getBairro());
        ps.setString(5, clientes.getEndereco());
        ps.setInt(6, clientes.getNumero());
        ps.setString(7, clientes.getComplemento());
        ps.setString(8,clientes.getRG());
        ps.setString(9, clientes.getCPF());
        ps.setString(10, clientes.getDataNasc());
        ps.setString(11, clientes.getFone1());
        ps.setString(12, clientes.getFone2());
        ps.setString(13, clientes.getEmail());
        ps.setString(14, clientes.getStatus());
        ps.setString(15, df.format(c.getTime()));
        ps.execute();
        ps.close();
    }
    
    
      public void deletarDados(Clientes clientes) throws SQLException
    {
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
        String sql = "Delete from clientes where id= ?";
        ps= this.conexao.prepareStatement(sql);
        ps.setInt(1, clientes.getId());
        ps.execute();
        ps.close();
    }

       public void atualizaDados(Clientes clientes)throws SQLException
       {
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
        
        String sql = "Update clientes set nome = ?, id_cidade = ?, uf = ?, bairro = ?, endereco = ?, " +
                    "numero = ?, complemento = ?, rg = ?, cpf = ?, datanasc = ?, fone1 = ?, fone2 = ?, email = ?, status = ? " +
                     " where id = ?";
        
        
       ps = this.conexao.prepareStatement(sql);
        ps.setString(1, clientes.getNome());
        ps.setInt(2,clientes.getID_Cidade());
        ps.setString(3, clientes.getUF());
        ps.setString(4, clientes.getBairro());
        ps.setString(5, clientes.getEndereco());
        ps.setInt(6, clientes.getNumero());
        ps.setString(7, clientes.getComplemento());
        ps.setString(8,clientes.getRG());
        ps.setString(9, clientes.getCPF());
        ps.setString(10, clientes.getDataNasc());
        ps.setString(11, clientes.getFone1());
        ps.setString(12, clientes.getFone2());
        ps.setString(13, clientes.getEmail());
        ps.setString(14, clientes.getStatus());
        ps.setInt(15, clientes.getId());
        ps.execute();
        ps.close();
        
        
        
        
        
        
       }
       
      
}
