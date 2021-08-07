/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movel.Consulta;

/**
 *
 * @author Marcelo
 */
public class consulta_serv extends HttpServlet {

    private static final String CMD_PSQDIASDISP="PSQDIAS_M";
    private static final String CMD_PSQHORARIODISP="PSQHORA_M";
    private static final String CMD_GRAVARCONSULTA="GRAVAR_M";
    private static final String CMD_CONFIRMARCONSULTA="CONFIRMAR_M";
    private static final String CMD_CANCELARCONSULTA="CANCELAR_M";
    private static final String CMD_PSQCONSREALIZADAS="PSQREALIZADAS_M";
    private static final String CMD_PSQCONSPENDENTES="PSQPENDENTES_M";
    private static final String CMD_PSQCONSMEDICO="PSQPORMEDICO_M";
    private static final String MSG_GRAVACAO_OK = "OK!!";

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String sComando, sDataPsq, sCodMedico, sCodPaciente;
        int iCodMedico=0, iCodPaciente=0;

        // Parametros (O & separa os parametros).
        sComando     = request.getParameter("comando");
        sDataPsq     = request.getParameter("datapsq");
        sCodMedico   = request.getParameter("medico");
        sCodPaciente = request.getParameter("paciente");

        try
        {
            if (sDataPsq != null)
                sDataPsq = sDataPsq.replace("-", "/");

            if (sCodMedico != null)
                iCodMedico = Integer.parseInt(sCodMedico);

            if (sCodPaciente != null)
                iCodPaciente = Integer.parseInt(sCodPaciente);
            
            if (sComando.equals(CMD_PSQDIASDISP))
            {
                PesquisarDiasDisponiveis(out, sDataPsq, iCodMedico);
            }
            else if (sComando.equals(CMD_PSQHORARIODISP))
            {
                PesquisarHorariosDisponiveis(out, sDataPsq, iCodMedico);
            }
            else if (sComando.equals(CMD_PSQCONSREALIZADAS))
            {
                PesquisarConsultasRealizadas(out, iCodPaciente);
            }
            else if (sComando.equals(CMD_PSQCONSPENDENTES))
            {
                PesquisarConsultasPendentes(out, iCodPaciente);
            }
            else if (sComando.equals(CMD_PSQCONSMEDICO))
            {
                PesquisarConsultasPorMedico(out, iCodMedico, sDataPsq);
            }
            else
                out.println("else (que nao faz nada!)");


        }
        catch (Exception e){
                out.println("Erro:"+e.getMessage());
        }
        finally {
            out.close();
        }
    } 

    private void PesquisarDiasDisponiveis(PrintWriter pOut, String pDataPsq, int pMedico)
    {
            Consulta objConsulta = new Consulta();

            Date dtAtual = new Date();
            Date dtDataPsq = null;
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

            try
            {
                dtDataPsq = formatador.parse(pDataPsq);
            } catch (ParseException ex)
            {
                System.out.print("Data invalida");
            }

            if (dtDataPsq.before(dtAtual))
            {
                dtDataPsq = dtAtual;
            }

            pOut.println(objConsulta.PesquisarDiasDisponiveis(pMedico, dtDataPsq));
    }

    private void PesquisarHorariosDisponiveis(PrintWriter pOut, String pDataPsq, int pMedico)
    {
            Consulta objConsulta = new Consulta();

            Date dtDataPsq = null;
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

            // Convertendo a data para o tipo date.
            try
            {
                dtDataPsq = formatador.parse(pDataPsq);
            } catch (ParseException ex)
            {
                System.out.print("Data invalida");
            }

            pOut.println(objConsulta.PesquisarHorariosDisponiveis(pMedico, dtDataPsq));
    }

    private void MarcarConsulta(PrintWriter pOut, int pCodigoAgenda, int pCodigoPaciente) throws Exception
    {
        try
        {
            Consulta objConsulta = new Consulta();

            objConsulta.Marcar(pCodigoAgenda, pCodigoPaciente);

            pOut.print(MSG_GRAVACAO_OK);
        }
        catch (Exception e)
        {
            pOut.print("Mensagem do Servidor. Erro ao Marcar consulta! Erro: "+e.getMessage());
        }

    }

    private void ConfirmarConsulta(PrintWriter pOut, int pCodigoAgenda, int pCodigoPaciente) throws Exception
    {
        try
        {
            Consulta objConsulta = new Consulta();

            objConsulta.Confirmar(pCodigoAgenda, pCodigoPaciente);

            pOut.print(MSG_GRAVACAO_OK);
        }
        catch (Exception e)
        {
            pOut.print("Mensagem do Servidor. Erro ao confirmar consulta! Erro: "+e.getMessage());
        }

    }

    private void CancelarConsulta(PrintWriter pOut, int pCodigoAgenda, int pCodigoPaciente) throws Exception
    {
        try
        {
            Consulta objConsulta = new Consulta();

            objConsulta.Cancelar(pCodigoAgenda, pCodigoPaciente);

            pOut.print(MSG_GRAVACAO_OK);
        }
        catch (Exception e)
        {
            pOut.print("Mensagem do Servidor. Erro ao cancelar consulta! Erro: "+e.getMessage());
        }

    }

    private void PesquisarConsultasRealizadas(PrintWriter pOut, int pCodPaciente)
    {
            Consulta objConsulta = new Consulta();

            Date dtAtual = new Date();

            pOut.println(objConsulta.PesquisarConsultasRealizadas(pCodPaciente, dtAtual));
    }

    private void PesquisarConsultasPendentes(PrintWriter pOut, int pCodPaciente)
    {
            Consulta objConsulta = new Consulta();

            Date dtAtual = new Date();

            pOut.println(objConsulta.PesquisarConsultasPendentes(pCodPaciente, dtAtual));
    }

    private void PesquisarConsultasPorMedico(PrintWriter pOut, int pMedico, String pDataPsq)
    {
            Consulta objConsulta = new Consulta();

            Date dtDataPsq = null;
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

            // Convertendo a data para o tipo date.
            try
            {
                dtDataPsq = formatador.parse(pDataPsq);
            } catch (ParseException ex)
            {
                System.out.print("Data invalida");
            }

            pOut.println(objConsulta.PesquisarConsultasPorMedico(pMedico, dtDataPsq));

    }

    // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Clique no sinal de + à esquerda para editar o código.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        BufferedReader brClient = request.getReader();
        String sBuffer="";
        Boolean bDadosOK=false;
        String sCodigoAgenda="", sCodigoPaciente="";
        int iCodigoAgenda=0, iCodigoPaciente=0;

        // Parametros.
        String sComando="";
        sComando = request.getParameter("comando");

        try
        {
            bDadosOK = false;

            // Lendo os dados enviados pelo client
            while ( (sBuffer = brClient.readLine()) != null )
            {
                StringTokenizer stDados = new StringTokenizer(sBuffer, ";");

                if ( stDados.hasMoreTokens() )
                {
                    sCodigoAgenda = (String)stDados.nextToken();
                }

                if ( stDados.hasMoreTokens() )
                {
                    sCodigoPaciente = (String)stDados.nextToken();
                }
            }

            iCodigoAgenda   = Integer.parseInt(sCodigoAgenda);
            iCodigoPaciente = Integer.parseInt(sCodigoPaciente);

            bDadosOK = true;
        }
        catch (Exception e)
        {
            bDadosOK = false;
            out.print("Erro ao ler dados do client movel! Erro:"+e.getMessage());
        }

        if (bDadosOK)
        {

            try
            {
                if (sComando.equals(CMD_GRAVARCONSULTA))
                {
                    MarcarConsulta(out, iCodigoAgenda, iCodigoPaciente);
                }
                else if (sComando.equals(CMD_CONFIRMARCONSULTA))
                {
                    ConfirmarConsulta(out, iCodigoAgenda, iCodigoPaciente);
                }
                else if (sComando.equals(CMD_CANCELARCONSULTA))
                {
                    CancelarConsulta(out, iCodigoAgenda, iCodigoPaciente);
                }
            }
            catch (Exception e)
            {
                out.print("Erro ao gravar dados! Erro:"+e.getMessage());
            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
