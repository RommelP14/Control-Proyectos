/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets.registro.proyecto;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manageBean.empleado.Empleado_MB;

/**
 *
 * @author mauro
 */
public class Registrar_View_Srv extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if (!session.isNew())
        {
            if (session.getAttribute("id_usuario") == null)
            {
                response.sendRedirect(Utils.constantes.Constantes.LOGIN_MASTER);
            } else
            {
                Empleado_MB empleado_mb = (Empleado_MB) session.getAttribute("usuario");
                if (empleado_mb != null)
                {
                    int credencial = Integer.parseInt(session.getAttribute("credencial").toString());
                    switch (credencial)
                    {
                        case Utils.constantes.Constantes.JEFE_DEPARTAMENTO:
                            request.setAttribute("empleado", empleado_mb);
                            request.getRequestDispatcher("/views/Paginas/RegistroDuenio.jsp").forward(request, response);
                            break;
                        default:
                            System.out.println("No se va a ningún caso el Registrar_View_Srv");
                            break;
                    }

                } else
                {

                }
            }
        } else
        {
            response.sendRedirect("../../app/autenticacion/sesionExpirada.do");
        }
    }

    public static boolean verificaAccesoJefe(ArrayList<String> puestos, String puestoCompar)
    {
        for (String p : puestos)
        {
            if (p.equals(puestoCompar))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    }

    private void redirectView(HttpServletRequest req, HttpServletResponse resp, String pathView)
            throws ServletException, IOException
    {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pathView);
        dispatcher.forward(req, resp);
    }
}
