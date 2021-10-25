
package model;

import java.sql.*;

/**
 * classe de connexion a la BDD
 */
public class Connexion 
{
    public static Connection connecterDB()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/edt";//url de la BDD
            String user="root";//login
            String password="";//MdP
            Connection cnx=DriverManager.getConnection(url,user,password); 
            return cnx;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
