/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage.bean.similitudes;

/**
 *
 * @author romme
 */
public class Similitudes_MB
{
    private int noFolio_proyecto_tab_revision;
    private int noFolio_proyecto_tab_parecido;

    public Similitudes_MB(int noFolio_proyecto_tab_revision, int noFolio_proyecto_tab_parecido)
    {
        this.noFolio_proyecto_tab_revision = noFolio_proyecto_tab_revision;
        this.noFolio_proyecto_tab_parecido = noFolio_proyecto_tab_parecido;
    }

    public Similitudes_MB(int noFolio_proyecto_tab_parecido)
    {
        this.noFolio_proyecto_tab_parecido = noFolio_proyecto_tab_parecido;
    }
    
    /**
     * @return the noFolio_proyecto_tab_revision
     */
    public int getNoFolio_proyecto_tab_revision()
    {
        return noFolio_proyecto_tab_revision;
    }

    /**
     * @param noFolio_proyecto_tab_revision the noFolio_proyecto_tab_revision to set
     */
    public void setNoFolio_proyecto_tab_revision(int noFolio_proyecto_tab_revision)
    {
        this.noFolio_proyecto_tab_revision = noFolio_proyecto_tab_revision;
    }

    /**
     * @return the noFolio_proyecto_tab_parecido
     */
    public int getNoFolio_proyecto_tab_parecido()
    {
        return noFolio_proyecto_tab_parecido;
    }

    /**
     * @param noFolio_proyecto_tab_parecido the noFolio_proyecto_tab_parecido to set
     */
    public void setNoFolio_proyecto_tab_parecido(int noFolio_proyecto_tab_parecido)
    {
        this.noFolio_proyecto_tab_parecido = noFolio_proyecto_tab_parecido;
    }

    @Override
    public String toString()
    {
        return "Similitudes_MB{" + "noFolio_proyecto_tab_revision=" + noFolio_proyecto_tab_revision + ", noFolio_proyecto_tab_parecido=" + noFolio_proyecto_tab_parecido + '}';
    }
}
