package servlets.post;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/Altapost")
public class Altapost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// se recupera del contexto la rutaControladorFrontal, que se usar� para toda opci�n de visualizado
		String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
		// se trata de recuperar la sesi�n y se compruebe si existe la variable usuarioAutenticado
		HttpSession laSesion = request.getSession(false);
		if (laSesion != null) {  // existe la sesi�n 
			String usuarioAutenticado = (String) laSesion.getAttribute("usuarioAutenticado");
			if (usuarioAutenticado != null) {  // existe un usuario autenticado
				// se adjunta el usuarioAutenticado al request que ir� a la vista
				request.setAttribute("usuarioAutenticado", usuarioAutenticado);
				// se adjunta la rutaControladorFrontal al request que va a la vista
				request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
				// se indica cu�l ser� la vista que se colocar� en la seccci�n de contenido de la plantilla
				// y se le pasa a la plantilla de construcci�n de la p�gina 
				String vistaContenido = "post/VistaAlta.jsp";
				request.setAttribute("vistaContenido", vistaContenido);
				// se indica el t�tulo de la p�gina a generar
				request.setAttribute("titulo", "Altapost de post");
				// se indica la vista de destino, que ser� siempre la PlantillaPagina.jsp
				String vistaDestino = "/vistas/PlantillaPagina.jsp";
				// se redirige a PlantillaPagina.jsp para consruir la vista
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
			} else {  // no existe un usuario autenticado
				request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
				request.setAttribute("usuario", "");
				request.setAttribute("mensajeLogin", "Sesi�n caducada, autent�quese de nuevo");
				String vistaDestino = "/vistas/usuario/VistaAutenticacion.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
			}
		} else {  // no hay una sesi�n activa => se va a la p�gina de Autenticacion
			request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
			request.setAttribute("usuario", "");
			request.setAttribute("mensajeLogin", "Sesi�n caducada, autent�quese de nuevo");
			String vistaDestino = "/vistas/usuario/VistaAutenticacion.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
			dispatcher.forward(request,response);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// se recupera del contexto la rutaControladorFrontal, que se usar� para toda opci�n de visualizado
		String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
		// se trata de recuperar la sesi�n y se compruebe si existe la variable usuarioAutenticado
		HttpSession laSesion = request.getSession(false);
		if (laSesion != null) {  // existe la sesi�n 
			String usuarioAutenticado = (String) laSesion.getAttribute("usuarioAutenticado");
			if (usuarioAutenticado != null) {  // existe un usuario autenticado
				// se reciben los par�metros del formulario
				// aqu� habr�a que analizarlos sint�cticamente y validar la l�gica de negocio
				String titulo = request.getParameter("titulo");
				String cuerpo = request.getParameter("cuerpo");
				String fecha = request.getParameter("fecha");
				// se parsea la fecha
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
				Date fechaParseada = null;
				try {
					fechaParseada = formatoDelTexto.parse(fecha);
				} catch (ParseException ex) {
					ex.printStackTrace();
					System.out.println("Formato de fecha err�neo");
				} 
				// se construye un objeto post, con id ficticio=0,
				Post elPost = new Post(0, fechaParseada, titulo, cuerpo, usuarioAutenticado);
				
			} else {  // no existe un usuario autenticado
				request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
				request.setAttribute("usuario", "");
				request.setAttribute("mensajeLogin", "Sesi�n caducada, autent�quese de nuevo");
				String vistaDestino = "/vistas/usuario/VistaAutenticacion.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
			}
		} else {  // no hay una sesi�n activa => se va a la p�gina de Autenticacion
			request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
			request.setAttribute("usuario", "");
			request.setAttribute("mensajeLogin", "Sesi�n caducada, autent�quese de nuevo");
			String vistaDestino = "/vistas/usuario/VistaAutenticacion.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
			dispatcher.forward(request,response);	
		}
/*		
				
		try {
			AccesoDatos.insertaPost(elPost);
			request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
			request.setAttribute("mensaje", "Post dado de alta correctamente");
			String vistaDestino = "/vistas/VistaExitoOperacion.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
			dispatcher.forward(request,response);
		} else {  // se encont� al usuario
					// aqu� se accede al realizar una autenticaci�n exitosa 
					// se crea la sesi�n y se registra la variable usuarioAutenticado
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
			// out.println(elUsuario);
			if (elUsuario == null) {  // no se encontr� un usuario con esas credenciales
				// se vuelve a ala vista de login, informando del error de autenticaci�n
				String rutaControladorFrontal = (String) getServletContext().getAttribute("RUTA_CONTROLADOR_FRONTAL");
				request.setAttribute("rutaControladorFrontal", rutaControladorFrontal);
				request.setAttribute("mensajeLogin", "Usuario y/o contrase�a desconocidos");
				String vistaDestino = "/vistas/usuario/VistaAutenticacion.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vistaDestino);
				dispatcher.forward(request,response);
			} else {  // se encont� al usuario
				// aqu� se accede al realizar una autenticaci�n exitosa 
				// se crea la sesi�n y se registra la variable usuarioAutenticado
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
				 */		
	}

}
