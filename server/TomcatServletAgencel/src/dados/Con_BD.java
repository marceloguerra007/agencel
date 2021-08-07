package dados;

import java.sql.*;

public class Con_BD {

	Connection objConn = null;
	
	// Função para conectar no banco de dados - SQL Server //
	public boolean Conectar_SQLServer(String pDbName, String pUsuario, String pSenha)
	{
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver carregado com sucesso.");
			objConn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName="+pDbName,pUsuario,pSenha);
			System.out.println("Conexão realizada com sucesso!");
			return true;
		}
		catch (Exception e)
		{
			System.out.println("Erro ao conectar ao banco de dados. Erro:"+e.getMessage());
			return false;
		}

	}
	
	// Função para executar comandos do tipo select no banco de dados //
	public ResultSet Select(String pComandoSql) throws Exception
	{	
		Statement objStm = objConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      
		ResultSet objRs = objStm.executeQuery(pComandoSql);

		return objRs;
	}
	
	// Função para executar comandos do tipo insert, update e delete //
	public void ComandoSql(String pComandoSql) throws Exception
	{	
		Statement objStm = objConn.createStatement();
		
		objStm.execute(pComandoSql);
	}
	
	// Função para desconectar do banco de dados //
	public boolean Desconectar() throws Exception
	{
		try
		{
			Statement objStm = objConn.createStatement();

			objStm.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("Erro ao conectar ao banco de dados. Erro:"+e.getMessage());
			return false;
		}
	}

}
