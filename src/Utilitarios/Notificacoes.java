/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import Banco.Conexao;
import Formularios.FrmNotificacoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Abel
 */
public class Notificacoes {
    
    Connection conexao;

    public Notificacoes() {
        
        
        
    }
    
    
    
    public int NotificarParcAtraz(){
        
        Calendar c = Calendar.getInstance();
        DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
       
         int parc = 0;

        
        try{
            
            this.conexao = Conexao.getConexao();
            String sql = "Select data_vencto from fluxocaixa where data_vencto < ? and  pago = 'N'";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, data.format(c.getTime()));
            ResultSet rsts = ps.executeQuery();
            
            while(rsts.next()){
                
                   parc++;     

            }
            
                if(parc != 0){
                    
                    /**
                    FrmNotificacoes frmNotificacao = new FrmNotificacoes();
                    frmNotificacao.setVisible(true);
                    frmNotificacao.mensagem("Aviso !!!", "Existem " + parc + " parcelas atrazadas !!!", "Clique aqui para visualiza-las");
                    **/
                    new Audio().Inicializar("C:\\Users\\Abel\\Desktop\\abel\\AUDIO DO WINDOWS\\notify.wav");
                    
                    
                    
                }
                   
            
        }catch(SQLException err){
            
            JOptionPane.showMessageDialog(null, "Erro em parcelas atrazadas " + err);
            
        }
    
        
        return parc;
        
      
        
    }
    
    public void notificarBackupAgendado(){
        
        FrmNotificacoes frmNotificacao = new FrmNotificacoes();
        frmNotificacao.setVisible(true);
        frmNotificacao.mensagem("A.R.A Sistemas Avisa !!!", "Backup Agendado, foi realizado com sucesso", null);
                    
        
        
    }
    
}
