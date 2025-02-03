/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets.registro.proyecto;

import com.google.gson.Gson;
import dao.colaborador.Colaborador_DAO;
import dao.departamento.Departamento_DAO;
import dao.proyecto_colab.Proyecto_Colab_DAO;
import dao.proyectos.Proyectos_DAO;
import dao.residencia.Residencia_DAO;
import dao.titulacion.Titulacion_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manage.bean.colaborador.Colaborador_MB;
import manage.bean.departamento.Departamento_MB;
import manage.bean.proyecto_colab.Proyecto_Colab_MB;
import manage.bean.proyectos.Proyecto_MB;
import manage.bean.residencia.Residencia_MB;
import manage.bean.titulacion.Titulacion_MB;
import manageBean.empleado.Empleado_MB;
import manageBean.general.GenericResponse;

/**
 *
 * @author mauro
 */
public class Proyecto_SRV extends HttpServlet
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

        GenericResponse respuesta = new GenericResponse();
        RequestDispatcher dispatcher;

        String accion = request.getParameter("accion");
        String noFolio = "";
        switch (accion)
        {
            case "obtenerDatosProyecto":
                noFolio = request.getParameter("idProyecto");
                String tipo_proyecto = request.getParameter("tipo_proyecto");
                switch (tipo_proyecto)
                {
                    case "residencia":
                        Residencia_DAO residencia_Dao = new Residencia_DAO();
                        Residencia_MB residencia_Mb = residencia_Dao.consultaProyectoResidenciaParaVerDatos(Integer.parseInt(noFolio), respuesta);
                        request.setAttribute("residencia_Mb", residencia_Mb);
                        dispatcher = getServletContext().getRequestDispatcher("/views/jefes/Paginas/modal/Ver_datos_proyecto_modal_residencia.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case "titulacion":
                        Titulacion_DAO titulacion_Dao = new Titulacion_DAO();
                        Titulacion_MB titulacion_Mb = titulacion_Dao.consultaProyectoTitulacionParaVerDatos(Integer.parseInt(noFolio), respuesta);
                        request.setAttribute("titulacion_Mb", titulacion_Mb);
                        dispatcher = getServletContext().getRequestDispatcher("/views/jefes/Paginas/modal/Ver_datos_proyecto_modal_titulacion.jsp");
                        dispatcher.forward(request, response);
                        break;
                    case "proyecto":
                        Proyecto_Colab_DAO proyecto_Colab_Dao = new Proyecto_Colab_DAO();
                        Colaborador_DAO colaborador_DAO = new Colaborador_DAO();
                        
                        Proyecto_Colab_MB proyecto_colab_Mb = proyecto_Colab_Dao.consultaProyectoProyectoColabParaVerDatos(Integer.parseInt(noFolio), respuesta);
                        List<Colaborador_MB> colaboradores = colaborador_DAO.consultaColaboradores(Integer.parseInt(noFolio));
                        
                        request.setAttribute("colaboradores", colaboradores);
                        request.setAttribute("proyecto_colab_Mb", proyecto_colab_Mb);
                        dispatcher = getServletContext().getRequestDispatcher("/views/jefes/Paginas/modal/Ver_datos_proyecto_modal_proyecto_colab.jsp");
                        dispatcher.forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
                break;
            default:
            //throw new AssertionError();
        }

        try (PrintWriter out = response.getWriter())
        {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Empleado_MB empleado_Mb = (Empleado_MB) session.getAttribute("empleado");
        int id_duenio = -1;
        id_duenio = (int) session.getAttribute("id_duenio");
        String titulo = request.getParameter("titulo");
        String tipo = request.getParameter("tipo");
        String planteamiento = request.getParameter("planteamiento");
        String justificacion = request.getParameter("justificacion");
        String alcances = request.getParameter("alcances");

        if (empleado_Mb != null && id_duenio != -1)
        {
            int id_departamento = empleado_Mb.getDeptoID();
            Proyectos_DAO proyecto_Dao = new Proyectos_DAO();
            GenericResponse respuesta;

            String accion = request.getParameter("accion");
            if ("comparar".equals(accion))
            {
                respuesta = proyecto_Dao.compararProyecto(
                        id_departamento, id_duenio, titulo, tipo, planteamiento, justificacion, alcances);
            } else
            {
                respuesta = new GenericResponse();
                respuesta.setStatus(-300);
                respuesta.setMensaje("Acción no reconocida");
            }

            try (PrintWriter out = response.getWriter())
            {
                Gson gson = new Gson();

                // Extrae únicamente los campos necesarios
                Map<String, Object> jsonResponse = new HashMap<>();

                // Validar si la respuesta tiene un objeto responseObject y si es un mapa
                if (respuesta.getResponseObject() instanceof Map)
                {
                    Map<String, Object> responseMap = (Map<String, Object>) respuesta.getResponseObject();

                    // Obtener estatus y folio si existen
                    if (responseMap.containsKey("estatus"))
                    {
                        jsonResponse.put("estatus", responseMap.get("estatus"));
                    } else
                    {
                        jsonResponse.put("estatus", null); // Si no existe, agregar como null
                    }
                    if (responseMap.containsKey("folio"))
                    {
                        jsonResponse.put("folio", responseMap.get("folio"));
                    } else
                    {
                        jsonResponse.put("folio", null); // Si no existe, agregar como null
                    }
                }

                // Agregar mensaje y status
                jsonResponse.put("mensaje", respuesta.getMensaje());
                jsonResponse.put("status", respuesta.getStatus());

                System.out.println("respuesta.getMensaje(): " + respuesta.getMensaje());
                System.out.println("status: " + respuesta.getStatus());

                // Enviar solo los datos requeridos
                out.print(gson.toJson(jsonResponse));
            }
        } else
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empleado o ID del dueño no encontrado en sesión.");
        }
    }
}

//
//
//    /*
//        Valida que el id que se está mandando al registrar un dueño, sea el mismo que el que se tiene 
//     */
//    public boolean validaSessionDuenio(Integer idDuenio, HttpServletRequest request)
//    {
//        HttpSession session = request.getSession(false);
//        if (session != null)
//        {
//            Integer tokenDuenio = (Integer) session.getAttribute("tokenDuenio");
//            if (idDuenio == tokenDuenio)
//            {
//                return true;
//            }
//        }
//        return false;
//    }
