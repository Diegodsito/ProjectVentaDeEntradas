package proyectosia9;

  public class Usuario {
      private String nombre;
      private String correoElectronico;
      private int edad;
      private String interes;

      // Constructores;

      public Usuario(){
          this.nombre = "";
          this.correoElectronico = "";
      }
      public Usuario(String nombre, String correoElectronico)
      {
          this.nombre = nombre;
          this.correoElectronico = correoElectronico;
          this.edad = 0;
          this.interes = "Ninguno";
          
      }
      
      // getters y setters;
      public String getNombre() {
          return nombre;
      }

      public void setNombre(String nombre) {
          this.nombre = nombre;
      }

      public String getCorreoElectronico() {
          return correoElectronico;
      }

      public void setCorreoElectronico(String correoElectronico) {
          this.correoElectronico = correoElectronico;
      }
      public int getEdad() {
          return edad;
      }
      public void setEdad(int edad) {
          this.edad = edad;
      }
      public String getInteres() {
          return interes;
      }
      public void setInteres(String interes) {
          this.interes = interes;
      }


  }
