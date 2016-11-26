<%-- 
    Document   : login
    Created on : 17-Nov-2016, 10:08:59
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/login.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/login" method="POST" class="login">
            <fieldset>
                <legend>Ingrese sus credenciales</legend>
                <label for="username">Usuario</label> 
                <input type="text" name="username"<br>
                <label for="pass">Contraseña</label>
                <input type="password" name="pass"><br>
                <button class="button-login" type="submit">Login</button>
            </fieldset>
        </fieldset>
        <span style="color:${color}; font-size: 14pt; margin-top: 10px; display: block;">${mensaje}</span>
        <li><a href="/index">Volver</a></li>
    </form>
</body>
</html>
