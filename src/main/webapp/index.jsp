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
			Almacenar en la base de datos.<br>
			Se almacenara la traduccion del texto introducido.
			<input type="text" name="palabra" style="overflow: auto hidden;">
			<input type="submit" value="Guardar en Cloudant">
		</form>
		</li>
		
		<li>
		<form action="hablar" method="GET">
			Traductor(ES-EN)<br>
			<input type="text" name="palabra" style="overflow: auto hidden;">
			Se reproducira la traduccion
			<input type="submit" value="Traducir">
		</form>
		</li>
		
		<!--		
		<li><a href="hablar">Audio</a></li>
		<li><a href="reproduce">reproduce</a></li>
		
		
		 <a href="insertar?palabra=hola">Insertar</a> 
			<textarea  
			placeholder="Introducir texto" rows="5" 
			name="palabra" style="overflow: auto hidden;" >
			</textarea>	
		-->
	</ul>
</body>
</html>