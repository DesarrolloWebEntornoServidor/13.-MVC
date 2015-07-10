package servlets.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.AccesoDatos;
import modelo.Post;
import modelo.Usuario;

@WebServlet("/Alta")
public class Alta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// aquí se accede con un usuario no autenticado => hay destruir la sesión si existe
		HttpSession laSesion = request.getSession(false);
		if (laSesion != null) {
			laSesion.invalidate();
		}
		
		String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
		request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
		request.setAttribute("usuarioAutenticado", "");
		Map <String, Object> parametrosVistaContenido = new LinkedHashMap<String, Object>();
		parametrosVistaContenido.put("avisos", "");
		request.setAttribute("parametrosVistaContenido", parametrosVistaContenido);
		String vistaContenido = "usuario/VistaAlta.jsp";
		request.setAttribute("vistaContenido", vistaContenido);
		String vistaDestino = "/vistas/PlantillaPagina.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
		dispatcher.forward(request,response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("text/html;charset=UTF-8");
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		// PrintWriter out = response.getWriter();
		// out.println("PROCESO: Autenticación POST" + " <br />");
		// out.println("Recibidos us: " + usuario + " ; ps: " + clave + " <br />");
		try {
			Usuario elUsuario = AccesoDatos.devuelveUsuarioPorCredenciales(usuario, clave);
			// out.println(elUsuario);
			if (elUsuario == null) {  // no se encontró un usuario con esas credenciales
				// se vuelve a ala vista de login, informando del error de autenticación
				String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
				request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
				request.setAttribute("mensajeLogin", "Usuario y/o contraseña desconocidos");
				String vistaDestino = "/vistas/usuario/VistaAutenticacion.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
			} else {  // se encontó al usuario
				// aquí se accede al realizar una autenticación exitosa 
				// se crea la sesión y se registra la variable usuarioAutenticado
				HttpSession laSesion = request.getSession(true);
				laSesion.setAttribute("usuarioAutenticado", usuario);
				
				// se recuperan todos los posts del modelo
				List<Post> listaPosts = AccesoDatos.listaTodosPosts();

				request.setAttribute("usuarioAutenticado", usuario);

				Map <String, Object> parametrosVistaContenido = new LinkedHashMap<String, Object>();
				parametrosVistaContenido.put("listaPosts", listaPosts);
				request.setAttribute("parametrosVistaContenido", parametrosVistaContenido);
				// request.setAttribute("listaPosts", listaPosts);
				String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
				request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
				String vistaContenido = "post/VistaListado.jsp";
				request.setAttribute("vistaContenido", vistaContenido);
				request.setAttribute("titulo", "Listado de posts");
				// String vistaDestino = "/vistas/post/VistaListado.jsp";
				String vistaDestino = "/vistas/PlantillaPagina.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
				
				/*				
				String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
				request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
				
				String controladorFrontal = (String) getServletContext().getAttribute("CONTROLADOR_FRONTAL");
				// String vistaDestino = controladorFrontal + "/post/Listado";
				String vistaDestino ="/";
				System.out.println("VISTA DESTINO = " + vistaDestino);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
				 */				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
			request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
			request.setAttribute("mensajeError", "Error de acceso a la BD");
			String vistaDestino = "/vistas/VistaError.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
			dispatcher.forward(request,response);	
		}
		

	}

}