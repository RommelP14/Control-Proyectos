/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.acciones.residencia;

import com.google.gson.Gson;
import dao.residencia.Residencia_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manageBean.general.GenericResponse;

/**
 *
 * @author romme
 */
public class Residencia_SRV extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Residencia_SRV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Residencia_SRV at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        GenericResponse respuesta = new GenericResponse();
        RequestDispatcher dispatcher;

        String accion = request.getParameter("accion");
        String noFolio = "";
        switch (accion)
        {
            case "agregar":
                noFolio = request.getParameter("idProyecto");
                request.setAttribute("noFolio", noFolio);
                dispatcher = getServletContext().getRequestDispatcher("/views/jefes/Paginas/modal/Calificacion_editar_modal.jsp");
                dispatcher.forward(request, response);
                break;
            case "registroOK":
                noFolio = request.getParameter("idFolio");
                String porcentaje = request.getParameter("porcentaje");
                Residencia_DAO residencia_Dao = new Residencia_DAO();
                residencia_Dao.actualizaCalificacionResidencia(Integer.parseInt(noFolio), Double.parseDouble(porcentaje), respuesta);
                break;
            default:
            //throw new AssertionError();
            }

        try (PrintWriter out = response.getWriter())
        {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
