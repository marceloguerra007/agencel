/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.sql.*;

/**
 *
 * @author Marcelo
 *
 * Esta classe de ser pai das classe x_Dados.
 */
public class Dados {

     	// Objeto ResultSet para armazenar todos os registros //
	ResultSet objRsTodos;

	// Função para conectar com o banco do tipo SQL Server //
	protected boolean ConectarBD(Con_BD objConn)
	{

		objConn.Conectar_SQLServer("AGENCEL", "ag", "agag");

		return true;
	}

	// Função para desconectar do banco //
	protected void DesconectarDB(Con_BD objConn) throws Exception
	{
		objConn.Desconectar();
	}


}
