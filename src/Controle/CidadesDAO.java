/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Banco.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Cidades;

/**
 *
 * @author Abel
 */
public class CidadesDAO {
    
    private Connection conexao;
    
    
    // Este método insere os dados na tabela
    public void inserirDados(Cidades cidades) throws SQLException{
       
        
       this.conexao = Conexao.getConexao();
        
       PreparedStatement ps;
       String sql = "INSERT into cidades(nome, uf) values(?, ?)";
       ps = this.conexao.prepareStatement(sql);
       ps.setString(1,cidades.getNome());
       ps.setString(2,cidades.getUf());
       ps.execute();
       ps.close();
        
                
        
    }
    
    public void deletarDados(Cidades cidades) throws SQLException{
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
        String sql = "Delete from cidades where id= ?";
        ps= this.conexao.prepareStatement(sql);
        ps.setInt(1, cidades.getId());
        ps.execute();
        ps.close();
    }
    
    public void atualizarDados(Cidades cidades) throws SQLException{
        
        this.conexao = Conexao.getConexao();
        String sql = "Update cidades set nome = ?, uf = ? where id = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setString(1, cidades.getNome());
        ps.setString(2, cidades.getUf());
        ps.setInt(3, cidades.getId());
        ps.execute();
        ps.close();
    }
    
    
    
    
   
    
    
    
}
