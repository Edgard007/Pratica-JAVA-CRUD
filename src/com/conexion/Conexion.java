
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: Conexion
 * Fecha: 17-08-2019
 * Version: 1.0 
 * Copyright: Denis Valladares
 * @author Denis Valladares
 */
public class Conexion {
    private final String base = "valladarespalaciosjocsantadeo";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    private Connection con = null;
    
    
    
    
    public Connection getConexion()
    {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
            
        } catch(SQLException e)
        {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
      return con;  
     
      
      
    }
   
    public boolean desconectar()
    {
        try 
        {
            if (con!=null) 
            {
                if (con.isClosed()==false) 
                {
                  con.close();
                }
            }
            
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al Desconectar"+e.getMessage());
        }
        
        
        return true;
    }
}
