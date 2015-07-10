<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.Map, java.util.List, modelo.Post"
%>
<%
	String rutaControladorFrontal = (String) request.getAttribute("rutaControladorFrontal");
	Map <String, Object> parametrosVistaContenido = (Map <String, Object>) request.getAttribute("parametrosVistaContenido");
	List<Post> listaPosts = (List<Post>) parametrosVistaContenido.get("listaPosts");
%>
<h1>Listado de posts</h1>
<table border='1' cellpadding='5' cellspacing='3'>
<tr>
	<th>Fecha</th>
	<th>Titulo</th>
	<th>Usuario</th>		
</tr>
<%
	if (listaPosts != null) {
		for (Post unPost : listaPosts) {
%>
<tr>
	<td><%=unPost.getFecha()%></td>
	<td><a href="<%= rutaControladorFrontal %>/post/Detalle?id=<%= unPost.getIdPost()%>"><%=unPost.getTitulo()%></a></td>
	<td><%= unPost.getUsuario() %></td>
</tr>
<%
		}
	}	
%>
</table>
<br />