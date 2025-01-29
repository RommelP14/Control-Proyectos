/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manageBean.conexion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Antonio Suárez Sánchez
 * @fecha_creacion 12/08/2024
 * @fecha_actualizacion
 *
 */
public class Conexion implements Serializable
{
    // campos d configuración
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String databaseURL = "jdbc:mysql://127.0.0.1:3306/controlproyecto?useSSL=false&serverTimezone=America/Mexico_City";
    private String databaseURL_SAM = "jdbc:mysql://localhost:3306/sam?allowLoadLocalInfile=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private transient Connection conn_SAM;
    private transient Connection conn;

    // ===========================================
    // Metodo de configuración y de base de datos
    // ===========================================
    /**
     * Crea una nueva conexión usando el controlador JDBC y la URL especificados
     *
     * @exception SQLException si falla la conexión o si ya existe
     */

    
    public void connect_SAM()
    {
        if (isConnected())
        {
            System.out.println("Ya está conectado.");
            conn_SAM = null;
            // throw new SQLException("Ya está conectado.");
        }

        // carga el controlador
        try
        {
            Class.forName(jdbcDriver);

        } catch (ClassNotFoundException e)
        {
            System.out.println("La clase " + jdbcDriver + " no se puedo cargar.");
        }

        // Abrir la conexión
        try
        {
            setConn_SAM(DriverManager.getConnection(databaseURL_SAM, "root", "1234"));
        } catch (SQLException e)
        {
            System.out.println("Error en la conexión... " + e.getMessage());
        }
    }
    
    public void connect(String username, String password)
    {
        if (isConnected())
        {
            System.out.println("Ya está conectado.");
            conn = null;
            // throw new SQLException("Ya está conectado.");
        }

        // carga el controlador
        try
        {
            Class.forName(jdbcDriver);

        } catch (ClassNotFoundException e)
        {
            System.out.println("La clase " + jdbcDriver + " no se puedo cargar.");
        }

        // Abrir la conexión
        try
        {
            conn = DriverManager.getConnection(databaseURL, username, password);
        } catch (SQLException e)
        {
            System.out.println("Error en la conexión... " + e.getMessage());
        }
    }

    /**
     * Cierra la conexión en curso
     */
    public void disconnect()
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            } catch (SQLException ignore)
            {
            } finally
            {
                conn = null;
            }
        }
        if (getConn_SAM() != null)
        {
            try
            {
                getConn_SAM().close();
            } catch (SQLException ignore)
            {
            } finally
            {
                setConn_SAM(null);
            }
        }
    }

    
    
    /**
     * Devuelve verdadero si hay una conexión activa
     *
     * @return True or False
     */
    public boolean isConnected()
    {
        return (conn != null);
    }

    /**
     * @return the conn
     */
    public Connection getCon()
    {
        return conn;
    }

    /**
     * @param con the conn to set
     */
    public void setCon(Connection con)
    {
        this.conn = con;
    }

    /**
     * @return the conn_SAM
     */
    public Connection getConn_SAM() {
        return conn_SAM;
    }

    /**
     * @param conn_SAM the conn_SAM to set
     */
    public void setConn_SAM(Connection conn_SAM) {
        this.conn_SAM = conn_SAM;
    }

}
