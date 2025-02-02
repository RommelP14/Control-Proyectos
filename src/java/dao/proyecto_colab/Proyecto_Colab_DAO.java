/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.proyecto_colab;

import Utils.constantes.VariablesSistema;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import manageBean.conexion.Conexion;
import manageBean.general.GenericResponse;

/**
 *
 * @author romme
 */
public class Proyecto_Colab_DAO
{
    private Conexion conexion;

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
