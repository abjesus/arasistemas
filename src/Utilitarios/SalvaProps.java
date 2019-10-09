/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Abel
 */
public class SalvaProps {
    
    public String puxaProp(String prop){
        
          
        String retorno = null;
        try {
              
              
            Properties properties = new Properties();
            File arq = new File(System.getProperty("user.dir") + "\\src\\Utilitarios\\SalvaProp.properties");
            
            
            FileInputStream file = new FileInputStream(arq);
            
            properties.load(file);
            
            retorno = properties.getProperty(prop);
            
            
     
        }catch(IOException err){
            JOptionPane.showMessageDialog(null, err);
        }
          
          return retorno;
        
    }
    
    public void salvaProp(String prop, String valor){
        
        try{
            Properties properties = new Properties();
            File arq = new File(System.getProperty("user.dir") + "\\src\\Utilitarios\\SalvaProp.properties");
            
            
            FileInputStream file = new FileInputStream(arq);
            
            properties.load(file);
            
            properties.setProperty(prop, valor);
            
            properties.store(new FileOutputStream(arq), null);
            
            
            
           }catch(IOException err){
              JOptionPane.showMessageDialog(null, err); 
           }
    }
    
}
