package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDSingleton {

    private static String CADENA_CONEXION = "jdbc:mysql://localhost:3306/";
    private static String NOMBRE_BD = "blog";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USUARIO_BD = "root";
    private static String CLAVE_USUARIO_BD = "root";

    public static Connection conexion;    
    // private static ConexionBDSingleton conBD;
    
    private ConexionBDSingleton() {
  /*  	
		Properties propiedades = new Properties();
		String rutaArchivoPropiedades = "/WEB-INF/propiedades/";
		String nombreArchivoPropiedades = "config.properties";
		try {
			propiedades.load(getServletContext().getResourceAsStream(rutaArchivoPropiedades + nombreArchivoPropiedades));
			String driver = propiedades.getProperty("driver");
			String url = propiedades.getProperty("url");
			String nombre_bd = propiedades.getProperty("nombre_bd");
			String usuario_bd = propiedades.getProperty("usuario_bd");
			String clave_usuario_bd = propiedades.getProperty("clave_usuario_bd");
			out.println("<h3>Archivo de propiedades leído desde una clase</h3>");
			out.println("driver: " + driver + " <br />");
			out.println("url: " + url + " <br />");
			out.println("nombre_bd: " + nombre_bd + " <br />");
			out.println("usuario_bd: " + usuario_bd + " <br />");
			out.println("clave_usuario_bd: " + clave_usuario_bd + " <br />");
		} catch (NullPointerException npE) {
			System.out.println("NO SE ACCEDE AL ARCHIVO DE PROPIEDADES");
			npE.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de E/S");
			e.printStackTrace();
		}
*/		
    	try {
    		Class.forName(DRIVER).newInstance();
            conexion = (Connection) DriverManager.getConnection(CADENA_CONEXION + NOMBRE_BD, 
																		USUARIO_BD, 
																		CLAVE_USUARIO_BD);
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
    }
       	
        
    public static synchronized Connection getConexionBD() {
        if (conexion == null) {
        	new ConexionBDSingleton();
        }
        return conexion;
    }
    
    

}
