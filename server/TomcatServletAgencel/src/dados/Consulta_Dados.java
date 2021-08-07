/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import movel.Paciente;

/**
 *
 * @author Marcelo
 */
public class Consulta_Dados extends Dados
{
        public static final int REG_DIASDISP = 1;
        public static final int REG_HORARIOSDISP = 2;
        public static final int REG_CONSREALIZADAS = 3;
        public static final int REG_CONSPENDENTES = 4;
        public static final String FORMATO_DATA = "dd/MM/yyyy";

        // Função para copiar os dados do ResultSet para o objeto de dados da consulta //
	private void copiaRsparaTab_DiasDisp(Consulta_Tab objCons, ResultSet objRs) throws Exception
	{
            objCons.DDC_IN_CODIGO = Integer.parseInt(objRs.getString("DDC_IN_CODIGO"));
            objCons.DDC_ST_DIAS   = objRs.getString("DDC_ST_DIAS");
	}

        // Função para copiar os dados do ResultSet para o objeto de dados da consulta //
	private void copiaRsparaTab_HorasDisp(Consulta_Tab objCons, ResultSet objRs) throws Exception
	{
            objCons.AGE_IN_CODIGO   = Integer.parseInt(objRs.getString("AGE_IN_CODIGO"));
            objCons.HDC_ST_HORARIOS = FormataHorarios(objRs.getString("HDC_ST_HORARIOS"));
	}

        // Função para copiar os dados do ResultSet para o objeto de dados da consulta //
	private void copiaRsparaTab_ConsRealizadas(Consulta_Tab objCons, ResultSet objRs) throws Exception
	{
            objCons.AGE_IN_REALIZADASCODIGO = Integer.parseInt(objRs.getString("AGE_IN_REALIZADASCODIGO"));
            objCons.AGE_ST_CONSREALIZADAS   = FormataHorarios(objRs.getString("AGE_ST_CONSREALIZADAS"));
	}

        // Função para copiar os dados do ResultSet para o objeto de dados da consulta //
	private void copiaRsparaTab_ConsPendentes(Consulta_Tab objCons, ResultSet objRs) throws Exception
	{
            objCons.AGE_IN_CODIGO        = Integer.parseInt(objRs.getString("AGE_IN_CODIGO"));
            objCons.AGE_ST_CONSPENDENTES = FormataHorarios(objRs.getString("AGE_ST_CONSPENDENTES"));
	}

        // Função necessário pois o banco de dados retornar os minutos com apenas um algorismo.
        private String FormataHorarios(String pHorarios)
        {
            String sHorarioFormatado="";

            sHorarioFormatado = pHorarios;
            /*
            for (int i=0;i<pHorarios.length();i++)
            {
                // Campo de minuto
		if ( pHorarios.substring(i,i+1).equals(":") )
                {
                    // Caso o primeiro algorismos seja zero e após isso seja o final da string.
                    if ( pHorarios.substring(i+1,i+2).equals("0") )
                    {
                        sHorarioFormatado = pHorarios.substring(0,i+2)+"0\n"+pHorarios.substring(i+3,pHorarios.length()-1);

                        return sHorarioFormatado;
                    }
                    else
                        return sHorarioFormatado;
                }
            }
            */
            return sHorarioFormatado;
        }

	// Função para retornar os dias disponiveis do médico e do ano/mês solicitados //
        public void PesquisarDiasDisponiveis(int pMedico, Date pDataPsq, Date pDataFinal) throws Exception
	{
		Con_BD objConn = new Con_BD();
                String sSQL = "";

                SimpleDateFormat sdfFormataData = new SimpleDateFormat(FORMATO_DATA);

                sSQL = "SET DATEFORMAT DMY; ";
                
                sSQL +=  "SELECT ";
                sSQL +="  row_number() over(order by AGE_DT_DATA) AS DDC_IN_CODIGO, ";
                sSQL +="  CONVERT(VARCHAR(2), DATEPART(dd, DIAS.AGE_DT_DATA)) AS DDC_ST_DIAS ";
                sSQL +="FROM ";
                sSQL +="( select DISTINCT AGE_DT_DATA FROM CLI_AGENDA ";
                sSQL +="   WHERE MED_IN_CODIGO = "+ pMedico;
                sSQL +="     AND AGE_DT_DATA >= '"+sdfFormataData.format(pDataPsq)+"' ";
                sSQL +="     AND AGE_DT_DATA <='"+sdfFormataData.format(pDataFinal)+"' ";
                sSQL +="     AND AGE_ST_SITUACAO = 'LV' ";
                sSQL +=") AS DIAS ";

		ConectarBD(objConn);

		objRsTodos = objConn.Select(sSQL);

                DesconectarDB(objConn);
	}

	// Função para retornar os dias disponiveis do médico e do ano/mês solicitados //
        public void PesquisarHorariosDisponiveis(int pMedico, Date pDataPsq) throws Exception
	{
		Con_BD objConn = new Con_BD();
                String sSQL = "";

                SimpleDateFormat sdfFormataData = new SimpleDateFormat(FORMATO_DATA);

                sSQL = "SET DATEFORMAT DMY; ";

                sSQL +=  "SELECT ";
                sSQL += "  AGE_IN_CODIGO, ";
                sSQL += "  CONVERT(VARCHAR(2), DATEPART(hh,AGE_DT_HORAINI))+':'+ CONVERT(VARCHAR(2),DATEPART(n, AGE_DT_HORAINI)) AS HDC_ST_HORARIOS ";
                sSQL += " FROM CLI_AGENDA ";
                sSQL += "WHERE MED_IN_CODIGO = "+pMedico;
                sSQL += "  AND AGE_DT_DATA = '"+sdfFormataData.format(pDataPsq)+"' ";
                sSQL += "  AND AGE_ST_SITUACAO = 'LV' ";
                sSQL += "ORDER BY AGE_DT_HORAINI ";

		ConectarBD(objConn);

		objRsTodos = objConn.Select(sSQL);

                DesconectarDB(objConn);
	}

	// Função para retornar as 3 ultimas consultas realizadas //
        public void PesquisarConsultasRealizadas(int pPaciente, Date pDataPsq) throws Exception
	{
		Con_BD objConn = new Con_BD();
                String sSQL = "";

                SimpleDateFormat sdfFormataData = new SimpleDateFormat(FORMATO_DATA);

                sSQL = "SET DATEFORMAT DMY; ";

                sSQL +=" SELECT TOP 3";
                sSQL +="   row_number() over(ORDER BY AGE_IN_CODIGO) AS AGE_IN_REALIZADASCODIGO,";
                sSQL +="   (M.MED_ST_NOME+'\n'+ ";
                sSQL +="   LTRIM(CONVERT(VARCHAR, AGE_DT_DATA, 103))+' '+";
                sSQL +="   CONVERT(VARCHAR(2), DATEPART(hh,AGE_DT_HORAINI))+':'+";
                sSQL +="   CONVERT(VARCHAR(2),DATEPART(n, AGE_DT_HORAINI))) AS AGE_ST_CONSREALIZADAS";
                sSQL +="  FROM CLI_AGENDA A, CLI_MEDICO M";
                sSQL +=" WHERE A.MED_IN_CODIGO = M.MED_IN_CODIGO";
                sSQL +="   AND PAC_IN_CODIGO = "+pPaciente;
                sSQL +="   AND AGE_DT_DATA < '"+sdfFormataData.format(pDataPsq)+"'";
                sSQL +="   AND AGE_ST_SITUACAO NOT IN ('LV','CC')";
                sSQL +=" ORDER BY AGE_DT_DATA DESC, AGE_DT_HORAINI DESC";

		ConectarBD(objConn);

		objRsTodos = objConn.Select(sSQL);

                DesconectarDB(objConn);
	}

        public void PesquisarConsultasPendentes(int pPaciente, Date pDataPsq) throws Exception
	{
		Con_BD objConn = new Con_BD();
                String sSQL = "";

                SimpleDateFormat sdfFormataData = new SimpleDateFormat(FORMATO_DATA);

                sSQL = "SET DATEFORMAT DMY; ";
                
                sSQL +=" SELECT";
                sSQL +="   AGE_IN_CODIGO,";
                sSQL +="   (M.MED_ST_NOME+'\n'+ ";
                sSQL +="   LTRIM(CONVERT(VARCHAR, AGE_DT_DATA, 103))+' '+";
                sSQL +="   CONVERT(VARCHAR(2), DATEPART(hh,AGE_DT_HORAINI))+':'+";
                sSQL +="   CONVERT(VARCHAR(2),DATEPART(n, AGE_DT_HORAINI))";
                sSQL +="   + CASE WHEN AGE_ST_SITUACAO = 'CF' THEN '\nConfirmada' ELSE '' END ";
                sSQL +="   ) AS AGE_ST_CONSPENDENTES";
                sSQL +="  FROM CLI_AGENDA A, CLI_MEDICO M";
                sSQL +=" WHERE A.MED_IN_CODIGO = M.MED_IN_CODIGO";
                sSQL +="   AND PAC_IN_CODIGO = "+pPaciente;
                sSQL +="   AND (AGE_DT_DATA > '"+sdfFormataData.format(pDataPsq)+"'";
                sSQL +="        OR (AGE_DT_DATA = '"+sdfFormataData.format(pDataPsq)+"' AND  AGE_DT_HORAINI > dateadd(day, -datediff(day, 0, getdate()), getdate()) ))";
                sSQL +="   AND AGE_ST_SITUACAO NOT IN ('LV','CC')";
                sSQL +=" ORDER BY AGE_DT_DATA DESC, AGE_DT_HORAINI DESC";

                ConectarBD(objConn);

		objRsTodos = objConn.Select(sSQL);

                DesconectarDB(objConn);
                
        }

        public void PesquisarConsultasPorMedico(int pMedico, Date pDataPsq) throws Exception
	{
		Con_BD objConn = new Con_BD();
                String sSQL = "";

                SimpleDateFormat sdfFormataData = new SimpleDateFormat(FORMATO_DATA);

                sSQL = "SET DATEFORMAT DMY; ";


                 sSQL +=" SELECT";
                 sSQL +="  AGE_IN_CODIGO,";
                 sSQL +="  (LTRIM(CONVERT(VARCHAR, AGE_DT_DATA, 103))+' '+";
                 sSQL +="  CONVERT(VARCHAR(2), DATEPART(hh,AGE_DT_HORAINI))+':'+";
                 sSQL +="  CONVERT(VARCHAR(2),DATEPART(n, AGE_DT_HORAINI))";
                 sSQL +="  + CASE WHEN AGE_ST_SITUACAO = 'CF' THEN '\nConfirmada' ELSE '' END ";
                 sSQL +="  ) AS AGE_ST_CONSPENDENTES";
                 sSQL +=" FROM CLI_AGENDA A, CLI_MEDICO M";
                 sSQL +=" WHERE A.MED_IN_CODIGO = M.MED_IN_CODIGO";
                 sSQL +="   AND M.MED_IN_CODIGO = "+ pMedico;
		 sSQL +="   AND AGE_DT_DATA = '"+sdfFormataData.format(pDataPsq)+"'";
                 sSQL +="   AND AGE_ST_SITUACAO NOT IN ('LV','CC')";
                 sSQL +=" ORDER BY AGE_DT_DATA DESC, AGE_DT_HORAINI DESC";

                ConectarBD(objConn);

		objRsTodos = objConn.Select(sSQL);

                DesconectarDB(objConn);

        }


        // Função para navegar para o próximo registro da tabela  //
	public Consulta_Tab ProximoRegistro(int pTipoRegistro) throws Exception
	{
		Consulta_Tab objConsultaTab = new Consulta_Tab();

		if ( objRsTodos.isLast() == false)
		{
			objRsTodos.next();

                        if (pTipoRegistro == REG_DIASDISP)
                        {
                            copiaRsparaTab_DiasDisp(objConsultaTab, objRsTodos);
                        }
                        else if (pTipoRegistro == REG_HORARIOSDISP)
                        {
                            copiaRsparaTab_HorasDisp(objConsultaTab, objRsTodos);
                        }
                        else if (pTipoRegistro == REG_CONSREALIZADAS)
                        {
                            copiaRsparaTab_ConsRealizadas(objConsultaTab, objRsTodos);
                        }
                        else if (pTipoRegistro == REG_CONSPENDENTES)
                        {
                            copiaRsparaTab_ConsPendentes(objConsultaTab, objRsTodos);
                        }

			return objConsultaTab;
		}
		else
			return null;

	}

	// Função para agendar a consulta //
	public boolean Marcar(int pCodigoAgenda, int pCodigoPaciente) throws Exception
	{

                int iCodigoConvenio = 0;

                Paciente objPaciente = new Paciente();

                iCodigoConvenio = objPaciente.PesquisarConvenio(pCodigoPaciente);

                Con_BD objConn = new Con_BD();

		ConectarBD(objConn);

		String vSQL="";

                vSQL += " UPDATE CLI_AGENDA ";
                vSQL += " SET PAC_IN_CODIGO = "+ String.valueOf(pCodigoPaciente);
                vSQL += "    ,CNV_IN_CODIGO = "+ String.valueOf(iCodigoConvenio);
                vSQL += "   ,AGE_ST_SITUACAO = 'RE' ";
                vSQL += " WHERE AGE_IN_CODIGO = "+ String.valueOf(pCodigoAgenda);

		System.out.println(vSQL);

		objConn.ComandoSql(vSQL);

		DesconectarDB(objConn);

		return true;
	}


	// Função para confirmar a consulta //
	public boolean Confirmar(int pCodigoAgenda, int pCodigoPaciente) throws Exception
	{
                Con_BD objConn = new Con_BD();

		ConectarBD(objConn);

		String vSQL="";

                vSQL += " UPDATE CLI_AGENDA";
                vSQL += "    SET AGE_ST_SITUACAO = 'CF'";
                vSQL += "  WHERE AGE_IN_CODIGO = "+ String.valueOf(pCodigoAgenda);
                vSQL += "    AND PAC_IN_CODIGO = "+ String.valueOf(pCodigoPaciente);

		System.out.println(vSQL);

		objConn.ComandoSql(vSQL);

		DesconectarDB(objConn);

		return true;
	}

	// Função para cancelar a consulta //
	public boolean Cancelar(int pCodigoAgenda, int pCodigoPaciente) throws Exception
	{
                Con_BD objConn = new Con_BD();

		ConectarBD(objConn);

		String vSQL="";

                vSQL += " UPDATE CLI_AGENDA";
                vSQL += "    SET AGE_ST_SITUACAO = 'CC'";
                vSQL += "      , PAC_IN_CODIGO = NULL";
                vSQL += "      , CNV_IN_CODIGO = NULL";
                vSQL += "  WHERE AGE_IN_CODIGO = "+ String.valueOf(pCodigoAgenda);
                vSQL += "    AND PAC_IN_CODIGO = "+ String.valueOf(pCodigoPaciente);

		System.out.println(vSQL);

		objConn.ComandoSql(vSQL);

		DesconectarDB(objConn);

		return true;
	}
}
