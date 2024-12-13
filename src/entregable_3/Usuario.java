package entregable_3;

public class Usuario {
	private int id;
	private int idPersona;
	private String email;
	private String passwd;

	public Usuario(int id, int idPersona, String email, String passwd) {
		super();
		this.idPersona = idPersona;
		this.email = email;
		this.passwd = passwd;
	}

	public Usuario(String email, String passwd) {
		super();
		this.email = email;
		this.passwd = passwd;
	}

	public Usuario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
