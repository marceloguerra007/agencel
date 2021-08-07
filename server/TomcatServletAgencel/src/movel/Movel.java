/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package movel;

/**
 *
 * @author Marcelo
 */
public class Movel {

    private String sResposta="";

    // Construtores
    public Movel(){
        sResposta = "";
    }

    public Movel(String pTextoResposta)
    {
        sResposta = pTextoResposta;
    }

    // Métodos  //
    public void adicionaCampoResposta(String pCampo)
    {

        sResposta += pCampo+";";
    }

    public void proximoRegistroResposta(boolean pExisteProximo)
    {
        if (pExisteProximo)
            sResposta += "|";
    }
    
    public void ajustaTextoRespostaErro(String pMsgErro)
    {
        // Verificar se não é um erro do tipo sem dados encontrados
        if (pMsgErro.indexOf("The result set has no current row") > 0 )
            sResposta = "!vazio!";
        else
            sResposta = "!"+pMsgErro+"!";
    }

    public String getTextoResposta()
    {
        return sResposta+"!!";
    }

    public String getTextoRespostaOkGravacao()
    {
        return "OK!!";
    }
}
