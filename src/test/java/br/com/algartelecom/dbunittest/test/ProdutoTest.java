package br.com.algartelecom.dbunittest.test;

import java.io.FileNotFoundException;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.algartelecom.dbunittest.Application;
import br.com.algartelecom.dbunittest.model.Produto;
import br.com.algartelecom.dbunittest.service.ProdutoService;
import br.com.algartelecom.dbunittest.util.DbUnitHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProdutoTest {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private DbUnitHelper helper;
	
	@Test
	public void testSave() throws FileNotFoundException, DatabaseUnitException{
		helper.clearAllTables();

		Produto produto = new Produto();
		produto.setNome("Produto 1");
		produto.setQuantidade(8l);
		produto.setValor(25.99);
		
		produtoService.save(produto);

		IDataSet dataSet = helper.getFileDataSet("save_produto/expected/ct_produto.xml");
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(dataSet);
		IDataSet queryDataSet = helper.getQueryDataSet("ct_produto");
		Assertion.assertEqualsIgnoreCols(expectedDataSet, queryDataSet, "ct_produto", new String[]{"CTCDPRODUTO"});
	}
	
	@Test
	public void testAddProduto() throws FileNotFoundException, DatabaseUnitException{
		helper.clearAllTables();
    	helper.executeScenario(DatabaseOperation.INSERT, "add_produto");

    	produtoService.add(1l, 3l);

		IDataSet dataSet = helper.getFileDataSet("add_produto/expected/ct_produto.xml");
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(dataSet);
		IDataSet queryDataSet = helper.getQueryDataSet("ct_produto");
		Assertion.assertEqualsIgnoreCols(expectedDataSet, queryDataSet, "ct_produto", new String[]{"CTCDPRODUTO"});
	}	
	
	@Test
	public void testRemove() throws FileNotFoundException, DatabaseUnitException{
		helper.clearAllTables();
    	helper.executeScenario(DatabaseOperation.INSERT, "remove_produto");

    	produtoService.remove(1l);

		IDataSet dataSet = helper.getFileDataSet("remove_produto/expected/ct_produto.xml");
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(dataSet);
		IDataSet queryDataSet = helper.getQueryDataSet("ct_produto");
		Assertion.assertEqualsIgnoreCols(expectedDataSet, queryDataSet, "ct_produto", new String[]{"CTCDPRODUTO"});
	}	

}
