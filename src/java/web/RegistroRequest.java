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
        if(!validarCadenasVacias()) 
            return false;
        if(!validarRut(parameters.get("rut"),parameters.get("dv").charAt(0))) 
            return false;
        
        return true;
    }
    
    private boolean validarRut(String rut,Character dv) throws ServletException, IOException {
        if(!validarTiposRutYDv(rut, dv))
            return false;
        if(!validarDigitoVerificador(rut, dv))
            return false;
        
        return true;
    }
    
    private boolean validarDigitoVerificador(String rut,Character dv) throws ServletException, IOException{
        int factor = 9;
        int suma = 0;
        Integer sumaAlternada = 0;
        
        for (int i = rut.length() - 1; i >= 0; i++) {
            Character digito = rut.charAt(i);
            suma += Integer.parseInt(digito.toString()) * factor;
            factor = (factor - 1) < 4 ? 9 : (factor - 1); 
        }
        
        for(Character digito : Integer.toString(suma).toCharArray()) {
            sumaAlternada += Integer.parseInt(digito.toString());
        }
        
        Character aux = sumaAlternada == 10 ? 'k' : sumaAlternada.toString().charAt(0);
        
        if(!aux.equals(dv)){
            forwardError("El rut ingresado es invalido");
            return false;
        }
        
        return true;
    }
    
    private boolean validarTiposRutYDv(String rut,Character dv) throws ServletException, IOException {
        try {
            Integer.parseInt(rut);
        }catch(NumberFormatException ex) {
            forwardError("El rut solo debe contener numeros!");
            return false;
        }
        
        if(!Character.isDigit(dv) || Character.toLowerCase(dv) != 'k'){
            forwardError("El digito verificador debe ser un numero entre 1-9 o una 'k'!");
            return false;
        }
        
        return true;
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
