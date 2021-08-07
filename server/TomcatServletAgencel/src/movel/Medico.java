/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package movel;

import dados.*;

/**
 *
 * @author Marcelo
 */
public class Medico {

    public String Listar()
    {
        return Listar(0);
    }

    public String Listar(int pCodigoEspec)
    {
        Medico_Tab objTabMedico = new Medico_Tab();
        Medico_Dados objDadosMedico = new Medico_Dados();
        Movel objMovel = new Movel();

        try
	{
            if (pCodigoEspec != 0)
            {
                objDadosMedico.SelecionarPorEspecialidade(pCodigoEspec);
            }
            else
                objDadosMedico.SelecionarTodosRegistros();

            objTabMedico = objDadosMedico.ProximoRegistro();


            while (objTabMedico != null)
            {
                objMovel.adicionaCampoResposta(String.valueOf(objTabMedico.MED_IN_CODIGO));
                objMovel.adicionaCampoResposta(objTabMedico.MED_ST_NOME);

                objTabMedico = objDadosMedico.ProximoRegistro();
                objMovel.proximoRegistroResposta(objTabMedico != null);
            }

        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao buscar dados dos Medicos.");
	}

        return objMovel.getTextoResposta();
    }

    public String PesquisarPorCodigoAcesso(String pCodigoAcesso)
    {
        Medico_Tab objTabMedico = new Medico_Tab();
        Medico_Dados objDadosMedico = new Medico_Dados();
        Movel objMovel = new Movel();

        try
	{
            objDadosMedico.SelecionarPorCodigoAcesso(pCodigoAcesso);

            objTabMedico = objDadosMedico.ProximoRegistro();

            while (objTabMedico != null)
            {
                objMovel.adicionaCampoResposta(String.valueOf(objTabMedico.MED_IN_CODIGO));
                objMovel.adicionaCampoResposta(objTabMedico.MED_ST_NOME);

                objTabMedico = objDadosMedico.ProximoRegistro();
                objMovel.proximoRegistroResposta(objTabMedico != null);
            }

        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao buscar dados dos Medicos. "+ erro.getMessage());
	}

        return objMovel.getTextoResposta();
    }


}
