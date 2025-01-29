/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.registro.porcentaje;

import Utils.constantes.Constantes;
import com.google.gson.Gson;
import dao.departamento.Departamento_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manage.bean.departamento.Departamento_MB;
import manageBean.empleado.Empleado_MB;
import manageBean.general.GenericResponse;

/**
 *
 * @author Carolina
 */
public class Porcentaje_SRV extends HttpServlet
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
        HttpSession session = request.getSession();
        Empleado_MB empleado = (Empleado_MB) session.getAttribute("empleado");
        if (empleado != null)
        {
            int id_departamento_sam = empleado.getDeptoID();
            System.out.println("id_departamento_sam = " + id_departamento_sam);
            Departamento_DAO departamento_dao = new Departamento_DAO();
            Departamento_MB departamento_mb = null;

            departamento_mb = departamento_dao.obtenerDepartamentoPorIdSAM(id_departamento_sam);

            if (departamento_mb == null)
            {
                departamento_mb = new Departamento_MB(id_departamento_sam, 0, 0);
            }
            session.setAttribute("departamento_mb", departamento_mb);
            request.getRequestDispatcher("/views/jefes/Paginas/Porcentaje.jsp").forward(request, response);
        } else
        {
            System.out.println("Es null y manda vista inicio sesion");
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
        HttpSession session = request.getSession();
        Departamento_MB departamento_mb = (Departamento_MB) session.getAttribute("departamento_mb");
        double porcentaje_min = Double.parseDouble(request.getParameter("porcentaje_min"));
        double porcentaje_max = Double.parseDouble(request.getParameter("porcentaje_max"));
        Departamento_DAO deparamento_Dao = new Departamento_DAO();
        GenericResponse respuesta = new GenericResponse();

        if (departamento_mb != null)
        {
            departamento_mb.setPorcentaje_min(porcentaje_min);
            departamento_mb.setPorcentaje_max(porcentaje_max);
            deparamento_Dao.actualizaPorcentajeDepto(departamento_mb.getId_departamento_sam(), departamento_mb.getPorcentaje_min(), departamento_mb.getPorcentaje_max());
            session.setAttribute("departamento_mb", departamento_mb);
            respuesta.setStatus(0);
            respuesta.setMensaje("Se ha actualiado correctamente el porcentaje del departamento");
            
        } else
        {
            respuesta.setStatus(-100);
            respuesta.setMensaje("Error");
            System.out.println("esta null y hay que redireccionar a algo");
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
