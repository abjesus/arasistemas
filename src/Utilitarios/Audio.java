/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Abel
 */
public class Audio {
    
 
    public void Inicializar(String cam){
        
    
    try{
        
    
         AudioClip inicio;
         inicio = Applet.newAudioClip(new File(cam).toURL());
         inicio.play();
    
    }catch(MalformedURLException err){
        JOptionPane.showMessageDialog(null, "Erro ao reproduzir som, 'Classe Audio  '" + err);
    }
   
      
            
 }
    
   
    
}
