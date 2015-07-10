<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% String usuario = (String) request.getParameter("usuario"); %>
<div id='cabecera' style='border: green 3px double; padding-left: 20px; margin: 0px; width: 400px;'>
	<p style='font-family:verdana;'>

<%      	  
	if (usuario.equals("")) {  // no se está registrado
%>	
	No está conectado
<%	
	} else {
%>		
	Conectado como: <b><%=usuario %></b>
<%
	}
%>    
	</p>
</div>