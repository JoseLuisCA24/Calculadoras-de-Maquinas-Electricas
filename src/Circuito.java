import javax.swing.*;
import java.awt.*;

public class Circuito extends JFrame {
    private JTextField[] camposTexto = new JTextField[12]; // Campos de entrada
    private JTextArea areaResultado; // Area de texto para mostrar el resultado

    public Circuito() {
        setTitle("Circuito Magnetico");
        setSize(1520, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color colorMargen = new Color(48, 156, 56);

        // Crear los márgenes
        JPanel margenSuperior = new JPanel();
        margenSuperior.setBackground(colorMargen);
        margenSuperior.setPreferredSize(new Dimension(1520, 5));
        add(margenSuperior, BorderLayout.NORTH);

        JPanel margenInferior = new JPanel();
        margenInferior.setBackground(colorMargen);
        margenInferior.setPreferredSize(new Dimension(1520, 5));
        add(margenInferior, BorderLayout.SOUTH);

        JPanel margenIzquierdo = new JPanel();
        margenIzquierdo.setBackground(colorMargen);
        margenIzquierdo.setPreferredSize(new Dimension(5, 800));
        add(margenIzquierdo, BorderLayout.WEST);

        JPanel margenDerecho = new JPanel();
        margenDerecho.setBackground(colorMargen);
        margenDerecho.setPreferredSize(new Dimension(5, 800));
        add(margenDerecho, BorderLayout.EAST);

        // Crear JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1520, 800));
        layeredPane.setBounds(0, 0, 1520, 800);

        // Título
        JLabel titulo = new JLabel("Calculadora Circuitos Magneticos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(0, 30, 1520, 50);
        layeredPane.add(titulo, JLayeredPane.PALETTE_LAYER);

        // Integrantes
        JLabel integrantes = new JLabel("Hecho por: Jose Luis Cantu Alvarado 2055195 IEA, Edgar Ivan Molinar Escudero 2050215 IMTC", SwingConstants.CENTER);
        integrantes.setFont(new Font("Arial", Font.PLAIN, 16));
        integrantes.setBounds(0, 80, 1520, 30);
        layeredPane.add(integrantes, JLayeredPane.PALETTE_LAYER);

        // Cargar imágenes
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/imagen.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(700, 400, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(imagenEscalada));
        etiquetaImagen.setBounds(0, 150, 700, 400);

        ImageIcon iconoOriginal2 = new ImageIcon(getClass().getResource("/fime.png"));
        Image imagenEscalada2 = iconoOriginal2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen2 = new JLabel(new ImageIcon(imagenEscalada2));
        etiquetaImagen2.setBounds(200, 10, 100, 100);

        ImageIcon iconoOriginal3 = new ImageIcon(getClass().getResource("/uanl.png"));
        Image imagenEscalada3 = iconoOriginal3.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen3 = new JLabel(new ImageIcon(imagenEscalada3));
        etiquetaImagen3.setBounds(1200, 10, 80, 100);

        // Agregar imágenes
        layeredPane.add(etiquetaImagen, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(etiquetaImagen2, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(etiquetaImagen3, JLayeredPane.DEFAULT_LAYER);

        // Campos de texto - posiciones ajustadas
        String[] variables = {"L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8", "L9", "N", "I", "\u03BC_r"};
        int[][] posiciones = {
            {40, 450}, {140, 460}, {260, 465}, {380, 470}, 
            {500, 530}, {650, 530}, {580, 480}, {620, 385}, 
            {620, 295}, {250, 120}, {320, 120}, {390, 120}
        };

        for (int i = 0; i < 12; i++) {
            camposTexto[i] = new JTextField(5);
            camposTexto[i].setBounds(posiciones[i][0], posiciones[i][1], 70, 25);
            layeredPane.add(camposTexto[i], JLayeredPane.PALETTE_LAYER);

            JLabel etiqueta = new JLabel(variables[i]);
            etiqueta.setBounds(posiciones[i][0], posiciones[i][1] - 20, 70, 20);
            layeredPane.add(etiqueta, JLayeredPane.PALETTE_LAYER);
        }

        // Area de resultados
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Arial", Font.BOLD, 14));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(areaResultado);
        scrollPane.setBounds(1100, 300, 350, 250);
        layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER);

        // Botón "Calcular"
        JButton botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(800, 500, 100, 35);
        botonCalcular.addActionListener(e -> calcularResultado());
        layeredPane.add(botonCalcular, JLayeredPane.PALETTE_LAYER);

        // Botón "Regresar al Menú"
        JButton botonRegresar = new JButton("Regresar al Menu");
        botonRegresar.setBounds(800, 550, 150, 35);
        botonRegresar.addActionListener(e -> {
            new MenuInicial().setVisible(true);
            dispose();
        });
        layeredPane.add(botonRegresar, JLayeredPane.PALETTE_LAYER);

        add(layeredPane, BorderLayout.CENTER);
    }

    private void calcularResultado() {
        try {
            // Obtener los valores de los campos de texto
            double L1 = Double.parseDouble(camposTexto[0].getText());
            double L2 = Double.parseDouble(camposTexto[1].getText());
            double L3 = Double.parseDouble(camposTexto[2].getText());
            double L4 = Double.parseDouble(camposTexto[3].getText());
            double L5 = Double.parseDouble(camposTexto[4].getText());
            double L6 = Double.parseDouble(camposTexto[5].getText());
            double L7 = Double.parseDouble(camposTexto[6].getText());
            double L8 = Double.parseDouble(camposTexto[7].getText());
            double L9 = Double.parseDouble(camposTexto[8].getText());
            double N = Double.parseDouble(camposTexto[9].getText());
            double I = Double.parseDouble(camposTexto[10].getText());
            double \u03BCr = Double.parseDouble(camposTexto[11].getText());

            // Calcular las áreas (A1 a A7)
            double A1 = (L1 * L6) / 1000;
            double A2 = (L9 * L6) / 1000;
            double A3 = (L1 * L6) / 1000;
            double A4 = (L5 * L6) / 1000;
            double A5 = (L6 * L7) / 1000;
            double A6 = (L6 * L7) / 1000;
            double A7 = (L3 * L6) / 1000;

            // Calcular las reluctancias (R1 a R7)
            double \u03BC0 = 4 * Math.PI * 1e-7; // Permeabilidad magnética del vacío
            double R1 = ((L9 * 0.5) + L8 + (L7 * 0.5)) / (\u03BCr * \u03BC0 * A1);
            double R2 = ((L1 * 0.5) + L2 + (L3 * 0.5)) / (\u03BCr * \u03BC0 * A2);
            double R3 = ((L3 * 0.5) + L4 + (L5 * 0.5)) / (\u03BCr * \u03BC0 * A3);
            double R4 = ((L9 * 0.5) + L8 + (L7 * 0.5)) / (\u03BCr * \u03BC0 * A4);
            double R5 = ((L3 * 0.5) + L4 + (L5 * 0.5)) / (\u03BCr * \u03BC0 * A5);
            double R6 = ((L1 * 0.5) + L2 + (L3 * 0.5)) / (\u03BCr * \u03BC0 * A6);
            double R7 = ((L9 * 0.5) + L8 + (L7 * 0.5)) / (\u03BCr * \u03BC0 * A7);

            // Calcular las reluctancias equivalentes (Rq1 a Rq4)
            double Rq1 = R1 + R2 + R6;
            double Rq2 = R3 + R4 + R5;
            double Rq3 = (R7 * Rq2) / (R7 + Rq2);
            double Rq4 = Rq3 + Rq1;

            // Calcular la fuerza magnetomotriz (F)
            double F = \u03BCr * I;

            // Calcular los flujos magnéticos (Ot, Ox, Oy, Oz)
            double \u00D8t = F / Rq4;
            double \u00D8x = \u00D8t;
            double \u00D8y = (Rq2 / (R7 + Rq2)) * \u00D8t;
            double \u00D8z = (Rq1 / (R7 + Rq1)) * \u00D8t;

            // Calcular las densidades de flujo magnético (Bx, By, Bz)
            double Bx = \u00D8x / A7;
            double By = \u00D8y / A4;
            double Bz = \u00D8z / A1;

            // Calcular las intensidades de campo magnético (Hy, Hz)
            double Hy = By / (\u03BCr * \u03BC0);
            double Hz = Bz / (\u03BCr * \u03BC0);

            // Mostrar los resultados en el área de texto
            String resultado = String.format(
                "\u00D8t: %.4f\n\u00D8x: %.4f\n\u00D8y: %.4f\n\u00D8z: %.4f\n\n" +
                "Bx: %.4f\nBy: %.4f\nBz: %.4f\n\n" +
                "Hy: %.4f\nHz: %.4f",
                \u00D8t, \u00D8x, \u00D8y, \u00D8z, Bx, By, Bz, Hy, Hz
            );
            areaResultado.setText(resultado);
        } catch (NumberFormatException ex) {
            areaResultado.setText("Error: Ingresa valores numericos validos");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Circuito().setVisible(true));
    }
}