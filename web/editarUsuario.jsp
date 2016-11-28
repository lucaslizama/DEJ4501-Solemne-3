<%-- 
    Document   : editarUsuario
    Created on : 27-Nov-2016, 23:40:58
    Author     : lucas
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
        <h1>Editar Usuario: ${usuarioEditable.userName}</h1>
        <form action="/editarUsuario" method="POST">
            <fieldset>
                <legend>Datos usuario</legend>
                <input type="hidden" value="${usuarioEditableId}" name="id">
                <label for="rol">Rol</label>
                <select name="rol">
                    <c:forEach var="rol" items="${roles}">
                        <c:choose>
                            <c:when test="${rol.id.equals(usuarioEditable.idRol.id)}">
                                <option value="${rol.id}" selected>${rol.nombreRol}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${rol.id}" >${rol.nombreRol}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <button type="submit">Guardar</button>
            </fieldset>
        </form>
    </body>
</html>
