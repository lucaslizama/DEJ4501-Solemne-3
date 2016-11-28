<%-- 
    Document   : cotizarPasaje
    Created on : Nov 21, 2016, 10:08:14 PM
    Author     : Pancho
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/registro.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${usuario != null}">
            <h1>Bienvenido ${usuario.nombre} ${usuario.apPaterno}</h1>
        </c:if>
        <form action="/cotizar" method="POST">
            <fieldset>
                <legend>Ingrese sus datos para realizar la cotizacion</legend>
                <label class="form-label" for="puertoOrigen">Puerto Origen</label>
                <select name="puertoOrigen">
                    <c:forEach var="puertoOrigen" items="${puertosOrigen}">
                        <option value="${puertoOrigen.id}">${puertoOrigen.nombrePuerto}</option>
                    </c:forEach>
                </select>
                <label class="form-label" for="puertoDestino">Puerto Destino</label>
                <select name="puertoDestino">
                    <c:forEach var="puertoDestino" items="${puertosDestino}">
                        <option value="${puertoDestino.id}">${puertoDestino.nombrePuerto}</option>
                    </c:forEach>
                </select>
                <label class="form-label" for="nomBarco">Nombre del Crucero</label>
                <select name="nomBarco">
                    <c:forEach var="barco" items="${barcos}">
                        <option value="${barco.id}">${barco.nombreBarco}</option>
                    </c:forEach>
                </select>
                <label class="form-label" for="tipoHabitacion">Tipo de Habitacion</label>
                <select name="tipoHabitacion">
                    <c:forEach var="tipoHabitacion" items="${tipoHabitaciones}">
                        <option value="${tipoHabitacion.id}">${tipoHabitacion.nombre}</option>
                    </c:forEach>
                </select>
                <label class="form-label" for="formaPago">Forma de Pago</label>
                <select name="formaPago">
                    <c:forEach var="formaPago" items="${formasPago}">
                        <option value="${formaPago.id}">${formaPago.nombre}</option>
                    </c:forEach>
                </select>
                <label class="form-label" for="fecEmbarque">Fecha de Origen</label>
                <input class="form-date" type="date" name="fecEmbarque">
                <label class="form-label" for="fecDesembarque">Fecha de Destino</label>
                <input class="form-date" type="date" name="fecDesembarque">
                <label class="form-label" for="cantidad">Cantida de pasajes</label>
                <select name="cantidad" size="1">
                    <option value="1">1</option> 
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select><br>
                <button class="submit-button" type="submit">Cotizar</button>
                <span style="color:${color}; display:block;">${mensaje}</span>
            </fieldset>
        </form>
        <span style="display: block; margin-left: 10px; margin-top: 10px; font-size: 14pt"><a href="/index">Volver</a></span>
    </body>
</html>
