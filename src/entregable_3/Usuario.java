package entregable_3;

public class Usuario {
    private String email;
    private String passwd;

    public Usuario(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
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
