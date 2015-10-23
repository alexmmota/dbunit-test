package br.com.algartelecom.dbunittest.test;

import java.io.FileNotFoundException;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import br.com.algartelecom.dbunittest.Application;
import br.com.algartelecom.dbunittest.model.Cliente;
import br.com.algartelecom.dbunittest.model.Pedido;
import br.com.algartelecom.dbunittest.service.PedidoService;
import br.com.algartelecom.dbunittest.util.DbUnitHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PedidoTest {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DbUnitHelper helper;
	
	@Test
	public void testCreateOrder() throws FileNotFoundException, DatabaseUnitException{
		helper.clearAllTables();
    	helper.executeScenario(DatabaseOperation.INSERT, "new_order");

    	Pedido pedido = new Pedido();
    	pedido.setCliente(new Cliente(1l));
		
    	pedidoService.save(pedido);
    	
		IDataSet dataSet = helper.getFileDataSet("new_order/expected/ct_pedido.xml");
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(dataSet);
		expectedDataSet.addReplacementObject("[NULL]", null);
		expectedDataSet.addReplacementObject("[DATAINCLUSAO]",new LocalDate().toDate());
		IDataSet queryDataSet = helper.getQueryDataSet("ct_pedido");
		
		Assertion.assertEqualsIgnoreCols(expectedDataSet, queryDataSet, "ct_pedido", new String[]{"CTCDPEDIDO"});
	}

	@Test
	public void testAddItem() throws FileNotFoundException, DatabaseUnitException{
		helper.clearAllTables();
    	helper.executeScenario(DatabaseOperation.INSERT, "add_item");

    	pedidoService.addItem(1l, 1l, 4l);
    	    	
		IDataSet dataSet = helper.getFileDataSet("add_item/expected/ct_pedido.xml");
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(dataSet);
		IDataSet queryDataSet = helper.getQueryDataSet("ct_pedido");
		Assertion.assertEquals(expectedDataSet, queryDataSet);
		
		dataSet = helper.getFileDataSet("add_item/expected/ct_pedidoitem.xml");
		expectedDataSet = new ReplacementDataSet(dataSet);
		queryDataSet = helper.getQueryDataSet("ct_pedidoitem");
		Assertion.assertEqualsIgnoreCols(expectedDataSet, queryDataSet, "ct_pedidoitem", new String[]{"CTCDPEDIDOITEM"});

		dataSet = helper.getFileDataSet("add_item/expected/ct_produto.xml");
		expectedDataSet = new ReplacementDataSet(dataSet);
		queryDataSet = helper.getQueryDataSet("ct_produto");
		Assertion.assertEquals(expectedDataSet, queryDataSet);

	}
	
}
