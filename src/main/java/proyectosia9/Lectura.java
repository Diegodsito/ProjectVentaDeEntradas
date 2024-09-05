package proyectosia9;


import java.io.*;
import java.util.*;

public class Lectura
{
  public static void leer_archivo(List<Evento> lista)
  {
    BufferedReader lector = null;
    try
    {
      lector = new BufferedReader(new FileReader("src/main/java/proyectosia9/ArchivosCSV/EVENTOS.txt"));
      String linea = "";
      while((linea = lector.readLine()) != null) 
      {
        String[] bloques = linea.split(";"); 
        if (bloques.length == 8)
        {
          String nombre = bloques[0];
          String tipo = bloques[1];
          String ubicacion = bloques[2];
          String fecha = bloques[3];
          int capacidadTotal = Integer.parseInt(bloques[4]);
          int entradasDisponibles = Integer.parseInt(bloques[5]);
          String topico = bloques[6];
          int id = Integer.parseInt(bloques[7]);
          lista.add(new Evento(nombre, tipo, ubicacion, fecha, capacidadTotal, entradasDisponibles, topico, id));
        }
      }
    } catch(IOException e)
    {
      System.out.println("Error al leer el archivo: " + e.getMessage());
    } finally
    {
      if (lector != null)
      {
        try
        {
          lector.close();
        } catch(IOException e)
        {
          System.out.println("Error al cerrar el archivo: " + e.getMessage());
        }
      }
    }
  }
}









































/*package proyectosia9;
import java.util.*;


public class Lectura
{
  private static void leer_archivo(List<Evento> lista)
  {
  try
    {
    BufferedReader lector = new BufferedReader(new FileReader("java/ArchivosCSV/Eventos.txt"));
    String linea = "";
    while((linea = lector.readline()) != null) 
    {
      String[] bloques = linea.split("\t"); 
      if (bloques.length == 8)
      {
        String nombre = bloques[0];
        String tipo = bloques[1];
        String ubicacion = bloques[2];
        String fecha = bloques[3];
        int capacidadTotal = Integer.parseInt(bloques[4]);
        int entradasDisponibles = Integer.parseInt(bloques[5]);
        String topico = bloques[6];
        int id = Integer.parseInt(bloques[7]);
        lista.add(new Evento(nombre, tipo, ubicacion, fecha, capacidadTotal, entradasDisponibles, topico, id));
        
      }
      lector.close();  
      } catch(IOException e)
    {
        System.out.println("Error al leer el archivo" + e.getMessage());
      }
  }
}

*/

