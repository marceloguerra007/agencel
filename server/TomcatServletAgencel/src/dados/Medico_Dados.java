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
public class Medico_Dados extends Dados
{

	// Função para copiar os dados do ResultSet para o objeto de tabela do médico //
	private void copiaRsparaTab(Medico_Tab objMedico, ResultSet objRs) throws Exception
	{
            objMedico.MED_IN_CODIGO = Integer.parseInt(objRs.getString("MED_IN_CODIGO"));
            objMedico.MED_ST_NOME   = objRs.getString("MED_ST_NOME");
            objMedico.ESP_IN_CODIGO = Integer.parseInt(objRs.getString("ESP_IN_CODIGO"));
	}

	// Função para retornar apenas um registro da tabela de contato //
	public Medico_Tab Selecionar(int pCodigo) throws Exception
	{
		Medico_Tab objContato = new Medico_Tab();
		Con_BD objConn = new Con_BD();

		ConectarBD(objConn);
		ResultSet objRs = objConn.Select("SELECT * FROM CLI_MEDICO WHERE MED_IN_CODIGO = "+ pCodigo);

		objRs.next();

		copiaRsparaTab(objContato, objRs);

		DesconectarDB(objConn);

		return objContato;

	}

	// Função para retornar todos os registro da tabela de contato //
        public void SelecionarTodosRegistros() throws Exception
	{
		Con_BD objConn = new Con_BD();

		ConectarBD(objConn);

		objRsTodos = objConn.Select("SELECT * FROM CLI_MEDICO ORDER BY MED_IN_CODIGO");

                DesconectarDB(objConn);
	}

        public void SelecionarPorEspecialidade(int pCodigoEspec) throws Exception
        {
		Con_BD objConn = new Con_BD();

		ConectarBD(objConn);

		objRsTodos = objConn.Select("SELECT * FROM CLI_MEDICO WHERE ESP_IN_CODIGO = "+pCodigoEspec+" ORDER BY MED_ST_NOME");

                DesconectarDB(objConn);
        }

        public void SelecionarPorCodigoAcesso(String pCodigoAcesso) throws Exception
        {
		Con_BD objConn = new Con_BD();

		ConectarBD(objConn);

		objRsTodos = objConn.Select("SELECT * FROM CLI_MEDICO WHERE MED_ST_CODACESSOMOV like '"+pCodigoAcesso+"'");

                DesconectarDB(objConn);
        }



	// Função para navegar para o próximo registro da tabela  //
	public Medico_Tab ProximoRegistro() throws Exception
	{
		Medico_Tab objMedicoTab = new Medico_Tab();

		if ( objRsTodos.isLast() == false)
		{
			objRsTodos.next();

			copiaRsparaTab(objMedicoTab, objRsTodos);

			return objMedicoTab;
		}
		else
			return null;

	}
/*
	// Função para navegar para o último registro da tabela de contato //
	public ContatoDB UltimoRegistro() throws Exception
	{
		ContatoDB objContato = new ContatoDB();

		objRsTodos.last();

		copiaRsparaTab(objContato, objRsTodos);

		return objContato;

	}

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
