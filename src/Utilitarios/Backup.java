/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import Formularios.FrmBackup;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Abel
 */
public class Backup {
    
    
            Calendar c = Calendar.getInstance();
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy - HH.mm.ss");
            SalvaProps prop = new SalvaProps();
    
            public void backupManual(){




                       //Pega o diretório escolhido, junto com o nome escolhido
                       File diretorio = new File(System.getProperty("user.dir") + "\\bkdir");
                       String nome = diretorio + "\\Backup-" + df.format(c.getTime()) + ".sql";


                          //Concatena o diretório com o nome escolhido e o formato .sql */
                          File bkp = new File(nome);
              
                          try{


                                  //String comando = "C:\\Program Files//MySQL//MySQL Server 5.5//bin//mysqldump.exe";
                                  ProcessBuilder pb = new ProcessBuilder("mysqldump",  "--user=root", "--password=91044608", "bd_tcc", "--result-file=" + bkp);
                                  pb.start();
                                  JOptionPane.showMessageDialog(null, "Backup realizado com sucesso", "Backup", JOptionPane.INFORMATION_MESSAGE);

                                  
                    



                           }catch(IOException err){
                               JOptionPane.showMessageDialog(null, "Erro ao realizao bkp  " + err);
                           }





           }
            
            public void backupAgendado(){
        
                try {
                    
                    //Classe que permite trabalhar com agendamentos
                    Timer t = new Timer();
                    
                    //Cria uma instancia da Classe Calendar
                    final Calendar calendar = Calendar.getInstance();
        
                    //Instancia a Classe DateFormat e formata a data conforme a necessidade
                    final DateFormat dataf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
                    //Pega a data agendada do backup                    
                    String data = prop.puxaProp("backupData");
                    



                    //Seta a data agendada do backup
                    calendar.setTime(dataf.parse(data));
                    
                    
                    t.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            
                            //Executa o Backup
                            backupManual();
                             
                            
                             //Se o bk for diário, ele agenda para o próximo dia
                             if("S".equals(prop.puxaProp("backupDiario"))){
                                 
                                 calendar.add(Calendar.DAY_OF_MONTH, 1);
                                 prop.salvaProp("backupData", dataf.format(calendar.getTime()));
                                 
                                 
                             }else{
                                 
                                 
                                //Quando o bk nao é diário, ele executa só uma vez e limpa a data
                                prop.salvaProp("backupData", "");
                                 
                             }
                            
                                  

                        }

                    }, calendar.getTime()); //Responsável por executar o bk com a data agendada
                    
                    
                    
                } catch (ParseException ex) {
                    Logger.getLogger(FrmBackup.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
        
        
    }
            
            public void backupEntraSaidoSistema(){
                
                
                backupManual();
                
       
            }
     
       public void restaurarManual(){
        
        //Configura o pasta padão do JFileChooser
        JFileChooser fc = new JFileChooser("C:\\Users\\Abel\\Desktop");
        fc.setDialogTitle("Restaurar Backup");
        fc.setFileFilter(new javax.swing.filechooser.FileFilter(){  
               public boolean accept(File f){  
                  return (f.getName().endsWith(".sql")) || f.isDirectory();  
               }  
               public String getDescription(){  
                   return "*.sql";  
               }  
            });
        fc.setAcceptAllFileFilterUsed(false);
        fc.showOpenDialog(null);

                //Pega o diretório escolhido, junto com o nome escolhido
                String diretorio = (fc.getSelectedFile().getPath());
                
                JOptionPane.showMessageDialog(null, diretorio);
                    
                   //Concatena o diretório com o nome escolhido e o formato .sql */
                   //File bkp = new File(diretorio + ".sql");
                  
                   try{
                        String bd = "restore";
                        
                                        
                      String comando = "cmd.exe /c \"C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql\" -hlocalhost -uroot -p91044608 restore < C:\\Users\\Abel\\Desktop\\restore.sql";  
                             
                       
           
                       Process proc = Runtime.getRuntime().exec(comando);  
                       proc.waitFor();
                       int res = proc.exitValue();
                       
                        if(res == 0){
                            JOptionPane.showMessageDialog(null, "Restore realizado com sucesso");
                        }else{
                            JOptionPane.showMessageDialog(null, "Falha ao Restaurar");
                        }
                       
                       
                            
                       
                       
                    }catch(IOException err){
                        JOptionPane.showMessageDialog(null, "Erro ao restaurar bkp  " + err);
                    }catch(HeadlessException err){
                        
                    }catch(InterruptedException err){
                        
                    }

    }
       
       public boolean zipar(String entrada, String saida){
           
           String dirInterno = "";
           boolean retorno = true;
           
           try{
               
               File file = new File(entrada);
               

               //Verifica se o arquivo ou diretório existe
               if(file.exists()){
                   JOptionPane.showMessageDialog(null, "O arquivo já existe", "Erro", JOptionPane.INFORMATION_MESSAGE);
                   return false;
               }
               
               ZipOutputStream zipDestino = new ZipOutputStream(new FileOutputStream(saida));
               
               //Se é um arquivo a ser zipado
               //Zipa e retorna
               if(file.isFile()){
                   ziparFile(file, dirInterno, zipDestino);
                   
               } //Senão lista o que tem no diretório e zipa
               else{
                   dirInterno = file.getName();
                   
                   //Verifica se é diretório 
                   
                   File[] files = file.listFiles();
                   
                   for (int i = 0; i < files.length; i++){
                       ziparFile(files[i], dirInterno, zipDestino);
                       
                   }
                   
               }
               zipDestino.close();
               
           }catch(IOException err){
               JOptionPane.showMessageDialog(null, "Erro no IO " + err);
               retorno = false;
           }
           
           return retorno;
           
       }
       
       private void ziparFile(File file, String dirInterno, ZipOutputStream zipDestino) throws IOException{
           
         
           
           //Verifica se a file é um diretório, então faz a recursão
           if(file.isDirectory()){
               File[] files = file.listFiles();
               
               for(int i = 0; i < files.length; i++){
                   
                   ziparFile(files[i], dirInterno + File.separator + file.getName(), zipDestino);
                   
               }
               
               return;
           }
           
           FileInputStream fi = new FileInputStream(file.getAbsolutePath());
           ZipEntry entry = new ZipEntry(dirInterno + File.separator + file.getName());
           zipDestino.putNextEntry(entry);
           
           zipDestino.closeEntry();
           fi.close();
       }
       
      
       
       
    
}
