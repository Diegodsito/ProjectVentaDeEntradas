package proyectosia9;

public class TipoSeminario extends Evento {
  
    private final int duracion; // Atributo para la duración

   
    public TipoSeminario(String nombre, String tipo, String ubicacion, String fecha, int capacidadTotal, int entradasDisponibles, String topico, int id) {
        super(nombre, tipo, ubicacion, fecha, capacidadTotal, entradasDisponibles, topico, id);
        this.duracion = 70; // Inicializa la duración con el valor fijo
    }
    public int getDuracion() {
        return duracion;
    }


    @Override
    public void mostrarInfoEvento() {  
        super.mostrarInfoEvento();  // Llama al método de la clase padre
        System.out.println("Duración: " + duracion + " minutos"); // Muestra la duración
        
    }
}
