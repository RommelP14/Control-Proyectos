/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manageBean.general;

import static Utils.constantes.Constantes.STATUS_EXITO;

/**
 *
 * @author Alex Antonio Suárez Sánchez
 * @fecha_creacion 12/08/2024
 * @fecha_actualizacion
 *
 */
public class GenericResponse<T>
{

    private int status;
    private String mensaje;
    private T responseObject;

    public GenericResponse()
    {
        this.status = STATUS_EXITO;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMensaje()
    {
        return mensaje;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public T getResponseObject()
    {
        return responseObject;
    }

    public void setResponseObject(T responseObject)
    {
        this.responseObject = responseObject;
    }

    @Override
    public String toString()
    {
        return "GenericResponse{" + "status=" + status + ", mensaje=" + mensaje + ", responseObject=" + responseObject + '}';
    }
}
