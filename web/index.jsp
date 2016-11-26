<%-- 
    Document   : index
    Created on : 16-Nov-2016, 07:43:49
    Author     : lucas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/registro.css" type="text/css"><link>
        <title>Inicio Cruceros</title>
    </head>
    <body>
        <fieldset>
        <c:if test="${usuario != null}">
            <h1>Bienvenido ${usuario.nombre} ${usuario.apPaterno}</h1>
        </c:if> 
        <ol>
            <c:choose>
                <c:when test="${usuario == null}">
                    <li><a href="/registro">Registrarse</a></li>
                    <li><a href="/login">Login</a></li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${usuario.idRol.nombreRol.equals('Administrador')}">
                        <li><a href="/admin">Panel Administrador</a></li>
                        </c:if>
                    <li><a href="/cotizar">Cotizar Pasajes</a></li>
                    <li><a href="/cotizaciones">Ver Cotizaciones</li>
                    <li><a href="/logout">Salir</a></li>
                    </c:otherwise>
                </c:choose>
        </ol>
    </fieldset>
</body>
</html>
