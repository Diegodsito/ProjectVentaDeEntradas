package proyectosia9;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.BorderLayout;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class Evento {

    private int id;

    private String tipo;

    private String nombre;

    private String topico;

    private String ubicacion;

    private String fecha;

    private int capacidadTotal;

    private int entradasDisponibles;

    private List<Compra> compras;

    private float recaudacion;

    public Evento() {
        this.nombre = "";
        this.id = 0;
        this.tipo = "";
        this.ubicacion = "";
        this.fecha = "";
        this.capacidadTotal = 0;
        this.entradasDisponibles = 0;
        this.topico = "";
    }

    public Evento(String nombre, String tipo, String ubicacion, String fecha, int capacidadTotal, int entradasDisponibles, String topico, int id) {
        this.nombre = nombre;
        this.id = id;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.capacidadTotal = capacidadTotal;
        this.entradasDisponibles = entradasDisponibles;
        this.topico = topico;
        this.compras = new ArrayList<Compra>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public int getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(int entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }

    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public void agregarCompra(Compra compra) {
        this.compras.add(compra);
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void mostrarInfoEvento() {
        System.out.println("");
        System.out.println("Evento : " + nombre);
        System.out.println("Ubicacion : " + ubicacion);
        System.out.println("Fecha : " + fecha);
        System.out.println("Capacidad Total : " + capacidadTotal);
        System.out.println("Entradas Disponibles : " + entradasDisponibles);
        System.out.println("Topico : " + topico);
    }

    public void mostrarInfoEvento(int idEvento) {
        if (id == idEvento) {
            System.out.println("");
            System.out.println("Evento : " + nombre);
            System.out.println("Ubicacion : " + ubicacion);
            System.out.println("Fecha : " + fecha);
            System.out.println("Capacidad Total : " + capacidadTotal);
            System.out.println("Entradas Disponibles : " + entradasDisponibles);
            System.out.println("Topico : " + topico);
        }
    }

    public void mostrarInfoEvento(String topico) {
        if (this.topico.equals(topico)) {
            System.out.println("");
            System.out.println("Evento : " + nombre);
            System.out.println("Ubicacion : " + ubicacion);
            System.out.println("Fecha : " + fecha);
            System.out.println("Capacidad Total : " + capacidadTotal);
            System.out.println("Entradas Disponibles : " + entradasDisponibles);
            System.out.println("Topico : " + topico);
        }
    }

    public void buscarEvento(int idEvento) {
        if (this.id == idEvento) {
            mostrarInfoEvento(idEvento);
        } else {
            System.out.println("No se encontro el evento con ID: " + idEvento);
        }
    }

    public void buscarEvento(String topico) {
        if (this.topico.equalsIgnoreCase(topico)) {
            mostrarInfoEvento(topico);
        }
    }

    public void imprimirCompras() {
        if (compras.isEmpty()) {
            System.out.println("No hay compras registradas");
            return;
        }
        for (Compra compra : compras) {
            System.out.println("Compra: " + compra.getIdCompra());
            System.out.println("Usuario: " + compra.getUsuario().getNombre());
            System.out.println("Edad: " + compra.getUsuario().getEdad());
            System.out.println("--------------------");
        }
    }

    public void eliminarCompra(String idCompra) {
    if (compras.isEmpty()) {
        throw new IllegalArgumentException("No hay compras registradas.");
    }
    
    Iterator<Compra> iterator = compras.iterator();
    boolean compraEliminada = false;
    
    while (iterator.hasNext()) {
        Compra compra = iterator.next();
        if (compra.getIdCompra().equals(idCompra)) {
            iterator.remove();
            compraEliminada = true;
            break;
        }
    }
    
    if (!compraEliminada) {
        throw new IllegalArgumentException("No se encontró la compra con ID: " + idCompra);
    }
}

    public float calcularRecaudacion() {
        int entradasVendidas = this.capacidadTotal - this.entradasDisponibles;
        if (this.tipo.equals("Charla")) {
            recaudacion = 1000 * entradasVendidas;
        } else {
            recaudacion = 1200 * entradasVendidas;
        }
        return recaudacion;
    }
    public void imprimirCompras2() {
    JFrame ventanaCompras = new JFrame("Compras del Evento: " + getNombre());
    ventanaCompras.setSize(400, 400);
    ventanaCompras.setLayout(new BorderLayout());

    // Crear una tabla para listar las compras
    String[] columnas = {"ID Compra", "Nombre Usuario", "Edad", "Correo"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
    
    if (compras.isEmpty()) {
        modelo.addRow(new Object[]{"N/A", "No hay compras registradas", "N/A", "N/A"});
    } else {
        for (Compra compra : compras) {
            Usuario usuario = compra.getUsuario();
            if (usuario != null) { // Verifica que el usuario no sea nulo
                modelo.addRow(new Object[]{
                    compra.getIdCompra(),
                    usuario.getNombre(),
                    usuario.getEdad(),
                    usuario.getCorreoElectronico()
                });
            } else {
                System.out.println("Compra sin usuario asociado: " + compra.getIdCompra());
            }
        }
    }

    JTable tablaCompras = new JTable(modelo);
    JScrollPane scrollPane = new JScrollPane(tablaCompras);
    ventanaCompras.add(scrollPane, BorderLayout.CENTER);

    // Botón para cerrar la ventana
    JButton btnCerrar = new JButton("Cerrar");
    btnCerrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaCompras.dispose();
        }
    });
    ventanaCompras.add(btnCerrar, BorderLayout.SOUTH);

    ventanaCompras.setVisible(true);
}}
    
    


