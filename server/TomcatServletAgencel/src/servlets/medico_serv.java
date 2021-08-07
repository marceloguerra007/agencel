package servlets;

/*
 * medico_Serv.java
 *
 * Created on 19 de Outubro de 2009, 19:44
 */

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import movel.Medico;

/**
 *
 * @author Marcelo
 * @version
 */
public class medico_serv extends HttpServlet {

    private static final String CMD_LISTARMOVEL="LST_M";
    private static final String CMD_PESQUISARMOVEL="PSQ_M";
    private static final String CMD_PESQUISACODIGOACESSO="PSQCODACESSO_M";

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        String sComando, sCodEsp, sCodAcesso;
        Medico objMedico = new Medico();

        response.setContentType("text/plain");

        // Parametros (O & separa os parametros).
        sComando = request.getParameter("comando");
        sCodEsp  = request.getParameter("cod_esp");
        sCodAcesso = request.getParameter("cod_acesso");

        if (sComando.equals(CMD_LISTARMOVEL))
        {
            out.println("Listar Todos \nComando Solicitado = "+sComando+".");
            out.println("sCodEsp="+sCodEsp);

            out.println("===> MEDICOS <===");

            // overload
            out.println(objMedico.Listar());
        }
        else if (sComando.equals(CMD_PESQUISARMOVEL))
        {
            out.println(objMedico.Listar(Integer.parseInt(sCodEsp)));
        }
        else if (sComando.equals(CMD_PESQUISACODIGOACESSO))
        {
            out.println(objMedico.PesquisarPorCodigoAcesso(sCodAcesso));
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
