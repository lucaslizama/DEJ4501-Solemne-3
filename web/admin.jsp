<%-- 
    Document   : admin
    Created on : 27-Nov-2016, 23:11:49
    Author     : lucas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/admin.css">
    </head>
    <body>
        <h1>Administracion Cuentas de Usuario</h1>
        <table>
            <thead>
                <th>ID</th>
                <th>Username</th>
                <th>Rol</th>
                <th>Contraseña</th>
                <th>Acciones</th>
            </thead>
            <tbody>
                <c:forEach var="usuario" items="${usuarios}" >
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.idRol.nombreRol}</td>
                        <td>${usuario.pass}</td>
                        <td><a href="/editarUsuario?id=${usuario.id}">Editar</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
