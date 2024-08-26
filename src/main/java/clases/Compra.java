package clases;

import java.time.LocalDate;



public class Compra {
    private String idCompra;
    private Usuario usuario;
    private LocalDate fechaCompra;
    private double montoTotal;

    // constructor;

    public Compra(String idCompra, Usuario usuario, LocalDate fechaCompra, double montoTotal) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.fechaCompra = fechaCompra;
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

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }



}