/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.registro.duenio;

import com.google.gson.Gson;
import dao.duenio.Duenio_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manage.bean.duenio.Duenio_MB;
import manageBean.empleado.Empleado_MB;
import manageBean.general.GenericResponse;

public class Duenio_SRV extends HttpServlet
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

        RequestDispatcher dispatcher;
        GenericResponse respuesta = new GenericResponse();
        Duenio_MB duenio_Mb = null;

        HttpSession session = request.getSession();
        Empleado_MB empleado = (Empleado_MB) session.getAttribute("usuario");

        if (empleado != null)
        {
            String nombre = empleado.getNombre();
            String primerApellido = empleado.getApellidoPa();
            String segundoApellido = empleado.getApellidoMa();
            String correo = empleado.getCorreoEmpleado();
            duenio_Mb = new Duenio_MB(nombre, primerApellido, segundoApellido, correo);
            session.setAttribute("empleado", empleado);
            respuesta.setStatus(0);
            respuesta.setMensaje("Datos del dueño");
            respuesta.setResponseObject(duenio_Mb);
        } else
        {
            respuesta.setStatus(-300);
            respuesta.setMensaje("Error");
        }

        try (PrintWriter out = response.getWriter())
        {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        GenericResponse respuesta = new GenericResponse();
        Duenio_DAO duenio_Dao = new Duenio_DAO();
        Duenio_MB duenio_Mb = null;

        String nombreE = request.getParameter("nombreE");
        String primerApellidoE = request.getParameter("primerApellidoE");
        String segundoApellidoE = request.getParameter("segundoApellidoE");
        String correoP = request.getParameter("correoP");
        String nombreCompleto = request.getParameter("nombreCompleto");
        String nombreEmpresa = request.getParameter("nombreEmpresa");
        String telefono = request.getParameter("telefono");
        String emailEmpresa = request.getParameter("emailEmpresa");


        int id_duenio = -1;
        boolean esMiProyecto = false;
        if (nombreE.isEmpty() && primerApellidoE.isEmpty() && segundoApellidoE.isEmpty() && correoP.isEmpty())
        {
            duenio_Mb = new Duenio_MB(nombreCompleto, nombreEmpresa, telefono, emailEmpresa);
        } else
        {
            duenio_Mb = new Duenio_MB(nombreE, primerApellidoE, segundoApellidoE, correoP);
            esMiProyecto = true;
        }
        id_duenio = duenio_Dao.insertaDuenio(duenio_Mb.getNombreE(), duenio_Mb.getPrimerApellidoE(), duenio_Mb.getSegundoApellidoT(), duenio_Mb.getCorreo(), esMiProyecto, respuesta);
        Empleado_MB empleado_Mb = (Empleado_MB)session.getAttribute("empleado");
        System.out.println("em:::: " + empleado_Mb.toString());
        respuesta.setStatus(0);
        respuesta.setMensaje("Dueño insertado");
        session.setAttribute("id_duenio", id_duenio);
        session.setAttribute("empleado", empleado_Mb);
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
