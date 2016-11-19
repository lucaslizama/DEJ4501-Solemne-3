package web;

import db.Usuario;
import ejb.UsuarioFacade;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Enumeration;
import java.util.HashMap;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
    private UsuarioFacade usuarioFacade;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    
    public RegistroRequest(HttpServletRequest request, HttpServletResponse response,UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
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
        if(!validarCorreo())
            return false;
        if(!validarExistenciaUser())
            return false;
        if(!validarPassword())
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
        Integer suma = 0;
        Integer sumaAlternada = 0;
        char[] digitos;
        
        for (int i = rut.length() - 1; i >= 0; i--) {
            Character digito = rut.charAt(i);
            suma += Integer.parseInt(digito.toString()) * factor;
            factor = (factor - 1) < 4 ? 9 : (factor - 1); 
        }

        digitos = suma.toString().toCharArray();
        
        sumaAlternada += Integer.parseInt(Character.toString(digitos[2]));
        sumaAlternada -= Integer.parseInt(Character.toString(digitos[1]));
        sumaAlternada += Integer.parseInt(Character.toString(digitos[0]));
        
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
        
        if(!Character.isDigit(dv) && Character.toLowerCase(dv) != 'k'){
            forwardError("El digito verificador debe ser un numero entre 1-9 o una 'k'!");
            return false;
        }
        
        return true;
    }
    
    private boolean validarCorreo() throws ServletException, IOException {
        try {
            InternetAddress mail = new InternetAddress(parameters.get("correo"));
        } catch (AddressException ex) {
            forwardError("El correo ingresado es invalido!");
            return false;
        }
        return true;
    }
    
    private boolean validarExistenciaUser() throws ServletException, IOException {
        List<Usuario> usuarios = usuarioFacade.findAll();
        
        for(Usuario user : usuarios) {
            if(user.getUserName().equals(parameters.get("user"))){
                forwardError("Este nombre de usuario ya se encuentra registrado");
                return false;
            }
        }
        
        return true;
    }
    
    private boolean validarPassword() throws ServletException, IOException {
        String pass1 = parameters.get("pass");
        String pass2 = parameters.get("repetirPass");
        
        if(!pass1.equals(pass2)){
            forwardError("Las contraseñas no son identicas!");
            return false;
        }
        
        return true;
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
