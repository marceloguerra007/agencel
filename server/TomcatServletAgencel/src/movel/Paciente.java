/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package movel;

import dados.Paciente_Dados;
import dados.Paciente_Tab;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class Paciente
{
    public String PesquisarPorCodigoAcesso(String pCodAcesso)
    {
        Paciente_Tab objTabPaciente = new Paciente_Tab();
        Paciente_Dados objDadosPaciente = new Paciente_Dados();
        Movel objMovel = new Movel();

        try
	{

            objDadosPaciente.SelecionarPorCodigoAcesso(pCodAcesso);
            /*
            if ( objDadosPaciente.NumeroRegistros() <= 0 )
            {
                throw new Exception("!vazio!");
            }
            */
            objTabPaciente = objDadosPaciente.ProximoRegistro();

            while (objTabPaciente != null)
            {
                objMovel.adicionaCampoResposta(String.valueOf(objTabPaciente.PAC_IN_CODIGO));
                objMovel.adicionaCampoResposta(objTabPaciente.PAC_ST_NOME);

                objTabPaciente = objDadosPaciente.ProximoRegistro();
                objMovel.proximoRegistroResposta(objTabPaciente != null);
            }

        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao buscar dados dos Pacientes."+ erro.getLocalizedMessage());
	}

        return objMovel.getTextoResposta();
    }

    public int PesquisarConvenio(int pCodigoPaciente)
    {
        Paciente_Dados objDadosPaciente = new Paciente_Dados();

        int iCodigoConvenio = 0;
        
        try 
        {
            objDadosPaciente.SelecionarConvenio(pCodigoPaciente);


            iCodigoConvenio = objDadosPaciente.GetConvenio();

        } catch (Exception e)
        {
            System.out.println("Erro ao pesquisar convenio do paciente.");
            iCodigoConvenio = 0;
        }

        return iCodigoConvenio;
    }
}
