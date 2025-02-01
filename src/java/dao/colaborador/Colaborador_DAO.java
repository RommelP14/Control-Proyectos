/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.colaborador;

import Utils.constantes.VariablesSistema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import manageBean.conexion.Conexion;
import manageBean.general.GenericResponse;

/**
 *
 * @author romme
 */
public class Colaborador_DAO
{
    private Conexion conexion;

    public void insertaColaborador(int noFolio, String nombre, String primerApellido, String segundoApellido, String carrera, String correo, String no_control, GenericResponse respuesta)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;

        try
        {
            String query = "INSERT INTO colaboradores_tab (noFolio, nombre, primerApellido, segundoApellido, carrera, correo, no_control) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            pstmt.setString(2, nombre);
            pstmt.setString(3, primerApellido);
            pstmt.setString(4, segundoApellido);
            pstmt.setString(5, carrera);
            pstmt.setString(6, correo);
            pstmt.setString(7, no_control);
            pstmt.executeUpdate();

            respuesta.setStatus(0);
            respuesta.setMensaje("Se ha insertado con exito el colaborador");
        } catch (Exception e)
        {
            System.out.println("Error en Colaborador: " + e);
            respuesta.setStatus(-100);
            respuesta.setMensaje("Ha ocurrido un error al insertar el colaborador");
        } finally
        {
            conexion.disconnect();
            try
            {
                if (pstmt != null)
                {
                    pstmt.close();
                }
            } catch (SQLException e)
            {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public void consultaColaboradorPorId(int noFolio, GenericResponse respuesta)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            String query = "SELECT COUNT(*) AS total FROM colaboradores_tab WHERE noFolio = ?";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            rs = pstmt.executeQuery();

            int numeroEncontrados = 0;
            if (rs.next())
            {
                numeroEncontrados = rs.getInt("total");
                respuesta.setStatus(0);
            } else
            {
                respuesta.setStatus(-500);
            }
            respuesta.setMensaje((numeroEncontrados >= 1 ? "Colaborador Encontrado" : "No se encontró colaborador"));
            respuesta.setResponseObject(numeroEncontrados);
        } catch (SQLException e)
        {
            System.out.println("Error al consultar colaboradores: " + e.getMessage());
            respuesta.setStatus(-100);
            respuesta.setMensaje("Ha ocurrido un error a la hora de buscar colaborador");
            respuesta.setResponseObject(null);
        } catch (Exception e)
        {
            System.out.println("Error general en consultarColaborador: " + e.getMessage());
        } finally
        {
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
    }
}
