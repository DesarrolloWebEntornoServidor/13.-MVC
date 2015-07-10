<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.Map, modelo.Post"
%>
<%
	String rutaControladorFrontal = (String) request.getAttribute("rutaControladorFrontal");
%>

	<h1>Alta de post</h1>
	<form action="<%= rutaControladorFrontal %>/post/Altapost" method="post">
	    <input type='hidden' name='fecha' id='fecha' />
		<label for='titulo'>Título</label> <input type='text' name='titulo' id='titulo' /><br />
	    <label for='cuerpo'>Cuerpo</label> <textarea name='cuerpo'></textarea><br />
        <input type='submit' value="Enviar" />
      </form>
      <script>
        document.getElementById("titulo").focus();
		// se genera la fecha del sistema para ser enviada como fecha del post
        fecha = new Date();
		dia = '' + fecha.getDate();
		mes = '' + (fecha.getMonth() + 1); // los meses van de 0 a 11
		anio = '' + fecha.getFullYear();
		if (dia.length == 1) dia = '0' + dia;
		if (mes.length == 1) mes = '0' + mes;
		fechaFormateada = anio + '-' + mes + '-' + dia;
	    document.getElementById('fecha').value = fechaFormateada;
      </script>