/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.ver.proyecto;

import com.google.gson.Gson;
import dao.departamento.Departamento_DAO;
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
import javax.servlet.http.HttpSession;
import manage.bean.departamento.Departamento_MB;
import manage.bean.proyectos.Proyecto_MB;
import manageBean.empleado.Empleado_MB;
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
        List<Proyecto_MB> misProyectos = null;
        
        switch (request.getParameter("accion"))
        {
            case "listarMisProyectos":
                HttpSession session = request.getSession();
                Empleado_MB empleado_Mb = (Empleado_MB) session.getAttribute("empleado");
                if (empleado_Mb != null)
                {
                    Departamento_DAO departamento_Dao = new Departamento_DAO();
                    Departamento_MB departamento_Mb = departamento_Dao.obtenerDepartamentoPorIdSAM(empleado_Mb.getDeptoID());
                    if (departamento_Mb != null)
                    {
                        System.out.println("empleado_Mb.getDeptoID: " + departamento_Mb.getId_departamento_tab());
                        misProyectos = proyecto_dao.consultarProyectosDuenio(departamento_Mb.getId_departamento_tab());
                    }

//                System.out.println("****************************");
//                for (Proyecto_MB p : misProyectos)
//                {
//                    System.out.println(p.toString());
//                }
//                System.out.println("****************************");
                    request.setAttribute("proyectos", misProyectos);
                    dispatcher = getServletContext().getRequestDispatcher("/views/jefes/Paginas/MisProyectos_view.jsp");
                    dispatcher.forward(request, response);
                } else
                {
                    System.out.println("error inicia sesion");
                }
                break;
            case "verificaEstado":
                String noFolio = request.getParameter("idProyecto");
                String estado = request.getParameter("estadoProyecto");
                System.out.println("noFolio_proyecto_tab_revision = " + noFolio);
                if (!noFolio.isEmpty() && !estado.isEmpty())
                {
                    muestraVistaDependiendoEstado(estado, Integer.parseInt(noFolio), respuesta);
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
    
    public void muestraVistaDependiendoEstado(String estado, int idNoFolio, GenericResponse respuesta)
    {
        switch (estado)
        {
            case "Para aprobación":
                respuesta.setStatus(-1000);
                respuesta.setMensaje("");
                respuesta.setResponseObject(idNoFolio);
                break;
            case "Requiere revisión":
            case "Denegado":
                respuesta.setStatus(-2000);
                respuesta.setMensaje("");
                respuesta.setResponseObject(idNoFolio);
                break;
            default:
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
