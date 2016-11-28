/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Usuario;
import ejb.RolusuarioFacade;
import ejb.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "EditarUsuarioServlet", urlPatterns = {"/editarUsuario"})
public class EditarUsuarioServlet extends HttpServlet {

    @EJB
    private UsuarioFacade uf;
    @EJB
    private RolusuarioFacade ruf;
    
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
        if(request.getSession(false).getAttribute("usuario") == null) {
            response.sendRedirect("/index");
            return;
        }
        
        String id = request.getParameter("id");
        
        request.setAttribute("roles", ruf.findAll());
        request.setAttribute("usuarioEditableId", id);
        request.setAttribute("usuarioEditable", uf.find(Integer.parseInt(id)));
        request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
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
        if(request.getSession(false).getAttribute("usuario") == null) {
            response.sendRedirect("/index");
            return;
        }
        
        String id = request.getParameter("id");
        Usuario user = uf.find(Integer.parseInt(id));
        String idRol = request.getParameter("rol");
        user.setIdRol(ruf.find(Integer.parseInt(idRol)));
        uf.edit(user);
        
        response.sendRedirect("/admin");
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
