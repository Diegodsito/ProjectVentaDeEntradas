package proyectosia9;
import java.io.*;
import java.util.*;

public class Main {

  //Creacion de mostrarMenueption
  //private static HashMap<Integer, List<Compra>> ventas = new HashMap<HashMapra>>();
  
  public static void main(String[] args)throws IOException {

    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
    String ingresado;
    //String usuariosTXT = "java/ArchivosCSV/USUARIO.txt";
    int opcion;
    int opcionMenu;

    Map<Integer,Evento> eventos = new HashMap<Integer,Evento>();
    Usuario usuarioIngresado = new Usuario();

    inicializarEventos(eventos);

    do{
  
      mostrarMenu();
      
      ingresado = lector.readLine();
      opcion = Integer.parseInt(ingresado);

      switch(opcion){
        case 1:
          mostrarEventosDisponibles(eventos);
          System.out.println("Ingrese el evento que desea saber mas informacion.");
          ingresado = lector.readLine();
          opcionMenu = Integer.parseInt(ingresado);
          
          //Obtener y mostrar evento;
          Evento eventoBuscado = eventos.get(opcionMenu);
          eventoBuscado.mostrarInfoEvento();

          
          if (eventoBuscado.getEntradasDisponibles() > 0) {
            
            mostrarMensajeCompra();

            ingresado = lector.readLine();
            opcionMenu = Integer.parseInt(ingresado);
            if (opcionMenu == 1) {
                System.out.println("Entradas disponibles: " + eventoBuscado.getEntradasDisponibles());
                System.out.println("Ingrese su nombre y correo para continuar");

                System.out.println("Nombre: ");
                ingresado = lector.readLine();
                usuarioIngresado.setNombre(ingresado);

                System.out.println("Correo electrónico: ");
                ingresado = lector.readLine();
                usuarioIngresado.setCorreoElectronico(ingresado);

                // Crear una compra y agregarla al evento
                String idCompra = "ID_" + new Random().nextInt(1000);  //  Es una id random para cada quien que compra
                Compra compra = new Compra(idCompra, usuarioIngresado, 0);  // Cuando queramos ponerle valor hay que modificar esto
                eventoBuscado.agregarCompra(compra);

                // Actualizar entradas disponibles
                int entradasRestantes = eventoBuscado.getEntradasDisponibles() - 1;  
                eventoBuscado.setEntradasDisponibles(entradasRestantes);

                System.out.println("Compra realizada con éxito.");
            } else if (opcionMenu == 2) {
                System.out.println("Volviendo al menú principal");
            }
          } else {
            // Si no hay entradas disponibles
            System.out.println("Lo sentimos, no hay más entradas disponibles para este evento.");
          }
          break;
        case 2:
          System.out.println("Ingrese el ID del evento que desea buscar.");
          ingresado = lector.readLine();
          opcionMenu = Integer.parseInt(ingresado);
          //buscarEventoPorId(opcionMenu);
          break;
        case 3:
          System.out.println("Ingrese el ID del evento que desea cancelar.");
          ingresado = lector.readLine();
          opcionMenu = Integer.parseInt(ingresado);
          //cancelarCompra();
          break;
        case 4:
          System.out.println("Gracias por su visita");
          break;
        default:
          System.out.println("Opcion no valida");
      }
      
      
    } while(opcion != 4);

    
  }

  public static void mostrarEventosDisponibles(Map<Integer, Evento> eventos) {

    int cont = 1;
    System.out.println("");
    System.out.println("Eventos disponibles:");
    for (Evento evento : eventos.values()) {
      System.out.println(cont + ") " + evento.getNombre());
      cont++;
    }
  }
  
  private static void mostrarMenu() {
    System.out.println("===============================================");
    System.out.println("   Bienvenido al sistema de venta de entradas ");
    System.out.println("===============================================");
    System.out.println("1. Eventos disponibles");
    System.out.println("2. Buscar evento por ID");
    System.out.println("3. Cancelar compra");
    System.out.println("4. Salir");
    System.out.print("Ingrese una opción: ");

  }

  private static void mostrarMensajeCompra(){
    System.out.println("");
    System.out.println("Desea comprar entradas?");
    System.out.println("1. Sí");
    System.out.println("2. No");
  }

  private static void inicializarEventos(Map<Integer, Evento> eventos){

    eventos.put(1, new Evento("Concierto de Rock", "Av. Calle 1", "2023-06-01", 50, 50, 1));
    eventos.put(2, new Evento("Festival de Música", "Av. Calle 2", "2023-06-02", 100, 100, 2));
    
  }

}
