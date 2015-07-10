package modelo;

import java.util.Date;


public class Usuario {
	private String usuario;
	private String clave;
	private String apellidos;
	private String nombre;
	private Date fechaNacimiento;
	private String sexo;
	private boolean bloqueado;
	
	public Usuario() {
	}

	public Usuario(String usuario, String clave, String apellidos,
			String nombre, Date fechaNacimiento, String sexo, boolean bloqueado) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.apellidos = apellidos;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.bloqueado = bloqueado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	@Override
	public String toString() {
		return "usuario = " + this.usuario + " # clave = " + this.clave + " # apellidos " + this.apellidos
				+ " # nombre = " + this.nombre + " # fechaNacimiento = " + this.fechaNacimiento 
				+ " # sexo = " + this.sexo + " # bloqueado = " + this.bloqueado;
	}	
	
}
