/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.bienvenida;

import Utils.constantes.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manageBean.conexion.Empleado_DAO;
import manageBean.empleado.Empleado_MB;
import manageBean.general.GenericResponse;
import manageBean.usuarios.Usuarios_MB;
import servlets.registro.proyecto.Registrar_View_Srv;

/**
 *
 * @author Usuario
 */
public class BienvenidaControlProyectos_SRV extends HttpServlet
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
            out.println("<title>Servlet BienvenidaControlProyectos_SRV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BienvenidaControlProyectos_SRV at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher = null;
        String rol = "";
        if (session != null)
        {
            if (!session.isNew())
            {
                Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");

                if (usuario != null)
                {
                    rol = usuario.getRolAccess();
                    String ruta = "/views/templates/errores/error403.jsp";
                } else
                {
                    System.out.println("no inicio sesion 1");
                    response.sendRedirect(request.getContextPath() + "app/autenticacion/InicioSesion.do");
                }
            } else
            {
                System.out.println("no inicio sesion 2");
                response.sendRedirect(request.getContextPath() + "/app/autenticacion/InicioSesion.do");
            }
        } else
        {
            String redirectPath = request.getContextPath() + "/app/autenticacion/InicioSesion.do";
            System.out.println("Redirigiendo a: " + redirectPath);
            response.sendRedirect(redirectPath);
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
        System.out.println("Holaaaa");
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            if (!session.isNew())
            {
                Empleado_DAO empleado_DAO = new Empleado_DAO();
                Empleado_MB empleado = (Empleado_MB) session.getAttribute("usuario");
                if (empleado != null)
                {
                    System.out.println("Usuario_MB: " + empleado.getNombre());
                    ArrayList<String> puestos = empleado_DAO.getPuestosJefe();
                    String nombrePuesto = empleado.getNombrePuesto();
                    System.out.println("empleado.getNombreDepartamento(): " + nombrePuesto);
                    if (Registrar_View_Srv.verificaAccesoJefe(puestos, nombrePuesto))
                    {
                        response.sendRedirect(request.getContextPath() + "/views/Bienvenida/BienvenidaJefe.jsp");
                    }else
                    {
                        System.out.println("FALSE");
                    }
                } else
                {
                    System.out.println("no inicio sesion");
                    response.sendRedirect("../../app/autenticacion/InicioSesion.do");
                }
            } else
            {
                System.out.println("no inicio sesion");
                response.sendRedirect("../../app/autenticacion/InicioSesion.do");
            }
        } else
        {
            System.out.println("no inicio sesion");
            response.sendRedirect("../../app/autenticacion/InicioSesion.do");
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
