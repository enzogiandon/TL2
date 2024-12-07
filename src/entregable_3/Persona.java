package entregable_3;

public class Persona {
    private int id; // Corresponde a la columna ID
    private String nombres; // Corresponde a la columna NOMBRES
    private String apellidos; // Corresponde a la columna APELLIDOS

    // Constructor vacío (útil para frameworks o inicializaciones dinámicas)
    public Persona() {
    }

    // Constructor completo
    public Persona(int id, String nombres, String apellidos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    // Constructor sin ID (útil para crear nuevos registros)
    public Persona(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    // Método para imprimir la información de una persona
    @Override
    public String toString() {
        return "Persona [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + "]";
    }
}
