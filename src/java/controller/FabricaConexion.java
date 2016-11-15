package controller;

import java.sql.Connection;

/**
 *
 * @author Pancho
 */
public interface FabricaConexion {

    Connection fabricarConexion() throws Exception;
    
}
