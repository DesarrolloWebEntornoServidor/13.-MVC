<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.Map, java.util.LinkedHashMap, java.util.Iterator"	
%>
<%
	String rutaControladorFrontal = (String) request.getAttribute("rutaControladorFrontal");
	// la lista de opciones a visualizar en el pie también se debería parametrizar, de la forma:
	// Map<String, String> listaOpciones = (Map<String,String>) request.getAttribute("listaOpciones");
	Map<String, String> listaOpciones = new LinkedHashMap<String,String>();
	listaOpciones.put("/post/Listado", "Listado de posts");
	listaOpciones.put("/post/Altapost", "Alta de post");
	listaOpciones.put("/usuario/Autenticacion", "Desconexión");
%>
<div id="pie">
<%
	if (listaOpciones.size() > 0) {  // se reciben opciones
%>
	<table border="0" cellpadding="10">
	<tr>	  
<%
		Iterator<String> iteradorConjuntoClaves = listaOpciones.keySet().iterator();
		while (iteradorConjuntoClaves.hasNext()) {
			String opcion = iteradorConjuntoClaves.next();  // clave
			String mensaje = listaOpciones.get(opcion);  // valor
%>
		<td style="border: green solid 3px;"><a href="<%= rutaControladorFrontal %><%= opcion %>"><%= mensaje %></a></td>
<%
		}
	}
%>
   </tr>
</table>	  
</div>