<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.Map, java.util.List"	
%>
<%
	String rutaControladorFrontal = (String) request.getAttribute("rutaControladorFrontal");
	Map <String, Object> parametrosVistaContenido = (Map <String, Object>) request.getAttribute("parametrosVistaContenido");
	String avisos = (String) parametrosVistaContenido.get("avisos");

%>
	<h1>Alta de usuario</h1>
	<div id="avisos" style="color:red"><%=avisos %></div>
<form name='fAltaModifica' method='post' action="<%=rutaControladorFrontal %>/usuario/Alta">
  <fieldset>
    <legend>Datos de conexión</legend>
    <label for="usuario">Usuario</label> <input type="text" name="usuario" id="usuario" value="">
    <label for="clave">Clave</label> <input type="password" name="clave" value="">
  </fieldset>
  <fieldset>
    <legend>Datos personales</legend>
    <label for="apellidos">Nombre</label> <input type="text" name="nombre" value="" size="30" maxlength="30">
    <label for="apellidos">Apellidos</label> <input type="text" name="apellidos" value="" size="50" maxlength="50">
    <br />
    <label for="fechaNacimiento">Fecha nacimiento (aaaa-mm-dd)</label> 
	<input type="text" name="fechaNacimiento" value="" size="10" maxlength="10" />
    <br />
    Sexo
    <label>Mujer</label> <input type="radio" name="sexo" value="M" checked="checked" /> 
    <label>Hombre</label> <input type="radio" name="sexo" value="H" />
  </fieldset>
  <input type="submit" value="Enviar" disabled="disabled" />
  <input type="reset" value="Resetear el formulario" />
</form>
<script>
  document.getElementById("usuario").focus();
</script>