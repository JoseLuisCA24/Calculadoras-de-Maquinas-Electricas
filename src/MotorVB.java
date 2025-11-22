import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class MotorVB extends JFrame {
    private JTextField[] camposTexto = new JTextField[8];
    private JTextArea areaResultado;

    public MotorVB() {
        setTitle("Calculadora de Vacio y Rotor Bloqueado, para motor de 2.5 HP, 4 polos");
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

        JLabel titulo = new JLabel("Calculadora de Vacio y Rotor Bloqueado, para motor de 2.5 HP, 4 polos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(0, 30, 1520, 50);
        layeredPane.add(titulo, JLayeredPane.PALETTE_LAYER);

        JLabel integrantes = new JLabel("Hecho por: Jose Luis Cantu Alvarado 2055195 IEA, Edgar Ivan Molinar Escudero 2050215 IMTC", SwingConstants.CENTER);
        integrantes.setFont(new Font("Arial", Font.PLAIN, 16));
        integrantes.setBounds(0, 80, 1520, 30);
        layeredPane.add(integrantes, JLayeredPane.PALETTE_LAYER);

        
       
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/pruebaVacio.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(399 , 204, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(imagenEscalada));
        etiquetaImagen.setBounds(0, 100, 700, 400);

        ImageIcon iconoOriginal4 = new ImageIcon(getClass().getResource("/pruebaRotor.png"));
        Image imagenEscalada4 = iconoOriginal4.getImage().getScaledInstance(399, 204, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen4 = new JLabel(new ImageIcon(imagenEscalada4));
        etiquetaImagen4.setBounds(0, 350, 700, 400);

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
        layeredPane.add(etiquetaImagen4, JLayeredPane.DEFAULT_LAYER);

        

        // Variables y posiciones
        String[] variables = {"P", "T", "A", "P2", "T2", "A2", "Rfase", "RPM"};
        int[][] posiciones = {
            {175, 360}, {300, 360}, {435, 360},     // HP, VF
               
            {175, 610}, {300, 610},  {435, 610},    // A_2, \u03B8_2
            {600, 360}, {700, 360}                  // Rfase, RPM (nuevos)
        };

        String[] descripciones = {
            "P", "T", "A", 
            "P2", "T2", "A2",
            "Rfase (ohm)", "RPM"
        };

      

        for (int i = 0; i < 8; i++) {
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

        double P = Double.parseDouble(camposTexto[0].getText());     // P
        double T = Double.parseDouble(camposTexto[1].getText());     // no se usa
        double A = Double.parseDouble(camposTexto[2].getText());     // A
        double P2 = Double.parseDouble(camposTexto[3].getText());    // P2
        double T2 = Double.parseDouble(camposTexto[4].getText());    // no se usa
        double A2 = Double.parseDouble(camposTexto[5].getText());    // A2
        double Rfase = Double.parseDouble(camposTexto[6].getText()); // Rfase
        double RPM = Double.parseDouble(camposTexto[7].getText());   // RPM

        // Constante de deslizamiento dinámica
        double S = (1800 - RPM) / 1800;

        // a) Perdidas por friccion, ventilacion y hierro
        double PH_Pfp = P - 3 * Math.pow(A, 2) * Rfase;

        // b) Perdidas en el cobre del estator bajo carga
        double Pcu = 3 * Rfase * Math.pow(A2, 2);

        // c) Potencia de entrada al rotor
        double PEr = P2 - (PH_Pfp + Pcu);

        // d) Perdidas en el cobre del rotor bajo carga
        double Pr = S * PEr;

        // e) Potencia de salida en el rotor (en HP)
        double Pd = PEr - Pr;
        double PdHP = Pd / 746;

        // f) Par desarrollado (convertido a lb-pie)
        double tNm = 7.04 * (Pd / 1668);

        // g) Eficiencia bajo carga
        double eficiencia = (Pd / PEr) * 100;

        // Mostrar resultados
        String resultado = String.format(
            "Resultados:\n\n" +
            "a) Perdidas por friccion, ventilacion y hierro: \n%.2f W\n\n" +
            "b) Perdidas en el cobre del estator bajo carga: \n%.2f W\n\n" +
            "c) Potencia de entrada al rotor: \n%.2f W\n\n" +
            "d) Perdidas en el cobre del rotor bajo carga:\n%.2f W\n\n" +
            "e) Potencia de salida en el rotor:\n%.2f HP\n\n" +
            "f) Par desarrollado:\n%.2f lb-pie \n\n" +
            "g) Eficiencia bajo carga:\n%.2f %%", 
            PH_Pfp, Pcu, PEr, Pr, PdHP, tNm, eficiencia
        );

        areaResultado.setText(resultado);
    } catch (NumberFormatException ex) {
        areaResultado.setText("Error: Ingresa valores numéricos validos en todos los campos.");
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MotorVB().setVisible(true));
    }
}
