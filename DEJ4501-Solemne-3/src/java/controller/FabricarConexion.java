package controller;

import java.sql.Connection;

/**
 *
 * @author Pancho
 */
public interface FabricarConexion {

    Connection fabricarConexion() throws Exception;
    
}
