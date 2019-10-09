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
public class Vendas {
    
    private int id_venda;
    private int id_cliente;
    private int id_produto;
    private Date dataVenda;
    private double total;
    private String statusCliente;
    private double Vlr_Parc;
    private double vlr_custo;
    private int NParc;
    private Double desconto;
    private Date dataVencto;
    private int estoque;
    private String cod_barra;
    private int qtdeEstoque;
    private String tipo_pgto;
    private double vlr_final;
    private String statusVendaCab;

    public String getTipo_pgto() {
        return tipo_pgto;
    }

    public void setTipo_pgto(String tipo_pgto) {
        this.tipo_pgto = tipo_pgto;
    }
    

    
    
    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(String statusCliente) {
        this.statusCliente = statusCliente;
    }

    public Integer getNParc() {
        return NParc;
    }

    public void setNParc(Integer NParc) {
        this.NParc = NParc;
    }

    public Double getVlr_Parc() {
        return Vlr_Parc;
    }

    public void setVlr_Parc(Double Vlr_Parc) {
        this.Vlr_Parc = Vlr_Parc;
    }

    public Date getDataVencto() {
        return dataVencto;
    }

    public void setDataVencto(Date dataVencto) {
        this.dataVencto = dataVencto;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getCod_barra() {
        return cod_barra;
    }

    public void setCod_barra(String cod_barra) {
        this.cod_barra = cod_barra;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(Integer qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Double getVlr_custo() {
        return vlr_custo;
    }

    public void setVlr_custo(Double vlr_custo) {
        this.vlr_custo = vlr_custo;
    }

    public double getVlr_final() {
        return vlr_final;
    }

    public void setVlr_final(double vlr_final) {
        this.vlr_final = vlr_final;
    }

    public String getStatusVendaCab() {
        return statusVendaCab;
    }

    public void setStatusVendaCab(String statusVendaCab) {
        this.statusVendaCab = statusVendaCab;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
            
    
    
}
