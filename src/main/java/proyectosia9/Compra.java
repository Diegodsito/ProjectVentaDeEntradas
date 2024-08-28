package proyectosia9;


public class Compra {
    private String idCompra;
    private Usuario usuario;
    private double montoTotal;

    // constructor;
    public Compra(String idCompra, Usuario usuario, double montoTotal) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.montoTotal = montoTotal;
        
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}