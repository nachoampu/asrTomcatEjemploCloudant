<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto ASR new...</title>
</head>
<body>
<h1>Ejemplo de Proyecto de ASR con Cloudant ahora con DevOps</h1>
<hr />
<p>Opciones sobre la base de datos Cloudant de Ignacio Ampuero</p>
<ul>
<li><a href="listar">Listar</a></li>
<li>
<form action="insertar" method="GET">
	Palabra en español 
	<input type="text" name="palabra">
	<input type="submit" value="Guardar en Cloudant">
</form>

<!-- <a href="insertar?palabra=hola">Insertar</a> -->

</li>
</ul>
</body>
</html>