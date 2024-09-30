package proyectosia9;
import java.io.*;
import java.util.*;


public class Main {

  
  public static void main(String[] args)throws IOException {
    
    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
    String ingresado;
   
    int opcion;
    int opcionMenu;

    Map<Integer,Evento> eventos = new HashMap<Integer,Evento>();

   
    Lectura.leer_archivo(eventos);

    Usuario usuarioIngresado = new Usuario();

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
                //Ahora mostraremos la informacion del evento
                
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

                //Aca debe modificarse CSV con nuevos usuarios

                // Actualizar entradas disponibles
                int entradasRestantes = eventoBuscado.getEntradasDisponibles() - 1;  
                eventoBuscado.setEntradasDisponibles(entradasRestantes);

                mostrarTicketDeCompra(idCompra,usuarioIngresado,eventoBuscado);

                System.out.println("Gracias por su compra!");
                System.out.println("Volviendo al menu principal...");
            } else if (opcionMenu == 2) {
                System.out.println("Volviendo al menú principal");
            }
          } else {
            // Si no hay entradas disponibles
            System.out.println("Lo sentimos, no hay más entradas disponibles para este evento.");
          }
          break;
        case 2:
          
          System.out.println("De que manera desea buscar su evento?.");
          System.out.println("1. Por topico");
          System.out.println("2. Por ID");
          System.out.println("3. Volver al menu principal");
          ingresado = lector.readLine();
          opcionMenu = Integer.parseInt(ingresado);
         
          if (opcionMenu == 1){
            System.out.println("Ingrese el topico del evento que desea buscar");
            
            ingresado = lector.readLine();
            String topico = ingresado.trim();
            for (Evento evento : eventos.values()) {
                if (evento.getTopico().trim().equalsIgnoreCase(topico)) {
                    evento.mostrarInfoEvento();
                }
            }
            
            //Implementar logica de mostrar varios eventos.
          }
          else if(opcionMenu == 2){  //Buscar por ID
            System.out.println("Ingrese el ID del evento que desea buscar");
            ingresado = lector.readLine();
            int idEvento = Integer.parseInt(ingresado);
            eventos.forEach((id, evento) -> evento.buscarEvento(idEvento));
          }
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
    System.out.println("2. Buscar evento");
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

  private static void mostrarTicketDeCompra(String idCompra, Usuario usuarioIngresado, Evento eventoBuscado){
    System.out.println("=============================");
    System.out.println("Ticket de Evento");
    System.out.println("ID de Compra: " + idCompra);
    System.out.println("Nombre: " + usuarioIngresado.getNombre());
    System.out.println("Correo Electrónico: " + usuarioIngresado.getCorreoElectronico());
    System.out.println("Evento: " + eventoBuscado.getNombre());
    System.out.println("Fecha: " + eventoBuscado.getFecha());
  }
/*
  private static void crearEvento(Map<Integer, Evento> eventos){

    Evento evento1 = new Evento("Programando ando","Seminario","Av. Calle 1", "2023-06-01", 50, 50, "Goku",0001);
    Evento evento2 = new Evento("IA un nuevo mundo por explorar YIAA", "Charla","Av. Calle 2", "2023-06-02", 100, 100, "Programacion",0002);
    
    eventos.put(evento1.getId(), evento1 );
    eventos.put(evento2.getId(), evento2 );
    
  }
*/
}
