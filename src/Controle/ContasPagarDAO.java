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
import java.util.Calendar;
import javax.swing.JOptionPane;
import modelo.ContasPagar;
import modelo.ContasReceber;

/**
 *
 * @author Abel
 */
public class ContasPagarDAO {
    
    
    Connection conexao;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar c = Calendar.getInstance();

    
    public void baixarContasaPagar(ContasPagar contas){
        try{
            
           
            
            this.conexao = Conexao.getConexao();
            
            String sql = "Update fluxocaixa set vlr_desconto = ?, vlr_juros = ?,  data_pgto = ?, tipo_pgto = ?, vlr_final = ?, pago = ? where id_vencom = ? and nparc = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setDouble(1, contas.getVlr_desc());
            ps.setDouble(2, contas.getVlr_juros());
            ps.setString(3, df.format(c.getTime())); //Pega a Data Atual
            ps.setString(4, contas.getTipo_pgto());
            ps.setDouble(5, contas.getVlr_final());
            ps.setString(6, "S");
            ps.setInt(7, contas.getId_venda());
            ps.setInt(8, contas.getNparc());
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Parcela baixada com sucesso");
            
        }catch(SQLException err){
            
            JOptionPane.showMessageDialog(null, "Erro no atualizaContasPagar " + err);                    
            
        }
    }
    
}
