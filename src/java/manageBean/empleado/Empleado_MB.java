/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manageBean.empleado;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Empleado_MB implements Serializable
{
    private int id_empleado;
    private String nombre;
    private String apellidoPa;
    private String apellidoMa;
    private int puestoID;
    private int deptoID;
    private String nombreDepartamento;
    private String nombrePuesto;
    private String correoEmpleado;
    private String correoPuesto;
    private String telefono;
    

    public static Empleado_MB load(ResultSet rs) throws SQLException
    {
        Empleado_MB empleado = new Empleado_MB();

        empleado.setId_empleado(rs.getInt(1));
        empleado.setNombre(rs.getString(2));
        empleado.setApellidoPa(rs.getString(3));
        empleado.setApellidoMa(rs.getString(4));
        empleado.setPuestoID(rs.getInt(5));
        empleado.setDeptoID(rs.getInt(6));
        empleado.setNombreDepartamento(rs.getString(7));
        empleado.setNombrePuesto(rs.getString(8));
        empleado.setCorreoEmpleado(rs.getString(9));
        empleado.setCorreoPuesto(rs.getString(10));
        empleado.setTelefono(rs.getString(11));
        return empleado;
    }

    /**
     * @return the id_empleado
     */
    public int getId_empleado()
    {
        return id_empleado;
    }

    /**
     * @param id_empleado the id_empleado to set
     */
    public void setId_empleado(int id_empleado)
    {
        this.id_empleado = id_empleado;
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
     * @return the apellidoPa
     */
    public String getApellidoPa()
    {
        return apellidoPa;
    }

    /**
     * @param apellidoPa the apellidoPa to set
     */
    public void setApellidoPa(String apellidoPa)
    {
        this.apellidoPa = apellidoPa;
    }

    /**
     * @return the apellidoMa
     */
    public String getApellidoMa()
    {
        return apellidoMa;
    }

    /**
     * @param apellidoMa the apellidoMa to set
     */
    public void setApellidoMa(String apellidoMa)
    {
        this.apellidoMa = apellidoMa;
    }

    /**
     * @return the puestoID
     */
    public int getPuestoID()
    {
        return puestoID;
    }

    /**
     * @param puestoID the puestoID to set
     */
    public void setPuestoID(int puestoID)
    {
        this.puestoID = puestoID;
    }

    /**
     * @return the deptoID
     */
    public int getDeptoID()
    {
        return deptoID;
    }

    /**
     * @param deptoID the deptoID to set
     */
    public void setDeptoID(int deptoID)
    {
        this.deptoID = deptoID;
    }

    /**
     * @return the nombreDepartamento
     */
    public String getNombreDepartamento()
    {
        return nombreDepartamento;
    }

    /**
     * @param nombreDepartamento the nombreDepartamento to set
     */
    public void setNombreDepartamento(String nombreDepartamento)
    {
        this.nombreDepartamento = nombreDepartamento;
    }

    /**
     * @return the nombrePuesto
     */
    public String getNombrePuesto()
    {
        return nombrePuesto;
    }

    /**
     * @param nombrePuesto the nombrePuesto to set
     */
    public void setNombrePuesto(String nombrePuesto)
    {
        this.nombrePuesto = nombrePuesto;
    }

    /**
     * @return the correoPuesto
     */
    public String getCorreoPuesto()
    {
        return correoPuesto;
    }

    /**
     * @param correoPuesto the correoPuesto to set
     */
    public void setCorreoPuesto(String correoPuesto)
    {
        this.correoPuesto = correoPuesto;
    }

    /**
     * @return the correoEmpleado
     */
    public String getCorreoEmpleado()
    {
        return correoEmpleado;
    }

    /**
     * @param correoEmpleado the correoEmpleado to set
     */
    public void setCorreoEmpleado(String correoEmpleado)
    {
        this.correoEmpleado = correoEmpleado;
    }

    /**
     * @return the telefono
     */
    public String getTelefono()
    {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    @Override
    public String toString()
    {
        return "Empleado_MB{" + "id_empleado=" + id_empleado + ", nombre=" + nombre + ", apellidoPa=" + apellidoPa + ", apellidoMa=" + apellidoMa + ", puestoID=" + puestoID + ", deptoID=" + deptoID + ", nombreDepartamento=" + nombreDepartamento + ", nombrePuesto=" + nombrePuesto + ", correoPuesto=" + correoPuesto + ", correoEmpleado=" + correoEmpleado + ", telefono=" + telefono + '}';
    }
}
