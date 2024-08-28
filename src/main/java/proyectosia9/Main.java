package proyectosia9;
import java.io.*;
import java.util.*;

public class Main {

  //Creacion de mostrarMenueption
  //private static HashMap<Integer, List<Compra>> ventas = new HashMap<HashMapra>>();
  
  public static void main(String[] args)throws IOException {

    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
    String ingresado;
    int opcion;
    int opcionMenu;

    Map<Integer,Evento> eventos = new HashMap<Integer,Evento>();
    
    Evento evento1 = new Evento("Charla Java", "Av. Calle 1","20/11/1004", 100, 50, 1);
    Evento evento2 = new Evento("Seminarios Programacion" , "Av. Calle 2", "20/11/2024", 100, 50, 2);

    eventos.put(1, evento1);
    eventos.put(2, evento2);

    


    do{
      int sizeEventos = eventos.size();
      System.out.println("Tamano eventos" + sizeEventos);
      System.out.flush();  // Forzar la impresión inmediata en la consola
      System.out.println();
      
      mostrarMenu();
      
      ingresado = lector.readLine();
      opcion = Integer.parseInt(ingresado);

      switch(opcion){
        case 1:
          //mostrarEventosDisponible();
          System.out.println("Ingrese el evento que desea saber mas informacion.");
          ingresado = lector.readLine();
          opcionMenu = Integer.parseInt(ingresado);
          //Obtener y mostrar evento;
          Evento eventoBuscado = eventos.get(opcionMenu);
          eventoBuscado.mostrarInfoEvento();
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
}
