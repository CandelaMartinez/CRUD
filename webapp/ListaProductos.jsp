<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.*, com.pildorasinformaticas.productos.*"%>

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
	margin-left: 800px;
}
</style>


</head>




<body>



	<table border="1">

		<tr>
			<td class="cabecera">Codigo</td>
			<td class="cabecera">Seccion</td>
			<td class="cabecera">Nombre</td>
			<td class="cabecera">Precio</td>
			<td class="cabecera">Importado</td>
			<td class="cabecera">PaisOrigen</td>
			<td class="cabecera">Accion</td>



		</tr>


		<c:forEach var="p" items="${LISTAPRODUCTOS}">

			<!-- 		link para cada producto con su campo clave -->
			<c:url var="linkTemp" value="ControladorProductos">


				<!-- 			envio a controlador una instruccion cargar y el codigo del articulo  -->
				<c:param name="instruccion" value="cargar"></c:param>
				<c:param name="cArticulo" value="${p.cArt}"></c:param>


			</c:url>

			<!-- 			link para eliminar cada registro con su campo clave -->
				<c:url var="linkTempEliminar" value="ControladorProductos">


				<!-- 			envio a controlador una instruccion eliminar y el codigo del articulo  -->
				<c:param name="instruccion" value="eliminar"></c:param>
				<c:param name="cArticulo" value="${p.cArt}"></c:param>


			</c:url>

			<tr>
				<td class="filas">${p.cArt}</td>
				<td class="filas">${p.seccion}</td>
				<td class="filas">${p.nArt}</td>
				<td class="filas">${p.precio}</td>
				<td class="filas">${p.importado}</td>
				<td class="filas">${p.pOrig}</td>
				<td class="filas"><a href="${linkTemp}">Actualizar</a>&nbsp;&nbsp;<a href="${linkTempEliminar}">Eliminar</a></td>



			</tr>


		</c:forEach>


	</table>

	<div id="contenedorBoton">

		<button onclick="window.location.href='inserta_producto.jsp'">InsertarProducto</button>

	</div>


</body>
</html>