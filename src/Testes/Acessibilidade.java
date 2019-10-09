/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Testes;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Abel
 */
public class Acessibilidade {
    
    
       public void setAcessibilidade(JFrame jFrame) {  
           
           
        JRootPane meurootpane = jFrame.getRootPane();  
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");  
        meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {  
  
            @Override
            public void actionPerformed(ActionEvent e) { 
                
                System.exit(0);
                
                int resp = JOptionPane.showConfirmDialog(null, "Sair ?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    
                    if(resp == JOptionPane.YES_OPTION){
                        
                        
                        
                    }
            }  
        });  
    }
       
       public void setDtaAction(JDateChooser jDateChooser){
           
              
        JRootPane meurootpane = jDateChooser.getRootPane();  
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");  
        meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {  

            @Override
            public void actionPerformed(ActionEvent e) {
                
                    JOptionPane.showMessageDialog(null, "Deu certo");
                
                
               }
 
        });
           
       }
    
}
