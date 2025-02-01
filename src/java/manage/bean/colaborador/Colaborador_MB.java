/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.colaborador;

/**
 *
 * @author romme
 */
public class Colaborador_MB
{
    private int id_colaborador;
    private int noFolio;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String carrera;
    private String correo;
    private String no_control;

    public Colaborador_MB(int noFolio, String nombre, String primerApellido, String segundoApellido, String carrera, String correo, String no_control)
    {
        this.noFolio = noFolio;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.carrera = carrera;
        this.correo = correo;
        this.no_control = no_control;
    }

    public Colaborador_MB(int id_colaborador, int noFolio, String nombre, String primerApellido, String segundoApellido, String carrera, String correo, String no_control)
    {
        this.id_colaborador = id_colaborador;
        this.noFolio = noFolio;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.carrera = carrera;
        this.correo = correo;
        this.no_control = no_control;
    }

    /**
     * @return the id_colaborador
     */
    public int getId_colaborador()
    {
        return id_colaborador;
    }

    /**
     * @param id_colaborador the id_colaborador to set
     */
    public void setId_colaborador(int id_colaborador)
    {
        this.id_colaborador = id_colaborador;
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
     * @return the primerApellido
     */
    public String getPrimerApellido()
    {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido)
    {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the segundoApellido
     */
    public String getSegundoApellido()
    {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido)
    {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the carrera
     */
    public String getCarrera()
    {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(String carrera)
    {
        this.carrera = carrera;
    }

    /**
     * @return the correo
     */
    public String getCorreo()
    {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    /**
     * @return the no_control
     */
    public String getNo_control()
    {
        return no_control;
    }

    /**
     * @param no_control the no_control to set
     */
    public void setNo_control(String no_control)
    {
        this.no_control = no_control;
    }

    @Override
    public String toString()
    {
        return "Colaborador_MB{" + "id_colaborador=" + id_colaborador + ", noFolio=" + noFolio + ", nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", carrera=" + carrera + ", correo=" + correo + ", no_control=" + no_control + '}';
    }
}
