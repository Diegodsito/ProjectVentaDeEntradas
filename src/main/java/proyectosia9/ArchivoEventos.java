package proyectosia9;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

public class ArchivoEventos {

    // Método para guardar un evento nuevo con formato especificado
    public static void guardarEventoEnArchivo(Map<Integer, Evento> eventos, Evento nuevoEvento, int idEvento) throws IOException {
        // Agregar el evento al mapa
        eventos.put(idEvento, nuevoEvento);

        // Escribir el nuevo evento en el archivo en el formato deseado
        try (FileWriter escritor = new FileWriter("src/main/java/proyectosia9/ArchivosCSV/EVENTOS.txt", true)) {  // true para añadir sin sobrescribir
            escritor.write(nuevoEvento.getNombre() + ";" + 
                           nuevoEvento.getTipo() + ";" + 
                           nuevoEvento.getUbicacion() + ";" + 
                           nuevoEvento.getFecha() + ";" + 
                           nuevoEvento.getEntradasDisponibles() + ";" + 
                           nuevoEvento.getEntradasDisponibles() + ";" + 
                           nuevoEvento.getTopico() + ";" + 
                           String.format("%04d", idEvento) + "\n");
        }
    }
       // Método para eliminar un evento por su ID
    public static void eliminarEventoPorId(Map<Integer, Evento> eventos, int idEvento) throws IOException {
        // Verificar si el evento existe en el mapa
        if (eventos.containsKey(idEvento)) {
            // Eliminar el evento del mapa
            eventos.remove(idEvento);

            // Leer el archivo y escribir solo los eventos que no han sido eliminados
            File archivo = new File("src/main/java/proyectosia9/ArchivosCSV/EVENTOS.txt");
            File archivoTemporal = new File("src/main/java/proyectosia9/ArchivosCSV/EVENTOS_TEMP.txt");

            try (BufferedReader lector = new BufferedReader(new FileReader(archivo));
                 FileWriter escritor = new FileWriter(archivoTemporal)) {

                String linea;
                while ((linea = lector.readLine()) != null) {
                    // Extraer el ID del evento de la línea (asumiendo que el ID está al final, separado por ';')
                    String[] partes = linea.split(";");
                    int id = Integer.parseInt(partes[partes.length - 1]);

                    // Si el ID no es el que se quiere eliminar, escribir la línea en el archivo temporal
                    if (id != idEvento) {
                        escritor.write(linea + "\n");
                    }
                }
            }

            // Reemplazar el archivo original por el archivo temporal
            if (archivo.delete()) {
                archivoTemporal.renameTo(archivo);
            }
        } else {
            System.out.println("El evento con ID " + idEvento + " no existe.");
        }
    }


    
    //public static void borrarEvento(Map<Integer, Evento> eventos, int idEvento) throws IOException {
        // Verifica si el evento existe
    //    if (eventos.containsKey(idEvento)) {
            // Elimina el evento del mapa
    //        eventos.remove(idEvento);
    //        System.out.println("Evento con ID " + idEvento + " ha sido eliminado.");

            // Actualiza el archivo EVENTOS.TXT
            // Aquí, necesitas guardar todos los eventos restantes en el archivo.
    //        ArchivoEventos.guardarEventoEnArchivo(eventos);
    //    } else {
    //        System.out.println("No existe un evento con el ID proporcionado.");
    //    }
    //} 
}