/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorios;
import Banco.Conexao;
import java.awt.BorderLayout;
import java.io.InputStream; 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.util.HashMap; 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
/**
 *
 * @author Formigao
 */
public class GeraRelatorio
{
    
        
    Connection conexao;
    
    ResultSet rst;
    
    
 
    //Cria um form para mostrar os relatórios, em tela cheia, fechando com o dispose
    public void verRelat(String titulo, JasperPrint print){
        
        /*
        *Cria um JRViewer para exibir o relatório
        *UM JRViewer é uma JPanel
        */
        
        
        JRViewer viewer = new JRViewer(print);
        
        
        //Cria o JFrame
        JFrame frameRelatorio = new JFrame(titulo);
        
        //Adiciona o JRViewer no Frame
        frameRelatorio.add(viewer, BorderLayout.CENTER);
        
        //Configura o tamanho padrão do JFrame
        frameRelatorio.setSize(500, 500);
        
        //Maxima o jframe para ocupar a tela toda
        frameRelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Configura a operação padrão quando o JFrame for fechado
        frameRelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Exibe o JFrame
        frameRelatorio.setVisible(true);
        
        
        
    }
    
    public void gerarRelatoriosFornecedores() throws Exception
    { 
    JasperPrint rel=null;
    //rel e um nome para demostra o relatorio. 
    //relatorio que traz todas informações. 
    try
    { 
        

        this.conexao = Conexao.getConexao();
        String sql = "select fo.*, ci.nome cidade from fornecedores fo inner join cidades ci on fo.id_cidade = ci.id";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ResultSet rs=ps.executeQuery(sql);//executa o sql 
        JRResultSetDataSource tst = new JRResultSetDataSource(rs);

        //JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Relatorios/relFornecedores.jasper"));
        InputStream inImputStream = getClass().getResourceAsStream("/Relatorios/relFornecedores.jasper");
        HashMap map = new HashMap();
        rel = JasperFillManager.fillReport(inImputStream,map,tst);
        

        verRelat("Relatório de Fornecedores", rel);
        
        
    }
    catch (JRException e)
    { 
        JOptionPane.showMessageDialog(null, e);
         
    } 
    

    } 
    
    
    
    public void gerarRelatoriosFuncionarios() throws Exception
    { 
    JasperPrint rel=null;
    //rel e um nome para demostra o relatorio. 
    //relatorio que traz todas informações. 
    try
    { 
        this.conexao = Conexao.getConexao();
        String sql = "select fu.*, ci.nome cidade, ca.cargo cargo from funcionarios fu inner join cidades ci on fu.id_cidade = ci.id inner join cargos ca on fu.id_cargo = ca.id";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ResultSet rs=ps.executeQuery(sql);//executa o sql 
        JRResultSetDataSource tst= new JRResultSetDataSource(rs);
        

        
      
        JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Relatorios/relFuncionarios.jasper"));
        HashMap map = new HashMap();
        
        jr.getTitle();
        rel = JasperFillManager.fillReport(jr,map,tst);
        
        verRelat("Relatório de Funcionários", rel);
        
    }
    catch (JRException e)
    { 
        JOptionPane.showMessageDialog(null, e);
         
    } 
    
    

    } 
    
    public void gerarRelatoriosProdutos() throws Exception
    { 
    JasperPrint rel=null;
    //rel e um nome para demostra o relatorio. 
    //relatorio que traz todas informações. 
    try
    { 
        this.conexao = Conexao.getConexao();
        String sql = "select pr.*, fr.fantasia from produtos pr inner join fornecedores fr on pr.id_fornecedor = fr.id";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ResultSet rs=ps.executeQuery(sql);//executa o sql 
        JRResultSetDataSource tst= new JRResultSetDataSource(rs);
        

        
      
        JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Relatorios/relProdutos.jasper"));
        HashMap map = new HashMap();
        rel = JasperFillManager.fillReport(jr,map,tst);
        
        verRelat("Relatório de Produtos", rel);
        
    }
    catch (JRException e)
    { 
        JOptionPane.showMessageDialog(null, e);
         
    } 
    

    } 
    
    
    public void gerarRelatoriosServicos() throws Exception
    { 
    JasperPrint rel=null;
    //rel e um nome para demostra o relatorio. 
    //relatorio que traz todas informações. 
    try
    { 
        this.conexao = Conexao.getConexao();
        String sql = "select * from servicos";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ResultSet rs=ps.executeQuery(sql);//executa o sql 
        JRResultSetDataSource tst= new JRResultSetDataSource(rs);
        

        
      
        JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Relatorios/relServicos.jasper"));
        HashMap map = new HashMap();
        rel = JasperFillManager.fillReport(jr,map,tst);
        
        verRelat("Relatório de Serviços", rel);
        
    }
    catch (JRException e)
    { 
        JOptionPane.showMessageDialog(null, e);
         
    } 
    

    } 
    
    
    public void gerarRelatoriosClientes() throws Exception
    { 
    JasperPrint rel=null;
    //rel e um nome para demostra o relatorio. 
    //relatorio que traz todas informações. 
    try
    { 
        this.conexao = Conexao.getConexao();
        String sql = "select cl.*, ci.nome cidade from clientes cl inner join cidades ci on cl.id_cidade = ci.id";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ResultSet rs=ps.executeQuery(sql);//executa o sql 
        JRResultSetDataSource tst= new JRResultSetDataSource(rs);
        

        
      
        JasperReport jr = (JasperReport)JRLoader.loadObject(getClass().getResource("/Relatorios/relClientess.jasper"));
        HashMap map = new HashMap();
        rel = JasperFillManager.fillReport(jr,map,tst);
        
        verRelat("Relatório de Clientes", rel);
        
    }
    catch (JRException e)
    { 
        JOptionPane.showMessageDialog(null, e);
         
    } 
    

    } 
    

    
    

    
    
}

    

