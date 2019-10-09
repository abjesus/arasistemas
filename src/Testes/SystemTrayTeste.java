/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Testes;

import Formularios.FrmLogin;
import Formularios.RecupSenha;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 *
 * @author Abel
 */
public class SystemTrayTeste extends JFrame{
    

 
    /**
     * Construtor da classe.
     * @param nome
     */
    public SystemTrayTeste(String nome) {
        super(nome); // Coloca o título da Janela
        new FrmLogin().setVisible(true);
    }
 
    public static void main(String[] args) {
        // Instancia nova janela
        final  SystemTrayTeste main = new SystemTrayTeste("Janela de Testes");
 
        // Instancia um novo SystemTray
        SystemTray tray = SystemTray.getSystemTray();
        PopupMenu menu = new PopupMenu();
        
        
 
        /**
         *  Pega uma imagem para definir como ícone.
         *
         *  main.getClass().getClassLoader().getResource("icone.jpg")
         *  pega a imagem do pacote onde a Classe se encontra.
         *  Será bem útil na hora de exportar a aplicação.
         */
        
        
        
            MenuItem itemSair = new MenuItem("Sair");
            MenuItem show = new MenuItem("Esqueci a Senha");
                   
            itemSair.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });
            
            show.addActionListener(new ActionListener(){
        
                        public void actionPerformed(ActionEvent e){
                            
                            
                            new RecupSenha().setVisible(true);
                            
                            
                        }
               });
        
            
            
        menu.add(show);    
        menu.add(itemSair);
        
        
        Image imageIcon = new ImageIcon((main.getClass()
                                                .getClassLoader()
                                                .getResource("imagems/desativado.png")))
                                                .getImage();
 
        // Instancia e Define o icone do TrayIcon
        TrayIcon trayIcon = new TrayIcon(imageIcon, null, menu);
        
        
       
 
        // Define o auto-ajuste da imagem
        trayIcon.setImageAutoSize(true); 
 
        /**
         * Instancia um mouse listener para ser usado no TrayIcon
         *
        MouseListener mlOpcoes = new MouseListener(){   
 
            public void mouseClicked(MouseEvent e) {}   
 
            public void mousePressed(MouseEvent e) {}   
 
            public void mouseReleased(MouseEvent e) {
                /**
                 *  Se o mouse for clicado com a roda do mouse ou com
                 *  o botão direito fechará a aplicação.
                 *
                if(e.getButton() == 2 || e.getButton() == 3){
                    System.exit(0);
                }else if(e.getButton() == 1){
                    
                   new Menu().setVisible(true);
                    
                    
                }
            }   
 
            public void mouseEntered(MouseEvent e) {}   
 
            public void mouseExited(MouseEvent e) {}
        };
        // adiciona o mouseListener no TrayIcon
        trayIcon.addMouseListener(mlOpcoes);
        * **/
        
        try {
            // Adiciona o Ícone no SystemTray
            tray.add(trayIcon);
        } catch (AWTException e) {} 
 
    }
}