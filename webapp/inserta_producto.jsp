<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<H1 style="text-align: center;">Insertar Registros</H1>

<!-- cuando el usuario envie el formulario, junto a la informacion visible va a ir invisible una instruccion 'insertarBBDD' -->
<!-- para poder usar un if en el controlador -->
<form name="form" method="GET" action="ControladorProductos">

<input type="hidden" name="instruccion" value="insertarBBDD">


<table style="width: 400px;">

 <tbody>

 
  <tr>
   <td>SECCION</td>
   <td><input maxlength="11" size="24" type="text" name="seccion" /></td>
  </tr>
 
  <tr>
   <td>NOMBREARTICULO</td>
   <td><input maxlength="24" size="24" type="text" name="nArt" /></td>
  </tr>
 
  <tr>
   <td>IMPORTADO</td>
   <td><input maxlength="9" size="24" type="text" name="imp" /></td>
  </tr>
 
  <tr>
   <td>PAISDEORIGEN</td>
   <td><input maxlength="9" size="24" type="text" name="pO" /></td>
  </tr>
 
  <tr>
   <td><input type="submit" value="Enviar" /></td>
   <td><input type="button" value="Restablecer" /></td>
  </tr>
  
 </tbody>
 
</table>

</form>




</body>
</html>