package proyectosia9;

import java.util.*;

public class Evento {
    private int id;
    private String tipo; // Charlas o Seminarios
    private String nombre;
    private String topico;

    private String ubicacion;
    private String fecha;
    private int capacidadTotal;
    private int entradasDisponibles;
    private List<Compra> compras; // Nueva lista para las compras

    // Constructor;

    public Evento() {
        this.nombre = "";
        this.id = 0;
        this.tipo = "";
        this.ubicacion = "";
        this.fecha = "";
        this.capacidadTotal = 0;
        this.entradasDisponibles = 0;
        this.topico = "";
    }

    public Evento(String nombre, String tipo, String ubicacion, String fecha, int capacidadTotal,
            int entradasDisponibles, String topico, int id) {
        this.nombre = nombre;
        this.id = id;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.capacidadTotal = capacidadTotal;
        this.entradasDisponibles = entradasDisponibles;
        this.topico = topico;
        this.compras = new ArrayList<Compra>();
    }
    // Getters y Setters;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public void agregarCompra(Compra compra) {
        compras.add(compra);
    }

    public List<Compra> getCompras() {
        return compras;
    }
    // Metodos

    public void mostrarInfoEvento() {
        System.out.println("");
        System.out.println("Evento : " + nombre);
        System.out.println("Ubicacion : " + ubicacion);
        System.out.println("Fecha : " + fecha);
        System.out.println("Capacidad Total : " + capacidadTotal);
        System.out.println("Entradas Disponibles : " + entradasDisponibles);
        System.out.println("Topico : " + topico);

    }

    public void mostrarInfoEvento(int idEvento) {
        if (id == idEvento) {
            System.out.println("");
            System.out.println("Evento : " + nombre);
            System.out.println("Ubicacion : " + ubicacion);
            System.out.println("Fecha : " + fecha);
            System.out.println("Capacidad Total : " + capacidadTotal);
            System.out.println("Entradas Disponibles : " + entradasDisponibles);
            System.out.println("Topico : " + topico);
        }
    }

    public void mostrarInfoEvento(String topico) {
        if (this.topico == topico) {
            System.out.println("");
            System.out.println("Evento : " + nombre);
            System.out.println("Ubicacion : " + ubicacion);
            System.out.println("Fecha : " + fecha);
            System.out.println("Capacidad Total : " + capacidadTotal);
            System.out.println("Entradas Disponibles : " + entradasDisponibles);
            System.out.println("Topico : " + topico);
        }
    }

    public void buscarEvento(int idEvento) {

        if (this.id == idEvento) {
            mostrarInfoEvento(idEvento);
        } else{
            System.out.println("No se encontro el evento con ID: " + idEvento);
        }
        
    }

    public void buscarEvento(String topico) {
        if (this.topico.equalsIgnoreCase(topico)){
            mostrarInfoEvento(topico);
        } 
    }


    public void imprimirCompras(){
        if(compras.isEmpty())
        {
            System.out.println("NO Hay comraps");
            return;
        }
        for(Compra compra: compras){
            System.out.println("Compra: " + compra.getIdCompra());
            System.out.println("Usuario: " + compra.getUsuario().getNombre());
            System.out.println("Monto Total: " + compra.getMontoTotal());
            System.out.println("--------------------");
           
        }
    }
    public void eliminarCompra(String idCompra) {
        if (compras.isEmpty()) {
            System.out.println("No hay compras");
            return;
        }

        Iterator<Compra> iterator = compras.iterator(); // Crear un iterador
        boolean compraEliminada = false;

        while (iterator.hasNext()) {
            Compra compra = iterator.next();
            if (compra.getIdCompra().equals(idCompra)) {
                iterator.remove(); // Eliminar la compra usando el iterador
                System.out.println("Compra eliminada");
                compraEliminada = true;
                break; // Salir del bucle una vez eliminada
            }
        }

        if (!compraEliminada) {
            System.out.println("No se encontró la compra con ID: " + idCompra);
        }
    }
    

    
}
