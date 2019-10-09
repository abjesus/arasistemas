/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Testes;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Abel
 */
public class Cursores {
    
    public void CursorBorracha(JFrame jTable){
      Toolkit kit = Toolkit.getDefaultToolkit();
                        Image image = kit.createImage(getClass().getResource("/imagems/borracha2.png"));
                        Point point = new Point(4,13);
                        String nome = "Borracha";
                        Cursor cursor = kit.createCustomCursor(image, point, nome);
                        
                        jTable.setCursor(cursor);
                        
    }
    
}
