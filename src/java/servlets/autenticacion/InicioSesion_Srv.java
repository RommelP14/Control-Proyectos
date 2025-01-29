/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.autenticacion;

import com.google.gson.Gson;
import Utils.constantes.Strings;
import Utils.constantes.VariablesSistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manageBean.conexion.Conexion;
import manageBean.conexion.Empleado_DAO;
import manageBean.empleado.Empleado_MB;
import manageBean.general.GenericResponse;
import servlets.registro.proyecto.Registrar_View_Srv;

/**
 *
 * @author Alex Antonio Suárez Sánchez
 * @fecha_creacion 13/08/2024
 * @fecha_actualizacion
 *
 */
public class InicioSesion_Srv extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("inpUsuario");
        String password = request.getParameter("inpPass");

        GenericResponse<Empleado_MB> respuesta = new GenericResponse<>();

        StringBuilder msg = new StringBuilder();

        HashMap<String, String> campos = new HashMap<>();

        campos.put("inpUsuario", username);
        campos.put("inpPass", password);

        if (valida(msg, campos))
        {
            respuesta.setMensaje(String.valueOf(msg));
            respuesta.setStatus(-300);
        } else
        {
//            if (conexion.isConnected())
//            {
            Empleado_DAO empleado_DAO = new Empleado_DAO();
            GenericResponse<Empleado_MB> responseObject
                    = empleado_DAO.getEmpleadoUsuario(username);

            if (responseObject.getResponseObject() != null)
            {
                respuesta.setStatus(0);
                respuesta.setMensaje(Strings.MSG_EXITO);
                respuesta.setResponseObject(responseObject.getResponseObject());

                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", respuesta.getResponseObject());

//                if (Registrar_View_Srv.verificaAccesoJefe(puestos, nombrePuesto))
//                {
//                    respuesta.setMensaje(Strings.MSG_EXITO);
                    System.out.println("********************************");
                    System.out.println("Usuario Logeado como: " + respuesta.getResponseObject().getNombre());
                    System.out.println("********************************");
//                } else
//                {
//                    session.invalidate();
//                    respuesta.setStatus(-403);
//                    respuesta.setMensaje("Usuario NO autorizado");
//                }
            } else
            {
                respuesta.setStatus(-200);
                respuesta.setMensaje("Datos incorrectos, intentelo de nuevo");
            }
//            } else
//            {
//                respuesta.setStatus(-500);
//                respuesta.setMensaje("Sistema no disponible, intentelo más tarde");
//            }
        }

        try (PrintWriter out = response.getWriter())
        {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }

    private boolean valida(StringBuilder msg, HashMap<String, String> campos)
    {
        boolean bA = false;
        for (String clave : campos.keySet())
        {
            String valor = campos.get(clave);
            if (valor == null)
            {
                msg.append("CAMPO REQUERIDO : ");
                msg.append(clave);
                msg.append(" | ");
                bA = true;
            }
        }
        return bA;
    }
}
