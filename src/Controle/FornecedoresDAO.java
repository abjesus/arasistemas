/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.Fornecedores;
import Banco.Conexao;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author Formigao
 */
public class FornecedoresDAO {
    
    private Connection conexao;
    Calendar c = Calendar.getInstance();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
     public void insereDados(Fornecedores fornecedores) throws SQLException
     {
        
        this.conexao = Conexao.getConexao();
        
        PreparedStatement ps;
     
        String sql = "insert into fornecedores (Razao_Social, Fantasia, Endereco, bairro, " +
                     "Id_cidade, uf, cnpj, ie, fone1, fone2, contato, email, data) " +
                     " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ps = this.conexao.prepareStatement(sql);
        ps.setString(1, fornecedores.getRazao_Social());
        ps.setString(2, fornecedores.getFantasia());
        ps.setString(3, fornecedores.getEndereco());
        ps.setString(4, fornecedores.getBairro());
        ps.setInt(5, fornecedores.getID_Cidade());
        ps.setString(6, fornecedores.getUF());
        ps.setString(7, fornecedores.getCNPJ());
        ps.setString(8, fornecedores.getIE());
        ps.setString(9, fornecedores.getFone1());
        ps.setString(10, fornecedores.getFone2());
        ps.setString(11, fornecedores.getContato());
        ps.setString(12, fornecedores.getEmail());
        ps.setString(13, df.format(c.getTime()));
        ps.execute();
        ps.close();
      
     }
     
     public void deletarDados(Fornecedores fornecedores) throws SQLException
     {
         this.conexao = Conexao.getConexao();
         
         PreparedStatement ps;
         String sql = "Delete from fornecedores where id =?";
         ps = this.conexao.prepareStatement(sql);
         ps.setInt(1, fornecedores.getId());
         ps.execute();
         ps.close();
     }
    
     public void atualizaDados(Fornecedores fornecedores) throws SQLException
     {
         this.conexao = Conexao.getConexao();
         
         PreparedStatement ps;
         String sql = "Update Fornecedores set Razao_Social = ?, Fantasia = ?, Endereco = ?, Bairro = ?, "
                    + "ID_Cidade = ?, uf = ?, CNPJ = ?, IE = ?, Fone1 = ?, Fone2 = ?, "
                    + "contato = ?, email = ? where id = ? ";
         
         ps = this.conexao.prepareStatement(sql);
         ps.setString(1, fornecedores.getRazao_Social());
         ps.setString(2, fornecedores.getFantasia());
         ps.setString(3, fornecedores.getEndereco());
         ps.setString(4, fornecedores.getBairro());
         ps.setInt(5, fornecedores.getID_Cidade());
         ps.setString(6, fornecedores.getUF());
         ps.setString(7, fornecedores.getCNPJ());
         ps.setString(8, fornecedores.getIE());
         ps.setString(9, fornecedores.getFone1());
         ps.setString(10, fornecedores.getFone2());
         ps.setString(11, fornecedores.getContato());
         ps.setString(12, fornecedores.getEmail());
         ps.setInt(13, fornecedores.getId());
         ps.execute();
         ps.close();
     }
}
