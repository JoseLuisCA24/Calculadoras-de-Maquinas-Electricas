import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class MotorFP extends JFrame {
    private JTextField[] camposTexto = new JTextField[6];
    private JTextArea areaResultado;

    public MotorFP() {
        setTitle("Calculadora de Corriente y Potencia en Motor de Fase Partida");
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


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1520, 800));

        JLabel titulo = new JLabel("Calculadora de Corriente y Potencia en Motor de Fase Partida", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(0, 30, 1520, 50);
        layeredPane.add(titulo, JLayeredPane.PALETTE_LAYER);

        JLabel integrantes = new JLabel("Hecho por: Jose Luis Cantu Alvarado 2055195 IEA, Edgar Ivan Molinar Escudero 2050215 IMTC", SwingConstants.CENTER);
        integrantes.setFont(new Font("Arial", Font.PLAIN, 16));
        integrantes.setBounds(0, 80, 1520, 30);
        layeredPane.add(integrantes, JLayeredPane.PALETTE_LAYER);

        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/MotorFP.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(700, 400, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(imagenEscalada));
        etiquetaImagen.setBounds(0, 290, 700, 400);
       

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

        // Variables y posiciones
        String[] variables = {"HP", "VF", "A", "\u03B8", "A_2", "\u03B8_2"};
        int[][] posiciones = {
            {50, 210}, {175, 210},     // HP, VF
            {340, 210}, {550, 210},    // A, \u03B8
            {750, 210}, {960, 210}     // A_2, \u03B8_2
        };

        String[] descripciones = {
            "Fuerza del motor(HP)", "Voltaje de alimentacion",
            "Corriente de Devanado (Amp)", "Grado de retardo (Arranque)",
            "Corriente de Devanado (Amp)", "Grado de retardo (Marcha)"
        };

        JLabel tituloMotor = new JLabel("Valores del motor");
        tituloMotor.setFont(new Font("Arial", Font.BOLD, 16));
        tituloMotor.setBounds(50, 160, 200, 20);
        layeredPane.add(tituloMotor, JLayeredPane.PALETTE_LAYER);

        JLabel tituloArranque = new JLabel("Valores de Devanado de arranque");
        tituloArranque.setFont(new Font("Arial", Font.BOLD, 16));
        tituloArranque.setBounds(340, 160, 300, 20);
        layeredPane.add(tituloArranque, JLayeredPane.PALETTE_LAYER);

        JLabel tituloMarcha = new JLabel("Valores de Devanado de marcha");
        tituloMarcha.setFont(new Font("Arial", Font.BOLD, 16));
        tituloMarcha.setBounds(750, 160, 300, 20);
        layeredPane.add(tituloMarcha, JLayeredPane.PALETTE_LAYER);

        for (int i = 0; i < 6; i++) {
            JLabel etiqueta = new JLabel(descripciones[i]);
            etiqueta.setBounds(posiciones[i][0], posiciones[i][1] - 25, 200, 20);
            layeredPane.add(etiqueta, JLayeredPane.PALETTE_LAYER);

            camposTexto[i] = new JTextField(5);
            camposTexto[i].setBounds(posiciones[i][0], posiciones[i][1], 80, 25);
            layeredPane.add(camposTexto[i], JLayeredPane.PALETTE_LAYER);
        }

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(areaResultado);
        scrollPane.setBounds(950, 300, 350, 250);
        layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER);

        JButton botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(800, 500, 100, 35);
        botonCalcular.addActionListener(e -> calcularResultado());
        layeredPane.add(botonCalcular, JLayeredPane.PALETTE_LAYER);

        JButton botonRegresar = new JButton("Regresar al Menu");
        botonRegresar.setBounds(800, 550, 150, 35);
        botonRegresar.addActionListener(e -> {
            new MenuInicial().setVisible(true);
            dispose();
        });
        layeredPane.add(botonRegresar, JLayeredPane.PALETTE_LAYER);

        add(layeredPane, BorderLayout.CENTER);
    }

    private JPanel margenPanel(Color color, String posicion, int w, int h) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(w, h));
        return panel;
    }

private void calcularResultado() {
    try {
        DecimalFormat df = new DecimalFormat("#.##");

        double HP = Double.parseDouble(camposTexto[0].getText());
        double VF = Double.parseDouble(camposTexto[1].getText());
        double A = Double.parseDouble(camposTexto[2].getText());
        double theta = Math.toRadians(Double.parseDouble(camposTexto[3].getText()));
        double A2 = Double.parseDouble(camposTexto[4].getText());
        double theta2 = Math.toRadians(Double.parseDouble(camposTexto[5].getText()));

        // Ia y Im en forma rectangular
        double Ia_real = A * Math.cos(theta);
        double Ia_imag = A * Math.sin(theta);
        double Im_real = A2 * Math.cos(theta2);
        double Im_imag = A2 * Math.sin(theta2);

        // Formas polares
        double Iap_mag = Math.hypot(Ia_real, Ia_imag);
        double Iap_ang = Math.toDegrees(Math.atan2(Ia_imag, Ia_real));

        double Imp_mag = Math.hypot(Im_real, Im_imag);
        double Imp_ang = Math.toDegrees(Math.atan2(Im_imag, Im_real));

        // Suma total
        double It_real = Ia_real + Im_real;
        double It_imag = Ia_imag + Im_imag;
        double Itotal_mag = Math.hypot(It_real, It_imag);
        double Itotal_ang = Math.toDegrees(Math.atan2(It_imag, It_real));
        double Itotal_rad = Math.atan2(It_imag, It_real);

        // Angulo de fase
        double theta_fase = Math.toDegrees(theta2 - theta);

        // Potencias
        double Pa = VF * A * Math.cos(theta);
        double Pm = VF * A2 * Math.cos(theta2);
        double Ptotal = Pa + Pm;

        // Eficiencia
        double eficiencia = ((HP * 746) / Ptotal) * 100;

        String resultado = String.format(
            "A) La corriente total de arranque y el factor de potencia (estado estable)\n" +
            "- Polar: %.2f < %.2f\u00B0\n" +
            "- Rectangular: %.2f + j%.2f\n" +
            "Factor de potencia = %.2f\n\n" +
            "B) Angulo de Fase entre corrientes:\n%.2f\u00B0\n\n" +
            "C) Potencia en devanado de arranque:\n%.2f W\n\n" +
            "D) Potencia en devanado de marcha:\n%.2f W\n\n" +
            "E) Potencia total disipada:\n%.2f W\n\n" +
            "F) Eficiencia del motor:\n%.2f %%",
            Itotal_mag, Itotal_ang, It_real, It_imag,
            Math.cos(Itotal_rad),
            theta_fase,
            Pa, Pm, Ptotal,
            eficiencia
        );

        areaResultado.setText(resultado);
    } catch (NumberFormatException ex) {
        areaResultado.setText("Error: Ingresa valores numericos validos");
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MotorFP().setVisible(true));
    }
}
