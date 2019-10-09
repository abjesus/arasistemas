/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;


/**
 *
 * @author Abel
 */
public class Conexao {
   
    
    public static Connection CriaBanco() {
    
    try{
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
        String username = "root";
        String password = "91044608";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Mysql", username, password);
        Statement st = connection.createStatement();
        String sql = "create database if not exists bd_tcc ";
        st.executeUpdate(sql);
        return connection;
    } catch (ClassNotFoundException e) {
        System.out.println("Erro ao criar o banco");
        return null;
    } catch (SQLException e) {
        System.out.println("Não foi possivel conectar ao banco");
        return null;
    }
        
        
        
        
    }
    
    public static Connection getConexao(){
        try{
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
        String url = "jdbc:mysql://localhost/bd_tcc";
        String username = "root";
        String password = "91044608";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    } catch (ClassNotFoundException e) {
        System.out.println("O driver expecificado não foi encontrado");
        return null;
    } catch (SQLException e) {
        System.out.println("Não foi possivel conectar ao banco");
        return null;
    }
    }
    
   

    
    

        
}
    
    

