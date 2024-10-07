
package proyectosia9;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;



public class GenerarReporte {

    public static void generarReporte(Map<Integer, Evento> eventos) {
    // Ruta específica donde se guardará el archivo
    String rutaArchivo = "src/main/java/proyectosia9/ArchivosCSV/Reporte.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
        // Encabezado del reporte
        writer.write("=========================================================");
        writer.newLine();
        writer.write("                 REPORTE DE EVENTOS                       ");
        writer.newLine();
        writer.write("=========================================================");
        writer.newLine();
        writer.write(String.format("%-5s %-30s %-20s %-10s %-15s %-15s","ID", "Nombre", "Tópico", "Entradas", "Compradores","Recaudación"));
        writer.newLine();
        writer.write("---------------------------------------------------------");
        writer.newLine();

          for (Map.Entry<Integer, Evento> entry : eventos.entrySet()) {
            Evento evento = entry.getValue();
            int cantidadCompradores = evento.getCompras().size(); // Número de compradores
            double recaudacionTotal = evento.calcularRecaudacion();
                    
            // Limitar la longitud del nombre del evento
            String nombreEvento = evento.getNombre();
            if (nombreEvento.length() > 30) {
                nombreEvento = nombreEvento.substring(0, 27) + "..."; // Truncar y agregar "..."
            }

           
            // Escribir la línea del evento
            writer.write(String.format("%-5d %-30s %-20s %-10d %-15d %-15.2f",
                    evento.getId(),
                    nombreEvento,
                    evento.getTopico(),
                    evento.getEntradasDisponibles(),
                    cantidadCompradores, // Mostrar cantidad de compradores
                    recaudacionTotal)); // Mostrar recaudación total
            writer.newLine();
        }

        writer.write("=========================================================");
        writer.newLine();
        System.out.println("Reporte generado exitosamente en " + rutaArchivo);

    } catch (IOException e) {
        System.out.println("Error al generar el reporte: " + e.getMessage());
    }
}
}