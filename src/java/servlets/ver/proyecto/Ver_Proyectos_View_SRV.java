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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manage.bean.proyectos.Proyecto_MB;
import manageBean.general.GenericResponse;

/**
 *
 * @author mauro
 */
public class Ver_Proyectos_View_SRV extends HttpServlet
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
            out.println("<title>Servlet Ver_Proyectos_View_SRV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Ver_Proyectos_View_SRV at " + request.getContextPath() + "</h1>");
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
        GenericResponse respuesta = new GenericResponse();

        Proyectos_DAO proyecto_dao = new Proyectos_DAO();
        List<Proyecto_MB> misProyectos;

        switch (request.getParameter("accion"))
        {
            case "listarMisProyectos":
                misProyectos = proyecto_dao.consultarProyectos();
//                System.out.println("****************************");
//                for (Proyecto_MB p : misProyectos)
//                {
//                    System.out.println(p.toString());
//                }
//                System.out.println("****************************");

                request.setAttribute("proyectos", misProyectos);
                dispatcher = getServletContext().getRequestDispatcher("/views/jefes/Paginas/MisProyectos_view.jsp");
                dispatcher.forward(request, response);
                break;
            case "verificaEstado":
                Similitudes_DAO similitudes = new Similitudes_DAO();
                List<Integer> folios_revision;
                String noFolio = request.getParameter("idProyecto");
                System.out.println("noFolio_proyecto_tab_revision = " + noFolio);
                if (!noFolio.isEmpty())
                {
                    Proyecto_MB proyecto_Mb = proyecto_dao.consultaProyectoPorId(Integer.parseInt(noFolio));
                    muestraVistaDependiendoEstado(proyecto_Mb, dispatcher, respuesta, request);
                } else
                {
                    respuesta.setStatus(-200);
                    respuesta.setMensaje("Error");
                }

                break;
            default:
                respuesta.setStatus(-100);
                respuesta.setMensaje("Error");
        }
        try (PrintWriter out = response.getWriter())
        {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }

    public void muestraVistaDependiendoEstado(Proyecto_MB proyecto_Mb, RequestDispatcher dispatcher, GenericResponse respuesta, HttpServletRequest request) throws ServletException
    {
        if (proyecto_Mb != null)
        {
            if (proyecto_Mb.getEstado().equals("Para aprobación"))
            {
                respuesta.setStatus(-1000);
                respuesta.setMensaje("");
                respuesta.setResponseObject(proyecto_Mb); 
            }

        } else
        {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        RequestDispatcher dispatcher;
        GenericResponse respuesta = new GenericResponse();

        Proyectos_DAO proyecto_dao = new Proyectos_DAO();

        switch (request.getParameter("accion"))
        {
            case "eliminar":
                String noFolio = request.getParameter("idProyecto");
                if (!noFolio.isEmpty())
                {
                    System.out.println("Eliminando: ");
                    proyecto_dao.eliminaProyecto(Integer.parseInt(noFolio), respuesta);
                } else
                {
                    respuesta.setStatus(-100);
                    respuesta.setMensaje("Error");
                }
                break;
            default:
                respuesta.setStatus(-100);
                respuesta.setMensaje("Error");
        }
        try (PrintWriter out = response.getWriter())
        {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }
}
