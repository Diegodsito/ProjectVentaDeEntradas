package clases;
import java.time.LocalDateTime;

public class Evento {
    private String nombre;
    private LocalDateTime fechaHora;
    private String ubicacion;
    private int capacidadTotal;
    private int entradasDisponibles;

    
    // Constructor;
    
    public Evento(String nombre, LocalDateTime fechaHora, String ubicacion, int capacidadTotal, int entradasDisponibles)
    {
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.capacidadTotal = capacidadTotal;
        this.entradasDisponibles = entradasDisponibles;
    }
    // Getters y Setters;
  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public int getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(int entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }



}