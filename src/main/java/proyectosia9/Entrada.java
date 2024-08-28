package proyectosia9;

public class Entrada {
    private String idEntrada;
    private String tipo;
    private double precio;
    private String estado;

    
    // Constructor;
    public Entrada(String idEntrada, String tipo, double precio, String estado) {
        this.idEntrada = idEntrada;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }
    // Getters y Setters;

    public String getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}








