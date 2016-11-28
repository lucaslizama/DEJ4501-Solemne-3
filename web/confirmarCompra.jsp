<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/comprar.css"/>
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
                <th> Forma Pago </th>
                <th> Valor </th>
            </thead>
            <tbody>
                <tr>
                    <td>${usuario.rutUsuario}-${usuario.dvUsuario}</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${cotizacion.fechaEnbarque}"/></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${cotizacion.fechaDesenbarque}"/></td>
                    <td>${cotizacion.idOrigen.nombrePuerto}</td>
                    <td>${cotizacion.idDestino.nombrePuerto}</td>
                    <td>${cotizacion.idHabitacion.idTipoHabitacion.nombre}</td>
                    <td>${cotizacion.idFormaPago.nombre}</td>
                    <td>&#36;${cotizacion.valorPasaje}</td>
                </tr>
            </tbody>
        </table>
        <form class="comprar-form" action="/comprar" method="POST">
            <button class="button" type="submit">Comprar</button>
        </form>
        <span style="color:${color}; display:block">${mensaje}</span>
    </body>
</html>
