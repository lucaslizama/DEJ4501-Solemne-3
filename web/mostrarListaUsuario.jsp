<%-- 
    Document   : mostrarListaUsuario
    Created on : Nov 21, 2016, 9:52:44 PM
    Author     : Pancho
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr> 
                <th> Rut </th>
                <th> Fecha Embarque </th>
                <th> Fecha Desenbarque </th>
                <th> Lugar Origen </th>
                <th> Lugar Destino </th>
                <th> Habitacion </th>
            </tr>
            
            <c:forEach var="compra" items="${listaCompra}">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>                
            </c:forEach>
        </table>
    </body>
</html>
