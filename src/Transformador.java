import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class Transformador extends JFrame {
    private JTextField[] camposTexto = new JTextField[10];
    private JLabel[] resultadosLabels = new JLabel[18];
    private JTextArea areaResultado;

    public Transformador() {
        setTitle("Circuito Equivalente de Transformador");
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
        layeredPane.setBounds(0, 0, 1520, 800);

        // Título
        JLabel titulo = new JLabel("Calculadora de Circuito Equivalente de Transformador", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(0, 30, 1520, 40);
        layeredPane.add(titulo, JLayeredPane.PALETTE_LAYER);

        // Integrantes
        JLabel integrantes = new JLabel("Hecho por: Jose Luis Cantu Alvarado 2055195 IEA, Edgar Ivan Molinar Escudero 2050215 IMTC", SwingConstants.CENTER);
        integrantes.setFont(new Font("Arial", Font.PLAIN, 16));
        integrantes.setBounds(0, 70, 1520, 25);
        layeredPane.add(integrantes, JLayeredPane.PALETTE_LAYER);

        // Cargar imágenes
        ImageIcon iconoOriginal2 = new ImageIcon(getClass().getResource("/fime.png"));
        Image imagenEscalada2 = iconoOriginal2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen2 = new JLabel(new ImageIcon(imagenEscalada2));
        etiquetaImagen2.setBounds(200, 10, 100, 100);

        ImageIcon iconoOriginal3 = new ImageIcon(getClass().getResource("/uanl.png"));
        Image imagenEscalada3 = iconoOriginal3.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen3 = new JLabel(new ImageIcon(imagenEscalada3));
        etiquetaImagen3.setBounds(1200, 10, 80, 100);

        // Agregar imágenes
        layeredPane.add(etiquetaImagen2, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(etiquetaImagen3, JLayeredPane.DEFAULT_LAYER);

        // Cargar imágenes adicionales con nuevos tamaños
        ImageIcon circuitoEquivalenteIcon = new ImageIcon(getClass().getResource("/circuito-equivalente.jpg"));
        Image circuitoEquivalenteImg = circuitoEquivalenteIcon.getImage().getScaledInstance(600, 280, Image.SCALE_SMOOTH);
        JLabel circuitoEquivalenteLabel = new JLabel(new ImageIcon(circuitoEquivalenteImg));
        circuitoEquivalenteLabel.setBounds(40, 150, 600, 280);

        ImageIcon cortoCircuitoIcon = new ImageIcon(getClass().getResource("/corto-circuito.jpg"));
        Image cortoCircuitoImg = cortoCircuitoIcon.getImage().getScaledInstance(480, 250, Image.SCALE_SMOOTH);
        JLabel cortoCircuitoLabel = new JLabel(new ImageIcon(cortoCircuitoImg));
        cortoCircuitoLabel.setBounds(650, 150, 480, 250);

        ImageIcon vacioIcon = new ImageIcon(getClass().getResource("/vacio.jpg"));
        Image vacioImg = vacioIcon.getImage().getScaledInstance(480, 250, Image.SCALE_SMOOTH);
        JLabel vacioLabel = new JLabel(new ImageIcon(vacioImg));
        vacioLabel.setBounds(650, 450, 480, 250);

        // Agregar imágenes adicionales
        layeredPane.add(circuitoEquivalenteLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(cortoCircuitoLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(vacioLabel, JLayeredPane.DEFAULT_LAYER);

        // Campos de texto para cortocircuito (posiciones ajustadas)
        String[] variablesCorto = {"Pcc", "Icc", "Vcc"};
        int[][] posicionesCorto = {{40, 480}, {140, 480}, {240, 480}};

        for (int i = 0; i < 3; i++) {
            camposTexto[i] = new JTextField(5);
            camposTexto[i].setBounds(posicionesCorto[i][0], posicionesCorto[i][1], 70, 25);
            layeredPane.add(camposTexto[i], JLayeredPane.PALETTE_LAYER);

            JLabel etiqueta = new JLabel(variablesCorto[i]);
            etiqueta.setBounds(posicionesCorto[i][0], posicionesCorto[i][1] - 20, 70, 20);
            layeredPane.add(etiqueta, JLayeredPane.PALETTE_LAYER);
        }

        // Campos de texto para vacío (posiciones ajustadas)
        String[] variablesVacio = {"Po", "Io", "Vo"};
        int[][] posicionesVacio = {{40, 560}, {140, 560}, {240, 560}};

        for (int i = 0; i < 3; i++) {
            camposTexto[i + 3] = new JTextField(5);
            camposTexto[i + 3].setBounds(posicionesVacio[i][0], posicionesVacio[i][1], 70, 25);
            layeredPane.add(camposTexto[i + 3], JLayeredPane.PALETTE_LAYER);

            JLabel etiqueta = new JLabel(variablesVacio[i]);
            etiqueta.setBounds(posicionesVacio[i][0], posicionesVacio[i][1] - 20, 70, 20);
            layeredPane.add(etiqueta, JLayeredPane.PALETTE_LAYER);
        }

        // Campos de texto adicionales (posiciones ajustadas)
        String[] variablesAdicionales = {"F.P", "SL (no colocar en K)", "V2", "V1"};
        int[][] posicionesAdicionales = {{40, 640}, {140, 640}, {260, 640}, {360, 640}};

        for (int i = 0; i < 4; i++) {
            camposTexto[i + 6] = new JTextField(5);
            camposTexto[i + 6].setBounds(posicionesAdicionales[i][0], posicionesAdicionales[i][1], 70, 25);
            layeredPane.add(camposTexto[i + 6], JLayeredPane.PALETTE_LAYER);
        
            JLabel etiqueta = new JLabel(variablesAdicionales[i]);
            etiqueta.setBounds(posicionesAdicionales[i][0], posicionesAdicionales[i][1] - 20, 150, 20); // Aumenté el ancho a 150
            layeredPane.add(etiqueta, JLayeredPane.PALETTE_LAYER);
        }

        // Posiciones para los resultados (ajustadas)
        int[][] posicionesResultados = {
            {920, 465}, {100, 160},    // R'X, RH
            {450, 150}, {200, 160},    // RX, XLH
            {980, 465}, {540, 150},    // X'LX, XLX
            {960, 255}, {160, 245},    // R'CH, RCH
            {960, 305}, {145, 320},    // X'LMCH, XLMCH
            {720, 465}, {790, 465},    // RH2, XLH2
            {725, 160}, {810, 160},   // RX2, XLX2
            {970, 550}, {970, 610},    // RCH2, XLMCH2
            {920, 160}, {990, 160}     // R'H, X'LH
        };

        for (int i = 0; i < 18; i++) {
            resultadosLabels[i] = new JLabel();
            resultadosLabels[i].setFont(new Font("Arial", Font.BOLD, 12));
            resultadosLabels[i].setBounds(posicionesResultados[i][0], posicionesResultados[i][1], 150, 20);
            resultadosLabels[i].setBorder(new EmptyBorder(0, 0, 0, 0));
            resultadosLabels[i].setOpaque(false);
            layeredPane.add(resultadosLabels[i], JLayeredPane.PALETTE_LAYER);
        }

        // Area de resultados (ajustada)
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Arial", Font.BOLD, 12));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(areaResultado);
        scrollPane.setBounds(1150, 320, 320, 250);
        layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER);

        // Botón "Calcular" (ajustado)
        JButton botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(380, 480, 90, 35);
        botonCalcular.addActionListener(e -> calcularResultado());
        layeredPane.add(botonCalcular, JLayeredPane.PALETTE_LAYER);

        // Botón "Regresar al Menú" (ajustado)
        JButton botonRegresar = new JButton("Regresar al Menu");
        botonRegresar.setBounds(380, 520, 160, 35);
        botonRegresar.addActionListener(e -> {
            new MenuInicial().setVisible(true);
            dispose();
        });
        layeredPane.add(botonRegresar, JLayeredPane.PALETTE_LAYER);

        add(layeredPane, BorderLayout.CENTER);
    }

    private void calcularResultado() {
        try {
            // Obtener valores de los campos de texto
            double Pcc = Double.parseDouble(camposTexto[0].getText());
            double Icc = Double.parseDouble(camposTexto[1].getText());
            double Vcc = Double.parseDouble(camposTexto[2].getText());
            double Po = Double.parseDouble(camposTexto[3].getText());
            double Io = Double.parseDouble(camposTexto[4].getText());
            double Vo = Double.parseDouble(camposTexto[5].getText());
            double FP = Double.parseDouble(camposTexto[6].getText());
            double SL = Double.parseDouble(camposTexto[7].getText());
            double V2 = Double.parseDouble(camposTexto[8].getText());
            double V1 = Double.parseDouble(camposTexto[9].getText());

            // Cálculos principales
            double RxPrima = (Pcc / Math.pow(Icc, 2)) / 2;
            double Rh = RxPrima;
            double a = V1 / V2;
            double Rx = Rh / Math.pow(a, 2);
            double XLh = (Math.sqrt(Math.pow(Vcc / Icc, 2) - Math.pow(Rh + RxPrima, 2))) / 2;
            double XLxPrima = XLh;
            double XLX = XLh / Math.pow(a, 2);
            double RchPrima = (Po / Math.pow(Io, 2)) - Rx;
            double Rch = Math.pow(a, 2) * RchPrima;
            double XlmchPrima = Math.sqrt(Math.pow(Vo / Io, 2) - Math.pow(Rx + RchPrima, 2)) - XLX;
            double Xlmch = Math.pow(a, 2) * XlmchPrima;

            // Nuevas variables
            double RhPrima = Rx;
            double XLhPrima = XLX;

            // Valores adicionales
            double Rh2 = Rh;
            double XLh2 = XLh;
            double Rx2 = Rx;
            double XLX2 = XLX;
            double Rch2 = Rch;
            double Xlmch2 = Xlmch;

            // Actualizar los labels de resultados con Ω y "i" para reactancias
            resultadosLabels[0].setText(String.format("%.4f \u03A9", RxPrima));
            resultadosLabels[1].setText(String.format("%.4f \u03A9", Rh));
            resultadosLabels[2].setText(String.format("%.4f \u03A9", Rx));
            resultadosLabels[3].setText(String.format("i%.4f \u03A9", XLh)); // XLH
            resultadosLabels[4].setText(String.format("i%.4f \u03A9", XLxPrima)); // X'LX
            resultadosLabels[5].setText(String.format("i%.4f \u03A9", XLX)); // XLX
            resultadosLabels[6].setText(String.format("%.4f \u03A9", RchPrima));
            resultadosLabels[7].setText(String.format("%.4f \u03A9", Rch));
            resultadosLabels[8].setText(String.format("i%.4f \u03A9", XlmchPrima)); // X'LMCH
            resultadosLabels[9].setText(String.format("i%.4f \u03A9", Xlmch)); // XLMCH
            resultadosLabels[10].setText(String.format("%.4f \u03A9", Rh2));
            resultadosLabels[11].setText(String.format("i%.4f \u03A9", XLh2)); // XLH2
            resultadosLabels[12].setText(String.format("%.4f \u03A9", Rx2));
            resultadosLabels[13].setText(String.format("i%.4f \u03A9", XLX2)); // XLX2
            resultadosLabels[14].setText(String.format("%.4f \u03A9", Rch2));
            resultadosLabels[15].setText(String.format("i%.4f \u03A9", Xlmch2)); // XLMCH2
            resultadosLabels[16].setText(String.format("%.4f \u03A9", RhPrima));
            resultadosLabels[17].setText(String.format("i%.4f \u03A9", XLhPrima)); // X'LH

            // Resto de cálculos
            double magnitudIL = SL / V2;
            double anguloIL = Math.toDegrees(Math.acos(-FP));
            Complex IL = Complex.fromPolar(magnitudIL, anguloIL);
            double anguloILNegativo = -anguloIL;
            double PoNuevo = ((V2 * magnitudIL) * (-FP) * 720) / 1000;
            Complex ZL = Complex.fromPolar(V2 / magnitudIL, Math.abs(anguloIL));
            Complex E2 = IL.multiply(new Complex(Rx + XLX, 0)).add(new Complex(V2, 0));
            Complex E1 = E2.multiply(new Complex(a, 0));
            Complex Zexc = new Complex(Rch, Xlmch);
            Complex Yexc = Zexc.reciprocal();
            Complex iExc = E1.divide(Zexc);
            double anguloIExc = iExc.toPolar().getAngulo();
            double anguloE1 = E1.toPolar().getAngulo();
            double angulo1 = 90 + anguloIExc + anguloE1;
            double magnitudIm = Math.cos(Math.toRadians(angulo1)) * iExc.toPolar().getMagnitude();
            double anguloIm = anguloIExc;
            Complex im = Complex.fromPolar(magnitudIm, anguloIm);
            double magnitudIc = Math.sin(Math.toRadians(angulo1)) * iExc.toPolar().getMagnitude();
            double anguloIc = anguloIExc;
            Complex ic = Complex.fromPolar(magnitudIc, anguloIc);
            double pc = Math.abs(Math.pow(ic.toPolar().getMagnitude(), 2) * Math.sin(Math.toRadians(anguloIc)) * Rch);
            double w = (pc / 1000) * 720;
            Complex iL = IL.divide(new Complex(a, 0));
            Complex I2 = iExc.add(iL);
            double Ptcv = (Math.pow(I2.toPolar().getMagnitude(), 2) * Rh) + (Math.pow(IL.toPolar().getMagnitude(), 2) * Rx);
            double PtcvW = (Ptcv / 1000) * 720;
            Complex sum1 = E1.add(I2.multiply(new Complex(Rh + XLh, 0)));
            double B = sum1.toPolar().getAngulo() + I2.toPolar().getAngulo();
            double fp1 = -Math.cos(Math.toRadians(B));
            double porcentajeN = ((V2 * magnitudIL * FP) / (sum1.toPolar().getMagnitude() * I2.toPolar().getMagnitude() * fp1)) * 100;
            double porcentajeReg = ((sum1.toPolar().getMagnitude() / a) - V2) / V2 * 100;

            

                        // Mostrar resultados en el área de texto
            // Mostrar resultados en el área de texto
                        DecimalFormat scientificFormat = new DecimalFormat("0.#####E0");
            String resultado = String.format(
                "Resultados:\n" +
                "IL = %.4f %.4f\n" +
                "Po = %.4f kW/h\n" +
                "ZL = %s\n" +
                "E2 = %s\n" +
                "E1 = %s\n" +
                "Zexc = %s\n" +
                "Yexc = %s\n" +
                "iExc = %s\n" +
                "im = %s\n" +
                "ic = %s\n" +
                "pc = %.4f Watts\n" +
                "w = %.4f kW/h (mes)\n" +
                "I2 = %s\n" +
                "Ptcv = %.4f Watts\n" +
                "Ptcv w = %.4f kW/h (mes)\n" +
                "\u2211  = %s\n" +
                "f.p1 = %.4f\n" +
                "%%n = %.4f%%\n" +
                "%%Reg = %.4f%%",
                magnitudIL, anguloILNegativo, PoNuevo, 
                ZL.toPolarString(), E2.toPolarString(),
                E1.toPolarString(), Zexc.toPolarString(),
                Yexc.toPolarString(), iExc.toPolarString(), 
                im.toPolarString(), ic.toPolarString(), 
                pc, w, I2.toPolarString(), Ptcv, 
                PtcvW, sum1.toPolarString(), fp1, 
                porcentajeN, porcentajeReg
            );
            areaResultado.setText(resultado);


        } catch (NumberFormatException ex) {
            areaResultado.setText("Error: Ingresa valores numéricos válidos");
        }
    }

    // Clase Complex para manejar números complejos (sin cambios)
    public static class Complex {
        private double real;
        private double imaginary;

        public Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        public double getReal() {
            return real;
        }

        public double getImaginary() {
            return imaginary;
        }

        public static Complex fromPolar(double magnitude, double angle) {
            double real = magnitude * Math.cos(Math.toRadians(angle));
            double imaginary = magnitude * Math.sin(Math.toRadians(angle));
            return new Complex(real, imaginary);
        }

        public Complex multiply(Complex other) {
            double realResult = this.real * other.real - this.imaginary * other.imaginary;
            double imaginaryResult = this.real * other.imaginary + this.imaginary * other.real;
            return new Complex(realResult, imaginaryResult);
        }

        public Complex add(Complex other) {
            return new Complex(this.real + other.real, this.imaginary + other.imaginary);
        }

        public Complex divide(Complex other) {
            double denominator = other.real * other.real + other.imaginary * other.imaginary;
            double realResult = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
            double imaginaryResult = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
            return new Complex(realResult, imaginaryResult);
        }

        public Complex reciprocal() {
            double denominator = real * real + imaginary * imaginary;
            return new Complex(real / denominator, -imaginary / denominator);
        }

        public Polar toPolar() {
            double magnitude = Math.sqrt(real * real + imaginary * imaginary);
            double angle = Math.toDegrees(Math.atan2(imaginary, real));
            return new Polar(magnitude, angle);
        }

        public String toPolarString() {
            Polar polar = this.toPolar();
            return String.format("%.4f %s %.4f\u00B0", polar.getMagnitude(), "<", polar.getAngulo());
        }
        

        @Override
        public String toString() {
            return String.format("%.4f + j%.4f", real, imaginary);
        }
    }

    // Clase Polar para representar números complejos en forma polar (sin cambios)
    public static class Polar {
        private double magnitude;
        private double angulo;

        public Polar(double magnitude, double angulo) {
            this.magnitude = magnitude;
            this.angulo = angulo;
        }

        public double getMagnitude() {
            return magnitude;
        }

        public double getAngulo() {
            return angulo;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Transformador().setVisible(true));
    }
}