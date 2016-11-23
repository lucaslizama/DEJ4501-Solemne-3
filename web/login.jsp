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
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login.do" method="POST">
            <fieldset></fieldset>
            Usuario <input type="text" name="usuario"<br>
            Contraseña <input type="password" name="pass"><br>
            <button type="submit">Login</button>
        </fieldset>
    </form>
</body>
</html>
