package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CopyOfAccesoDatos {
	
	public static ResultSet listadoPreparado() throws SQLException {
		// ConexionBDSingleton conBDSing= ConexionBDSingleton.getConexionBD();
		String consultaSQL = "SELECT * FROM EMP";
		PreparedStatement sentenciaPreparada;
        ResultSet cursor;
		sentenciaPreparada = ConexionBDSingleton.getConexionBD().prepareStatement(consultaSQL);
        cursor = sentenciaPreparada.executeQuery();
        return cursor;
	}
	
	public static ResultSet devuelveUsuarioPorCredenciales(String usuario, String clave) throws SQLException {
		String consultaSQL = "SELECT * FROM Usaurio WHERE usuario = ? AND clave = ?";
		PreparedStatement sentenciaPreparada;
        ResultSet cursor;
		sentenciaPreparada = ConexionBDSingleton.getConexionBD().prepareStatement(consultaSQL);
	    sentenciaPreparada.setString(1, usuario);
	    sentenciaPreparada.setString(1, clave);
        cursor = sentenciaPreparada.executeQuery();
		return cursor;
	}
	

}
