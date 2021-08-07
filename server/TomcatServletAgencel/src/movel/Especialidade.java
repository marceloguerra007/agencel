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
public class Especialidade {


    public String ListarTodas()
    {
        Especialidade_Tab objTabEspecialidade = new Especialidade_Tab();
        Especialidade_Dados objDadosEspecialidade = new Especialidade_Dados();
        Movel objMovel = new Movel();

        try
	{
            objDadosEspecialidade.SelecionarTodosRegistros();

            objTabEspecialidade = objDadosEspecialidade.ProximoRegistro();

            while (objTabEspecialidade != null)
            {
                objMovel.adicionaCampoResposta(String.valueOf(objTabEspecialidade.ESP_IN_CODIGO));
                objMovel.adicionaCampoResposta(objTabEspecialidade.ESP_ST_NOME);

                objTabEspecialidade = objDadosEspecialidade.ProximoRegistro();
                objMovel.proximoRegistroResposta(objTabEspecialidade != null);
            }

        }
	catch (Exception erro)
	{
            objMovel.ajustaTextoRespostaErro("Erro ao buscar dados das Especialidades.");
	}

        return objMovel.getTextoResposta();
    }

}
