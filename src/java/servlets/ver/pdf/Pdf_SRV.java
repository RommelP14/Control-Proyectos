/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.ver.pdf;

import dao.proyectos.Proyectos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manage.bean.proyectos.Proyecto_MB;
import manage.bean.similitudes.Similitudes_MB;
import manageBean.general.GenericResponse;

/**
 *
 * @author romme
 */
public class Pdf_SRV extends HttpServlet
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
            out.println("<title>Servlet Pdf_SRV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Pdf_SRV at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        GenericResponse<Proyecto_MB> respuesta = new GenericResponse<>();
        String noFolioParametro = request.getParameter("folio");

        if (noFolioParametro != null && !noFolioParametro.isEmpty())
        {
            int noFolio = Integer.parseInt(noFolioParametro);
            BuscaUnSoloProyecto(noFolio, respuesta, request, response);
        } else
        {
            response.getWriter().write("<p class='text-danger'>El folio está vacío.</p>");
        }
    }

    public void BuscaUnSoloProyecto(int noFolio, GenericResponse<Proyecto_MB> respuesta, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Proyectos_DAO proyectos_Dao = new Proyectos_DAO();
        proyectos_Dao.consultaProyectoPorId(noFolio, respuesta);

        if (respuesta.getResponseObject() != null)
        {
            request.setAttribute("proyecto_Mb", respuesta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/jefes/Paginas/PDF.jsp");
            dispatcher.include(request, response);
        } else
        {
            response.getWriter().write("<p class='text-danger'>No hay datos disponibles para mostrar.</p>");
        }
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
        processRequest(request, response);
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
