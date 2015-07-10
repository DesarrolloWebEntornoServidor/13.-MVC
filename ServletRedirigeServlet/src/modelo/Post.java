package modelo;

import java.util.Date;

public class Post {
	private int idPost;
	private Date fecha;
	private String titulo;
	private String cuerpo;
	private String usuario;
	
	public Post() {
	}
			
	public Post(int idPost, Date fecha, String titulo, String cuerpo, String usuario) {
		super();
		this.idPost = idPost;
		this.fecha = fecha;
		this.titulo = titulo;
		this.cuerpo = cuerpo;
		this.usuario = usuario;
	}

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}