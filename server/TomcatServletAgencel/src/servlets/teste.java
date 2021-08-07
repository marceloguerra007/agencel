/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movel.Consulta;
/**
 *
 * @author Marcelo
 */
public class teste extends HttpServlet {
   
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

        String sComando, sDataPsq;
        int iCodMedico=0, iCodPaciente=0;

        // Parametros (O & separa os parametros).
        sComando     = request.getParameter("comando");
        sDataPsq     = request.getParameter("datapsq");

        out.println("SERVLET APENAS PARA TESTE");

        try
        {
            if (sDataPsq != null)
                sDataPsq = sDataPsq.replace("-", "/");

            if (sComando.equals("TESTEDIASDISP"))
            {
                out.println("TESTEDIASDISP");
                out.println("sem timezone");

                Date dtAtual = new Date();
                Date dtDataPsq = null;
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

                try
                {
                    dtDataPsq = formatador.parse(sDataPsq);
                } catch (ParseException ex)
                {
                    out.println("X Data invalida X");
                }

                if (dtDataPsq.before(dtAtual))
                {
                    dtDataPsq = dtAtual;
                }

                Consulta objCons = new Consulta();
                out.println("Data Pesquisa="+sDataPsq+" - dsDataPsq="+formatador.format(dtDataPsq));
                out.println("Ultima Data="+formatador.format(objCons.UltimoDiaMes(dtDataPsq)));

                //Calendar calData = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"));
                Calendar calData = Calendar.getInstance();
                calData.setTime(dtDataPsq);

                // Obs: Contador de mês começa em 0.
                int iMes = calData.get(Calendar.MONTH);
                int iAno = calData.get(Calendar.YEAR);
                int iDia = calData.get(Calendar.DAY_OF_MONTH);

                out.println("            iMes="+iMes+" iDia="+iDia+" iAno="+iAno);

                // Jogar o proximo mês da data pesquisada.
                iMes += 2;

                out.println("2 meses --> iMes="+iMes+" iDia="+iDia+" iAno="+iAno);

            }


        }
        catch (Exception e){
                out.println("Erro:"+e.getMessage());
        }
        finally {
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
