<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<H1 style="text-align: center;">Actualizar Registros</H1>

<!-- cuando el usuario envie el formulario, junto a la informacion visible va a ir invisible una instruccion 'insertarBBDD' -->
<!-- para poder usar un if en el controlador -->
<form name="form1" method="get" action="ControladorProductos">

<input type="hidden" name="instruccion" value="actualizarBBDD">

<!-- envio el codigo del articulo al modelo para realizar el update  -->
<input type="hidden" name="cArt" value="${PRODUCTOACTUALIZAR.cArt}">


<table style="width: 400px;">

 <tbody>

<!-- dentro de los cuadros de texto va la informacion del articulo seleccionado, nombre de los campos dentro de la clase de productos -->
 
  <tr>
   <td>SECCION</td>
   <td><input maxlength="11" size="24" type="text" name="seccion" value="${PRODUCTOACTUALIZAR.seccion}"/></td>
  </tr>
 
  <tr>
   <td>NOMBREARTICULO</td>
   <td><input maxlength="24" size="24" type="text" name="nArt" value="${PRODUCTOACTUALIZAR.nArt}"/></td>
  </tr>
  
   <tr>
   <td>PRECIO</td>
   <td><input maxlength="11" size="24" type="text" name="precio"value="${PRODUCTOACTUALIZAR.precio}" /></td>
  </tr>
 
  <tr>
   <td>IMPORTADO</td>
   <td><input maxlength="9" size="24" type="text" name="imp" value="${PRODUCTOACTUALIZAR.importado}"/></td>
  </tr>
 
  <tr>
   <td>PAISDEORIGEN</td>
   <td><input maxlength="9" size="24" type="text" name="pO" value="${PRODUCTOACTUALIZAR.pOrig}"/></td>
  </tr>
 
  <tr>
   <td><input type="submit" value="Actualizar" /></td>
   <td><input type="button" value="Restablecer" /></td>
  </tr>
  
 </tbody>
 
</table>

</form>




</body>
</html>