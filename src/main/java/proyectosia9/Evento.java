package proyectosia9;


public class Evento {
    private String nombre;
    private String ubicacion;
    private String fecha;
    private int capacidadTotal;
    private int entradasDisponibles;
    private int topico;

    
    // Constructor;

    public Evento(){
        this.nombre = "";
        this.ubicacion = "";
        this.fecha = "";
        this.capacidadTotal = 0;
        this.entradasDisponibles = 0;
        this.topico = 0;
    }
    
    public Evento(String nombre, String ubicacion, String fecha, int capacidadTotal, int entradasDisponibles, int topico)
    {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.capacidadTotal = capacidadTotal;
        this.entradasDisponibles = entradasDisponibles;
        this.topico = topico;
    }
    // Getters y Setters;
  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getFecha() {
        return fecha;
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
    public int getTopico() {
        return topico;
    }
    public void setTopico(int topico) {
        this.topico = topico;
    }
    //Metodos

    public void mostrarInfoEvento(){
        System.out.println("");
        System.out.println("Evento : " + nombre);
        System.out.println("Ubicacion : " + ubicacion);
        System.out.println("Fecha : " + fecha);
        System.out.println("Capacidad Total : " + capacidadTotal);
        System.out.println("Entradas Disponibles : " + entradasDisponibles);
        System.out.println("Topico : " + topico);
         
    }


}