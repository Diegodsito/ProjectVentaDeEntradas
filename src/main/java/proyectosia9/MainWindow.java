package proyectosia9;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;
import java.awt.GridLayout;
import java.io.IOException;
import java.awt.BorderLayout;


public class MainWindow extends JFrame {

    private Map<Integer, Evento> eventos;

    private Usuario usuarioIngresado = new Usuario();

    public MainWindow(Map<Integer, Evento> eventos) {
        this.eventos = eventos;
        setTitle("Sistema de venta de entradas");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        int buttonWidth = 200;
        int buttonHeight = 30;
        int centerX = (getWidth() - buttonWidth) / 2;
        JButton btnEventos = new JButton("Ver eventos disponibles");
        btnEventos.setBounds(centerX, 50, buttonWidth, buttonHeight);
        add(btnEventos);
        btnEventos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarListaEventos();
            }
        });
        JButton btnBuscar = new JButton("Buscar Eventos");
        btnBuscar.setBounds(centerX, 100, buttonWidth, buttonHeight);
        add(btnBuscar);
        btnBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaBusqueda();
            }
        });
        JButton btnCancelar = new JButton("Cancelar compra");
        btnCancelar.setBounds(centerX, 150, buttonWidth, buttonHeight);
        add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarListaEventosParaCancelarCompra();
            }
        });
        JButton btnGEventos = new JButton("Gestion de Eventos");
        btnGEventos.setBounds(centerX, 200, buttonWidth, buttonHeight);
        add(btnGEventos);
        btnGEventos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaGestionEventos();
            }
        });
        JButton btnGenerarReporte = new JButton("Generar reporte");
        btnGenerarReporte.setBounds(centerX, 300, buttonWidth, buttonHeight);
        add(btnGenerarReporte);
        btnGenerarReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamada a la función para generar el reporte
                GenerarReporte.generarReporte(eventos);
            }
        });
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(centerX, 250, buttonWidth, buttonHeight);
        add(btnSalir);
        btnSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void mostrarVentanaBusqueda() {
        JFrame ventanaBusqueda = new JFrame("Buscar Evento");
        ventanaBusqueda.setSize(400, 200);
        ventanaBusqueda.setLayout(null);
        JButton btnBuscarID = new JButton("Buscar por ID");
        btnBuscarID.setBounds(50, 50, 130, 30);
        ventanaBusqueda.add(btnBuscarID);
        btnBuscarID.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String inputID = JOptionPane.showInputDialog("Ingrese el ID del evento:");
                if (inputID != null && !inputID.trim().isEmpty()) {
                    buscarEventoPorID(Integer.parseInt(inputID));
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un ID válido.");
                }
            }
        });
        JButton btnBuscarTopico = new JButton("Buscar por Topico");
        btnBuscarTopico.setBounds(200, 50, 130, 30);
        ventanaBusqueda.add(btnBuscarTopico);
        btnBuscarTopico.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String inputTopico = JOptionPane.showInputDialog("Ingrese el topico del evento:");
                if (inputTopico != null && !inputTopico.trim().isEmpty()) {
                    buscarEventoPorTopico(inputTopico);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un topico valido.");
                }
            }
        });
        ventanaBusqueda.setVisible(true);
    }

    private void buscarEventoPorID(int idEvento) {
        Evento evento = eventos.get(idEvento);
        if (evento != null) {
            String infoEvento = "Evento: " + evento.getNombre() + "\n" + "Ubicacion: " + evento.getUbicacion() + "\n" + "Fecha: " + evento.getFecha() + "\n" + "Capacidad Total: " + evento.getCapacidadTotal() + "\n" + "Entradas Disponibles: " + evento.getEntradasDisponibles() + "\n" + "Topico: " + evento.getTopico();
            JOptionPane.showMessageDialog(null, infoEvento, "Informacion del Evento", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro ningun evento con ese ID");
        }
    }

    private void buscarEventoPorTopico(String topico) {
        StringBuilder eventosEncontrados = new StringBuilder();
        boolean encontrado = false;
        for (Evento evento : eventos.values()) {
            if (evento.getTopico().equalsIgnoreCase(topico)) {
                eventosEncontrados.append("Evento: ").append(evento.getNombre()).append("\n").append("Ubicacion: ").append(evento.getUbicacion()).append("\n").append("Fecha: ").append(evento.getFecha()).append("\n").append("Capacidad Total: ").append(evento.getCapacidadTotal()).append("\n").append("Entradas Disponibles: ").append(evento.getEntradasDisponibles()).append("\n").append("Tópico: ").append(evento.getTopico()).append("\n\n");
                encontrado = true;
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, eventosEncontrados.toString(), "Eventos encontrados por Topico", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro ningun evento con ese topico.");
        }
    }

    private void mostrarListaEventos() {
        JFrame listaEventosFrame = new JFrame("Seleccionar un Evento");
        listaEventosFrame.setSize(400, 300);
        listaEventosFrame.setLayout(null);
        String[] nombresEventos = eventos.values().stream().map( evento->evento.getId() + ")  " + evento.getNombre()).toArray(String[]::new);
        JList<String> listaEventos = new JList(nombresEventos);
        JScrollPane scrollPane = new JScrollPane(listaEventos);
        scrollPane.setBounds(10, 10, 360, 200);
        listaEventosFrame.add(scrollPane);
        JButton btnVerInfo = new JButton("Ver informacion del evento");
        btnVerInfo.setBounds(100, 220, 200, 30);
        listaEventosFrame.add(btnVerInfo);
        btnVerInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaEventos.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedEventText = listaEventos.getSelectedValue();
                    int idEvento = Integer.parseInt(selectedEventText.split("\\)")[0].trim());
                    Evento eventoSeleccionado = eventos.get(idEvento);
                    if (eventoSeleccionado != null) {
                        mostrarInfoEvento(eventoSeleccionado);
                    }
                } else {
                    JOptionPane.showMessageDialog(listaEventosFrame, "Seleccione un evento de la lista.");
                }
            }
        });
        listaEventosFrame.setVisible(true);
    }

    private void mostrarInfoEvento(Evento evento) {
        JFrame infoFrame = new JFrame("Informacion del Evento");
        infoFrame.setSize(400, 300);
        infoFrame.setLayout(null);
        JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 10, 360, 150);
        String duracion;
        if (evento.getTipo().equals("Seminario")) {
            duracion = "Duracion: 70 minutos";
        } else {
            duracion = "Duracion: 35 minutos"; // Suponiendo que es Charla
        }
        textArea.setText("Evento: " + evento.getNombre() + "\n" + "Entradas disponibles: " + evento.getEntradasDisponibles() + "\n" + "Fecha: " + evento.getFecha() + "\n" + "Ubicacion: " + evento.getUbicacion() + "\n" +  duracion + "\n");
        infoFrame.add(textArea);
        JButton btnComprar = new JButton("Comprar entrada");
        btnComprar.setBounds(100, 200, 200, 30);
        infoFrame.add(btnComprar);
        btnComprar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (evento.getEntradasDisponibles() > 0) {
                    realizarCompra(evento);
                } else {
                    JOptionPane.showMessageDialog(infoFrame, "Lo sentimos, no hay más entradas disponibles.");
                }
            }
        });
        infoFrame.setVisible(true);
    }

    private void realizarCompra(Evento evento) {
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        usuarioIngresado.setNombre(nombre);
        String correo = JOptionPane.showInputDialog("Ingrese su correo electronico:");
        usuarioIngresado.setCorreoElectronico(correo);
        String StrEdad = JOptionPane.showInputDialog("Ingrese su edad:");
        int edad = Integer.parseInt(StrEdad);
        usuarioIngresado.setEdad(edad);
        String idCompra = "ID_" + new Random().nextInt(1000);
        Compra compra = new Compra(idCompra, usuarioIngresado, 0);
        evento.agregarCompra(compra);
        int entradasRestantes = evento.getEntradasDisponibles() - 1;
        evento.setEntradasDisponibles(entradasRestantes);
        JOptionPane.showMessageDialog(this, "Compra realizada con exito!");
        mostrarVoucherCompra(compra);
    }

    public static void main(String[] args) {
        Map<Integer, Evento> eventos = new HashMap();
        Lectura.leer_archivo(eventos);
        MainWindow window = new MainWindow(eventos);
        window.setVisible(true);
    }
    private void mostrarVoucherCompra(Compra compra) {
    StringBuilder voucher = new StringBuilder();
    voucher.append("ID de Compra: ").append(compra.getIdCompra()).append(" (Por favor guarda tu ID de compra)\n");
    voucher.append("Usuario: ").append(compra.getUsuario().getNombre()).append("\n");
    voucher.append("Edad: ").append(compra.getUsuario().getEdad()).append("\n");
    
    JOptionPane.showMessageDialog(null, voucher.toString(), "Voucher de Compra", JOptionPane.INFORMATION_MESSAGE);
}

    private void mostrarVentanaGestionEventos() {
        JFrame ventanaGestion = new JFrame("Gestion de Eventos");
        ventanaGestion.setSize(400, 400);
        ventanaGestion.setLayout(new FlowLayout());
        JLabel lblTitulo = new JLabel("Gestion de Eventos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        ventanaGestion.add(lblTitulo);
        JButton btnCrearEvento = new JButton("Crear Evento");
        JButton btnEliminarEvento = new JButton("Eliminar Evento");
        JButton btnVerParticipantes = new JButton("Ver Participantes");
        JButton btnVolver = new JButton("Volver");
        ventanaGestion.add(btnCrearEvento);
        ventanaGestion.add(btnEliminarEvento);
        ventanaGestion.add(btnVerParticipantes);
        ventanaGestion.add(btnVolver);
        btnCrearEvento.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaCrearEvento();
            }
        });
        btnEliminarEvento.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaEliminarEvento();
            }
        });
        btnVerParticipantes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaParticipantes();
                
            }
        });
        btnVolver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaGestion.dispose();
            }
        });
        ventanaGestion.setVisible(true);
    }

    private void mostrarVentanaCrearEvento() {
        JFrame ventanaCrear = new JFrame("Crear Evento");
        ventanaCrear.setSize(400, 300);
        ventanaCrear.setLayout(new GridLayout(8, 2));
        JTextField txtNombre = new JTextField();
        JTextField txtTipo = new JTextField();
        JTextField txtUbicacion = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextField txtCapacidadTotal = new JTextField();
        JTextField txtEntradasDisponibles = new JTextField();
        JTextField txtTopico = new JTextField();
        ventanaCrear.add(new JLabel("Nombre:"));
        ventanaCrear.add(txtNombre);
        ventanaCrear.add(new JLabel("Tipo (Charla/Seminario):"));
        ventanaCrear.add(txtTipo);
        ventanaCrear.add(new JLabel("Ubicación:"));
        ventanaCrear.add(txtUbicacion);
        ventanaCrear.add(new JLabel("Fecha:"));
        ventanaCrear.add(txtFecha);
        ventanaCrear.add(new JLabel("Capacidad Total:"));
        ventanaCrear.add(txtCapacidadTotal);
        ventanaCrear.add(new JLabel("Entradas Disponibles:"));
        ventanaCrear.add(txtEntradasDisponibles);
        ventanaCrear.add(new JLabel("Topico:"));
        ventanaCrear.add(txtTopico);
        JButton btnGuardar = new JButton("Guardar Evento");
        ventanaCrear.add(btnGuardar);
        ventanaCrear.setVisible(true);
        btnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = txtNombre.getText();
                    String tipo = txtTipo.getText();
                    String ubicacion = txtUbicacion.getText();
                    String fecha = txtFecha.getText();
                    int capacidadTotal;
                    int entradasDisponibles;
                    String topico = txtTopico.getText();
                    if (!tipo.equalsIgnoreCase("Charla") && !tipo.equalsIgnoreCase("Seminario")) {
                        throw new TipoEventoInvalidoException("Error: El tipo de evento debe ser 'Charla' o 'Seminario'.");
                    }
                    try {
                        capacidadTotal = Integer.parseInt(txtCapacidadTotal.getText());
                        entradasDisponibles = Integer.parseInt(txtEntradasDisponibles.getText());
                    } catch (NumberFormatException ex) {
                        throw new EntradaInvalidaException("Capacidad total y entradas deben ser números enteros.");
                    }
                    if (capacidadTotal < 0 || entradasDisponibles < 0) {
                        throw new CapacidadInvalidaException("La capacidad total y las entradas disponibles deben ser no negativas.");
                    }
                    if (entradasDisponibles > capacidadTotal) {
                        throw new CapacidadInvalidaException("Las entradas disponibles no pueden ser mayores que la capacidad total.");
                    }
                    int idLindo = eventos.size() + 1;
                    Evento nuevoEvento = new Evento(nombre, tipo, ubicacion, fecha, capacidadTotal, entradasDisponibles, topico, idLindo);
                    ArchivoEventos.guardarEventoEnArchivo(eventos, nuevoEvento, nuevoEvento.getId());
                    JOptionPane.showMessageDialog(null, "Evento creado con éxito.");
                    ventanaCrear.dispose();
                } catch (TipoEventoInvalidoException | EntradaInvalidaException | CapacidadInvalidaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar el evento en el archivo: " + ex.getMessage());
                }
            }
        });
    }

    private void mostrarVentanaEliminarEvento() {
        JFrame ventanaEliminar = new JFrame("Eliminar Evento");
        ventanaEliminar.setSize(300, 150);
        ventanaEliminar.setLayout(new GridLayout(2, 2));
        JLabel lblId = new JLabel("ID del evento a eliminar:");
        JTextField txtId = new JTextField();
        ventanaEliminar.add(lblId);
        ventanaEliminar.add(txtId);
        JButton btnEliminar = new JButton("Eliminar");
        ventanaEliminar.add(btnEliminar);
        ventanaEliminar.setVisible(true);
        btnEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEvento = Integer.parseInt(txtId.getText());
                    if (eventos.containsKey(idEvento)) {
                        ArchivoEventos.eliminarEventoPorId(eventos, idEvento);
                        JOptionPane.showMessageDialog(null, "Evento eliminado con exito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe un evento con ese ID.");
                    }
                    ventanaEliminar.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID invalido. Debe ser un numero.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el evento del archivo: " + ex.getMessage());
                }
            }
        });
    }
    private void mostrarListaEventosParaCancelarCompra() {
    JFrame listaEventosFrame = new JFrame("Seleccionar un Evento para Cancelar Compra");
    listaEventosFrame.setSize(400, 300);
    listaEventosFrame.setLayout(null);

    String[] nombresEventos = eventos.values().stream()
            .map(evento -> evento.getId() + ")  " + evento.getNombre())
            .toArray(String[]::new);

    JList<String> listaEventos = new JList<>(nombresEventos);
    JScrollPane scrollPane = new JScrollPane(listaEventos);
    scrollPane.setBounds(10, 10, 360, 200);
    listaEventosFrame.add(scrollPane);

    JButton btnEliminarCompra = new JButton("Eliminar compra");
    btnEliminarCompra.setBounds(100, 220, 200, 30);
    listaEventosFrame.add(btnEliminarCompra);
    
    btnEliminarCompra.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = listaEventos.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedEventText = listaEventos.getSelectedValue();
                int idEvento = Integer.parseInt(selectedEventText.split("\\)")[0].trim());
                Evento eventoSeleccionado = eventos.get(idEvento);
                
                if (eventoSeleccionado != null) {
                    if (!eventoSeleccionado.getCompras().isEmpty()) {
                        String idCompra = JOptionPane.showInputDialog(listaEventosFrame, "Ingrese el ID de la compra a eliminar:\nEj. (ID_941)");
                        if (idCompra != null && !idCompra.trim().isEmpty()) {
                            try {
                                eventoSeleccionado.eliminarCompra(idCompra);
                                JOptionPane.showMessageDialog(listaEventosFrame, "Compra eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            } catch (IllegalArgumentException ex) {
                                JOptionPane.showMessageDialog(listaEventosFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(listaEventosFrame, "ID de compra inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(listaEventosFrame, "No hay compras para este evento.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(listaEventosFrame, "Seleccione un evento de la lista.");
            }
        }
    });

    listaEventosFrame.setVisible(true);
}
    private void mostrarVentanaParticipantes() {
    JFrame ventanaEventos = new JFrame("Seleccionar un Evento para Ver Participantes");
    ventanaEventos.setSize(400, 300);
    ventanaEventos.setLayout(new BorderLayout());

    // Crear un arreglo de los nombres de los eventos para mostrar en la lista
    String[] nombresEventos = eventos.values().stream()
            .map(evento -> evento.getId() + ") " + evento.getNombre())
            .toArray(String[]::new);

    // Crear una lista con los nombres de los eventos
    JList<String> listaEventos = new JList<>(nombresEventos);
    JScrollPane scrollPane = new JScrollPane(listaEventos);
    ventanaEventos.add(scrollPane, BorderLayout.CENTER);

    // Botón para ver los participantes del evento seleccionado
    JButton btnVerParticipantes = new JButton("Ver Participantes");
    ventanaEventos.add(btnVerParticipantes, BorderLayout.SOUTH);

    btnVerParticipantes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = listaEventos.getSelectedIndex();
            if (selectedIndex != -1) {
                // Obtener el ID del evento seleccionado
                String selectedEventText = listaEventos.getSelectedValue();
                int idEvento = Integer.parseInt(selectedEventText.split("\\)")[0].trim()); // Cambiado a "\\)" para evitar confusión

                // Obtener el evento correspondiente por su ID
                Evento eventoSeleccionado = eventos.get(idEvento);

                if (eventoSeleccionado != null) {
                    // Mostrar los participantes del evento seleccionado
                    eventoSeleccionado.imprimirCompras2();

                    double recaudacion = eventoSeleccionado.calcularRecaudacion();
                    JOptionPane.showMessageDialog(ventanaEventos, "Recaudación: " + recaudacion);
                    ventanaEventos.dispose();  // Cerrar la ventana de selección
                } else {
                    JOptionPane.showMessageDialog(ventanaEventos, "No se encontró el evento seleccionado.");
                }
            } else {
                JOptionPane.showMessageDialog(ventanaEventos, "Seleccione un evento de la lista.");
            }
        }
    });

    ventanaEventos.setVisible(true);
}
  
   
}
