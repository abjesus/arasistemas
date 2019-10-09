/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Abel
 */
public class TrayIcones {
    
    Image imageIcon = new ImageIcon((getClass()
                                                .getClassLoader()
                                                .getResource("imagems/icone.png")))
                                                .getImage(); 
    
     String cam = System.getProperty("user.dir");
     TrayIcon trayIcon;
    
    
       public void icone(JFrame jFrame){
      
        
        jFrame.setIconImage(imageIcon);
        
        
    }
       
       public void tray(final JFrame jFrame){
           
           
           

            // Instancia um novo SystemTray e um novo Popup Menu
           SystemTray tray = SystemTray.getSystemTray();
           PopupMenu menu = new PopupMenu();



               //Items de Menu
               MenuItem itemSair = new MenuItem("Sair");
               MenuItem backup = new MenuItem("Backup");
               MenuItem restaurar = new MenuItem("Restaurar Janela");


               //Evento do MenuItem 'itemSair'
               itemSair.addActionListener(new ActionListener() {

                           @Override
                           public void actionPerformed(ActionEvent e) {
                               System.exit(0);
                           }
                       });

            
            //Evento do MenuItem 'backup'
            backup.addActionListener(new ActionListener(){
        
                        @Override
                        public void actionPerformed(ActionEvent e){
                            
                            
                            try{
                                
                               String com = cam + "\\sergioBackup\\BackupZipRar.exe";
                              
                                Runtime.getRuntime().exec("C:\\Users\\Abel\\Desktop\\Projeto TCC Marilda\\Projeto TCC\\BackupZipRar.exe");
                               }catch(IOException err){

                               }

                            
                        }
               });
            
            restaurar.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent e) {
                   
                   jFrame.setVisible(true);
        
                   jFrame.setExtendedState(jFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
                   
               }
           });
            
        
            
                //Adiciona os Items de Menu ao Tray    
                menu.add(backup);    
                menu.add(itemSair);
                menu.add(restaurar);




                // Instancia e Define o icone do TrayIcon
                trayIcon = new TrayIcon(imageIcon, null, menu);




                // Define o auto-ajuste da imagem
                trayIcon.setImageAutoSize(true);





                try {
                    // Adiciona o Ícone no SystemTray

                    tray.add(trayIcon);
                    //trayIcon.displayMessage("A.R.A Sistemas", "Aqui ficará algumas opções do sistemas", TrayIcon.MessageType.INFO);
                } catch (AWTException e) {} 

        }
       
       public void trayMensagens(String cap, String msg, int tipo)
       {
           
           TrayIcon.MessageType teste = null;
           
           //NÃO EXIBE O TIPO DA MSG
           if(0 == tipo){
               
               teste = TrayIcon.MessageType.NONE;
               
           //SE O VALOR FOR 1, O TIPO DA MSG É INFORMATIVA    
           }else if(1 == tipo){
               
               teste = TrayIcon.MessageType.INFO;
               
           //SE O VALOR FOR 2, O TIPO DA MSG É ERRO    
           }else if(2 == tipo){
               
               teste = TrayIcon.MessageType.ERROR;
               
           //SE O VALOR FOR 3, O TIPO DA MSG É AVISO    
           }else if(3 == tipo){
               
               teste = TrayIcon.MessageType.WARNING;
               
           }
        
           
           trayIcon.displayMessage(cap, msg, teste);
           
           
       }    
}
