package proyectosia9;

public class Usuario {

    private String nombre;

    private String correoElectronico;

    private int edad;

    public Usuario() {
        this.nombre = "";
        this.correoElectronico = "";
    }

    public Usuario(String nombre, String correoElectronico, int edad) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    private String interes;

    public Usuario(String nombre, String correoElectronico) {
    }

    public String getInteres() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setInteres(String interes) {
    }
}
