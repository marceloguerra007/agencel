/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marcelo
 */
public class Paciente_Dados extends Dados
{
	// Função para copiar os dados do ResultSet para o objeto de dados da Paciente //
	private void copiaRsparaTab(Paciente_Tab objPac, ResultSet objRs) throws Exception
	{
            objPac.PAC_IN_CODIGO = Integer.parseInt(objRs.getString("PAC_IN_CODIGO"));
            objPac.PAC_ST_NOME   = objRs.getString("PAC_ST_NOME");
	}

	// Função para retornar apenas um registro da tabela de Paciente //
	public void SelecionarPorCodigoAcesso(String pCodigo) throws Exception
	{
		Con_BD objConn = new Con_BD();

		ConectarBD(objConn);

                objRsTodos = objConn.Select("SELECT PAC_IN_CODIGO, PAC_ST_NOME FROM CLI_PACIENTE WHERE PAC_ST_CODACESSOMOV Like '"+ pCodigo+"'");

		DesconectarDB(objConn);
	}

        public void SelecionarConvenio(int pCodigo) throws Exception
        {
            Con_BD objConn = new Con_BD();

            ConectarBD(objConn);

            objRsTodos = objConn.Select("SELECT CNV_IN_CODIGO FROM CLI_PACIENTE WHERE PAC_IN_CODIGO = "+ String.valueOf(pCodigo));

            DesconectarDB(objConn);
        }

	// Função para navegar para o próximo registro da tabela  //
	public Paciente_Tab ProximoRegistro() throws Exception, SQLException
	{
		Paciente_Tab objPacienteTab = new Paciente_Tab();

		if ( objRsTodos.isLast() == false)
		{
			objRsTodos.next();

			copiaRsparaTab(objPacienteTab, objRsTodos);

			return objPacienteTab;
		}
		else
			return null;

	}

	// Função para navegar para o próximo registro da tabela  //
	public int GetConvenio() throws Exception
	{
		if ( objRsTodos.isLast() == false)
		{
			objRsTodos.next();

			return Integer.parseInt(objRsTodos.getString("CNV_IN_CODIGO"));
		}
		else
                    return 0;

	}

}
