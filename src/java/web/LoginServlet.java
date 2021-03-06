/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Usuario;
import ejb.UsuarioFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author lucas
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private UsuarioFacade uf;
    
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
        if(request.getSession(false).getAttribute("usuario") != null){
            RequestDispatcher rd = request.getRequestDispatcher("/index");
            rd.forward(request, response);
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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
        if(request.getSession().getAttribute("usuario") != null){
            response.sendRedirect("/index");
        }
        
        if(request.getParameter("username") == null || request.getParameter("pass") == null){
            request.setAttribute("mensaje", null);
            response.sendRedirect("/index");
        }
        
        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        
        if(username.isEmpty() || password.isEmpty()){
            request.setAttribute("mensaje", "No puede haber campos vacios!");
            request.setAttribute("color", "red");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }
        
        Usuario user;
        
        try {
            user = uf.findByUsername(username);
        } catch(EJBException ex) {
            request.setAttribute("mensaje", "El usuario " + username + " no se encuentra registrado");
            request.setAttribute("color", "red");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }
        
        if(BCrypt.checkpw(password, user.getPass())){
            request.getSession().setAttribute("usuario", user);
            response.sendRedirect("/index");
            return;
        } else {
            request.setAttribute("mensaje", "La contraseña es incorrecta");
            request.setAttribute("color", "red");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }
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
