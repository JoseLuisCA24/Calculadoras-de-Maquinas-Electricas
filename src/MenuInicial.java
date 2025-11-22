import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial extends JFrame {
    public MenuInicial() {
        setTitle("Maquinas Electricas");
        setSize(1000, 800);  // Tamaño aumentado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color colorMargen = new Color(48, 156, 56);

        // Crear márgenes verdes
        JPanel margenSuperior = new JPanel();
        margenSuperior.setBackground(colorMargen);
        margenSuperior.setPreferredSize(new Dimension(1000, 10));
        add(margenSuperior, BorderLayout.NORTH);

        JPanel margenInferior = new JPanel();
        margenInferior.setBackground(colorMargen);
        margenInferior.setPreferredSize(new Dimension(1000, 10));
        add(margenInferior, BorderLayout.SOUTH);

        JPanel margenIzquierdo = new JPanel();
        margenIzquierdo.setBackground(colorMargen);
        margenIzquierdo.setPreferredSize(new Dimension(10, 800));
        add(margenIzquierdo, BorderLayout.WEST);

        JPanel margenDerecho = new JPanel();
        margenDerecho.setBackground(colorMargen);
        margenDerecho.setPreferredSize(new Dimension(10, 800));
        add(margenDerecho, BorderLayout.EAST);

        // Panel principal con imagen de fondo
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Panel para el título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.WHITE);
        JLabel titulo = new JLabel("MAQUINAS ELECTRICAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        panelTitulo.add(titulo);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        panelBotones.setBackground(Color.WHITE);

        // Botones
        JButton botonCalculadora = new JButton("Calculadora de Circuitos Magneticos");
        estiloBoton(botonCalculadora);
        botonCalculadora.addActionListener(e -> {
            new Circuito().setVisible(true);
            dispose();
        });

        JButton botonTransformador = new JButton("Calculadora de Circuito Equivalente del Transformador");
        estiloBoton(botonTransformador);
        botonTransformador.addActionListener(e -> {
            new Transformador().setVisible(true);
            dispose();
        });
        JButton botonMotorFP = new JButton("Calculadora de Corriente y Potencia en Motor de Fase Partida");
        estiloBoton(botonMotorFP);
        botonMotorFP.addActionListener(e -> {
            new MotorFP().setVisible(true);
            dispose();
        });

        JButton botonMotorVB = new JButton("Calculadora de Vacio y Rotor Bloqueado, para motor de 2.5 HP, 4 polos");
        estiloBoton(botonMotorVB);
        botonMotorVB.addActionListener(e -> {
            new MotorVB().setVisible(true);
            dispose();
        });

        JButton botonSalir = new JButton("Salir");
        estiloBoton(botonSalir);
        botonSalir.addActionListener(e -> System.exit(0));

        panelBotones.add(botonCalculadora);
        panelBotones.add(botonTransformador);
        panelBotones.add(botonMotorFP);
        panelBotones.add(botonMotorVB);
        panelBotones.add(botonSalir);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Panel inferior con créditos e imágenes
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(Color.WHITE);
        
        // Créditos
        JLabel creditos = new JLabel("Hecho por: Jose Luis Cantu Alvarado 2055195 IEA, Edgar Ivan Molinar Escudero 2050215 IMTC", SwingConstants.CENTER);
        creditos.setFont(new Font("Arial", Font.PLAIN, 14));
        panelInferior.add(creditos, BorderLayout.CENTER);

        // Panel para imágenes
        JPanel panelImagenes = new JPanel(new FlowLayout());
        panelImagenes.setBackground(Color.WHITE);
        
        // Imagen FIME
        ImageIcon iconoFime = new ImageIcon(getClass().getResource("/fime.png"));
        Image imagenFime = iconoFime.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel labelFime = new JLabel(new ImageIcon(imagenFime));
        panelImagenes.add(labelFime);

        // Imagen UANL
        ImageIcon iconoUanl = new ImageIcon(getClass().getResource("/uanl.png"));
        Image imagenUanl = iconoUanl.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel labelUanl = new JLabel(new ImageIcon(imagenUanl));
        panelImagenes.add(labelUanl);

        panelInferior.add(panelImagenes, BorderLayout.SOUTH);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private void estiloBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setBackground(new Color(48, 156, 56));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuInicial menu = new MenuInicial();
            menu.setVisible(true);
        });
    }
}