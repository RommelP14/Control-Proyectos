/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.proyectos;

/**
 *
 * @author mauro
 */
public class Proyecto_MB
{
    private int noFolio;
    private String nombre;
    private String planteamiento;
    private String alcances;
    private String justificacion;
    private int id_duenio;
    private int id_departamento_tab;
    private String estado;
    private String tipo_proyecto;
    
    /*Atributos para poder consultar los datos y mostrarlos en tabla*/
    private double porcentaje_avance;
    private String estado_aprobacion;

    public Proyecto_MB(int noFolio, String nombre, String planteamiento, String alcances, String justificacion, int id_duenio, int id_departamento_tab, String estado, String tipo_proyecto)
    {
        this.noFolio = noFolio;
        this.nombre = nombre;
        this.planteamiento = planteamiento;
        this.alcances = alcances;
        this.justificacion = justificacion;
        this.id_duenio = id_duenio;
        this.id_departamento_tab = id_departamento_tab;
        this.estado = estado;
        this.tipo_proyecto = tipo_proyecto;
    }

    public Proyecto_MB(String nombre, String planteamiento, String alcances, String justificacion, int id_duenio, int id_departamento_tab)
    {
        this.nombre = nombre;
        this.planteamiento = planteamiento;
        this.alcances = alcances;
        this.justificacion = justificacion;
        this.id_duenio = id_duenio;
        this.id_departamento_tab = id_departamento_tab;
    }

    public Proyecto_MB(int noFolio, String nombre, String estado, String tipo_proyecto, double porcentaje_avance, String estado_aprobacion)
    {
        this.noFolio = noFolio;
        this.nombre = nombre;
        this.estado = estado;
        this.tipo_proyecto = tipo_proyecto;
        this.porcentaje_avance = porcentaje_avance;
        this.estado_aprobacion = estado_aprobacion;
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
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the planteamiento
     */
    public String getPlanteamiento()
    {
        return planteamiento;
    }

    /**
     * @param planteamiento the planteamiento to set
     */
    public void setPlanteamiento(String planteamiento)
    {
        this.planteamiento = planteamiento;
    }

    /**
     * @return the alcances
     */
    public String getAlcances()
    {
        return alcances;
    }

    /**
     * @param alcances the alcances to set
     */
    public void setAlcances(String alcances)
    {
        this.alcances = alcances;
    }

    /**
     * @return the justificacion
     */
    public String getJustificacion()
    {
        return justificacion;
    }

    /**
     * @param justificacion the justificacion to set
     */
    public void setJustificacion(String justificacion)
    {
        this.justificacion = justificacion;
    }

    /**
     * @return the id_duenio
     */
    public int getId_duenio()
    {
        return id_duenio;
    }

    /**
     * @param id_duenio the id_duenio to set
     */
    public void setId_duenio(int id_duenio)
    {
        this.id_duenio = id_duenio;
    }

    /**
     * @return the id_departamento_tab
     */
    public int getId_departamento_tab()
    {
        return id_departamento_tab;
    }

    /**
     * @param id_departamento_tab the id_departamento_tab to set
     */
    public void setId_departamento_tab(int id_departamento_tab)
    {
        this.id_departamento_tab = id_departamento_tab;
    }

    /**
     * @return the estado
     */
    public String getEstado()
    {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    /**
     * @return the tipo_proyecto
     */
    public String getTipo_proyecto()
    {
        return tipo_proyecto;
    }

    /**
     * @param tipo_proyecto the tipo_proyecto to set
     */
    public void setTipo_proyecto(String tipo_proyecto)
    {
        this.tipo_proyecto = tipo_proyecto;
    }

    /**
     * @return the porcentaje_avance
     */
    public double getPorcentaje_avance()
    {
        return porcentaje_avance;
    }

    /**
     * @param porcentaje_avance the porcentaje_avance to set
     */
    public void setPorcentaje_avance(double porcentaje_avance)
    {
        this.porcentaje_avance = porcentaje_avance;
    }

    /**
     * @return the estado_aprobacion
     */
    public String getEstado_aprobacion()
    {
        return estado_aprobacion;
    }

    /**
     * @param estado_aprobacion the estado_aprobacion to set
     */
    public void setEstado_aprobacion(String estado_aprobacion)
    {
        this.estado_aprobacion = estado_aprobacion;
    }
    
    @Override
    public String toString()
    {
        return "Proyecto_MB{" + "noFolio=" + noFolio + ", nombre=" + nombre + ", planteamiento=" + planteamiento + ", alcances=" + alcances + ", justificacion=" + justificacion + ", id_duenio=" + id_duenio + ", id_departamento_tab=" + id_departamento_tab + ", estado=" + estado + ", tipo_proyecto=" + tipo_proyecto + '}';
    }
}
