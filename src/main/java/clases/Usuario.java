package clases;

  public class Usuario {
      private String nombre;
      private String correoElectronico;

      // Constructores;

      public Usuario(){
          this.nombre = "";
          this.correoElectronico = "";
      }
      public Usuario(String nombre, String correoElectronico)
      {
          this.nombre = nombre;
          this.correoElectronico = correoElectronico;
          
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


  }
