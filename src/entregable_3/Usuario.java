package entregable_3;

public class Usuario {
    private int id;
    private int idPersona;
    private String email;
    private String passwd;

    // Constructor que incluye todos los atributos
    public Usuario(int id, int idPersona, String email, String passwd) {
        this.id = id;
        this.idPersona = idPersona;
        this.email = email;
        this.passwd = passwd;
    }

    // Constructor para solo email y password
    public Usuario(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    // Constructor vacío
    public Usuario() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
