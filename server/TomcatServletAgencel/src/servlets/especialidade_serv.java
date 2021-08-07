/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movel.Especialidade;

/**
 *
 * @author Marcelo
 */
public class especialidade_serv extends HttpServlet {
   
    private static final String CMD_LISTARMOVEL="LST_M";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String sComando, sCodEsp;

        // Parametros (O & separa os parametros).
        sComando = request.getParameter("comando");
        sCodEsp  = request.getParameter("cod_esp");

        /* Comandos possiveis:
           LST_M = listar todos para disp. móvel
         * PSQ_M = pesquisar registro especifico para disp. móvel
         *
        */

        try
        {
            //out.println("Servlet-Especialidade Organizado\nComando Solicitado = "+sComando+".");
            //out.println("sCodEsp="+sCodEsp);

            if (sComando.equals(CMD_LISTARMOVEL))
            {
                Listar_Movel(out);
            }
            else
            {
                out.println("Comando invalido");
            }

        } finally
        {
            out.close();
        }
    }

    private void Listar_Movel(PrintWriter pOut)
    {
            Especialidade objEspec = new Especialidade();

            pOut.println(objEspec.ListarTodas());
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
    throws ServletException, IOException {
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
    throws ServletException, IOException {
        processRequest(request, response);
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
