/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.proyecto_colab;

/**
 *
 * @author romme
 */
public class Proyecto_Colab_MB
{

    private int id_proyecto_colab;
    private int noFolio;
    private String fecha_aprobacion;
    private String fecha_liberacion;
    private String estado_aprobacion_proyecto_colab;
    
    /*Atributos para poder consultar los datos de un proyecto de proyecto_colab*/
     private String nombreProyecto;
     private String nombreColaborador;
     private String correoColaborador;

    public Proyecto_Colab_MB(int id_proyecto_colab, int noFolio, String fecha_aprobacion, String fecha_liberacion, String estado_aprobacion_proyecto_colab)
    {
        this.id_proyecto_colab = id_proyecto_colab;
        this.noFolio = noFolio;
        this.fecha_aprobacion = fecha_aprobacion;
        this.fecha_liberacion = fecha_liberacion;
        this.estado_aprobacion_proyecto_colab = estado_aprobacion_proyecto_colab;
    }

    public Proyecto_Colab_MB(int noFolio, String fecha_aprobacion, String fecha_liberacion, String estado_aprobacion_proyecto_colab)
    {
        this.noFolio = noFolio;
        this.fecha_aprobacion = fecha_aprobacion;
        this.fecha_liberacion = fecha_liberacion;
        this.estado_aprobacion_proyecto_colab = estado_aprobacion_proyecto_colab;
    }

    public Proyecto_Colab_MB(int noFolio, String fecha_aprobacion, String fecha_liberacion, String nombreProyecto, String nombreColaborador, String correoColaborador)
    {
        this.noFolio = noFolio;
        this.fecha_aprobacion = fecha_aprobacion;
        this.fecha_liberacion = fecha_liberacion;
        this.nombreProyecto = nombreProyecto;
        this.nombreColaborador = nombreColaborador;
        this.correoColaborador = correoColaborador;
    }

    /**
     * @return the id_proyecto_colab
     */
    public int getId_proyecto_colab()
    {
        return id_proyecto_colab;
    }

    /**
     * @param id_proyecto_colab the id_proyecto_colab to set
     */
    public void setId_proyecto_colab(int id_proyecto_colab)
    {
        this.id_proyecto_colab = id_proyecto_colab;
    }

    /**
     * @return the noFolio
     */
    public int getNoFolio()
    {
        return noFolio;
    }

    /**
     * @param noFolio the noFolio to set
     */
    public void setNoFolio(int noFolio)
    {
        this.noFolio = noFolio;
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
     * @return the estado_aprobacion_proyecto_colab
     */
    public String getEstado_aprobacion_proyecto_colab()
    {
        return estado_aprobacion_proyecto_colab;
    }

    /**
     * @param estado_aprobacion_proyecto_colab the
     * estado_aprobacion_proyecto_colab to set
     */
    public void setEstado_aprobacion_proyecto_colab(String estado_aprobacion_proyecto_colab)
    {
        this.estado_aprobacion_proyecto_colab = estado_aprobacion_proyecto_colab;
    }

    /**
     * @return the nombreProyecto
     */
    public String getNombreProyecto()
    {
        return nombreProyecto;
    }

    /**
     * @param nombreProyecto the nombreProyecto to set
     */
    public void setNombreProyecto(String nombreProyecto)
    {
        this.nombreProyecto = nombreProyecto;
    }

    /**
     * @return the nombreColaborador
     */
    public String getNombreColaborador()
    {
        return nombreColaborador;
    }

    /**
     * @param nombreColaborador the nombreColaborador to set
     */
    public void setNombreColaborador(String nombreColaborador)
    {
        this.nombreColaborador = nombreColaborador;
    }

    /**
     * @return the correoColaborador
     */
    public String getCorreoColaborador()
    {
        return correoColaborador;
    }

    /**
     * @param correoColaborador the correoColaborador to set
     */
    public void setCorreoColaborador(String correoColaborador)
    {
        this.correoColaborador = correoColaborador;
    }
        
    @Override
    public String toString()
    {
        return "Proyecto_Colab_MB{" + "id_proyecto_colab=" + id_proyecto_colab + ", noFolio=" + noFolio + ", fecha_aprobacion=" + fecha_aprobacion + ", fecha_liberacion=" + fecha_liberacion + ", estado_aprobacion_proyecto_colab=" + estado_aprobacion_proyecto_colab + '}';
    }
}
