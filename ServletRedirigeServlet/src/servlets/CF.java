package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
// import java.util.Map.Entry;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*** OJO AL MAPEO, para poder añadirle un PathInfo ***/
// Funciona 
// @WebServlet("/CF/*")

@WebServlet(urlPatterns={"/CF","/CF/*"})
public class CF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// se guarda en un atributo del contexto el nombre del controlador frontal
		ServletContext contexto = getServletContext();
		contexto.setAttribute("CONTROLADOR_FRONTAL", request.getServletPath());
		contexto.setAttribute("RUTA_CONTROLADOR_FRONTAL", request.getContextPath() + request.getServletPath());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String haciaDonde = request.getPathInfo();
		if (haciaDonde == null) {
			haciaDonde = "";
		}
		out.println(haciaDonde + "<br />");
		
		out.println("request.getServletPath() : " + request.getServletPath() + "<br />");
		out.println("request.getContextPath() : " + request.getContextPath() + "<br />");
		String rutaControladorFrontal = request.getContextPath() + request.getServletPath();
		out.println("RUTA al CONTROLADOR FRONTAL : " + rutaControladorFrontal + "<br />");
		
		
		// se añade la ruta al CONTEXTO de la APLICACIÓN
		this.getServletConfig().getServletContext().setAttribute("rutaControladorFrontal", rutaControladorFrontal); 
		
	
				
		LinkedHashMap<String,String> controladorYAccion = new LinkedHashMap<String,String>(); 
		controladorYAccion.put("controlador", "");
		controladorYAccion.put("accion", "");
				
		extraeControladorYAccion(haciaDonde, controladorYAccion);
		
		System.out.println("controladorURL: " + controladorYAccion.get("controlador"));
		System.out.println("accionURL: "  + controladorYAccion.get("accion"));
		/*
		for (Entry<String, String> elemento : controladorYAccion.entrySet()) {
		    String clave = elemento.getKey();
		    String valor = elemento.getValue();
		    out.println(clave + " => " + valor + "<br />");
		}
		*/
		
		/* Cálculo del destino efectivo */
		String controlador = "";
		String controladorPorDefecto = "usuario";
		if (controladorYAccion.get("controlador").isEmpty()) {
			controlador = controladorPorDefecto;
		} else {
			// el controlador va TODO EN MINÚSCULAS
			controlador = controladorYAccion.get("controlador").toLowerCase();
		}
		String accion = "";
		String accionPorDefecto = "Autenticacion";
		if (controladorYAccion.get("accion").isEmpty()) {
			accion =  accionPorDefecto;
		} else {
			// la acción va LA PRIMERA EN MAYÚSCULAS Y EL RESTO EN MINÚSCULAS
			accion = controladorYAccion.get("accion").substring(0, 1).toUpperCase() +
					 controladorYAccion.get("accion").substring(1).toLowerCase();
		}
		System.out.println("controlador: " + controlador);
		System.out.println("accion: " + accion);


		try {
			String paquete = "servlets";
			Class c = Class.forName(paquete + "." + controlador + "." + accion);  // si existe el servlet, redirige a él
			// out.println(c.toString());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + accion);
			dispatcher.forward(request,response);

		} catch (ClassNotFoundException e) {
			out.println("No existe el controlador y/o la acción");
		}
		

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private static void extraeControladorYAccion(String pathInfo, 
						LinkedHashMap<String,String> controladorYAccion) {
		if (pathInfo.length() > 0) {  // hay datos del PathInfo
			pathInfo = pathInfo.substring(1); // se elimina el / inicial
			String[] valores = pathInfo.split("/");
			int longitudValores = valores.length; // número de elementos
			if (longitudValores == 1) {
				controladorYAccion.put("controlador", valores[0]);
			}
			if (longitudValores == 2) {	
				controladorYAccion.put("controlador", valores[0]); 
				controladorYAccion.put("accion", valores[1]);
			}
		
		}	
	}
	
}