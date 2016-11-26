<%-- 
    Document   : registro
    Created on : 16-Nov-2016, 08:32:20
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/registro.css"/>
    </head>
    <body>
        <fieldset class="registro-usuario-wrapper">
            <legend>Ingrese sus datos</legend>
            <form action="/registro" method="POST" >
                <label class="form-label" for="rut">Rut </label>
                <input class="form-textfield" type="text" name="rut">
                <span> - </span>
                <input class="form-dv" type="text" name="dv">
                <label class="form-label" for="nombre">Nombre</label>
                <input class="form-textfield" type="text" name="nombre">
                <label class="form-label" for="apellidoPat">Apellido Paterno</label>
                <input class="form-textfield" type="text" name="apellidoPat">
                <label class="form-label" for="apellidoMat">Apellido Materno</label>
                <input class="form-textfield" type="text" name="apellidoMat">
                <label class="form-label" for="correo">Correo</label>
                <input class="form-textfield" type="email" name="correo">
                <label class="form-label" for="user">Usuario</label>
                <input class="form-textfield" type="text" name="user">
                <label class="form-label" for="pass">Contraseña</label>
                <input class="form-textfield" type="password" name="pass">
                <label class="form-label" for="repetirPass">Contraseña</label>
                <input class="form-textfield" type="password" name="repetirPass">
                <button class="submit-button" type="submit">Registrar</button>
                <span style="color:${color}">${mensaje}</span>
                <div><a href="/" style="visibility:${mensaje != null ? 'visible' : 'hidden'}">Volver a inicio</a></div>
                <li><a href="/index">Volver</a></li>
            </form>
        </fieldset>
    </body>
</html>
