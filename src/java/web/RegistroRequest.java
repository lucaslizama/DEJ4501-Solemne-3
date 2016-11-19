package web;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 * Clase para recibir, validar y procesar los datos de un usuario
 * y registrarlo en la base de datos.
 */
public class RegistroRequest {

    private HashMap<String,String> parameters;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    
    public RegistroRequest(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        rd = request.getRequestDispatcher("registro.jsp");
        parameters = new HashMap<>();
        Enumeration<String> nombres = request.getParameterNames();
        while(nombres.hasMoreElements()){
            //Inicializo un HashMap con los parametros y sus 
            //respectivos nombres como llaves.
            String nombre = nombres.nextElement();
            parameters.put(nombre,request.getParameter(nombre));
        }
    }
    
    
    public boolean validarParametros() throws ServletException, IOException {
        if(!validarCadenasVacias()) return false;
        
        return true;
    }
    
    private boolean validarRut() {
        return false;
    }
    
    private boolean validarCorreo() {
        return false;
    }
    
    private boolean validarExistenciaUser() {
        return false;
    }
    
    private boolean validarCadenasVacias() throws ServletException, IOException {
        for(String key : parameters.keySet()) {
            if(parameters.get(key).isEmpty()){
                forwardError("Todos los campos deben llenarse!");
                return false;
            }
        }
        return true;
    }
    
    private void forwardError(String message) throws ServletException, IOException {
        request.setAttribute("mensaje", message);
        request.setAttribute("color", "red");
        rd.forward(request, response);
    }
}
