package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccesoDatos {

/* POSTS */	
	public static List<Post> listaTodosPosts() throws SQLException {
		List<Post> listaPosts = new ArrayList<Post>();
		
		Connection conexion = ConexionBDSingleton.getConexionBD();
		String consultaSQL = "SELECT * FROM Post ORDER BY fecha DESC";
		PreparedStatement sentenciaPreparada;
        ResultSet cursor;
		sentenciaPreparada = conexion.prepareStatement(consultaSQL);
        cursor = sentenciaPreparada.executeQuery();
		while (cursor.next()) {
			Post unPost = new Post(cursor.getInt("idPost"), cursor.getDate("fecha"),
										cursor.getString("titulo"), cursor.getString("cuerpo"),
										cursor.getString("usuario"));
			listaPosts.add(unPost);
		}
        return listaPosts;
	}

	public static Post devuelvePostPorId(int idPost) throws SQLException {
		Post elPost = null;

		Connection conexion = ConexionBDSingleton.getConexionBD();
		String consultaSQL = "SELECT * FROM Post WHERE idPost = ?";
		PreparedStatement sentenciaPreparada;
        ResultSet cursor;
        sentenciaPreparada = conexion.prepareStatement(consultaSQL);
	    sentenciaPreparada.setInt(1, idPost);
        cursor = sentenciaPreparada.executeQuery();
        if (cursor.next()) {
			elPost = new Post(cursor.getInt("idPost"), cursor.getDate("fecha"), cursor.getString("titulo"),
								cursor.getString("cuerpo"), cursor.getString("usuario"));
		}
        return elPost;
	}

/* USUARIOS */	
	public static Usuario devuelveUsuarioPorCredenciales(String usuario, String clave) throws SQLException {
		Usuario elUsuario = null;

		Connection conexion = ConexionBDSingleton.getConexionBD();
		String consultaSQL = "SELECT * FROM Usuario WHERE usuario = ? AND clave = ?";
		PreparedStatement sentenciaPreparada;
        ResultSet cursor;
        sentenciaPreparada = conexion.prepareStatement(consultaSQL);
	    sentenciaPreparada.setString(1, usuario);
	    sentenciaPreparada.setString(2, clave);
        cursor = sentenciaPreparada.executeQuery();
        if (cursor.next()) {
        	boolean bloqueado = false;
        	if (cursor.getString("bloqueado").equals("s")) {
        		bloqueado = true;
        	}        	
			elUsuario = new Usuario(cursor.getString("usuario"), cursor.getString("clave"),
										cursor.getString("apellidos"), cursor.getString("nombre"),
										cursor.getDate("fechaNacimiento"), cursor.getString("sexo"),
										bloqueado);
		}
        return elUsuario;
	}
	
}
