import java.io.*;

public class Main {
  public static void main(String[] args)throws IOException {

    String ingresado;
    int opcion;

    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

    do{

      System.out.println("Bienvenido al sistema de venta de entradas");
      System.out.println("1. Eventos disponibles");
      System.out.println("2. Buscar evento por ID");
      System.out.println("3. Cancelar compra");
      System.out.println("4. Salir");

      ingresado = lector.readLine();
      opcion = Integer.parseInt(ingresado);

      switch(opcion){
        case 1:
          //mostrarEventosDisponible();
          break;
        case 2:
          //buscarEventoPorID(Id);
          break;
        case 3:
          //cancelarCompra(Id);
          break;
        case 4:
          System.out.println("Gracias por usar el sistema de venta de entradas");
          break;
        default:
          System.out.println("Opcion no valida");
          break;
      }
      
      
    } while(opcion != 4);

    
  }