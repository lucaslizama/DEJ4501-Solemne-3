/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Compra;
import db.Pasaje;
import ejb.CompraFacade;
import ejb.PasajeFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "ComprarServlet", urlPatterns = {"/comprar"})
public class ComprarServlet extends HttpServlet {

    @EJB
    private CompraFacade cf;
    @EJB
    private PasajeFacade pf;
    
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
        
        if(request.getSession(false).getAttribute("cotizacion") == null) {
            response.sendRedirect("/index");
            return;
        }
        
        Compra compra = (Compra)request.getSession(false).getAttribute("cotizacion");
        
        try {
            cf.create(compra);
        } catch(EJBException ex) {
            request.setAttribute("mensaje", "Error al ingresar la compra!");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("confirmarCompra.jsp").forward(request, response);
            return;
        }
        
        try{
            compra = cf.find(compra.getId());
        } catch(EJBException ex) {
            request.setAttribute("mensaje", "Error al recuperar compra!");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("confirmarCompra.jsp").forward(request, response);
            return;
        }
        
        for (int i = 0; i < compra.getNumeroPasajeros(); i++) {
            Pasaje pasaje = new Pasaje();
            pasaje.setIdCompra(compra);
            try{
                pf.create(pasaje);
            } catch(EJBException ex) {
                request.setAttribute("mensaje", "Error al ingresar pasajes!");
                request.setAttribute("color", "red");
                request.getRequestDispatcher("confirmarCompra.jsp").forward(request, response);
                return;
            }
        }
        
        response.sendRedirect("/compras");
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
