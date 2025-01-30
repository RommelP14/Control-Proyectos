/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.ver.proyecto;

import com.google.gson.Gson;
import dao.proyectos.Proyectos_DAO;
import dao.similitudes.Similitudes_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manage.bean.proyectos.Proyecto_MB;
import manage.bean.similitudes.Similitudes_MB;
import manageBean.general.GenericResponse;

/**
 *
 * @author romme
 */
public class RedireccionaVistas_View_SRV extends HttpServlet
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
            out.println("<title>Servlet RedireccionaVistas_View_SRV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RedireccionaVistas_View_SRV at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = null;
        Proyectos_DAO proyecto_dao = new Proyectos_DAO();
        String url = "";

        int noFolio = convierteidFolioJson(request.getParameter("idFolio"));
        Proyecto_MB proyecto_Mb = proyecto_dao.consultaProyectoPorId(noFolio);
        request.setAttribute("proyecto_Mb", proyecto_Mb);
        System.out.println("sssss: " + request.getParameter("accion"));
        switch (request.getParameter("accion"))
        {
            case "aprobacion":
                url = "/views/jefes/Paginas/ProyectoParaAprobacion.jsp";
                break;
            case "denegado":
                Similitudes_DAO similitudes_DAO = new Similitudes_DAO();
                GenericResponse<List<Similitudes_MB>> respuestaSimilitudes = new GenericResponse<>();
                similitudes_DAO.consultaProyectosSimilaresPorId(noFolio, respuestaSimilitudes);
                request.setAttribute("respuestaSimilitudes", respuestaSimilitudes);
                url = "/views/jefes/Paginas/CompararProyectos.jsp";
            default:
                System.out.println("No esta ese parametro, ve a inicio de sesion");
        }
        dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public int convierteidFolioJson(String idFolioJson) throws UnsupportedEncodingException
    {
        int idFolio = -1;
        Gson gson = new Gson();
        if (!idFolioJson.isEmpty())
        {
            idFolio = gson.fromJson(URLDecoder.decode(idFolioJson, "UTF-8"), Integer.class);
        } else
        {
            System.out.println("No esta ese parametro, ve a inicio de sesion");
        }
        return idFolio;
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
