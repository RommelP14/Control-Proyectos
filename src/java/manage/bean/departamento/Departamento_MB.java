/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.departamento;

/**
 *
 * @author mauro
 */
public class Departamento_MB
{
    private int id_departamento_tab;
    private int id_departamento_sam;
    private double porcentaje_min;
    private double porcentaje_max;

    public Departamento_MB(int id_departamento_sam, double porcentaje_min, double porcentaje_max)
    {
        this.id_departamento_sam = id_departamento_sam;
        this.porcentaje_min = porcentaje_min;
        this.porcentaje_max = porcentaje_max;
    }

    public Departamento_MB(int id_departamento_tab, int id_departamento_sam, double porcentaje_min, double porcentaje_max)
    {
        this.id_departamento_tab = id_departamento_tab;
        this.id_departamento_sam = id_departamento_sam;
        this.porcentaje_min = porcentaje_min;
        this.porcentaje_max = porcentaje_max;
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
     * @return the id_departamento_sam
     */
    public int getId_departamento_sam()
    {
        return id_departamento_sam;
    }

    /**
     * @param id_departamento_sam the id_departamento_sam to set
     */
    public void setId_departamento_sam(int id_departamento_sam)
    {
        this.id_departamento_sam = id_departamento_sam;
    }

    /**
     * @return the porcentaje_min
     */
    public double getPorcentaje_min()
    {
        return porcentaje_min;
    }

    /**
     * @param porcentaje_min the porcentaje_min to set
     */
    public void setPorcentaje_min(double porcentaje_min)
    {
        this.porcentaje_min = porcentaje_min;
    }

    /**
     * @return the porcentaje_max
     */
    public double getPorcentaje_max()
    {
        return porcentaje_max;
    }

    /**
     * @param porcentaje_max the porcentaje_max to set
     */
    public void setPorcentaje_max(double porcentaje_max)
    {
        this.porcentaje_max = porcentaje_max;
    }

    @Override
    public String toString()
    {
        return "Departamento_MB{" + "id_departamento_tab=" + id_departamento_tab + ", id_departamento_sam=" + id_departamento_sam + ", porcentaje_min=" + porcentaje_min + ", porcentaje_max=" + porcentaje_max + '}';
    }
}
