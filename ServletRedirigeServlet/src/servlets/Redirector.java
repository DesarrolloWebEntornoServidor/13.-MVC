package servlets;

import java.io.IOException;
// import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Redirector")
public class Redirector extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String haciaDonde = "/Redirigido";
		// PrintWriter out = response.getWriter();
		// out.println("Nombre = " + haciaDonde);
		// String pathInfo = request.getPathInfo();
		// pathInfo = pathInfo.substring(1);
				
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(haciaDonde);
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	


}
