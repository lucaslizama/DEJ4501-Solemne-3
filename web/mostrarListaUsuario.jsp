<%-- 
    Document   : mostrarListaUsuario
    Created on : Nov 21, 2016, 9:52:44 PM
    Author     : Pancho
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <th> Valor </th>
            </thead>
            <tbody>
                <c:if test="${listaCompra != null}">
                    <c:forEach var="compra" items="${listaCompra}">
                        <tr>
                            <td>${usuario.rutUsuario}-${usuario.dvUsuario}</td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${compra.fechaEnbarque}"/></td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${compra.fechaDesenbarque}"/></td>
                            <td>${compra.idOrigen.nombrePuerto}</td>
                            <td>${compra.idDestino.nombrePuerto}</td>
                            <td>${compra.idHabitacion.idTipoHabitacion.nombre}</td>
                            <td>&#36;${compra.valorPasaje * compra.numeroPasajeros}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
        <span style="display: block; margin-left: 10px; margin-top: 10px; font-size: 14pt"><a href="/index">Volver</a></span>
    </body>
</html>
