/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.residencia;

import Utils.constantes.VariablesSistema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import manage.bean.residencia.Residencia_MB;
import manageBean.conexion.Conexion;
import manageBean.general.GenericResponse;

/**
 *
 * @author mauro
 */
public class Residencia_DAO
{

    private Conexion conexion;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Residencia_MB consultaProyectoResidenciaParaVerDatos(int noFolio, GenericResponse respuesta)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Residencia_MB residencia_mb = null;

        try
        {
            String query = "SELECT \n"
                    + "    p.noFolio, \n"
                    + "    COALESCE(p.nombre, 'Aun no registrado') AS nombreProyecto, \n"
                    + "    COALESCE(c.nombre, 'Aun no registrado') AS nombreColaborador, \n"
                    + "    COALESCE(c.correo, 'Aun no registrado') AS correoColaborador, \n"
                    + "    COALESCE(r.fecha_inicio, 'Aun no registrado') AS fecha_inicio, \n"
                    + "    COALESCE(r.primer_seguimiento, 'Aun no registrado') AS primer_seguimiento, \n"
                    + "    COALESCE(r.segundo_seguimiento, 'Aun no registrado') AS segundo_seguimiento, \n"
                    + "    COALESCE(r.fecha_fin, 'Aun no registrado') AS fecha_fin, \n"
                    + "    COALESCE(r.calificacion, 0.0) AS calificacion\n"
                    + "FROM proyectos_tab p\n"
                    + "LEFT JOIN colaboradores_tab c ON p.noFolio = c.noFolio\n"
                    + "LEFT JOIN residencia_tab r ON p.noFolio = r.noFolio\n"
                    + "WHERE p.noFolio = ?";

            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                String nombreProyecto = rs.getString("nombreProyecto");
                String nombreColaborador = rs.getString("nombreColaborador");
                String correoColaborador = rs.getString("correoColaborador");
                String fechaInicio = rs.getString("fecha_inicio");
                String fechaPrimerSeguimiento = rs.getString("primer_seguimiento");
                String fechaSegundoSeguimiento = rs.getString("segundo_seguimiento");
                String fechaFinal = rs.getString("fecha_fin");
                double calificacion = rs.getDouble("calificacion");

                residencia_mb = new Residencia_MB(noFolio, fechaInicio, fechaPrimerSeguimiento,
                        fechaSegundoSeguimiento, fechaFinal, calificacion, nombreProyecto,
                        nombreColaborador, correoColaborador);
            } else
            {
                respuesta.setStatus(-10);
                respuesta.setMensaje("No se encontro el proyecto con ese noFolio");
            }
        } catch (SQLException e)
        {
            System.out.println("Error al consultar residencia: " + e.getMessage());
            respuesta.setStatus(-3);
            respuesta.setMensaje("Ha ocurrido un error en consultar un proyecto de residencia");
        } catch (Exception e)
        {
            System.out.println("Error general en consultarProyectoResidencia: " + e.getMessage());
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
        return residencia_mb;
    }

    public void insertaResidencia(int noFolio, String estado_aprobacion_residencia, GenericResponse response)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        PreparedStatement pstmt = null;
        String fecha_inicio = obtenerFechaSistema();
        String fechas3[] = obtener3Fechas(fecha_inicio);
        try
        {
            String query = "INSERT INTO residencia_tab (noFolio, fecha_inicio, primer_seguimiento, segundo_seguimiento, fecha_fin,"
                    + " estado_aprobacion_residencia) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            pstmt.setString(2, fecha_inicio);
            pstmt.setString(3, fechas3[0]);
            pstmt.setString(4, fechas3[1]);
            pstmt.setString(5, fechas3[2]);
            pstmt.setString(6, estado_aprobacion_residencia);

            pstmt.executeUpdate();
            response.setStatus(0);
            response.setMensaje("Se ha insertado con exito en Residencia");
            response.setResponseObject(estado_aprobacion_residencia);
        } catch (Exception e)
        {
            System.out.println("Error en Similitudes: " + e);
            response.setStatus(-100);
            response.setMensaje("Ha ocurrido un error al insertar la residencia");
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

    public void actualizaCalificacionResidencia(int noFolio, double calificacion, GenericResponse response)
    {
        conexion = new Conexion();
        conexion.connect(VariablesSistema.USERNAME_BD, VariablesSistema.PASSWORD_BD);
        PreparedStatement pstmt = null;
        try
        {
            String query = "UPDATE residencia_tab SET calificacion = ? WHERE noFolio = ?";
            pstmt = conexion.getCon().prepareStatement(query);
            pstmt.setInt(1, noFolio);
            pstmt.setDouble(2, calificacion);

            pstmt.executeUpdate();
            response.setStatus(0);
            response.setMensaje("Se ha asignado la calificacion al proyecto con folio: " + noFolio);
        } catch (Exception e)
        {
            System.out.println("Error en asignador la calificacion: " + e);
            response.setStatus(-701);
            response.setMensaje("Ha ocurrido un error al asignador la calificacion");
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

    private static String[] obtener3Fechas(String fechaInicio)
    {
        String[] fechas = new String[3];
        LocalDate fecha = LocalDate.parse(fechaInicio, FORMATTER);

        for (int i = 0; i < 3; i++)
        {
            fecha = sumar30Dias(fecha);
            fechas[i] = fecha.format(FORMATTER);
        }

        return fechas;
    }

    private static LocalDate sumar30Dias(LocalDate fecha)
    {
        do
        {
            fecha = fecha.plusDays(30);

            // Si la fecha cae en julio, avanzamos al 1 de agosto
            if (fecha.getMonthValue() == 7)
            {
                fecha = LocalDate.of(fecha.getYear(), 8, 1);
            }

            // Si la fecha cae en los últimos 15 días de diciembre, avanzamos al 1 de enero del siguiente año
            if (fecha.getMonthValue() == 12 && fecha.getDayOfMonth() > 16)
            {
                fecha = LocalDate.of(fecha.getYear() + 1, 1, 1);
            }
        } while (fecha.getMonthValue() == 7 || (fecha.getMonthValue() == 12 && fecha.getDayOfMonth() > 16));

        return fecha;
    }

}
