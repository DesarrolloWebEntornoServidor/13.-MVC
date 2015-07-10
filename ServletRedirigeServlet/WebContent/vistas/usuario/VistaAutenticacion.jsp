<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<% 
  	String rutaControladorFrontal = (String) request.getAttribute("rutaControladorFrontal"); 
	// aquí siempre se estará desconectado => usuario = ""
	String usuario = (String) request.getAttribute("usuario");			
	if (usuario == null) {
		usuario = "";
	}
	String mensajeLogin = (String) request.getAttribute("mensajeLogin");
	if (mensajeLogin == null) {  // si no se ha recibido mensaje => mensaje = ""
		mensajeLogin = "";
	}
%>
<html>
<body>
	<jsp:include page="../cabecera.jsp">
		  <jsp:param name="usuario" value="<%=usuario%>"/>
	</jsp:include>
	
  <h1>Autenticación de usuario</h1>
  <div id="mensajeLogin" style="color: red;"><%= mensajeLogin %></div>
  <form action="<%= rutaControladorFrontal %>/usuario/Autenticacion" method="post">
    <label for='usuario'>Usuario</label> <input type='text' name='usuario' id='usuario' /><br />
    <label for='clave'>Contraseña</label> <input type='password' name='clave' id='clave' /><br />
    <input type='submit' />
  </form>
  <script>document.getElementById('usuario').focus();
  </script>
  
	<jsp:include page="pieVistaAutenticacion.jsp">
		  <jsp:param name="rutaControladorFrontal" value="<%=rutaControladorFrontal%>"/>
	</jsp:include>  
</body>
</html>