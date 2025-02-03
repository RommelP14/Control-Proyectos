/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.departamento;

import Utils.constantes.VariablesSistema;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import manage.bean.departamento.Departamento_MB;
import manageBean.conexion.Conexion;

/**
 *
 * @author mauro
 */
public class Departamento_DAO
{

    private Conexion conexion;

    public int insertaDepartamento(int id_departamento_sam)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        int idGenerado = -1; // Valor por defecto en caso de error

        try
        {
            String query = "INSERT INTO departamento_tab (id_departamento_sam) VALUES (?)";
            pstmt = conexion.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id_departamento_sam);
            pstmt.executeUpdate();

            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next())
            {
                idGenerado = generatedKeys.getInt(1);
            }

        } catch (Exception e)
        {
            System.out.println("Error en insertar departamento: " + e);
        } finally
        {
            try
            {
                if (generatedKeys != null)
                {
                    generatedKeys.close();
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

        return idGenerado;
    }

    public void actualizaPorcentajeDepto(int id_departamento_sam, double porcentaje_min, double porcentaje_max)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;

        try
        {
            String query = "UPDATE departamento_tab SET porcentaje_min = ?, porcentaje_max = ? WHERE id_departamento_sam = ?";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setDouble(1, porcentaje_min);
            pstmt.setDouble(2, porcentaje_max);
            pstmt.setInt(3, id_departamento_sam);
            pstmt.executeUpdate();
        } catch (Exception e)
        {
            System.out.println("Error en insertar departamento: " + e);
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

    public Departamento_MB obtenerDepartamentoPorIdSAM(int id_departamento_sam)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Departamento_MB departamento_mb = null;
        try
        {
            String query = "SELECT * FROM departamento_tab WHERE id_departamento_sam = ?";

            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, id_departamento_sam);

            rs = pstmt.executeQuery();
            if (rs.next())
            {
                departamento_mb = new Departamento_MB(rs.getInt("id_departamento_tab"), rs.getInt("id_departamento_sam"), rs.getDouble("porcentaje_min"), rs.getDouble("porcentaje_max"));
            } else
            {
                departamento_mb = new Departamento_MB(insertaDepartamento(id_departamento_sam), id_departamento_sam, -1, -1);
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

        return departamento_mb;
    }

    public void actualizaFormatoDepto(int id_departamento_sam, InputStream imageStream)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        obtenerDepartamentoPorIdSAM(id_departamento_sam);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            // La ruta ya no es necesaria; usamos imageStream directamente
            String query = "UPDATE departamento_tab SET formato = ? WHERE id_departamento_sam = ?";

            pstmt = conexion.getCon().prepareStatement(query);

            pstmt.setBinaryStream(1, imageStream); // Se pasa el InputStream como un flujo binario
            pstmt.setInt(2, id_departamento_sam); // Usamos el id_departamento_sam como parámetro

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Imagen guardada exitosamente en la base de datos.");
            }
        } catch (Exception e)
        {
            System.out.println("Error al actualizar la imagen: " + e.getMessage());
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
                    System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
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
        }
    }
}
