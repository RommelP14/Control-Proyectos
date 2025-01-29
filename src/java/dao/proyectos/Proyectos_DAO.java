/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.proyectos;

import Utils.constantes.VariablesSistema;
import dao.departamento.Departamento_DAO;
import dao.similitudes.Similitudes_DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import manage.bean.departamento.Departamento_MB;
import manage.bean.proyectos.Proyecto_MB;
import manageBean.conexion.Conexion;
import manageBean.general.GenericResponse;
import org.apache.commons.text.similarity.LevenshteinDistance;

/**
 *
 * @author mauro
 */
public class Proyectos_DAO
{

    private Conexion conexion;

    public Proyecto_MB consultaProyectoPorId(int noFolio)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Proyecto_MB proyecto_mb = null;

        try
        {
            // Consulta para obtener los proyectos
            String query = "SELECT * FROM proyectos_tab WHERE noFolio = ?";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            rs = pstmt.executeQuery();

            // Itera sobre los resultados
            if (rs.next())
            {
                int noF = rs.getInt("noFolio");
                String nombre = rs.getString("nombre");
                String planteamiento = rs.getString("planteamiento");
                String alcances = rs.getString("alcances");
                String justificacion = rs.getString("justificacion");
                int id_duenio = rs.getInt("id_duenio");
                int id_departamento_tab = rs.getInt("id_departamento_tab");
                String estado = rs.getString("estado");

                proyecto_mb = new Proyecto_MB(
                        noF, nombre, planteamiento, alcances, justificacion, id_duenio, id_departamento_tab, estado
                );
            }
        } catch (SQLException e)
        {
            System.out.println("Error al consultar proyectos: " + e.getMessage());
        } catch (Exception e)
        {
            System.out.println("Error general en consultarProyectos: " + e.getMessage());
        } finally
        {
            // Cerrar conexión y recursos
            if (rs != null)
            {
                try
                {
                    rs.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            conexion.disconnect();
        }
        return proyecto_mb;
    }

    public void eliminaProyecto(int noFolio, GenericResponse respuesta)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        PreparedStatement pstmt = null;

        try
        {
            String query = "DELETE FROM proyectos_tab WHERE noFolio = ?";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            pstmt.executeUpdate();
            respuesta.setStatus(0);
            respuesta.setMensaje("Contacto eliminado");
        } catch (Exception e)
        {
            respuesta.setStatus(-200);
            respuesta.setMensaje("Error al eliminar el Contacto");
            respuesta.setResponseObject(null);
            System.out.println("Error al eliminar el proyecto: " + e.getMessage());
        } finally
        {
            conexion.disconnect();
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
        }
    }

    public int insertaProyecto(String nombre, String planteamiento, String alcances, String justificacion, int id_duenio, int id_departamento_tab, String estado)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int proyectoId = -1;

        try
        {
            String query = "INSERT INTO proyectos_tab "
                    + "(nombre, planteamiento, alcances, justificacion, id_duenio, id_departamento_tab, estado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conexion.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nombre);
            pstmt.setString(2, planteamiento);
            pstmt.setString(3, alcances);
            pstmt.setString(4, justificacion);
            pstmt.setInt(5, id_duenio);
            pstmt.setInt(6, id_departamento_tab);
            pstmt.setString(7, estado);

            pstmt.executeUpdate();

            // Obtener el ID generado
            rs = pstmt.getGeneratedKeys();
            if (rs.next())
            {
                proyectoId = rs.getInt(1);
            }
        } catch (Exception e)
        {
            System.out.println("Error al insertar el proyecto: " + e.getMessage());
        } finally
        {
            conexion.disconnect();
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            if (rs != null)
            {
                try
                {
                    rs.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
        }
        return proyectoId;
    }

    public List<Proyecto_MB> consultarProyectos()
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Proyecto_MB> proyectos = new ArrayList<>();

        try
        {
            // Consulta para obtener los proyectos
            String query = "SELECT * FROM proyectos_tab";
            pstmt = conexion.getCon().prepareStatement(query);

            rs = pstmt.executeQuery();

            // Itera sobre los resultados
            while (rs.next())
            {
                int noFolio = rs.getInt("noFolio");
                String nombre = rs.getString("nombre");
                String planteamiento = rs.getString("planteamiento");
                String alcances = rs.getString("alcances");
                String justificacion = rs.getString("justificacion");
                int id_duenio = rs.getInt("id_duenio");
                int id_departamento_tab = rs.getInt("id_departamento_tab");
                String estado = rs.getString("estado");

                // Crear objeto Proyecto_MB y agregar a la lista
                Proyecto_MB proyecto_mb = new Proyecto_MB(
                        noFolio, nombre, planteamiento, alcances, justificacion, id_duenio, id_departamento_tab, estado
                );
                proyectos.add(proyecto_mb);
            }
        } catch (SQLException e)
        {
            System.out.println("Error al consultar proyectos: " + e.getMessage());
        } catch (Exception e)
        {
            System.out.println("Error general en consultarProyectos: " + e.getMessage());
        } finally
        {
            // Cerrar conexión y recursos
            if (rs != null)
            {
                try
                {
                    rs.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar ResultSet: " + e.getMessage());
                }
            }
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            conexion.disconnect();
        }

        return proyectos;
    }

    public GenericResponse compararProyecto(int id_departamento, int id_duenio, String titulo, String tipo,
            String planteamiento, String justificacion, String alcances)
    {
        GenericResponse respuesta = new GenericResponse();
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        Departamento_DAO departamento_Dao = new Departamento_DAO();
        Departamento_MB departamento_Mb = departamento_Dao.obtenerDepartamentoPorIdSAM(id_departamento);

        Similitudes_DAO similitudes_Dao = new Similitudes_DAO();

        double porcentajeMin = departamento_Mb.getPorcentaje_min();
        double porcentajeMax = departamento_Mb.getPorcentaje_max();

        List<Proyecto_MB> proyectos = consultarProyectos();
        List<Proyecto_MB> proyectosSimilares = new ArrayList<>();

        String estado = "Para aprobación";
        LevenshteinDistance levenshtein = new LevenshteinDistance();

        for (Proyecto_MB proyecto : proyectos)
        {
            double similitud = calcularSimilitudLevenshtein(
                    titulo, proyecto.getNombre(),
                    planteamiento, proyecto.getPlanteamiento(),
                    justificacion, proyecto.getJustificación(),
                    alcances, proyecto.getAlcances(),
                    levenshtein
            );

            System.out.println("Similitud con " + proyecto.getNombre() + ": " + similitud + "%");

            if (titulo.equalsIgnoreCase(proyecto.getNombre())
                    && planteamiento.equalsIgnoreCase(proyecto.getPlanteamiento())
                    && justificacion.equalsIgnoreCase(proyecto.getJustificación())
                    && alcances.equalsIgnoreCase(proyecto.getAlcances()))
            {
                estado = "Denegado";
                System.out.println("Proyecto denegado por coincidencia exacta.");
                break;
            }

            if (similitud >= porcentajeMax)
            {
                estado = "Denegado";
                System.out.println("Proyecto denegado. Similitud: " + similitud + "% (Mayor o igual que " + porcentajeMax + "%)");
                break;
            }

            if (similitud >= porcentajeMin)
            {
                proyectosSimilares.add(proyecto);
                if (!estado.equals("Denegado"))
                {
                    estado = "Requiere revisión";
                    System.out.println("Proyecto en revisión. Similitud: " + similitud + "% (Entre " + porcentajeMin + "% y " + porcentajeMax + "%)");
                }
            }
        }

        int proyectoId = insertaProyecto(titulo, planteamiento, alcances, justificacion, id_duenio, departamento_Mb.getId_departamento_tab(), estado);

        if (!proyectosSimilares.isEmpty())
        {
            for (Proyecto_MB similar : proyectosSimilares)
            {
                similitudes_Dao.insertaSimilitud(proyectoId, similar.getNoFolio());
            }
        }

        insertaEnTablaCorrespondiente(tipo, proyectoId);

        respuesta.setStatus(0);
        respuesta.setMensaje("Comparación completada.");

        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("estatus", estado);
        responseObject.put("folio", proyectoId);

        respuesta.setResponseObject(responseObject);

        return respuesta;
    }

    private double calcularSimilitudLevenshtein(String titulo1, String titulo2, String planteamiento1, String planteamiento2,
            String justificacion1, String justificacion2, String alcances1, String alcances2,
            LevenshteinDistance levenshtein)
    {
        // Manejo de valores nulos
        titulo1 = (titulo1 != null) ? titulo1.toLowerCase().trim() : "";
        titulo2 = (titulo2 != null) ? titulo2.toLowerCase().trim() : "";
        planteamiento1 = (planteamiento1 != null) ? planteamiento1.toLowerCase().trim() : "";
        planteamiento2 = (planteamiento2 != null) ? planteamiento2.toLowerCase().trim() : "";
        justificacion1 = (justificacion1 != null) ? justificacion1.toLowerCase().trim() : "";
        justificacion2 = (justificacion2 != null) ? justificacion2.toLowerCase().trim() : "";
        alcances1 = (alcances1 != null) ? alcances1.toLowerCase().trim() : "";
        alcances2 = (alcances2 != null) ? alcances2.toLowerCase().trim() : "";

        int distancia = levenshtein.apply(titulo1, titulo2)
                + levenshtein.apply(planteamiento1, planteamiento2)
                + levenshtein.apply(justificacion1, justificacion2)
                + levenshtein.apply(alcances1, alcances2);

        int longitudTotal = titulo1.length() + titulo2.length()
                + planteamiento1.length() + planteamiento2.length()
                + justificacion1.length() + justificacion2.length()
                + alcances1.length() + alcances2.length();

        return (longitudTotal == 0) ? 0.0 : (1.0 - (double) distancia / longitudTotal) * 100;
    }

    public void insertaEnTablaCorrespondiente(String tipo, int proyectoId)
    {

        if (proyectoId != -1)
        {
            switch (tipo.toLowerCase())
            {
                case "titulacion":
                    //insertaTitulacion(proyectoId);
                    System.out.println("Se ha insertado en titulacion");
                    break;
                case "residencia":
                    //insertaResidencia(proyectoId);
                    System.out.println("Se ha insertado en residencia");

                    break;
                default:
                    System.out.println("Tipo de proyecto no reconocido: " + tipo);
            }
        }
    }

}
