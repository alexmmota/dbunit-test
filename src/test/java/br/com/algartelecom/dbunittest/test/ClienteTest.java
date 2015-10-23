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
import br.com.algartelecom.dbunittest.model.Cliente;
import br.com.algartelecom.dbunittest.service.ClienteService;
import br.com.algartelecom.dbunittest.util.DbUnitHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ClienteTest {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private DbUnitHelper helper;
	
	@Test
	public void testSave() throws FileNotFoundException, DatabaseUnitException{
		helper.clearAllTables();

		Cliente cliente = new Cliente();
		cliente.setNome("Alex Mota");
		cliente.setEndereco("Rua Teste, 666");
		cliente.setTelefone("3499999999");
		clienteService.save(cliente);

		IDataSet dataSet = helper.getFileDataSet("save_cliente/expected/ct_cliente.xml");
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(dataSet);
		IDataSet queryDataSet = helper.getQueryDataSet("ct_cliente");
		Assertion.assertEqualsIgnoreCols(expectedDataSet, queryDataSet, "ct_cliente", new String[]{"CTCDCLIENTE"});
	}
	
	@Test
	public void testChange() throws FileNotFoundException, DatabaseUnitException{
		helper.clearAllTables();
    	helper.executeScenario(DatabaseOperation.INSERT, "change_cliente");

    	clienteService.changeAddress(1l, "Novo Endereço, 565656");

		IDataSet dataSet = helper.getFileDataSet("change_cliente/expected/ct_cliente.xml");
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(dataSet);
		IDataSet queryDataSet = helper.getQueryDataSet("ct_cliente");
		Assertion.assertEqualsIgnoreCols(expectedDataSet, queryDataSet, "ct_cliente", new String[]{"CTCDCLIENTE"});
	}	
	
	@Test
	public void testRemove() throws FileNotFoundException, DatabaseUnitException{
		helper.clearAllTables();
    	helper.executeScenario(DatabaseOperation.INSERT, "remove_cliente");

    	clienteService.remove(1l);

		IDataSet dataSet = helper.getFileDataSet("remove_cliente/expected/ct_cliente.xml");
		ReplacementDataSet expectedDataSet = new ReplacementDataSet(dataSet);
		IDataSet queryDataSet = helper.getQueryDataSet("ct_cliente");
		Assertion.assertEqualsIgnoreCols(expectedDataSet, queryDataSet, "ct_cliente", new String[]{"CTCDCLIENTE"});
	}

}
