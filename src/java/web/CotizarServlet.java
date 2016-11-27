/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.Compra;
import db.Habitacion;
import db.Usuario;
import ejb.BarcoFacade;
import ejb.FormapagoFacade;
import ejb.HabitacionFacade;
import ejb.PuertodestinoFacade;
import ejb.PuertoorigenFacade;
import ejb.TipohabitacionFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "CotizarServlet", urlPatterns = {"/cotizar"})
public class CotizarServlet extends HttpServlet {

    @EJB
    private BarcoFacade bf;
    @EJB
    private PuertodestinoFacade pdf;
    @EJB
    private PuertoorigenFacade pof;
    @EJB
    private TipohabitacionFacade hf;
    @EJB
    private FormapagoFacade fpf;

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
        request.setAttribute("puertosOrigen", pof.findAll());
        request.setAttribute("puertosDestino", pdf.findAll());
        request.setAttribute("barcos", bf.findAll());
        request.setAttribute("tipoHabitaciones", hf.findAll());
        request.setAttribute("formasPago", fpf.findAll());

        RequestDispatcher rd = request.getRequestDispatcher("cotizarPasaje.jsp");
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

        if (request.getSession(false).getAttribute("usuario") == null) {
            response.sendRedirect("/index");
            return;
        }

        String origen = request.getParameter("puertoOrigen");
        String destino = request.getParameter("puertoDestino");
        String barco = request.getParameter("barco");
        String tipoHabitacion = request.getParameter("tipoHabitacion");
        String embarque = request.getParameter("fecEmbarque");
        String desembarque = request.getParameter("fecDesembarque");
        String cantidad = request.getParameter("cantidad");
        String formaPago = request.getParameter("formaPago");

        if (origen.isEmpty() || destino.isEmpty() || barco.isEmpty() || tipoHabitacion.isEmpty()
                || embarque.isEmpty() || desembarque.isEmpty() || cantidad.isEmpty()) {
            request.setAttribute("mensaje", "No pueden haber campos Vacios !!!");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }
        if (Integer.parseInt(origen) > pof.findAll().size() || Integer.parseInt(origen) < 1) {
            request.setAttribute("mensaje", "Puerto de origen invalido");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }
        if (Integer.parseInt(destino) > pdf.findAll().size() || Integer.parseInt(destino) < 1) {
            request.setAttribute("mensaje", "Puerto de destino invalido");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }
        if (Integer.parseInt(barco) > bf.findAll().size() || Integer.parseInt(barco) < 1) {
            request.setAttribute("mensaje", "El cruzero es invalido");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }
        if (Integer.parseInt(tipoHabitacion) > hf.findAll().size() || Integer.parseInt(tipoHabitacion) < 1) {
            request.setAttribute("mensaje", "Tipo de habitacion es invalido");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaOrigen;
        Date fechaDestino;
        
        try {
            fechaOrigen = formato.parse(origen);
            fechaDestino = formato.parse(destino);
        } catch (Exception ex) {
            request.setAttribute("mensaje", "Formato de fecha invalido!");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }
        
        if(fechaOrigen.after(fechaDestino)){
            request.setAttribute("mensaje", "La fecha de desembarque no puede ser anterior a la de embarque!");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }
        
        Habitacion habitacion;
        
        try {
            habitacion = hf.findHabitacionVaciaByTipo(Integer.parseInt(tipoHabitacion));
        } catch(EJBException ex) {
            request.setAttribute("mensaje", "No existen habitaciones del tipo especificado!");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }
       
        if(habitacion == null) {
            request.setAttribute("mensaje", "Todas las habitaciones del tipo especificado se encuentran ocupadas!");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("cotizarPasaje.jsp").forward(request, response);
            return;
        }
        
        Compra compra = new Compra();
        compra.setFechaEnbarque(fechaOrigen);
        compra.setFechaDesenbarque(fechaDestino);
        compra.setNumeroPasajeros(Integer.parseInt(cantidad));
        compra.setIdBarco(bf.find(Integer.parseInt(barco)));
        compra.setIdDestino(pdf.find(Integer.parseInt(destino)));
        compra.setIdOrigen(pof.find(Integer.parseInt(origen)));
        compra.setIdFormaPago(fpf.find(Integer.parseInt(formaPago)));
        compra.setIdUsuario((Usuario)request.getSession(false).getAttribute("usuario"));
        compra.setIdHabitacion(habitacion);
        compra.setValorPasaje(habitacion.getIdTipoHabitacion().getValor() * compra.getNumeroPasajeros());
        
        request.getSession(false).setAttribute("cotizacion", compra);
        request.getRequestDispatcher("confirmarCompra.jsp").forward(request, response);
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
