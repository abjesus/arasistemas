/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Formigao
 */
public class Produtos {
    
    private int id;
    private String descricao;
    private String cod_barra;
    private int id_fornecedor;
    private Integer estoque;
    private Integer estoque_min;
    private double valor_custo;
    private double valor_venda;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getEstoque_min() {
        return estoque_min;
    }

    public void setEstoque_min(Integer estoque_min) {
        this.estoque_min = estoque_min;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public double getValor_custo() {
        return valor_custo;
    }

    public void setValor_custo(double valor_custo) {
        this.valor_custo = valor_custo;
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }



    public void setValor_venda(Integer valor_venda) {
        this.valor_venda = valor_venda;
    }

    public String getCod_barra() {
        return cod_barra;
    }

    public void setCod_barra(String cod_barra) {
        this.cod_barra = cod_barra;
    }
    
    
    
    
    
}
