<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.Map"
%>
<%
	String titulo = (String) request.getAttribute("titulo");
	String usuario = (String) request.getAttribute("usuarioAutenticado");
	String rutaControladorFrontal = (String) request.getAttribute("rutaControladorFrontal");
	String vistaContenido = (String) request.getAttribute("vistaContenido");
	Map <String, Object> parametrosVistaContenido = (Map <String, Object>) request.getAttribute("parametrosVistaContenido");
%>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title><%=titulo %></title>
</head>
<body>
	<jsp:include page="cabecera.jsp">
		  <jsp:param name="usuario" value="<%=usuario%>"/>
	</jsp:include>

<!-- page="<%=vistaContenido%>"  -->
	<jsp:include page="<%=vistaContenido%>">
		  <jsp:param name="rutaControladorFrontal" value="<%=rutaControladorFrontal%>"/>
		  <jsp:param name="parametrosVistaContenido" value="<%=parametrosVistaContenido%>"/>
	</jsp:include>

	<jsp:include page="pie.jsp">
		  <jsp:param name="rutaControladorFrontal" value="<%=rutaControladorFrontal%>"/>
	</jsp:include>
</body>
</html>