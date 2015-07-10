<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<% 
  	String rutaControladorFrontal = (String) request.getAttribute("rutaControladorFrontal"); 
	// aquí siempre se estará desconectado => usuario = ""
	String usuario = (String) request.getAttribute("usuario");			
	if (usuario == null) {
		usuario = "";
	}
	String mensajeError = (String) request.getAttribute("mensajeError");
	if (mensajeError == null) {  // si no se ha recibido mensaje => mensaje = ""
		mensajeError = "";
	}
%>
<html>
<body>
	<jsp:include page="cabecera.jsp">
		  <jsp:param name="usuario" value="<%=usuario%>"/>
	</jsp:include>
  <h1>Error</h1>
  <h3 id="mensajeLogin" style="color: red;"><%= mensajeError %></h3>
</body>
</html>