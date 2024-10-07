package proyectosia9;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class Funciones {

    public static void mostrarEventosDisponibles(Map<Integer, Evento> eventos) {
        System.out.println("");
        System.out.println("Eventos disponibles:");
        int contador = 1;
        for (Evento evento : eventos.values()) {
            if (evento != null) {
                System.out.println(contador + ". " + evento.getNombre());
                contador++;
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("===============================================");
        System.out.println("   Bienvenido al sistema de venta de entradas ");
        System.out.println("===============================================");
        System.out.println("1. Eventos disponibles");
        System.out.println("2. Buscar evento");
        System.out.println("3. Gestion de eventos");
        System.out.println("4. Cancelar compra");
        System.out.println("5. Salir");
        System.out.print("Ingrese una opción: ");
    }

    public static void mostrarMensajeCompra() {
        System.out.println("");
        System.out.println("Desea comprar entradas?");
        System.out.println("1. Sí");
        System.out.println("2. No");
    }

    public static void mostrarTicketDeCompra(String idCompra, Usuario usuarioIngresado, Evento eventoBuscado) {
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

    public static void procesarEvento(BufferedReader lector, Map<Integer, Evento> eventos) throws IOException {
        Funciones.mostrarEventosDisponibles(eventos);
        System.out.println("Ingrese el evento que desea saber más información:");
        int opcionMenu = Integer.parseInt(lector.readLine());
        Evento eventoBuscado = eventos.get(opcionMenu);
        if (eventoBuscado.getTipo().equalsIgnoreCase("Seminario")) {
            TipoSeminario seminario = new TipoSeminario(eventoBuscado.getNombre(), eventoBuscado.getTipo(), eventoBuscado.getUbicacion(), eventoBuscado.getFecha(), eventoBuscado.getCapacidadTotal(), eventoBuscado.getEntradasDisponibles(), eventoBuscado.getTopico(), eventoBuscado.getId());
            seminario.mostrarInfoEvento();
        } else if (eventoBuscado.getTipo().equalsIgnoreCase("Charla")) {
            TipoCharla charla = new TipoCharla(eventoBuscado.getNombre(), eventoBuscado.getTipo(), eventoBuscado.getUbicacion(), eventoBuscado.getFecha(), eventoBuscado.getCapacidadTotal(), eventoBuscado.getEntradasDisponibles(), eventoBuscado.getTopico(), eventoBuscado.getId());
            charla.mostrarInfoEvento();
        }
        if (eventoBuscado.getEntradasDisponibles() > 0) {
            Funciones.mostrarMensajeCompra();
            opcionMenu = Integer.parseInt(lector.readLine());
            if (opcionMenu == 1) {
                System.out.println("Entradas disponibles: " + eventoBuscado.getEntradasDisponibles());
                System.out.println("Ingrese su nombre y correo para continuar");
                System.out.print("Nombre: ");
                String nombreIngresado = lector.readLine();
                System.out.print("Correo electrónico: ");
                String correoIngresado = lector.readLine();
                System.out.print("Ingrese su edad: ");
                int edad = Integer.parseInt(lector.readLine());
                
                
               Usuario usuarioIngresado = new Usuario(nombreIngresado, correoIngresado, edad);
                String idCompra = "ID_" + new Random().nextInt(1000);
                Compra compra = new Compra(idCompra, usuarioIngresado, 0);
                eventoBuscado.agregarCompra(compra);
                int entradasRestantes = eventoBuscado.getEntradasDisponibles() - 1;
                eventoBuscado.setEntradasDisponibles(entradasRestantes);
                Funciones.mostrarTicketDeCompra(idCompra, usuarioIngresado, eventoBuscado);
                System.out.println("¡Compra realizada con éxito!");
                System.out.println("Volviendo al menú principal...");
            } else if (opcionMenu == 2) {
                System.out.println("Volviendo al menú principal");
            }
        } else {
            System.out.println("Lo sentimos, no hay más entradas disponibles para este evento.");
        }
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
        scanner.nextLine();
    }

    public static void gestionarEventos(BufferedReader lector, Map<Integer, Evento> eventos) throws IOException {
        int opcionGestion;
        do {
            mostrarMenuGestionEventos();
            String ingresado = lector.readLine();
            opcionGestion = Integer.parseInt(ingresado);
            switch(opcionGestion) {
                case 1:
                    crearEvento(lector, eventos);
                    break;
                case 2:
                    eliminarEvento(lector, eventos);
                    break;
                case 3:
                    if (eventos.isEmpty()) {
                        System.out.println("No hay eventos disponibles.");
                        break;
                    }
                    System.out.println("Seleccione un evento para ver la lista de compras:");
                    mostrarEventosDisponibles(eventos);
                    verificarEventoSeleccionado(lector, eventos);
                    
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcionGestion != 4);
    }

    public static void mostrarMenuGestionEventos() {
        System.out.println("===============================================");
        System.out.println("   Gestión de Eventos");
        System.out.println("===============================================");
        System.out.println("1. Crear evento");
        System.out.println("2. Eliminar evento");
        System.out.println("3. Ver participantes");
        System.out.println("4. Volver al menú principal");
        System.out.print("Ingrese una opción: ");
    }

    public static void crearEvento(BufferedReader lector, Map<Integer, Evento> eventos) {
        String nombre = "";
        String tipo = "";
        String ubicacion = "";
        String fecha = "";
        int capacidadTotal = 0;
        int entradasDisponibles = 0;
        String topico = "";
        try {
            System.out.println("Ingrese el nombre del evento:");
            nombre = lector.readLine();
            System.out.println("Ingrese el tipo de evento (Charla o Seminario):");
            tipo = lector.readLine();
            if (!tipo.equalsIgnoreCase("Charla") && !tipo.equalsIgnoreCase("Seminario")) {
                throw new TipoEventoInvalidoException("Error: El tipo de evento debe ser 'Charla' o 'Seminario'.");
            }
            System.out.println("Ingrese la ubicación del evento:");
            ubicacion = lector.readLine();
            System.out.println("Ingrese la fecha del evento:");
            fecha = lector.readLine();
            System.out.println("Ingrese la capacidad total del evento:");
            try {
                capacidadTotal = Integer.parseInt(lector.readLine());
            } catch (NumberFormatException e) {
                throw new EntradaInvalidaException("Error: Se esperaba un número entero para la capacidad total.");
            }
            System.out.println("Ingrese la cantidad de entradas disponibles:");
            try {
                entradasDisponibles = Integer.parseInt(lector.readLine());
            } catch (NumberFormatException e) {
                throw new EntradaInvalidaException("Error: Se esperaba un número entero para las entradas disponibles.");
            }
            System.out.println("Ingrese el tópico del evento:");
            topico = lector.readLine();
            if (capacidadTotal < 0 || entradasDisponibles < 0) {
                throw new CapacidadInvalidaException("La capacidad total y las entradas disponibles deben ser números no negativos.");
            }
            if (entradasDisponibles > capacidadTotal) {
                throw new CapacidadInvalidaException("Las entradas disponibles no pueden ser mayores que la capacidad total.");
            }
            Evento nuevoEvento = new Evento(nombre, tipo, ubicacion, fecha, capacidadTotal, entradasDisponibles, topico, eventos.size() + 1);
            try {
                ArchivoEventos.guardarEventoEnArchivo(eventos, nuevoEvento, nuevoEvento.getId());
                System.out.println("Evento creado y guardado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al guardar el evento.");
                e.printStackTrace();
            }
            System.out.println("Evento creado con éxito. ID del evento: " + nuevoEvento.getId());
        } catch (EntradaInvalidaException | CapacidadInvalidaException | TipoEventoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer la entrada. " + e.getMessage());
        }
    }

    public static void eliminarEvento(BufferedReader lector, Map<Integer, Evento> eventos) {
        try {
            System.out.println("Ingrese el ID del evento que desea eliminar:");
            String input = lector.readLine().trim();
            int idEvento = Integer.parseInt(input);
            Evento eventoAEliminar = eventos.get(idEvento);
            if (eventoAEliminar != null) {
                if (!eventoAEliminar.getCompras().isEmpty()) {
                    System.out.println("Las entradas han sido reembolsadas.");
                }
                ArchivoEventos.eliminarEventoPorId(eventos, idEvento);
                System.out.println("Evento eliminado con éxito.");
            } else {
                System.out.println("No existe un evento con el ID ingresado: " + idEvento);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Se esperaba un número. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer la entrada. " + e.getMessage());
        }
    }

    public static void seleccionarEventoYEliminarCompra(BufferedReader lector, Map<Integer, Evento> eventos) {
        try {
            System.out.println("Seleccione el evento que compró su entrada:");
            int index = 1;
            for (Evento evento : eventos.values()) {
                if (evento != null) {
                    System.out.println(index + ". " + evento.getNombre());
                    index++;
                }
            }
            int opcionEvento2 = Integer.parseInt(lector.readLine());
            if (opcionEvento2 > 0 && opcionEvento2 <= index) {
                Evento eventoSeleccionado = eventos.get(opcionEvento2);
                if (eventoSeleccionado != null) {
                    System.out.println("Compras para el evento: " + eventoSeleccionado.getNombre());
                    System.out.println("Ingrese el ID de la compra que desea eliminar:");
                    String idCompraEliminar = lector.readLine();
                    eventoSeleccionado.eliminarCompra(idCompraEliminar);
                } else {
                    System.out.println("El evento seleccionado no existe.");
                }
            } else {
                System.out.println("Opción inválida. Por favor, seleccione un número válido.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer la entrada. " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Se esperaba un número. " + e.getMessage());
        }
    }

    public static void verificarEventoSeleccionado(BufferedReader lector, Map<Integer, Evento> eventos) {
        try {
            System.out.println("Ingrese el Numero del evento que desea seleccionar:");
            int opcionEvento = Integer.parseInt(lector.readLine());
            if (eventos.containsKey(opcionEvento)) {
                Evento eventoSeleccionado = eventos.get(opcionEvento);
                if (eventoSeleccionado != null) {
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Recaudación total del evento: " + eventoSeleccionado.calcularRecaudacion());
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("");
                    System.out.println("Compras para el evento: " + eventoSeleccionado.getNombre());
                    eventoSeleccionado.imprimirCompras();
                } else {
                    System.out.println("El evento seleccionado no existe.");
                }
            } else {
                System.out.println("Opción inválida. Por favor, seleccione un número válido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Se esperaba un número. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer la entrada. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public static void buscarPorTopico(BufferedReader lector, Map<Integer, Evento> eventos) throws IOException {
        System.out.println("Ingrese el tópico del evento que desea buscar:");
        String ingresado = lector.readLine();
        String topico = ingresado.trim();
        boolean eventoEncontrado = false;
        for (Evento evento : eventos.values()) {
            if (evento.getTopico().trim().equalsIgnoreCase(topico)) {
                evento.mostrarInfoEvento();
                eventoEncontrado = true;
            }
        }
        if (!eventoEncontrado) {
            System.out.println("No se encontraron eventos con el tópico ingresado.");
        }
    }

    public static void buscarPorID(BufferedReader lector, Map<Integer, Evento> eventos) throws IOException {
        System.out.println("Ingrese el ID del evento que desea buscar:");
        String ingresado = lector.readLine();
        int idEvento = Integer.parseInt(ingresado);
        Evento eventoBuscado = eventos.get(idEvento);
        if (eventoBuscado != null) {
            eventoBuscado.mostrarInfoEvento();
        } else {
            System.out.println("No existe un evento con el ID ingresado.");
        }
    }

    public static void buscarEvento(BufferedReader lector, Map<Integer, Evento> eventos) throws IOException {
        System.out.println("¿De qué manera desea buscar su evento?");
        System.out.println("1. Por tópico");
        System.out.println("2. Por ID");
        System.out.println("3. Volver al menú principal");
        String ingresado = lector.readLine();
        int opcionMenu = Integer.parseInt(ingresado);
        switch(opcionMenu) {
            case 1:
                buscarPorTopico(lector, eventos);
                break;
            case 2:
                buscarPorID(lector, eventos);
                break;
            case 3:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
