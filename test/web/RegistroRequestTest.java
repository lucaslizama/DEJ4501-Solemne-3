/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Rolusuario;
import db.Usuario;
import ejb.AbstractFacade;
import ejb.RolusuarioFacade;
import ejb.UsuarioFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static java.util.Collections.*;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author lucas
 */
public class RegistroRequestTest {
    
    public RegistroRequestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validarParametros method, of class RegistroRequest.
     */
    @Test
    public void testValidarParametros() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        UsuarioFacade usuarioFacade = mock(UsuarioFacade.class);
        RolusuarioFacade rolFacade = mock(RolusuarioFacade.class);
        List<String> nombres = new ArrayList<String>();
        
        nombres.add("rut");
        nombres.add("dv");
        nombres.add("nombre");
        nombres.add("apellidoPat");
        nombres.add("apellidoMat");
        nombres.add("correo");
        nombres.add("user");
        nombres.add("pass");
        nombres.add("repetirPass");
        
        when(request.getParameter("rut")).thenReturn("18464695");
        when(request.getParameter("dv")).thenReturn("1");
        when(request.getParameter("nombre")).thenReturn("Lucas");
        when(request.getParameter("apellidoPat")).thenReturn("Lizama");
        when(request.getParameter("apellidoMat")).thenReturn("Monje");
        when(request.getParameter("correo")).thenReturn("lucaslizama3@hotmail.com");
        when(request.getParameter("user")).thenReturn("lucaslizama3");
        when(request.getParameter("pass")).thenReturn("lol1234");
        when(request.getParameter("repetirPass")).thenReturn("lol1234");
        when(request.getParameterNames()).thenReturn(enumeration(nombres));
        when(usuarioFacade.findAll()).thenReturn(new ArrayList<Usuario>());
        when(rolFacade.find(1)).thenReturn(new Rolusuario(1, "Estandar"));
        
        RegistroRequest rr = new RegistroRequest(request, response, usuarioFacade, rolFacade);
        try {
            assertTrue(rr.validarParametros());
        }catch (IOException ex){
            fail("Error de IO: " + ex.getMessage());
        }catch(ServletException ex) {
            fail("Error de servlet: " + ex.getMessage());
        }
    }   

    /**
     * Test of ingresarUsuario method, of class RegistroRequest.
     */
    @Test
    public void testIngresarUsuario() throws Exception {
        System.out.println("ingresarUsuario");
        RegistroRequest instance = null;
        boolean expResult = false;
        boolean result = instance.ingresarUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
