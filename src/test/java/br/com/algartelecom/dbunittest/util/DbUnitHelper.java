package br.com.algartelecom.dbunittest.util;

import java.io.InputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbUnitHelper {
	
	private IDatabaseConnection dbConn;
	private String xmlFolder;

	@Autowired
	public DbUnitHelper(DataSource dataSource) {
		this.xmlFolder = "dbUnitXml";
		try {
			dbConn = new DatabaseDataSourceConnection(dataSource);
			DatabaseConfig config = dbConn.getConfig();
			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Oracle10DataTypeFactory());
		} catch (Exception e) {
			throw new RuntimeException("Erro inicializando DBUnit", e);
		}
	}

	public void executeScenario(DatabaseOperation operation, String scenarioFolder) {
		try {
			execute(operation, "/" + xmlFolder.concat("/").concat(scenarioFolder) + "/all_tables.xml");
		} catch (Exception e) {
			throw new RuntimeException("Erro executando DbUnit", e);
		}
	}

	public void execute(DatabaseOperation operation, String xml) {
		try {
			InputStream is = getClass().getResourceAsStream(xml);
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			IDataSet dataSet = builder.build(is);
			ReplacementDataSet rdataSet = new ReplacementDataSet(dataSet);
			rdataSet.addReplacementObject("[NULL]", null);
			operation.execute(dbConn, rdataSet);
		} catch (Exception e) {
			throw new RuntimeException("Erro executando DbUnit", e);
		}
	}
	
	public IDataSet getQueryDataSet(String table){
		try {
			QueryDataSet queryDataSet = new QueryDataSet(dbConn);
			queryDataSet.addTable(table);
			return queryDataSet;
		} catch (AmbiguousTableNameException e) {
			e.printStackTrace();
			return null;
		}
	}

	public IDataSet getQueryDataSet(String table, String query){
		try {
			QueryDataSet queryDataSet = new QueryDataSet(dbConn);
			queryDataSet.addTable(table, query);
			return queryDataSet;
		} catch (AmbiguousTableNameException e) {
			e.printStackTrace();
			return null;
		}
	}

	public IDataSet getFileDataSet(String fileName){
		try {
			InputStream is = getClass().getResourceAsStream("/" + xmlFolder + "/" + fileName);
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
			return expectedDataSet;
		} catch (DataSetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void clearAllTables(){
		try {
			DatabaseOperation.DELETE_ALL.execute(
				dbConn,
				dbConn.createDataSet(new String[] 
					{
						"CT_CLIENTE",
						"CT_PRODUTO", 
						"CT_PEDIDO", 
						"CT_PEDIDOITEM"
					}
				)
			);
		} catch (DataSetException e) {
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
