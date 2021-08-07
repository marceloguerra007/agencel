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
import movel.Paciente;

/**
 *
 * @author Marcelo
 */
public class paciente_serv extends HttpServlet
{
    private final String CMD_PSQMOVEL_CODACESSO = "PSQACESSO_M";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String sComando, sCodAcesso;
        Paciente objPaciente = new Paciente();


        // Parametros (O & separa os parametros).
        sComando = request.getParameter("comando");
        sCodAcesso  = request.getParameter("cod_acesso");

        //out.println("Comando Sol. = "+sComando+".");
        //out.println("sCodEsp="+sCodEsp);
        try
        {
            if (sComando.equals(CMD_PSQMOVEL_CODACESSO))
            {
                out.println(objPaciente.PesquisarPorCodigoAcesso(sCodAcesso));
            }
        } finally {
            out.close();
        }

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
