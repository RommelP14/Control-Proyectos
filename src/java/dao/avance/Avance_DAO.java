/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.avance;

import Utils.constantes.VariablesSistema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import manage.bean.avance.Avance_MB;
import manageBean.conexion.Conexion;
import manageBean.general.GenericResponse;

/**
 *
 * @author romme
 */
public class Avance_DAO
{
    private Conexion conexion;

     public void actualizaPorcentajeAvance(int id_avance, double porcentaje)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        
        PreparedStatement pstmt = null;
        
        try
        {
            String query = "UPDATE avances_tab SET porcentaje = ? WHERE id_avance = ?";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setDouble(1, porcentaje);
            pstmt.setInt(2, id_avance);
            pstmt.executeUpdate();
        } catch (Exception e)
        {
            System.out.println("Error en actualizar porcentaje avance: " + e);
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
     
    public Avance_MB obtenerAvancePorNoFolio(int noFolio)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Avance_MB avance_Mb = null;
        try
        {
            String query = "SELECT * FROM avances_tab WHERE noFolio = ?";
            
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                avance_Mb = new Avance_MB(rs.getInt("id_avance"),rs.getInt("noFolio"),
                        rs.getDouble("porcentaje"), rs.getString("descripcion"), 
                        rs.getString("evidencia"), rs.getString("fecha_avance"));
            }
        } catch (Exception e)
        {
            System.out.println("error al obtener el porcentaje");
        } finally
        {
            conexion.disconnect();
            if (rs != null)
            {
                try
                {
                    rs.close();
                } catch (SQLException e)
                {
                    System.out.println("error al cerrar el ResultSet" + e.getMessage());
                }
            }
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
        
        return avance_Mb;
    }
    public void insertaAvance(int noFolio, double porcentaje, String descripcion, String evidencia, GenericResponse respuesta)
    {
        Avance_MB avance_Mb = obtenerAvancePorNoFolio(noFolio);
        double porcentajeTmp = porcentaje;
        if (avance_Mb != null)
        {
            porcentajeTmp += avance_Mb.getPorcentaje();
            if(porcentajeTmp > 100)
            {
                respuesta.setStatus(-900);
                respuesta.setMensaje("El porcentaje al que quieres actualizar es mayor a 100");
                respuesta.setResponseObject(porcentajeTmp);
                return;
            }
            this.actualizaPorcentajeAvance(noFolio, porcentaje);
        } 
        insertaAvanceBaseDeDatos(noFolio, Math.round(porcentajeTmp), descripcion, evidencia, respuesta);
    }
    private void insertaAvanceBaseDeDatos(int noFolio, double porcentaje, String descripcion, String evidencia, GenericResponse respuesta)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        PreparedStatement pstmt = null;
        String fecha_avance = obtenerFechaSistema();
        try
        {
            String query = "INSERT INTO avances_tab (noFolio, porcentaje, descripcion, evidencia, fecha_avance) VALUES (?, ROUND(?, 1), ?, ?, ?);";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            pstmt.setDouble(2, porcentaje);
            pstmt.setString(3, descripcion);
            pstmt.setString(4, evidencia);
            pstmt.setString(5, fecha_avance);
            pstmt.executeUpdate();

            respuesta.setStatus(0);
            respuesta.setMensaje("Se ha insertado con éxito un avance");
        } catch (Exception e)
        {
            System.out.println("Error en Avance: " + e);
            respuesta.setStatus(-100);
            respuesta.setMensaje("Ha ocurrido un error al insertar un avance");
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
    
    private String obtenerFechaSistema()
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaSistema = new Date();

        return formato.format(fechaSistema);
    }
}
