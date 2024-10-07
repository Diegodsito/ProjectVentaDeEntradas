package proyectosia9;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<Integer, Evento> eventos = new HashMap();
        Lectura.leer_archivo(eventos);
        MainWindow window = new MainWindow(eventos);
        window.setVisible(true);
    }


    //Consola

  /* public static void ejecutarConsola(BufferedReader lector, Map<Integer, Evento> eventos) throws IOException {
        String ingresado;
        int opcion;
        int opcionMenu;
        do {
            Funciones.mostrarMenu();
            ingresado = lector.readLine();
            opcion = Integer.parseInt(ingresado);
            switch(opcion) {
                case 1:
                    Funciones.procesarEvento(lector, eventos);
                    break;
                case 2:
                    Funciones.buscarEvento(lector, eventos);
                    break;
                case 3:
                    Funciones.gestionarEventos(lector, eventos);
                    Funciones.presionaTeclaParaContinuar();
                    break;
                case 4:
                    Funciones.seleccionarEventoYEliminarCompra(lector, eventos);
                    Funciones.presionaTeclaParaContinuar();
                    break;
                case 5:
                    System.out.println("Gracias por su visita.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);


    */}


