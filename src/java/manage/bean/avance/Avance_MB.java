/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.avance;

/**
 *
 * @author romme
 */
public class Avance_MB
{
    private int id_avance;
    private int noFolio;
    private double porcentaje;
    private String descripcion;
    private String evidencia;
    private String fecha_avance;

    public Avance_MB(int id_avance, int noFolio, double porcentaje, String descripcion, String evidencia, String fecha_avance)
    {
        this.id_avance = id_avance;
        this.noFolio = noFolio;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.evidencia = evidencia;
        this.fecha_avance = fecha_avance;
    }

    public Avance_MB(int noFolio, double porcentaje, String descripcion, String evidencia, String fecha_avance)
    {
        this.noFolio = noFolio;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.evidencia = evidencia;
        this.fecha_avance = fecha_avance;
    }

    /**
     * @return the id_avance
     */
    public int getId_avance()
    {
        return id_avance;
    }

    /**
     * @param id_avance the id_avance to set
     */
    public void setId_avance(int id_avance)
    {
        this.id_avance = id_avance;
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
     * @return the porcentaje
     */
    public double getPorcentaje()
    {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(double porcentaje)
    {
        this.porcentaje = porcentaje;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * @return the evidencia
     */
    public String getEvidencia()
    {
        return evidencia;
    }

    /**
     * @param evidencia the evidencia to set
     */
    public void setEvidencia(String evidencia)
    {
        this.evidencia = evidencia;
    }

    /**
     * @return the fecha_avance
     */
    public String getFecha_avance()
    {
        return fecha_avance;
    }

    /**
     * @param fecha_avance the fecha_avance to set
     */
    public void setFecha_avance(String fecha_avance)
    {
        this.fecha_avance = fecha_avance;
    }
}
