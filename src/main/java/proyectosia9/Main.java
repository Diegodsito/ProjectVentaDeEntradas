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

          
          System.out.println("");
          System.out.println("Desea comprar entradas?");
          System.out.println("1. Si");
          System.out.println("2. No");

          ingresado = lector.readLine();
          opcionMenu = Integer.parseInt(ingresado);
          if(opcionMenu == 1){
            
            System.out.println("Entradas disponibles: " + eventoBuscado.getEntradasDisponibles());
            System.out.println("Ingrese su nombre y correo para continuar");
            
            System.out.println("Nombre: ");
            ingresado = lector.readLine();
            usuarioIngresado.setNombre(ingresado);
            
            System.out.println("Correo electronico: ");
            ingresado = lector.readLine();
            usuarioIngresado.setCorreoElectronico(ingresado);
          }
          else if(opcionMenu == 2){
            System.out.println("Volviendo al menu principal");
            break;
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

  private static void inicializarEventos(Map<Integer, Evento> eventos){
    Evento evento1 = new Evento("Charla Java", "Av. Calle 1","20/11/1004", 100, 50, 1);
    Evento evento2 = new Evento("Seminarios Programacion" , "Av. Calle 2", "20/11/2024", 100, 50, 2);

    eventos.put(1, evento1);
    eventos.put(2, evento2);
  }

}
