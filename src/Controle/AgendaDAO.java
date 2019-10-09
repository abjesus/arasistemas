/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Banco.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Agenda;

/**
 *
 * @author Abel
 */
public class AgendaDAO {
    
    Connection conexao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    public void gravaAgenda(Agenda agenda){
        
        try {
            this.conexao = Conexao.getConexao();
            
            String sql = "Insert into agenda (id_funcionario, id_cliente, id_servico, data, hora, texto) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, agenda.getIdFuncionario());
            ps.setInt(2, agenda.getIdCliente());
            ps.setInt(3, agenda.getIdServico());
            ps.setString(4, df.format(agenda.getData()));
            ps.setString(5, agenda.getHora());
            ps.setString(6, agenda.getTexto());
            ps.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void removerAgenda(Agenda agenda){
        
        try {
            this.conexao = Conexao.getConexao();
            
            String sql = "delete from agenda where id = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, agenda.getId());
            ps.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void alterarAgenda(Agenda agenda){
        
        try {
            this.conexao = Conexao.getConexao();
            
            String sql = "Update agenda set id_funcionario = ?, id_cliente = ?, id_servico = ?, data = ?, hora = ?, texto = ? where id = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, agenda.getIdFuncionario());
            ps.setInt(2,agenda.getIdCliente());
            ps.setInt(3, agenda.getIdServico());
            ps.setString(4, df.format(agenda.getData()));
            ps.setString(5, agenda.getHora());
            ps.setString(6, agenda.getTexto());
            ps.setInt(7, agenda.getId());
            ps.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
