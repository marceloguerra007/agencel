/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.sql.*;

/**
 *
 * @author Marcelo
 */
public class Especialidade_Dados extends Dados
{

	// Função para copiar os dados do ResultSet para o objeto de dados da especialidade //
	private void copiaRsparaTab(Especialidade_Tab objEspec, ResultSet objRs) throws Exception
	{
            objEspec.ESP_IN_CODIGO = Integer.parseInt(objRs.getString("ESP_IN_CODIGO"));
            objEspec.ESP_ST_NOME   = objRs.getString("ESP_ST_NOME");
	}

	// Função para retornar apenas um registro da tabela de especialidade //
	public Especialidade_Tab Selecionar(int pCodigo) throws Exception
	{
		Especialidade_Tab objEspecTab = new Especialidade_Tab();
		Con_BD objConn = new Con_BD();

		ConectarBD(objConn);
		ResultSet objRs = objConn.Select("SELECT * FROM CLI_ESPECIALIDADE WHERE ESP_IN_CODIGO = "+ pCodigo);

		objRs.next();

		copiaRsparaTab(objEspecTab, objRs);

		DesconectarDB(objConn);

		return objEspecTab;
	}

	// Função para retornar todos os registro da tabela de especialidade //
        public void SelecionarTodosRegistros() throws Exception
	{
		Con_BD objConn = new Con_BD();

		ConectarBD(objConn);

		objRsTodos = objConn.Select("SELECT * FROM CLI_ESPECIALIDADE ORDER BY ESP_IN_CODIGO");

                DesconectarDB(objConn);
	}

	// Função para navegar para o próximo registro da tabela  //
	public Especialidade_Tab ProximoRegistro() throws Exception
	{
		Especialidade_Tab objEspecialidadeTab = new Especialidade_Tab();

		if ( objRsTodos.isLast() == false)
		{
			objRsTodos.next();

			copiaRsparaTab(objEspecialidadeTab, objRsTodos);

			return objEspecialidadeTab;
		}
		else
			return null;

	}
/*
	// Função para incluir um novo contato //
	public boolean Incluir(ContatoDB DadosContato) throws Exception
	{
		Conn objConn = new Conn();

		ConectarBD(objConn);

		String vSQL="";

		vSQL = "INSERT INTO AGD_CONTATO (CON_ST_NOME, CON_ST_EMAIL, CON_ST_FONE) VALUES ";
		vSQL += "('"+DadosContato.CON_ST_NOME+"', '"+DadosContato.CON_ST_EMAIL+"' , '"+DadosContato.CON_ST_FONE+"')";

		System.out.println(vSQL);

		objConn.ComandoSql(vSQL);

		DesconectarDB(objConn);

		return true;

	}

	// Função para alterar os dados de um contato //
	public boolean Alterar(ContatoDB DadosContato) throws Exception
	{
		Conn objConn = new Conn();

		ConectarBD(objConn);

		String vSQL="";

		vSQL  = "UPDATE AGD_CONTATO SET CON_ST_NOME='"+DadosContato.CON_ST_NOME+"', CON_ST_EMAIL='"+DadosContato.CON_ST_EMAIL+"', CON_ST_FONE='"+DadosContato.CON_ST_FONE+"'";
		vSQL += " WHERE CON_IN_CODIGO = "+DadosContato.CON_IN_CODIGO;

		System.out.println(vSQL);

		objConn.ComandoSql(vSQL);

		DesconectarDB(objConn);

		return true;
	}

	// Função para excluir contato //
	public boolean Excluir(int pCodigo) throws Exception
	{

		Conn objConn = new Conn();

		ConectarBD(objConn);

		String vSQL="";

		vSQL  = "DELETE FROM AGD_CONTATO ";
		vSQL += "WHERE CON_IN_CODIGO = "+ pCodigo;

		System.out.println(vSQL);

		objConn.ComandoSql(vSQL);

		DesconectarDB(objConn);

		return true;
	}
*/

}
