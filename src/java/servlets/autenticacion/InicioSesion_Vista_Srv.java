/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.autenticacion;

import Utils.constantes.VariablesSistema;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manageBean.usuarios.Usuarios_MB;

/**
 *
 * @author Alex Antonio Suárez Sánchez
 * @fecha_creacion 13/08/2024
 * @fecha_actualizacion
 *
 */
public class InicioSesion_Vista_Srv extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            Usuarios_MB usuario = (Usuarios_MB) session.getAttribute("usuario");
            if (usuario != null)
            {
                response.sendRedirect(VariablesSistema.APLICACION + "app/portalMovilidad/bienvenida.do");
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
