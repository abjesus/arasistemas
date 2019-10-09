/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Banco.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Abel
 */
public class ContasVencidas {
    
    Connection conexao;
    
    
    public void contasVencidas(){
        
        try {
            this.conexao = Conexao.getConexao();
            
            String sql = "SELECT cl.nome from clientes cl " +
                    "inner join fluxocaixa fc on fc.id_cliente = cl.id " +
                    "where fc.data_vencto < date(now()) and pago = 'N' and cl.status = 'R'" +
                    "group by cl.nome";
            
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet rsts = ps.executeQuery();
            
            
            
            while(rsts.next()){
                
                String sql2 = "Update clientes set status = 'I' where nome = ?";
                PreparedStatement ps2 = this.conexao.prepareStatement(sql2);
                ps2.setString(1, rsts.getString("cl.nome"));
                ps2.execute();
                
            }
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não tem vencidas");
            Logger.getLogger(ContasVencidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void vencidasPagas(){
        
        this.conexao = Conexao.getConexao();
        String sql = "SELECT cl.nome from clientes cl " +
                    "inner join fluxocaixa fc on fc.id_cliente = cl.id " +
                    "where fc.data_vencto < date(now()) and pago = 'N' and cl.status = 'R'" +
                    "group by cl.nome";
        
        
        
    }
    
}
