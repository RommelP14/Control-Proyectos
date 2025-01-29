/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manageBean.usuarios;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alex Antonio Suárez Sánchez
 * @fecha_creacion 12/08/2024
 * @fecha_actualizacion
 *
 */
public class Usuarios_MB
{

    private int id;
    private String usuario;
    private String password;
    private String rolAccess;
    private int puestoID;
    private int departamentoID;

    public static Usuarios_MB load(ResultSet rs) throws SQLException
    {
        Usuarios_MB usuarios_MB = new Usuarios_MB();
        usuarios_MB.setId(rs.getInt(1));
        usuarios_MB.setUsuario(rs.getString(2));
        usuarios_MB.setPassword(rs.getString(3));
        usuarios_MB.setPuestoID(rs.getInt(4));
        usuarios_MB.setDepartamentoID(rs.getInt(5));

        return usuarios_MB;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuarios_MB{");
        sb.append("id=").append(id);
        sb.append(", usuario=").append(usuario);
        sb.append(", password=").append(password);
        sb.append(", rolAccess=").append(rolAccess);
        sb.append(", puestoID=").append(puestoID);
        sb.append(", departamentoID=").append(departamentoID);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**s
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the usuario
     */
    public String getUsuario()
    {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
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
     * @return the departamentoID
     */
    public int getDepartamentoID()
    {
        return departamentoID;
    }

    /**
     * @param departamentoID the departamentoID to set
     */
    public void setDepartamentoID(int departamentoID)
    {
        this.departamentoID = departamentoID;
    }

    /**
     * @return the rolAccess
     */
    public String getRolAccess()
    {
        return rolAccess;
    }

    /**
     * @param rolAccess the rolAccess to set
     */
    public void setRolAccess(String rolAccess)
    {
        this.rolAccess = rolAccess;
    }
}
