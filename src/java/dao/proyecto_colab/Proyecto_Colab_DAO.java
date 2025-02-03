/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.proyecto_colab;

import Utils.constantes.VariablesSistema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import manage.bean.proyecto_colab.Proyecto_Colab_MB;
import manageBean.conexion.Conexion;
import manageBean.general.GenericResponse;

/**
 *
 * @author romme
 */
public class Proyecto_Colab_DAO
{
    private Conexion conexion;

    public Proyecto_Colab_MB consultaProyectoProyectoColabParaVerDatos(int noFolio, GenericResponse respuesta)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Proyecto_Colab_MB proyecto_colab_Mb = null;

        try
        {
            String query = "SELECT \n"
                    + "    p.noFolio, \n"
                    + "    COALESCE(p.nombre, 'Aún no registrado') AS nombreProyecto, \n"
                    + "    COALESCE(c.nombre, 'Aún no registrado') AS nombreColaborador, \n"
                    + "    COALESCE(c.correo, 'Aún no registrado') AS correoColaborador, \n"
                    + "    COALESCE(pc.fecha_aprobacion, 'Aún no registrado') AS fecha_aprobacion, \n"
                    + "    COALESCE(pc.fecha_liberacion, 'Aún no registrado') AS fecha_liberacion\n"
                    + "FROM proyectos_tab p\n"
                    + "LEFT JOIN colaboradores_tab c ON p.noFolio = c.noFolio\n"
                    + "LEFT JOIN proyecto_colab_tab pc ON p.noFolio = pc.noFolio\n"
                    + "WHERE p.noFolio = ?";

            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                String nombreProyecto = rs.getString("nombreProyecto");
                String nombreColaborador = rs.getString("nombreColaborador");
                String correoColaborador = rs.getString("correoColaborador");
                String fecha_aprobacion = rs.getString("fecha_aprobacion");
                String fecha_liberacion = rs.getString("fecha_liberacion");

                proyecto_colab_Mb = new Proyecto_Colab_MB(noFolio, fecha_aprobacion, fecha_liberacion, nombreProyecto, nombreColaborador, correoColaborador);
            } else
            {
                respuesta.setStatus(-10);
                respuesta.setMensaje("No se encontro el proyecto con ese noFolio");
            }
        } catch (SQLException e)
        {
            System.out.println("Error al consultar titulacion: " + e.getMessage());
            respuesta.setStatus(-3);
            respuesta.setMensaje("Ha ocurrido un error en consultar un proyecto de titulacion");
        } catch (Exception e)
        {
            System.out.println("Error general en consultarProyectoTitulacion: " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                conexion.disconnect();
            } catch (SQLException e)
            {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return proyecto_colab_Mb;
    }

    public void insertaProyectoColab(int noFolio, String estado_aprobacion_proyecto_colab, GenericResponse response)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        PreparedStatement pstmt = null;
        try
        {
            String query = "INSERT INTO proyecto_colab_tab (noFolio, fecha_aprobacion, estado_aprobacion_proyecto_colab) VALUES (?, ?, ?)";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            pstmt.setString(2, obtenerFechaSistema());
            pstmt.setString(3, estado_aprobacion_proyecto_colab);
            pstmt.executeUpdate();
            response.setStatus(0);
            response.setMensaje("Se ha insertado con exito el Proyecto");
            response.setResponseObject(estado_aprobacion_proyecto_colab);
        } catch (Exception e)
        {
            System.out.println("Error en Insertar proyecto: " + e);
            response.setStatus(-100);
            response.setMensaje("Ha ocurrido un error al insertar un proyecto Colab");
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
                    System.out.println("Error al cerrar PreparedStatement " + e.getMessage());
                }
            }
        }
    }

    private String obtenerFechaSistema()
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaSistema = new Date();

        return formato.format(fechaSistema);
    }
}
