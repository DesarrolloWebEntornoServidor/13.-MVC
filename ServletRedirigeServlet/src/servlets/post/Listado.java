package servlets.post;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.AccesoDatos;
import modelo.Post;
import modelo.Usuario;

@WebServlet("/Listado")
public class Listado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// se trata de recuperar la sesión y se compruebe si existe la variable usuarioAutenticado
		HttpSession laSesion = request.getSession(false);
		if (laSesion != null) {  // existe la sesión 
			String usuarioAutenticado = (String) laSesion.getAttribute("usuarioAutenticado");
			if (usuarioAutenticado != null) {  // existe un usuario autenticado
				try {
					// se recuperan todos los posts del modelo
					List<Post> listaPosts = AccesoDatos.listaTodosPosts();

					request.setAttribute("usuarioAutenticado", usuarioAutenticado);

					Map <String, Object> parametrosVistaContenido = new LinkedHashMap<String, Object>();
					parametrosVistaContenido.put("listaPosts", listaPosts);
					request.setAttribute("parametrosVistaContenido", parametrosVistaContenido);
					String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
					request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
					String vistaContenido = "post/VistaListado.jsp";
					request.setAttribute("vistaContenido", vistaContenido);
					request.setAttribute("titulo", "Listado de posts");
					// String vistaDestino = "/vistas/post/VistaListado.jsp";
					String vistaDestino = "/vistas/PlantillaPagina.jsp";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
					dispatcher.forward(request,response);
				} catch (SQLException e) {
					e.printStackTrace();
					String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
					request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
					request.setAttribute("mensajeError", "Error de acceso a la BD");
					String vistaDestino = "/vistas/VistaError.jsp";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
					dispatcher.forward(request,response);	
				}
			} else {  // no existe un usuario autenticado
				String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
				request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
				request.setAttribute("usuario", "");
				request.setAttribute("mensajeLogin", "Sesión caducada, autentíquese de nuevo");
				String vistaDestino = "/vistas/usuario/VistaAutenticacion.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
			}
		} else {  // no hay una sesión activa => se va a la página de Autenticacion
			String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
			request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
			request.setAttribute("usuario", "");
			request.setAttribute("mensajeLogin", "Sesión caducada, autentíquese de nuevo");
			String vistaDestino = "/vistas/VistaAutenticacion.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
			dispatcher.forward(request,response);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
