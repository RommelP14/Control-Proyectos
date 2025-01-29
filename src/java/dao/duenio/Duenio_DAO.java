/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.duenio;

import Utils.constantes.VariablesSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import manageBean.conexion.Conexion;
import manageBean.general.GenericResponse;

/**
 *
 * @author mauro
 */
public class Duenio_DAO
{

    private Conexion conexion;

    public int insertaDuenio(String nombreE, String primerApellidoE, String segundoApellidoT, String correo, boolean esMiProyecto, GenericResponse respuesta)
    {
        int idEncontradoDuenio = verificaExistenciaMiProyecto(nombreE, primerApellidoE, segundoApellidoT, correo);
        int idRetornado = -1;
        if (esMiProyecto)
        {
            if (idEncontradoDuenio != -1)
            {
                return idEncontradoDuenio;
            }
        }
        idRetornado = insertaDuenioBaseDatos(nombreE, primerApellidoE, segundoApellidoT, correo, respuesta);
        return idRetornado;
    }

    private int insertaDuenioBaseDatos(String nombreE, String primerApellidoE, String segundoApellidoT, String correo, GenericResponse respuesta)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int idRetornado = -1;

        try
        {
            String query = "INSERT INTO duenio_tab (nombreE, primerApellidoE, segundoApellidoT, correo) VALUES (?, ?, ?, ?)";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setString(1, nombreE);
            pstmt.setString(2, primerApellidoE);
            pstmt.setString(3, segundoApellidoT);
            pstmt.setString(4, correo);
            pstmt.executeUpdate();

            String lastIdQuery = "SELECT LAST_INSERT_ID() AS id";
            pstmt = conexion.getCon().prepareStatement(lastIdQuery);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                idRetornado = rs.getInt("id");
                respuesta.setStatus(0);
                respuesta.setMensaje("Dueño insertado");
            } else
            {
                respuesta.setStatus(-100);
                respuesta.setMensaje("Error al agregar el Dueño");
            }
        } catch (Exception e)
        {
            System.out.println("Error en Duenio: " + e);
        } finally
        {
            conexion.disconnect();
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
            } catch (SQLException e)
            {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return idRetornado;
    }

    public int verificaExistenciaMiProyecto(String nombreE, String primerApellidoE, String segundoApellidoT, String correo)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int idRetornado = -1;
        try
        {
            String query = "SELECT * FROM duenio_tab WHERE nombreE = ? AND "
                    + "primerApellidoE = ? AND "
                    + "segundoApellidoT = ? AND "
                    + "correo = ?";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setString(1, nombreE);
            pstmt.setString(2, primerApellidoE);
            pstmt.setString(3, segundoApellidoT);
            pstmt.setString(4, correo);
            pstmt.execute();
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                idRetornado = rs.getInt("id_duenio");
            }
        } catch (Exception e)
        {
            System.out.println("Error en verificaExistenciaMiProyecto: " + e);
        } finally
        {
            conexion.disconnect();
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
            } catch (SQLException e)
            {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return idRetornado;
    }
}
