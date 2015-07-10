<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.Map, modelo.Post"
%>

<%
	String rutaControladorFrontal = (String) request.getAttribute("rutaControladorFrontal");
	Map <String, Object> parametrosVistaContenido = (Map <String, Object>) request.getAttribute("parametrosVistaContenido");
	Post elPost = (Post) parametrosVistaContenido.get("elPost");
%>
	<h1><%=elPost.getTitulo() %></h1>
    <div class='cuerpo'><%=elPost.getCuerpo() %></div>
    <div class='fecha'><%=elPost.getFecha() %> - <b><%=elPost.getUsuario() %></b></div>
    