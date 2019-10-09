/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Abel
 */
public class ContasReceber {
    
   
    private int id_venda;
    private int id_cliente;
    private int nparc;
    private double vlr_juros;
    private double vlr_desc;
    private double vlrParc;
    private String tipo_pgto;
    private double vlr_final;
  

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getNparc() {
        return nparc;
    }

    public void setNparc(int nparc) {
        this.nparc = nparc;
    }

    public String getTipo_pgto() {
        return tipo_pgto;
    }

    public void setTipo_pgto(String tipo_pgto) {
        this.tipo_pgto = tipo_pgto;
    }

    public double getVlr_desc() {
        return vlr_desc;
    }

    public void setVlr_desc(double vlr_desc) {
        this.vlr_desc = vlr_desc;
    }

    public double getVlr_juros() {
        return vlr_juros;
    }

    public void setVlr_juros(double vlr_juros) {
        this.vlr_juros = vlr_juros;
    }

    public double getVlrParc() {
        return vlrParc;
    }

    public void setVlrParc(double vlrParc) {
        this.vlrParc = vlrParc;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public double getVlr_final() {
        return vlr_final;
    }

    public void setVlr_final(double vlr_final) {
        this.vlr_final = vlr_final;
    }
    
    
    
    
    
    
    
    
    
}
