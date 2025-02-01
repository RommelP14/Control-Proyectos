/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.residencia;

/**
 *
 * @author mauro
 */
public class Residencia_MB
{
    private int id_residencia;
    private int noFolio;
    private String fecha_inicio;
    private String primer_seguimiento;
    private String segundo_seguimiento;
    private String fecha_fin;
    private double calificacion;
    private String estado_aprobacion_residencia;

    public Residencia_MB(int id_residencia, int noFolio, String fecha_inicio, String primer_seguimiento, String segundo_seguimiento, String fecha_fin, double calificacion, String estado_aprobacion_residencia)
    {
        this.id_residencia = id_residencia;
        this.noFolio = noFolio;
        this.fecha_inicio = fecha_inicio;
        this.primer_seguimiento = primer_seguimiento;
        this.segundo_seguimiento = segundo_seguimiento;
        this.fecha_fin = fecha_fin;
        this.calificacion = calificacion;
        this.estado_aprobacion_residencia = estado_aprobacion_residencia;
    }

    public Residencia_MB(int noFolio, String fecha_inicio, String primer_seguimiento, String segundo_seguimiento, String fecha_fin, double calificacion, String estado_aprobacion_residencia)
    {
        this.noFolio = noFolio;
        this.fecha_inicio = fecha_inicio;
        this.primer_seguimiento = primer_seguimiento;
        this.segundo_seguimiento = segundo_seguimiento;
        this.fecha_fin = fecha_fin;
        this.calificacion = calificacion;
        this.estado_aprobacion_residencia = estado_aprobacion_residencia;
    }

    /**
     * @return the id_residencia
     */
    public int getId_residencia()
    {
        return id_residencia;
    }

    /**
     * @param id_residencia the id_residencia to set
     */
    public void setId_residencia(int id_residencia)
    {
        this.id_residencia = id_residencia;
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
     * @return the fecha_inicio
     */
    public String getFecha_inicio()
    {
        return fecha_inicio;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(String fecha_inicio)
    {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * @return the primer_seguimiento
     */
    public String getPrimer_seguimiento()
    {
        return primer_seguimiento;
    }

    /**
     * @param primer_seguimiento the primer_seguimiento to set
     */
    public void setPrimer_seguimiento(String primer_seguimiento)
    {
        this.primer_seguimiento = primer_seguimiento;
    }

    /**
     * @return the segundo_seguimiento
     */
    public String getSegundo_seguimiento()
    {
        return segundo_seguimiento;
    }

    /**
     * @param segundo_seguimiento the segundo_seguimiento to set
     */
    public void setSegundo_seguimiento(String segundo_seguimiento)
    {
        this.segundo_seguimiento = segundo_seguimiento;
    }

    /**
     * @return the fecha_fin
     */
    public String getFecha_fin()
    {
        return fecha_fin;
    }

    /**
     * @param fecha_fin the fecha_fin to set
     */
    public void setFecha_fin(String fecha_fin)
    {
        this.fecha_fin = fecha_fin;
    }

    /**
     * @return the calificacion
     */
    public double getCalificacion()
    {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(double calificacion)
    {
        this.calificacion = calificacion;
    }

    /**
     * @return the estado_aprobacion_residencia
     */
    public String getEstado_aprobacion_residencia()
    {
        return estado_aprobacion_residencia;
    }

    /**
     * @param estado_aprobacion_residencia the estado_aprobacion_residencia to set
     */
    public void setEstado_aprobacion_residencia(String estado_aprobacion_residencia)
    {
        this.estado_aprobacion_residencia = estado_aprobacion_residencia;
    }

    @Override
    public String toString()
    {
        return "Residencia_MB{" + "id_residencia=" + id_residencia + ", noFolio=" + noFolio + ", fecha_inicio=" + fecha_inicio + ", primer_seguimiento=" + primer_seguimiento + ", segundo_seguimiento=" + segundo_seguimiento + ", fecha_fin=" + fecha_fin + ", calificacion=" + calificacion + ", estado_aprobacion_residencia=" + estado_aprobacion_residencia + '}';
    }
    
    
}
