/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manageBean.conexion;

import Utils.constantes.Constantes;
import Utils.constantes.Strings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import manageBean.empleado.Empleado_MB;
import manageBean.general.GenericResponse;

/**
 *
 *
 * @author romme
 * @fecha_creacion 08/01/2025
 * @fecha_actualizacion
 *
 */
public class Empleado_DAO
{

    private Conexion conexion;

    public Empleado_MB getEmpleado(String token)
    {
        conexion = new Conexion();
        conexion.connect_SAM();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Empleado_MB empleado_MB = null;

        try
        {
            // preparar la consulta SQL
            String sql = "SELECT em.id_empleado AS idEmpleado, "
            + "em.nombre AS nombre, "
            + "em.apellidoPa AS apellidoPaterno, "
            + "em.apellidoMa AS apellidoMaterno, "
            + "em.id_puesto AS puestoID, "
            + "em.id_departamento AS deptoID, "
            + "dep.nombre AS nombreDepartamento, "
            + "puesto.nombre AS nombrePuesto, "
            + "em.correo AS correoEmpleado, "
            + "puesto.correoPuesto AS correoPuesto, "
            + "em.telefono AS telefonoEmpleado "
            + "FROM sesiones s "
            + "JOIN empleados em ON em.id_empleado = s.id_empleado "
            + "JOIN departamento dep ON em.id_departamento = dep.id_departamento "
            + "JOIN puesto ON em.id_puesto = puesto.id_puesto "
            + "WHERE s.token = ?";


            pstmt = conexion.getConn_SAM().prepareStatement(sql);
            pstmt.setString(1, token);

            // ejecutar la consulta
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                empleado_MB = Empleado_MB.load(rs);
            }
        } catch (Exception e)
        {
            System.out.println("Error al obtener el empleado: " + e.getMessage());
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
        return empleado_MB;
    }

    public GenericResponse<Empleado_MB> getEmpleadoUsuario(String usuario)
    {
        conexion = new Conexion();
        conexion.connect_SAM();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        GenericResponse<Empleado_MB> gr = new GenericResponse<>();
        Empleado_MB empleado_MB = null;

        try
        {
            String sql = "SELECT em.id_empleado AS idEmpleado, "
                    + "em.nombre AS nombre, "
                    + "em.apellidoPa AS apellidoPaterno, "
                    + "em.apellidoMa AS apellidoMaterno, "
                    + "em.id_puesto AS puestoID, "
                    + "em.id_departamento AS deptoID, "
                    + "dep.nombre AS nombreDepartamento, "
                    + "puesto.nombre AS nombrePuesto, "
                    + "em.correo AS correoEmpleado, "
                    + "puesto.correoPuesto AS correoPuesto, "
                    + "em.telefono AS telefonoEmpleado "
                    + "FROM sesiones s "
                    + "JOIN empleados em ON em.id_empleado = s.id_empleado "
                    + "JOIN departamento dep ON em.id_departamento = dep.id_departamento "
                    + "JOIN puesto ON em.id_puesto = puesto.id_puesto "
                    + "WHERE em.usuario = ?";

            pstmt = conexion.getConn_SAM().prepareStatement(sql);
            System.out.println("Usuario; " + usuario);
            pstmt.setString(1, usuario);

            rs = pstmt.executeQuery();
            if (rs.next())
            {
                empleado_MB = Empleado_MB.load(rs);
            }

            if (empleado_MB != null)
            {
                //response.setStatus(STATUS_REGISTRO_EXITOSO_BD);
                gr.setResponseObject(empleado_MB);
                gr.setMensaje("Usuario encontrado");
                System.out.println("encontroUsuario");
            } else
            {
                //response.setStatus(STATUS_REGISTRO_FALLIDO_BD);
                gr.setResponseObject(null);
                gr.setMensaje("Usuario no encontrado");
                System.out.println("Se metio aqui");
            }
        } catch (Exception e)
        {
            System.out.println("Manda mensaje aqui:");
            System.out.println("Error al obtener el empleado: " + e.getMessage());
            gr.setStatus(Constantes.STATUS_CONEXION_FALLIDA_BD);
            gr.setResponseObject(null);
            gr.setMensaje(Strings.MSG_ERROR_CONEXION_BD);
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
        return gr;
    }

//    public String getEmpleadoPuesto(int id_puesto)
//    {
//        conexion = new Conexion();
//        conexion.connect_SAM();
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Empleado_MB empleado_MB = null;
//        String nombrePuesto = "";
//
//        try
//        {
//            // preparar la consulta SQL
//            String sql = "SELECT nombre FROM puesto WHERE id_puesto = ?";
//
//            pstmt = conexion.getConn_SAM().prepareStatement(sql);
//            pstmt.setInt(1, id_puesto);
//
//            // ejecutar la consulta
//            rs = pstmt.executeQuery();
//            if (rs.next())
//            {
//                nombrePuesto = rs.getString("nombre");
//            }
//        } catch (Exception e)
//        {
//            System.out.println("error al obtener el empleado " + e.getMessage());
//        } finally
//        {
//            conexion.disconnect();
//            if (rs != null)
//            {
//                try
//                {
//                    rs.close();
//                } catch (SQLException e)
//                {
//                    System.out.println("error al cerrar el ResultSet" + e.getMessage());
//                }
//            }
//            if (pstmt != null)
//            {
//                try
//                {
//                    pstmt.close();
//                } catch (SQLException e)
//                {
//                    System.out.println("Error al cerrar PreparedStatement " + e.getMessage());
//                }
//            }
//        }
//        return nombrePuesto;
//    }

    public ArrayList<String> getPuestosJefe()
    {
        conexion = new Conexion();
        conexion.connect_SAM();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Empleado_MB empleado_MB = null;
        ArrayList<String> puestos = new ArrayList<>();

        try
        {
            String sql = "SELECT nombre FROM puesto WHERE nombre LIKE 'Jefe%' OR nombre LIKE 'Jefa%';";
            pstmt = conexion.getConn_SAM().prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next())
            {
                puestos.add(rs.getString("nombre"));
            }
        } catch (Exception e)
        {
            System.out.println("error en la consulta puestos " + e.getMessage());
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
        return puestos;
    }
}
