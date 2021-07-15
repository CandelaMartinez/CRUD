<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
.cabecera {
	font-size: 1.2em;
	font-weight: bold;
	color: #FFFFFF;
	background-color: #08088A;
}

.filas {
	text-align: center;
	background-color: #5882FA;
}

table {
	float: left;
}

#contenedorBoton {
	margin-left:1000px;
}
</style>


</head>

<body>

	<table border="1">

		<tr>
			<td class="cabecera">Seccion</td>
			<td class="cabecera">Nombre</td>
			<td class="cabecera">Importado</td>
			<td class="cabecera">PaisOrigen</td>

		</tr>

		<c:forEach var="p" items="${LISTAPRODUCTOS}">

			<tr>
				<td class="filas">${p.seccion}</td>
				<td class="filas">${p.nArt}</td>
				<td class="filas">${p.importado}</td>
				<td class="filas">${p.pOrig}</td>
			</tr>


		</c:forEach>


	</table>

	<div id="contenedorBoton">

		<button onclick="window.location.href='inserta_producto.jsp'">Insertar</button>

	</div>


</body>
</html>