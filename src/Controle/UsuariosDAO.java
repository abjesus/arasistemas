/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import modelo.Usuarios;

/**
 *
 * @author Abel
 */
public class UsuariosDAO {
    
    Connection conexao;
    
    public void insereDados(Usuarios usuarios)throws SQLException{
        
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       
        this.conexao = Conexao.getConexao();
        String sql = "Insert into usuarios (id_funcionario, funcionario," +
                     "usuario, senha, nivel, data) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, usuarios.getIdFun());
        ps.setString(2, usuarios.getFuncionario());
        ps.setString(3, usuarios.getUsuario());
        ps.setString(4, usuarios.getSenha());
        ps.setString(5, usuarios.getNivel());
        ps.setString(6, df.format(c.getTime()));
        ps.execute();
        ps.close();
       
        
    }
    
    public void deletarDados(Usuarios usuario)throws SQLException{
        
        this.conexao = Conexao.getConexao();
        String sql = "Delete from usuarios where id = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, usuario.getId());
        ps.execute();
        ps.close();
    }
    
    public void atualizaDados(Usuarios usuarios)throws SQLException{
        
        this.conexao = Conexao.getConexao();
        String sql = "Update usuarios set funcionario = ?, usuario = ?, senha = ?, nivel = ? where id = ?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setString(1, usuarios.getFuncionario());
        ps.setString(2, usuarios.getUsuario());
        ps.setString(3, usuarios.getSenha());
        ps.setString(4, usuarios.getNivel());
        ps.setInt(5, usuarios.getId());
        ps.execute();
        ps.close();
        
        
    }
    
}
