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
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${usuario != null}">
            <h1>Bienvenido ${usuario.nombre} ${usuario.apPaterno}</h1>
        </c:if>
        <form action="cotizacion.do" method="POST">
            <fieldset>
                <legend>Ingrese sus datos para realizar la cotizacion</legend>
                <label class="form-label" for="puertoOrigen">Puerto Origen</label>
                <select name="puertoOrigen">
                    <c:forEach var="puertoOrigen" items="puertosOrigen">
                        <option value="${puertoOrigen.id}">${puertoOrigen.nombrePuerto}</option>
                    </c:forEach>
                </select><br>
                <label class="form-label" for="puertoDestino">Puerto Destino</label>
                <select name="puertoDestino">
                    <c:forEach var="puertoDestino" items="puertosDestino">
                        <option value="${puertoDestino.id}">${puertoDestino.nombrePuerto}</option>
                    </c:forEach>
                </select><br> 
                <label class="form-label" for="nomCrucero">Nombre del Crucero</label>
                <select name="nomBarco">
                    <c:forEach var="barco" items="barcos">
                        <option value="${barco.id}">${barco.nombre}</option>
                    </c:forEach>
                </select><br>
                <label class="form-label" for="tipoHabitacion">Tipo de Habitacion</label>
                <select name="tipHabitacion">
                    <c:forEach var="tipoHabitacion" items="tipoHabitaciones">
                        <option value="${tipoHabitacion.id}">${tipoHabitacion.nombre}</option>
                    </c:forEach>
                </select><br>
                <label class="form-label" for="fecEmbarque">Fecha de Origen</label>
                <input class="form-date" type="date" name="fecEmbarque"><br>
                <label class="form-label" for="fecDesembarque">Fecha de Destion</label>
                <input class="form-date" type="date" name="fecDesembarque"><br>
                <label class="form-label" for="cantidad">Cantidada de pasajes</label>
                <input class="form-textfield" type="text" name="cantidad">
                <button class="submit-button" type="submit">Registrar</button>
                <span style="color:${color}">${mensaje}</span>
                <div><a href="/" style="visibility:${mensaje != null ? 'visible' : 'hidden'}">Volver a inicio</a></div>
            </fieldset>
        </form>        
    </body>
</html>
