<%-- 
    Document   : mostrarListaUsuario
    Created on : Nov 21, 2016, 9:52:44 PM
    Author     : Pancho
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/lista.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <thead> 
                <th> Rut </th>
                <th> Fecha Embarque </th>
                <th> Fecha Desenbarque </th>
                <th> Lugar Origen </th>
                <th> Lugar Destino </th>
                <th> Habitacion </th>
            </thead>
            <tbody>
                <c:if test="${listaCompra != null}">
                    <c:forEach var="compra" items="${listaCompra}">
                        <tr>
                            <td>${usuario.rut}-${usuario.dv}</td>
                            <td>${compra.fechaEmbarque}</td>
                            <td>${compra.fechaDesenbarque}</td>
                            <td>${compra.puertoOrigen.nombre}</td>
                            <td>${compra.puertoDestino.nombre}</td>
                            <td>${compra.habitacion.idTipoHabitacion.nombre}</td>
                        </tr>                
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
        <li><a href="/index">Volver</a></li>
    </body>
</html>
