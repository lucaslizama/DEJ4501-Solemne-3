-<%-- 
    Document   : confirmarCompra
    Created on : 27-Nov-2016, 10:42:34
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <<link rel="stylesheet" href="css/comprar.css"/>
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
                <c:if test="${listaCompra != null}">
                    <tr>
                        <td>${usuario.rutUsuario}-${usuario.dvUsuario}</td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${compra.fechaEnbarque}"/></td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${compra.fechaDesenbarque}"/></td>
                        <td>${compra.idOrigen.nombrePuerto}</td>
                        <td>${compra.idDestino.nombrePuerto}</td>
                        <td>${compra.idHabitacion.idTipoHabitacion.nombre}</td>
                        <td>${compra.idFormaPago.nombre}</td>
                        <td>&#36;${compra.valorPasaje}</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <form class="comprar-form" action="/comprar" method="POST">
            <button type="submit">Comprar</button>
        </form>
    </body>
</html>
