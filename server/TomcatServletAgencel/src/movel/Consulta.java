/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package movel;

import dados.Consulta_Dados;
import dados.Consulta_Tab;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class Consulta
{
    public String PesquisarDiasDisponiveis(int pMedico, Date pDataPsq)
    {
        Consulta_Tab objTabConsulta = new Consulta_Tab();
        Consulta_Dados objDadosConsulta = new Consulta_Dados();
        Movel objMovel = new Movel();

        try
	{
            objDadosConsulta.PesquisarDiasDisponiveis(pMedico, pDataPsq, UltimoDiaMes(pDataPsq));

            objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_DIASDISP);

            while (objTabConsulta != null)
            {
                objMovel.adicionaCampoResposta(String.valueOf(objTabConsulta.DDC_IN_CODIGO));
                objMovel.adicionaCampoResposta(objTabConsulta.DDC_ST_DIAS);

                objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_DIASDISP);
                objMovel.proximoRegistroResposta(objTabConsulta != null);
            }

        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao pesquisar dias disponiveis para consulta. "+erro.getMessage());
	}

        return objMovel.getTextoResposta();
    }

    public String PesquisarHorariosDisponiveis(int pMedico, Date pDataPsq)
    {
        Consulta_Tab objTabConsulta = new Consulta_Tab();
        Consulta_Dados objDadosConsulta = new Consulta_Dados();
        Movel objMovel = new Movel();

        try
	{
            objDadosConsulta.PesquisarHorariosDisponiveis(pMedico, pDataPsq);

            objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_HORARIOSDISP);

            while (objTabConsulta != null)
            {
                objMovel.adicionaCampoResposta(String.valueOf(objTabConsulta.AGE_IN_CODIGO));
                objMovel.adicionaCampoResposta(objTabConsulta.HDC_ST_HORARIOS);

                objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_HORARIOSDISP);
                objMovel.proximoRegistroResposta(objTabConsulta != null);
            }
        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao pesquisar os horários disponiveis para consulta. "+erro.getMessage());
	}

        return objMovel.getTextoResposta();
    }

    public String PesquisarHorariosAgendados(int pMedico, Date pDataPsq)
    {
        return "";
    }

    public void Marcar(int pCodigoAgenda, int pCodigoPaciente) throws Exception
    {
        Consulta_Dados objDadosConsulta = new Consulta_Dados();

        objDadosConsulta.Marcar(pCodigoAgenda, pCodigoPaciente);
    }

    public void Confirmar(int pCodigoAgenda, int pCodigoPaciente) throws Exception
    {
        Consulta_Dados objDadosConsulta = new Consulta_Dados();

        objDadosConsulta.Confirmar(pCodigoAgenda, pCodigoPaciente);
    }

    public void Cancelar(int pCodigoAgenda, int pCodigoPaciente) throws Exception
    {
        Consulta_Dados objDadosConsulta = new Consulta_Dados();

        objDadosConsulta.Cancelar(pCodigoAgenda, pCodigoPaciente);
    }

    public String PesquisarConsultasRealizadas(int pCodPaciente, Date pDataPsq)
    {
        Consulta_Tab objTabConsulta = new Consulta_Tab();
        Consulta_Dados objDadosConsulta = new Consulta_Dados();
        Movel objMovel = new Movel();

        try
	{
            objDadosConsulta.PesquisarConsultasRealizadas(pCodPaciente, pDataPsq);

            objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_CONSREALIZADAS);

            while (objTabConsulta != null)
            {
                objMovel.adicionaCampoResposta(String.valueOf(objTabConsulta.AGE_IN_REALIZADASCODIGO));
                objMovel.adicionaCampoResposta(objTabConsulta.AGE_ST_CONSREALIZADAS);

                objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_CONSREALIZADAS);
                objMovel.proximoRegistroResposta(objTabConsulta != null);
            }

        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao pesquisar consultas realizadas. "+erro.getMessage());
	}

        return objMovel.getTextoResposta();
    }

    public String PesquisarConsultasPendentes(int pCodPaciente, Date pDataPsq)
    {
        Consulta_Tab objTabConsulta = new Consulta_Tab();
        Consulta_Dados objDadosConsulta = new Consulta_Dados();
        Movel objMovel = new Movel();

        try
	{
            objDadosConsulta.PesquisarConsultasPendentes(pCodPaciente, pDataPsq);

            objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_CONSPENDENTES);

            while (objTabConsulta != null)
            {
                objMovel.adicionaCampoResposta(String.valueOf(objTabConsulta.AGE_IN_CODIGO));
                objMovel.adicionaCampoResposta(objTabConsulta.AGE_ST_CONSPENDENTES);

                objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_CONSPENDENTES);
                objMovel.proximoRegistroResposta(objTabConsulta != null);
            }

        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao pesquisar consultas pendentes. "+erro.getMessage());
	}

        return objMovel.getTextoResposta();
    }

    public String PesquisarConsultasPorMedico(int pCodPaciente, Date pDataPsq)
    {
        Consulta_Tab objTabConsulta = new Consulta_Tab();
        Consulta_Dados objDadosConsulta = new Consulta_Dados();
        Movel objMovel = new Movel();

        String msg="1";

        try
	{
            msg="2";
            objDadosConsulta.PesquisarConsultasPorMedico(pCodPaciente, pDataPsq);
            msg="3";
            objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_CONSPENDENTES);
            msg="4";
            while (objTabConsulta != null)
            {
                msg="4.1";
                objMovel.adicionaCampoResposta(String.valueOf(objTabConsulta.AGE_IN_CODIGO));
                objMovel.adicionaCampoResposta(objTabConsulta.AGE_ST_CONSPENDENTES);
                msg="4.2";
                objTabConsulta = objDadosConsulta.ProximoRegistro(objDadosConsulta.REG_CONSPENDENTES);
                msg="4.3";
                objMovel.proximoRegistroResposta(objTabConsulta != null);
                msg="4.4";
            }
            msg="5";
        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao pesquisar consultas por medico. "+erro.getMessage()+erro.getLocalizedMessage());
	}

        return objMovel.getTextoResposta()+msg;
    }

    // Funções uteis
    public Date UltimoDiaMes(Date pData)
    {
        Calendar calData = Calendar.getInstance();
        calData.setTime(pData);

        // Obs: Contador de mês começa em 0.
        int iMes = calData.get(Calendar.MONTH);
        int iAno = calData.get(Calendar.YEAR);

        // Jogar o proximo mês da data pesquisada.
        iMes += 2;

        // Se a data do parametro for dezembro então jogar próximo mês como janeiro.
        if (iMes >= 12)
        {
            iMes = 1;
            iAno++;
        }

        // Ajustar data para o primeiro dia do proximo mês para voltar um dia e obter o ultimo dia do mês.
        String sPrimeiroDia = String.valueOf(iAno)+"/"+String.valueOf(iMes)+"/01";
        Date dtUltimoDiaMes = new Date(sPrimeiroDia);

        dtUltimoDiaMes.setDate(dtUltimoDiaMes.getDate() - 1);
        
        return dtUltimoDiaMes;


    }
}
