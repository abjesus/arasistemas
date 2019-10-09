/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.Funcionarios;
import Banco.Conexao;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * 
 * @author Abel
 */
public class FuncionariosDAO {
    
    private Connection conexao;
    //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    public void insereDados(Funcionarios funcionarios) throws SQLException{
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
     
        String sql = "insert into funcionarios (nome, endereco, numero, bairro, " +
                "complemento, id_cidade, uf, admissao, id_cargo, nascimento, rg, cpf, telefone1, telefone2, " +
                " email) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ps = this.conexao.prepareStatement(sql);
        ps.setString(1, funcionarios.getNome());
        ps.setString(2, funcionarios.getEndereco());
        ps.setInt(3, funcionarios.getNumero());
        ps.setString(4, funcionarios.getBairro());
        ps.setString(5, funcionarios.getComplemento());
        ps.setInt(6, funcionarios.getId_cidade());
        ps.setString(7, funcionarios.getUf());
        ps.setString(8, funcionarios.getAdmissao());
        ps.setInt(9, funcionarios.getId_cargo());
        ps.setString(10, funcionarios.getNascimento());
        ps.setString(11, funcionarios.getRg());
        ps.setString(12, funcionarios.getCpf());
        ps.setString(13, funcionarios.getTelefone1());
        ps.setString(14, funcionarios.getTelefone2());
        ps.setString(15, funcionarios.getEmail());
        ps.execute();
        ps.close();
                
        
        
    }
    
    public void deletarDados(Funcionarios funcionarios) throws SQLException{
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
        String sql = "Delete from funcionarios where id= ?";
        ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, funcionarios.getId());
        ps.execute();
        ps.close();
    }
    
    public void atualizaDados(Funcionarios funcionarios)throws SQLException{
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
        
        String sql = "Update funcionarios set nome = ?, endereco = ?, numero = ?, bairro = ?, " +
                    "complemento = ?, id_cidade = ?, uf = ?,  admissao = ?, id_cargo = ?, nascimento = ?, rg = ?, cpf = ?, " +
                    "telefone1 = ?, telefone2 = ?,email = ? where id = ?";
        
        
        ps = this.conexao.prepareStatement(sql);
        ps.setString(1, funcionarios.getNome());
        ps.setString(2, funcionarios.getEndereco());
        ps.setInt(3, funcionarios.getNumero());
        ps.setString(4, funcionarios.getBairro());
        ps.setString(5, funcionarios.getComplemento());
        ps.setInt(6, funcionarios.getId_cidade());
        ps.setString(7, funcionarios.getUf());
        ps.setString(8, funcionarios.getAdmissao());
        ps.setInt(9, funcionarios.getId_cargo());
        ps.setString(10, funcionarios.getNascimento());
        ps.setString(11, funcionarios.getRg());
        ps.setString(12, funcionarios.getCpf());
        ps.setString(13, funcionarios.getTelefone1());
        ps.setString(14, funcionarios.getTelefone2());

        ps.setString(15, funcionarios.getEmail());
        ps.setInt(16, funcionarios.getId());
        ps.execute();
        ps.close();
            
    }
    
}
