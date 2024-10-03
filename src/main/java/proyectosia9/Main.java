package proyectosia9;
import java.io.*;
import java.util.*;


public class Main {

  
  public static void main(String[] args)throws IOException {
    
    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
    String ingresado;
   
    int opcion;
    int opcionMenu;

    Map<Integer,Evento> eventos = new HashMap<Integer,Evento>(); //Inicializacion mapa de eventos
    Lectura.leer_archivo(eventos);                               //Inicializar eventos con el archivo EVENTOS.txt
    int tamanioMapa = eventos.size();

    System.out.println("Tamaño del mapa: " + tamanioMapa);

    
    do{
  
      mostrarMenu(); //Menu Principal
      
      ingresado = lector.readLine();
      opcion = Integer.parseInt(ingresado);

      limpiarPantalla();

      switch(opcion){
        case 1:                                                                         //Opcion uno mostrar eventos
          mostrarEventosDisponibles(eventos);
          System.out.println("Ingrese el evento que desea saber mas informacion.");
          ingresado = lector.readLine();
          opcionMenu = Integer.parseInt(ingresado);
          
          //Obtener y mostrar evento;
          Evento eventoBuscado = eventos.get(opcionMenu);
          

          eventoBuscado.mostrarInfoEvento();
          if (eventoBuscado.getEntradasDisponibles() > 0) {      //Compra de entradas
            
            mostrarMensajeCompra();

            ingresado = lector.readLine();
            opcionMenu = Integer.parseInt(ingresado);

            limpiarPantalla();
            
            if (opcionMenu == 1) {
                System.out.println("Entradas disponibles: " + eventoBuscado.getEntradasDisponibles());
                //Ahora mostraremos la informacion del evento
                
                System.out.println("Ingrese su nombre y correo para continuar");

              System.out.print("Nombre: ");
              String nombreIngresado = lector.readLine();  // Leer nombre

              System.out.print("Correo electrónico: ");
              String correoIngresado = lector.readLine();  // Leer correo

              // Crear una nueva instancia de Usuario
              Usuario usuarioIngresado = new Usuario(nombreIngresado, correoIngresado);
                

                // Crear una compra y agregarla al evento
                String idCompra = "ID_" + new Random().nextInt(1000);  //  Es una id random para cada quien que compra
                Compra compra = new Compra(idCompra, usuarioIngresado, 0);  // Cuando queramos ponerle valor hay que modificar esto
                eventoBuscado.agregarCompra(compra);

                //Aca debe modificarse CSV con nuevos usuarios

                // Actualizar entradas disponibles
                int entradasRestantes = eventoBuscado.getEntradasDisponibles() - 1;  
                eventoBuscado.setEntradasDisponibles(entradasRestantes);

                limpiarPantalla();

                mostrarTicketDeCompra(idCompra,usuarioIngresado,eventoBuscado);

                System.out.println("Gracias por su compra!");

                presionaTeclaParaContinuar();
                limpiarPantalla();
              
                System.out.println("Volviendo al menu principal...");
            } else if (opcionMenu == 2) {
                System.out.println("Volviendo al menú principal");
            }
          } else {
            // Si no hay entradas disponibles
            System.out.println("Lo sentimos, no hay más entradas disponibles para este evento.");
          }
          break;
        case 2: //Opcion de busqueda de eventos
          
          System.out.println("De que manera desea buscar su evento?.");
          System.out.println("1. Por topico");
          System.out.println("2. Por ID");
          System.out.println("3. Volver al menu principal");
          ingresado = lector.readLine();
          opcionMenu = Integer.parseInt(ingresado);
         
          if (opcionMenu == 1){  //Busqueda por topico
            
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

            boolean eventoEncontrado = false;  // Variable para verificar si se encontró el evento

            for (Map.Entry<Integer, Evento> entry : eventos.entrySet()) {
                if (entry.getKey() == idEvento) {
                    entry.getValue().buscarEvento(idEvento);
                    eventoEncontrado = true;
                    break;
                }
            }

            if (!eventoEncontrado) {
                System.out.println("No existe un evento con el ID ingresado.");
            }
          }
          break;
          
        case 3:  //Cancelacion de compra
          System.out.println("Seleccione el evento que compro su entrada:");

          // Mostrar todos los eventos
          for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            if (evento != null) {  // Asegúrate de que el evento no sea null
                System.out.println((i + 1) + ". " + evento.getNombre());
            }
          }

          // Leer la opción del usuario
          int opcionEvento2 = Integer.parseInt(lector.readLine());
          if (opcionEvento2 > 0 && opcionEvento2 <= eventos.size()) {
           Evento eventoSeleccionado = eventos.get(opcionEvento2 - 1);
              if (eventoSeleccionado != null) {  // Verifica que el evento no sea null
                  System.out.println("Compras para el evento: " + eventoSeleccionado.getNombre());

                  // Pedir al usuario que ingrese el ID de la compra a eliminar
                  System.out.println("Ingrese el ID de la compra que desea eliminar:");
                  String idCompraEliminar = lector.readLine(); // Leer el ID de compra

                  // Llamar al método para eliminar la compra
                  eventoSeleccionado.eliminarCompra(idCompraEliminar);

                  
              } else {
                  System.out.println("El evento seleccionado no existe.");
              }
          } else {
              System.out.println("Opción inválida. Por favor, seleccione un número válido.");
          }
          
          

          //Para cancelar compra ingresar correo y idCompra al cual tiene guardao pa cancelarlo, se devuelven fondos
          
          //cancelarCompra();
          break;
        case 4:
          System.out.println("Gracias por su visita");
          break;

        case 999:
          System.out.println("modo jakerxdxd");

          if (eventos.isEmpty()) {
            System.out.println("No hay eventos disponibles.");
            break;
          }

          System.out.println("Seleccione un evento para ver la lista de compras:");

          // Mostrar todos los eventos
          for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            if (evento != null) {  // Asegúrate de que el evento no sea null
                System.out.println((i + 1) + ". " + evento.getNombre());
            }
          }

          // Leer la opción del usuario
          int opcionEvento = Integer.parseInt(lector.readLine());

          // Verificar que la opción sea válida
          if (opcionEvento > 0 && opcionEvento <= eventos.size()) {
            Evento eventoSeleccionado = eventos.get(opcionEvento - 1);
            if (eventoSeleccionado != null) {  // Verifica que el evento no sea null
                System.out.println("Compras para el evento: " + eventoSeleccionado.getNombre());
                eventoSeleccionado.imprimirCompras(); // Llama al método para imprimir las compras del evento
            } else {
                System.out.println("El evento seleccionado no existe.");
            }
          } else {
            System.out.println("Opción inválida. Por favor, seleccione un número válido.");
          }
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
    System.out.println("=============================");
    System.out.println("");
  }


  public static void limpiarPantalla() {
      try {
          if (System.getProperty("os.name").contains("Windows")) {
              new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
          } else {
              System.out.print("\033[H\033[2J");
              System.out.flush();
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static void presionaTeclaParaContinuar() {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Presiona Enter para continuar...");
      scanner.nextLine();  // Espera a que el usuario presione Enter
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
