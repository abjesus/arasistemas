/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import Banco.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Abel
 */
public class Email{

    
    Connection conexao;
    

    

    
    public boolean enviaEsqueciSenha(String emailSolicitado){
      
           boolean controle = false;
        
           try{
               
              
               
            this.conexao = Conexao.getConexao();
               
               // Instrução que verifica no banco se o email esta cadastrado no sistema como usuario
            String sql = "SELECT fu.email, us.senha, us.usuario from usuarios us inner join funcionarios"
                    + " fu on us.id_funcionario = fu.id where fu.email =  ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, emailSolicitado);
            ResultSet rsts = ps.executeQuery();
            
            rsts.next();   
            
                if(rsts.getString("fu.email").equals(emailSolicitado)){
                    
                    String msg = "Solicitação de senha:" + 
                         "\n" +
                         "\n" +
                         "\n" +
                         "Sua senha é: " + rsts.getString("us.senha");
                         
                            //Atribui o email escolhido no atributo destinatário
                           SimpleEmail email = new SimpleEmail();
                           email.setDebug(true);

                           //Servidor SMTP do email que irá enviar a msg com a senha
                           email.setHostName("smtp.gmail.com");

                           //Autenticação do email que enviará a msg com a senha
                           email.setAuthentication("abeljesusdasilva19@gmail.com", "abel91044608rhimes");
                           email.setSSL(true);

                           //Email solicitado
                           email.addTo(emailSolicitado); //Destinatário

                           email.setFrom("abeljesusdasilva19@gmail.com", "Suporte A.R.A."); //Remetente
                           email.setSubject("Senha"); //Assunto do Email
                           email.setMsg(msg); //Mensagem do Email


                           email.send();

                            
                            
                            

                           JOptionPane.showMessageDialog(null, "Email enviado para " + emailSolicitado, "Recuperar Senha",
                                                                                       JOptionPane.INFORMATION_MESSAGE);

                           controle = true;
                     
                    
                }//FIM SE
                
            
            
         
           }catch(EmailException err){
               JOptionPane.showMessageDialog(null, "Erro ao enviar email, verifique sua conexão com a internet ", "Erro", JOptionPane.ERROR_MESSAGE);
               controle = false;
               
           }catch(SQLException err){
             JOptionPane.showMessageDialog(null, "Email não encontrado no sistema, " +
                                                         "verifique se vc esta cadastrado(a) no sistema !", "Erro", JOptionPane.ERROR_MESSAGE);
                controle = false;
           }
           
          return controle;
           
    }
    
    
    public void anexo() throws EmailException{
        
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Ecolha o Anexo");
        fc.showOpenDialog(null);
        EmailAttachment anexo = new EmailAttachment();
        anexo.setPath(fc.getSelectedFile().getAbsolutePath()); //Pega o arquivo selecionado
        
        anexo.setDisposition(EmailAttachment.ATTACHMENT);
        anexo.setDescription("Contem arquivo anexado"); //Coloca descricao
        anexo.setName(fc.getSelectedFile().getName()); // Titulo do anexo
        
        
        MultiPartEmail email = new MultiPartEmail();
        
        email.setDebug(true);
        email.setHostName("smtp.gmail.com"); 
        email.setAuthentication("abeljesusdasilva19@gmail.com", "abel91044608rhimes");
        email.setSSL(true);
        email.addTo("abel.jesus.silva@outlook.com"); //Destinatário

        email.setFrom("abeljesusdasilva19@gmail.com", "Suporte A.R.A"); //Remetente
        email.setSubject("Senha"); //Assunto do Email
        email.setMsg("Sua senha e 123"); //Mensagem do Email
         
        
        email.attach(anexo); // Seta o anexo ao email
        
        email.send(); //Envia o Email
        
        
      
        
        
    }
            

   
    
    
}
