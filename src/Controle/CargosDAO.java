/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

/**
 *
 * @author Formigao
 *
 * 
 */


import java.sql.Connection;
import modelo.Cargos;
import Banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CargosDAO {
    
     Connection conexao;
     
     
     public void insereDados(Cargos cargos)throws SQLException
     
     {
        
        this.conexao = Conexao.getConexao();
        
        
        PreparedStatement ps;
        String sql = "insert into cargos" +
                "(cargo, salario) values(?, ?)";
        ps = this.conexao.prepareStatement(sql);
        ps.setString(1, cargos.getCargo());
        ps.setDouble(2, cargos.getSalario());
        ps.execute();
        ps.close();
    }
     
    public void excluirDados(Cargos cargos)throws SQLException
    {
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
        String sql = "delete from cargos where id= ?";
        ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, cargos.getId());
        ps.execute();
        ps.close();
        
    }
    
    
    public void atualizaDados(Cargos cargos)throws SQLException
    {
        
        this.conexao = Conexao.getConexao();
        String sql = "Update cargos set cargo = ?, salario = ? where id = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setString(1, cargos.getCargo());
        ps.setDouble(2, cargos.getSalario());
        ps.setInt(3, cargos.getId());
        ps.execute();
        ps.close();
        
        
    }
    
}
