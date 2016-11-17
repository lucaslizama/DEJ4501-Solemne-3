/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Compra;
import db.Pasaje;
import db.Rolusuario;
import db.Usuario;
import ejb.RolusuarioFacade;
import ejb.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.PasswordHash;

/**
 *
 * @author lucas
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends HttpServlet {
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private RolusuarioFacade rolUsuarioFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("registro.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario user = new Usuario();
        user.setRutUsuario((Integer.parseInt(request.getParameter("rut"))));
        user.setDvUsuario(request.getParameter("dv").charAt(0));
        user.setNombre(request.getParameter("nombre"));
        user.setApPaterno(request.getParameter("apellidoPat"));
        user.setApMaterno(request.getParameter("apellidoMat"));
        user.setUserName(request.getParameter("user"));
        user.setCorreo(request.getParameter("correo"));
        Integer id = 1;
        Rolusuario rol = rolUsuarioFacade.find(new Integer(1));
        user.setIdRol(rol);
        
        try{
            user.setPass(PasswordHash.createHash(request.getParameter("pass")));
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
            request.setAttribute("mensaje", "Error al guardar contraseña");
        }
        
        try {
            usuarioFacade.create(user);
        }catch(EJBException ex) {
            RequestDispatcher rd = request.getRequestDispatcher("registro.jsp");
            request.setAttribute("mensaje", "El rut ingresado ya se enceuntra registrado!");
            request.setAttribute("color", "red");
            rd.forward(request, response);
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("registro.jsp");
        request.setAttribute("mensaje", "Registrado con exito!");
        request.setAttribute("color", "green");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
