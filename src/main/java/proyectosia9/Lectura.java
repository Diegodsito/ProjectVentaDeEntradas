package proyectosia9;
import java.io.*;
import java.util.*;


public class Lectura {
    public static void leer_archivo(Map<Integer, Evento> eventos) {
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new FileReader("src/main/java/proyectosia9/ArchivosCSV/EVENTOS.txt"));
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] bloques = linea.split(";");
                if (bloques.length == 8) {
                    String nombre = bloques[0];
                    String tipo = bloques[1];
                    String ubicacion = bloques[2];
                    String fecha = bloques[3];
                    int capacidadTotal = Integer.parseInt(bloques[4]);
                    int entradasDisponibles = Integer.parseInt(bloques[5]);
                    String topico = bloques[6];
                    int id = Integer.parseInt(bloques[7]);
                    Evento evento = new Evento(nombre, tipo, ubicacion, fecha, capacidadTotal, entradasDisponibles, topico, id);
                    eventos.put(id, evento);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }
}
