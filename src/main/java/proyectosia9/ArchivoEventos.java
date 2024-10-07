package proyectosia9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

public class ArchivoEventos {

    public static void guardarEventoEnArchivo(Map<Integer, Evento> eventos, Evento nuevoEvento, int idEvento) throws IOException {
        eventos.put(idEvento, nuevoEvento);
        try (FileWriter escritor = new FileWriter("src/main/java/proyectosia9/ArchivosCSV/EVENTOS.txt", true)) {
            escritor.write(nuevoEvento.getNombre() + ";" + nuevoEvento.getTipo() + ";" + nuevoEvento.getUbicacion() + ";" + nuevoEvento.getFecha() + ";" + nuevoEvento.getEntradasDisponibles() + ";" + nuevoEvento.getEntradasDisponibles() + ";" + nuevoEvento.getTopico() + ";" + String.format("%04d", idEvento) + "\n");
        }
    }

    public static void eliminarEventoPorId(Map<Integer, Evento> eventos, int idEvento) throws IOException { 
        if (eventos.containsKey(idEvento)) {
            eventos.remove(idEvento);
            File archivo = new File("src/main/java/proyectosia9/ArchivosCSV/EVENTOS.txt");
            File archivoTemporal = new File("src/main/java/proyectosia9/ArchivosCSV/EVENTOS_TEMP.txt");
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo));
                FileWriter escritor = new FileWriter(archivoTemporal)) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] partes = linea.split(";");
                    if (partes.length < 8) {
                        System.out.println("Línea mal formada: " + linea);
                        continue;
                    }
                    try {
                        int id = Integer.parseInt(partes[partes.length - 1].trim());
                        if (id != idEvento) {
                            escritor.write(linea + "\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir ID en línea: " + linea);
                    }
                }
            }
            if (archivo.delete()) {
                archivoTemporal.renameTo(archivo);
            }
        } else {
            System.out.println("El evento con ID " + idEvento + " no existe.");
        }
    }
}
