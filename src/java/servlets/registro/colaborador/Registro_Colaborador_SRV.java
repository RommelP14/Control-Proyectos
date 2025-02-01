/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.registro.colaborador;

import com.google.gson.Gson;
import dao.colaborador.Colaborador_DAO;
import dao.proyectos.Proyectos_DAO;
import dao.residencia.Residencia_DAO;
import dao.titulacion.Titulacion_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manage.bean.proyectos.Proyecto_MB;
import manageBean.general.GenericResponse;

/**
 *
 * @author romme
 */
public class Registro_Colaborador_SRV extends HttpServlet
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
            out.println("<title>Servlet Registro_Colaborador_SRV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registro_Colaborador_SRV at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        GenericResponse respuesta = new GenericResponse();
        Colaborador_DAO colaborador_Dao = new Colaborador_DAO();

        String idFolio = request.getParameter("idFolio");
        String nombre = request.getParameter("nombreE");
        String primerApellido = request.getParameter("primerApellidoE");
        String segundoApellido = request.getParameter("segundoApellidoE");
        String carrera = request.getParameter("carrera");
        String correo = request.getParameter("correoP");
        String no_control = request.getParameter("noControl");
        System.out.println("idFolio recibido en servlet: " + idFolio);

        String opc = request.getParameter("accion");
        System.out.println("opc = " + opc);
        switch (opc)
        {
            case "registro":
                colaborador_Dao.insertaColaborador(Integer.parseInt(idFolio), nombre, primerApellido, segundoApellido, carrera, correo, no_control, respuesta);
                break;
            default:
                System.out.println("El proyecto no es de titulación ni residencia");
                break;
        }

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
