package controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Pancho
 */
public class JDBCMysql implements FabricaConexion {
    
    private static final String USUARIO = "root";
    private static final String PASS = "";
    private static final String URL = "jdbc:mysql://localhost/bd_crucero";

    public JDBCMysql() {
    }

    
    @Override
    public Connection fabricarConexion() throws Exception {        
        Class.forName("com.mysql.jdbc.Driver").newInstance(); 
        return DriverManager.getConnection(URL, USUARIO, PASS);
    }
}
