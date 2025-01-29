/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.duenio;

/**
 *
 * @author mauro
 */
public class Duenio_MB
{
    private String nombreE;
    private String primerApellidoE;
    private String segundoApellidoT;
    private String correo;

    public Duenio_MB(String nombreE, String primerApellidoE, String segundoApellidoT, String correo)
    {
        this.nombreE = nombreE;
        this.primerApellidoE = primerApellidoE;
        this.segundoApellidoT = segundoApellidoT;
        this.correo = correo;
    }

    /**
     * @return the nombreE
     */
    public String getNombreE()
    {
        return nombreE;
    }

    /**
     * @param nombreE the nombreE to set
     */
    public void setNombreE(String nombreE)
    {
        this.nombreE = nombreE;
    }

    /**
     * @return the primerApellidoE
     */
    public String getPrimerApellidoE()
    {
        return primerApellidoE;
    }

    /**
     * @param primerApellidoE the primerApellidoE to set
     */
    public void setPrimerApellidoE(String primerApellidoE)
    {
        this.primerApellidoE = primerApellidoE;
    }

    /**
     * @return the segundoApellidoT
     */
    public String getSegundoApellidoT()
    {
        return segundoApellidoT;
    }

    /**
     * @param segundoApellidoT the segundoApellidoT to set
     */
    public void setSegundoApellidoT(String segundoApellidoT)
    {
        this.segundoApellidoT = segundoApellidoT;
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

    @Override
    public String toString()
    {
        return "Duenio_MB{" + "nombreE=" + nombreE + ", primerApellidoE=" + primerApellidoE + ", segundoApellidoT=" + segundoApellidoT + ", correo=" + correo + '}';
    }
}
