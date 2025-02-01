/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.titulacion;

import java.sql.Date;

/**
 *
 * @author mauro
 */
public class Titulacion_MB
{
    private int id_titulacion; 
     private String fecha_aprobacion;
     private String fecha_liberacion;
     private String estado_aprobacion_titulacion;

    public Titulacion_MB(int id_titulacion, String fecha_aprobacion, String fecha_liberacion, String estado_aprobacion_titulacion)
    {
        this.id_titulacion = id_titulacion;
        this.fecha_aprobacion = fecha_aprobacion;
        this.fecha_liberacion = fecha_liberacion;
        this.estado_aprobacion_titulacion = estado_aprobacion_titulacion;
    }

    public Titulacion_MB(String fecha_aprobacion, String fecha_liberacion, String estado_aprobacion_titulacion)
    {
        this.fecha_aprobacion = fecha_aprobacion;
        this.fecha_liberacion = fecha_liberacion;
        this.estado_aprobacion_titulacion = estado_aprobacion_titulacion;
    }

    /**
     * @return the id_titulacion
     */
    public int getId_titulacion()
    {
        return id_titulacion;
    }

    /**
     * @param id_titulacion the id_titulacion to set
     */
    public void setId_titulacion(int id_titulacion)
    {
        this.id_titulacion = id_titulacion;
    }

    /**
     * @return the fecha_aprobacion
     */
    public String getFecha_aprobacion()
    {
        return fecha_aprobacion;
    }

    /**
     * @param fecha_aprobacion the fecha_aprobacion to set
     */
    public void setFecha_aprobacion(String fecha_aprobacion)
    {
        this.fecha_aprobacion = fecha_aprobacion;
    }

    /**
     * @return the fecha_liberacion
     */
    public String getFecha_liberacion()
    {
        return fecha_liberacion;
    }

    /**
     * @param fecha_liberacion the fecha_liberacion to set
     */
    public void setFecha_liberacion(String fecha_liberacion)
    {
        this.fecha_liberacion = fecha_liberacion;
    }

    /**
     * @return the estado_aprobacion_titulacion
     */
    public String getEstado_aprobacion_titulacion()
    {
        return estado_aprobacion_titulacion;
    }

    /**
     * @param estado_aprobacion_titulacion the estado_aprobacion_titulacion to set
     */
    public void setEstado_aprobacion_titulacion(String estado_aprobacion_titulacion)
    {
        this.estado_aprobacion_titulacion = estado_aprobacion_titulacion;
    }

    @Override
    public String toString()
    {
        return "Titulacion_MB{" + "id_titulacion=" + id_titulacion + ", fecha_aprobacion=" + fecha_aprobacion + ", fecha_liberacion=" + fecha_liberacion + ", estado_aprobacion_titulacion=" + estado_aprobacion_titulacion + '}';
    }
    
    

    
}
