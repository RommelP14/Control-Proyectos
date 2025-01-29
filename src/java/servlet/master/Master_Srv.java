/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.master;

import Utils.constantes.Constantes;
import Utils.constantes.VariablesSistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import manageBean.conexion.Conexion;
import manageBean.conexion.Empleado_DAO;
import manageBean.empleado.Empleado_MB;
import manageBean.general.GenericResponse;
import manageBean.usuarios.Usuarios_MB;
import servlets.registro.proyecto.Registrar_View_Srv;

/**
 *
 * @author Alex Antonio Suárez Sánchez
 * @fecha_creacion 28/08/2024
 * @fecha_actualizacion
 *
 */
public class Master_Srv extends HttpServlet
{

    private Conexion conexion;
    private transient Connection conn;
    protected String myContextParam = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        String logout = request.getParameter("logout");
        if (logout != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", null);
            session.setAttribute("id_usuario", null);
            response.sendRedirect(Utils.constantes.Constantes.LOGIN_MASTER);
        } else
        {
            String token = request.getParameter("token");
            Empleado_DAO empleado_Dao = new Empleado_DAO();
            Empleado_MB empleado = empleado_Dao.getEmpleado(token);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", empleado);
            session.setAttribute("id_usuario", empleado.getId_empleado() + "");

            ArrayList<String> puestos = empleado_Dao.getPuestosJefe();
            String nombrePuesto = empleado.getNombrePuesto();
            System.out.println("Empleado ingreso:" + empleado.toString());
            int vista = -1;
            if (Registrar_View_Srv.verificaAccesoJefe(puestos, nombrePuesto))
            {
                System.out.println("Redirigiendo Jefe...");
                vista = Utils.constantes.Constantes.JEFE_DEPARTAMENTO;
            } else if (nombrePuesto.toLowerCase().trim().equals("docente"))
            {
                System.out.println("Redirigiendo Docente...");
                vista = Utils.constantes.Constantes.DOCENTE;
            } else
            {
                System.out.println("Redirigiendo Alumno...");
                vista = Utils.constantes.Constantes.ALUMNO;
            }

            vistaNavBar(vista, response, request, session);
        }

    }

    public void vistaNavBar(int vista, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException, ServletException
    {
        switch (vista)
        {
            case Utils.constantes.Constantes.JEFE_DEPARTAMENTO:
                session.setAttribute("credencial", Utils.constantes.Constantes.JEFE_DEPARTAMENTO);
                String url = "/views/Bienvenida/BienvenidaJefe.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
                break;
            case Utils.constantes.Constantes.DOCENTE:
                session.setAttribute("credencial", Utils.constantes.Constantes.DOCENTE);
//                response.sendRedirect(request.getContextPath() + "/views/Bienvenida/BienvenidaJefe.jsp");
                break;
            case Utils.constantes.Constantes.ALUMNO:
                session.setAttribute("credencial", Utils.constantes.Constantes.ALUMNO);
//                response.sendRedirect(request.getContextPath() + "/views/Bienvenida/BienvenidaJefe.jsp");
                break;
            default:
                System.out.println("No Hay Redirección checa el servlet Master_Srv");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

    }
}
