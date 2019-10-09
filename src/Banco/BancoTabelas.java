/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author Abel
 */
public class BancoTabelas {
    
    private Connection conexao;
   

    
    public void CriaTabelas() throws Exception{
        
        this.conexao = Conexao.getConexao();
        
        String[] sql = new String[20];
        PreparedStatement ps[] = new PreparedStatement[20];
        
       
        //Tabela cidades
        sql[1]= "Create table if not exists cidades" +
                "(id integer auto_increment not null," +
                "nome varchar(40) not null," +
                "uf char(2) not null," +
                "primary key(id))";
        ps[1] = this.conexao.prepareStatement(sql[1]);
        ps[1].execute();
        
        
        
        //Tabela Funcionários
        sql[2]= "Create table if not exists funcionarios " +
                "(ID integer not null auto_increment, " +
                "nome varchar(40) not null," +
                "endereco varchar(40) not null," +
                "numero integer not null, " +
                "bairro varchar(20) not null, " +
                "complemento varchar(30) not null, " +
                "id_cidade integer not null," +
                "uf char(2) not null," + 
                "admissao varchar(15) not null, " +
                "id_cargo integer not null, " +
                "nascimento varchar(15) not null, " +
                "RG varchar(20) not null, " +
                "CPF varchar(20) not null, " +
                "telefone1 varchar(20) not null, " +
                "telefone2 varchar(20) not null, " +
                "email varchar(40) not null, " +
                "primary key(ID))";
        
        ps[2] = this.conexao.prepareStatement(sql[2]);
        ps[2].execute();
        
        
        //Tabela Serviços
        sql[3] = "Create table if not exists servicos" +
                "(id integer auto_increment not null," +
                "descricao varchar(40) not null," +
                "vlr_unit decimal(10,2) not null," +
                "data date not null, " +
                "primary key(ID))";
        ps[3] = this.conexao.prepareStatement(sql[3]);
        ps[3].execute();
        
        
        //Tabela Usuários
        sql[4] = "Create table if not exists usuarios" +
                "(id integer auto_increment not null," +
                "usuario varchar(40) not null," +
                "senha varchar(20) not null," +
                "id_funcionario integer not null," +
                "funcionario varchar(40) not null," +
                "nivel char(1) not null, " +
                "data date not null, " +
                "primary key(ID))";
        ps[4] = this.conexao.prepareStatement(sql[4]);
        ps[4].execute();
        
        //Tabela de Fornecedores
        sql [5] = "Create table if not exists fornecedores" + 
                  "(id integer auto_increment not null, " + 
                  "Razao_Social varchar(40) not null, " + 
                  "Fantasia varchar(40) not null," + 
                  "Endereco varchar (40) not null," +
                  "Bairro varchar (40) not null," + 
                  "ID_Cidade integer not null," + 
                  "UF char(2) not null," + 
                  "CNPJ varchar(25) not null," + 
                  "IE varchar(20) not null," + 
                  "Fone1 varchar(20) not null," + 
                  "Fone2 varchar(20) not null," + 
                  "Contato varchar(30) not null," +
                  "email varchar(50) not null," +
                  "data date not null, " +
                  "primary Key (ID))";
        ps[5] = this.conexao.prepareStatement(sql[5]);
        ps[5].execute();
        
                
        //Tabela Clientes
        sql[6] =  "Create table if not exists clientes " + 
                  "(id integer auto_increment not null," + 
                  "nome varchar(60) not null, " +
                  "id_cidade integer not null, " + 
                  "uf char(2) not null," + 
                  "bairro varchar(40) not null, " + 
                  "endereco varchar(50) not null, " + 
                  "numero integer not null, " + 
                  "complemento varchar(50) not null, " + 
                  "rg varchar(20) not null, " + 
                  "cpf varchar(20) not null, " + 
                  "datanasc varchar (10) not null, " +
                  "fone1 varchar(25) not null, " + 
                  "fone2 varchar(25) not null, " + 
                  "email varchar (60) not null, " +
                  "status char(1) not null, " +
                  "data date not null, " +
                  "primary Key(ID))";
        ps[6] = this.conexao.prepareStatement(sql[6]);
        ps[6].execute();
        
        
        //Tabela Cargos
        sql [7] = "Create table if not exists cargos" +
                  "(id integer auto_increment not null, " + 
                  "cargo varchar(30) not null, " +
                  "salario decimal(10,2) not null, " + 
                  "primary Key (ID))";
        ps[7] = this.conexao.prepareStatement(sql[7]);
        ps[7].execute();
        
        //Tabela Produtos
        sql[8] = "Create table if not exists produtos"  +
                 "(id integer auto_increment not null," +
                 "cod_barra varchar(50) not null, " +
                 "descricao varchar(50) not null, "+  
                 "id_fornecedor integer not null, "+
                 "estoque integer not null," + 
                 "estoque_min integer not null," + 
                 "valor_custo decimal(10,2) not null," + 
                 "valor_venda decimal(10,2) not null," +
                 "data date not null, " +
                 "primary Key(ID), "
                + "FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedores` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE)";
        ps[8] = this.conexao.prepareStatement(sql[8]);
        ps[8].execute();       
                          
        
                    //Tabela de Fluxo de Caixa
        sql[9] = "Create table if not exists fluxocaixa" +
                "(id integer auto_increment not null," +
                "id_vencom integer not null," +
                "id_cliente integer," +
                "id_fornecedor integer," +
                "nparc integer," +
                "vlr_parc decimal(10,2)," +
                "vlr_juros decimal(10,2)," +
                "vlr_desconto decimal(10,2)," +
                "vlr_final decimal(10,2) DEFAULT 0.0 not null, " +
                "data_venda date not null," +
                "data_vencto date," +
                "data_pgto date," +
                "tipo_pgto varchar(10)," +
                "operacao char(1) not null," +
                "pago char(1) not null," +
                "primary key(ID)," +
                "FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedores` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE," +
                "FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE)";
        ps[9] = this.conexao.prepareStatement(sql[9]);
        ps[9].execute();
                  
        
        
        //Tabela Venda Cab
        sql[10] = "Create table if not exists Venda_Cab" +
                  "(id integer auto_increment not null," +
                  "id_cliente integer not null," +
                  "data date not null," +
                  "total decimal(10,2) not null," +
                  "status char(1) not null," +
                  "primary key(ID))";
        
        ps[10] = this.conexao.prepareStatement(sql[10]);
        ps[10].execute();
        
        //Tabela Venda Det
        sql[11] = "Create table if not exists Venda_Det" +
                  "(id integer auto_increment not null," +
                  "id_venda integer not null," +
                  "cod_barra varchar(50) not null," +
                  "qtde numeric not null," +
                  "vlr_venda decimal(10,2) not null," +
                  "primary key(ID))";
        ps[11] = this.conexao.prepareStatement(sql[11]);
        ps[11].execute();
        
            //Tabela Compra Cab
        sql[12] = "Create table if not exists Compra_Cab" +
                  "(id integer auto_increment not null," +
                  "id_fornecedor integer not null," +
                  "data date not null," +
                  "total decimal(10,2) not null," +
                  "primary key(ID))";
        
        ps[12] = this.conexao.prepareStatement(sql[12]);
        ps[12].execute();
        
        //Tabela Compra Det
        sql[13] = "Create table if not exists Compra_Det" +
                  "(id integer auto_increment not null," + 
                  "id_compra integer not null, " + 
                  "cod_barra integer not null, " + 
                  "qtde numeric not null," + 
                  "vlr_unit decimal (10,2) not null," + 
                  "primary key (ID))";
        ps[13] = this.conexao.prepareStatement(sql[13]);
        ps[13].execute();
        
        
        //Tabela de Baixa de Estoque
        sql[14] = "create table if not exists baixa_estoque " +
                 "(id integer auto_increment not null, " +
                 "cod_barra varchar(50) not null, " +
                 "id_funcionario integer not null, " +
                 "descricao varchar(40) not null, " +
                 "qtde integer not null, " +
                 "data date not null, " +
                 "primary key(ID))";
        ps[14] = this.conexao.prepareStatement(sql[14]);
        ps[14].execute();
        
        //Tabela de Agenda
        sql[15] = "create table if not exists agenda" + 
                  "(id integer auto_increment not null," + 
                  "id_funcionario integer not null, " + 
                  "id_cliente integer not null, " + 
                  "id_servico integer not null, " + 
                  "data date not null, " + 
                  "hora time not null, " + 
                  "texto varchar(50) not null, " + 
                  "primary key(ID))";
        ps[15] = this.conexao.prepareStatement(sql[15]);
        ps[15].execute();
        
 
    }
            
    
    

    
}
