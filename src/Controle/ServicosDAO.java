/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.Connection;
import modelo.Servicos;
import Banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Abel
 */
public class ServicosDAO {
    
    Connection conexao;
    
    


    public void insereDados(Servicos servicos)throws SQLException{
        
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        this.conexao = Conexao.getConexao();
        
        
        PreparedStatement ps;
        String sql = "insert into servicos" +
                "(descricao, vlr_unit, data) values(?, ?, ?)";
        ps = this.conexao.prepareStatement(sql);
        ps.setString(1, servicos.getDescricao());
        ps.setDouble(2, servicos.getVlr_unit());
        ps.setString(3, df.format(c.getTime()));
        ps.execute();
        ps.close();
    }
    
    
    public void excluirDados(Servicos servicos)throws SQLException{
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
        String sql = "delete from servicos where id= ?";
        ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, servicos.getId());
        ps.execute();
        ps.close();
        
    }
    
    public void atualizaDados(Servicos servicos)throws SQLException{
        
        this.conexao = Conexao.getConexao();
        String sql = "Update servicos set descricao = ?, vlr_unit = ? where id = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setString(1, servicos.getDescricao());
        ps.setDouble(2, servicos.getVlr_unit());
        ps.setInt(3, servicos.getId());
        ps.execute();
        ps.close();
        
        
    }
    
    
    
}
