/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.similitudes;

import Utils.constantes.VariablesSistema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import manage.bean.proyectos.Proyecto_MB;
import manageBean.conexion.Conexion;

/**
 *
 * @author romme
 */
public class Similitudes_DAO
{

    private Conexion conexion;

    public void insertaSimilitud(int noFolio_proyecto_tab_revision, int noFolio_proyecto_tab_parecido)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        PreparedStatement pstmt = null;
        try
        {
            String query = "INSERT INTO similitudes_tab (noFolio_proyecto_tab_revision, noFolio_proyecto_tab_parecido) VALUES (?, ?)";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio_proyecto_tab_revision);
            pstmt.setInt(2, noFolio_proyecto_tab_parecido);
            pstmt.executeUpdate();
        } catch (Exception e)
        {
            System.out.println("Error en Similitudes: " + e);
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

    public List<Integer> consultaProyectosSimilaresPorId(int noFolio_proyecto_tab_revision)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Integer> folios_Parecidos = new ArrayList<>();

        try
        {
            // Consulta para obtener los proyectos
            String query = "SELECT noFolio_proyecto_tab_parecido\n"
                                + "FROM similitudes_tab\n"
                                + "WHERE noFolio_proyecto_tab_revision = ?;";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio_proyecto_tab_revision);
            rs = pstmt.executeQuery();

            // Itera sobre los resultados
            while (rs.next())
            {
                folios_Parecidos.add(rs.getInt("noFolio_proyecto_tab_parecido"));
            }
        } catch (SQLException e)
        {
            System.out.println("Error al consultar proyectos similares: " + e.getMessage());
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

        return folios_Parecidos;
    }
}
