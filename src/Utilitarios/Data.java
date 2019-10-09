/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;


import java.util.Date;
import java.text.SimpleDateFormat;


/**
 *
 * @author Abel
 */
public class Data {
    
    public String mes, dia, ano, dia_semana, mostrarData, hora;
    
    //Classe que formata a hora
    SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm:ss");
    
    public void leHora()
    {
        Date hotaAtual = new Date();
        
        hora = horaFormatada.format(hotaAtual);
    }
    
    public void leData()
    {
        
        Date data = new Date();
        
        mes = ""+data.getMonth();
        dia = ""+data.getDate();
        ano = ""+(1900 + data.getYear());
       
        /**O Swith coloca o valor na msm variavel e filtra com os
         * dias da semana
         * dia_semana = ""+data.getDay();
         */
        
        
        /** O Swith pegao valor inteiro do getDay e
         * Converte para os dias da semana
         */
        switch(data.getDay())
        {
            case 0: dia_semana = "Domingo"; break;
            case 1: dia_semana = "Segunda Feira"; break;
            case 2: dia_semana = "Terça Feira"; break;
            case 3: dia_semana = "Quarta Feira"; break;
            case 4: dia_semana = "Quinta Feira"; break;
            case 5: dia_semana = "Sexta Feira"; break;
            case 6: dia_semana = "Sabado";break;
                
        }
        
        mostrarData = "Hoje é: "+ dia + "/" + mes + "/" + ano + " no dia " + dia_semana;
        
    }
    
}
