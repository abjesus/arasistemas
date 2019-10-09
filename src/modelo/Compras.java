/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Formigao
 */
public class Compras 
{
   private int id_compra;
   private int id_fornecedor;
   private Date datavenda;
   private Date dataVencto;
   private int nparc;
   private double total;
   private double vlr_parc;
   private int qtdeEstoque;
   private String cod_barra;
   private double vlr_unit;
   private String tipo_pgto;
   private String statusComprasCab;

    public String getCod_barra() {
        return cod_barra;
    }

    public void setCod_barra(String cod_barra) {
        this.cod_barra = cod_barra;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }
   
   
   
   
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

   
   
   
    public Date getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(Date datavenda) {
        this.datavenda = datavenda;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public double getVlr_unit() {
        return vlr_unit;
    }

    public void setVlr_unit(double vlr_unit) {
        this.vlr_unit = vlr_unit;
    }

    public String getTipo_pgto() {
        return tipo_pgto;
    }

    public void setTipo_pgto(String tipo_pgto) {
        this.tipo_pgto = tipo_pgto;
    }

    public Date getDataVencto() {
        return dataVencto;
    }

    public void setDataVencto(Date dataVencto) {
        this.dataVencto = dataVencto;
    }

    public int getNparc() {
        return nparc;
    }

    public void setNparc(int nparc) {
        this.nparc = nparc;
    }

    public double getVlr_parc() {
        return vlr_parc;
    }

    public void setVlr_parc(double vlr_parc) {
        this.vlr_parc = vlr_parc;
    }

    public String getStatusComprasCab() {
        return statusComprasCab;
    }

    public void setStatusComprasCab(String statusComprasCab) {
        this.statusComprasCab = statusComprasCab;
    }

    
    
    
    
    
    
   
   
    
}
