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

    public Proyecto_MB(int noFolio, String nombre, String planteamiento, String alcances, String justificacion, int id_duenio, int id_departamento_tab, String estado)
    {
        this.noFolio = noFolio;
        this.nombre = nombre;
        this.planteamiento = planteamiento;
        this.alcances = alcances;
        this.justificacion = justificacion;
        this.id_duenio = id_duenio;
        this.id_departamento_tab = id_departamento_tab;
        this.estado = estado;
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

    @Override
    public String toString()
    {
        return "Proyecto_MB{" + "noFolio=" + noFolio + ", nombre=" + nombre + ", planteamiento=" + planteamiento + ", alcances=" + alcances + ", justificacion=" + justificacion + ", id_duenio=" + id_duenio + ", id_departamento_tab=" + id_departamento_tab + ", estado=" + estado + '}';
    }
}
