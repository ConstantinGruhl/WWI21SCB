package Constantin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;



public class GraphPanel extends JPanel implements ActionListener {

    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    public static double durchschnitt;
    public static double min = 100;
    public static double max = 0;
    private List<Double> scores;

    private static JLabel LaengeText;
    private static JLabel AktienText;
    private static JButton Button;
    private static JTextField Dauer;
    private static JTextField AktienEingabe;

    public GraphPanel(List<Double> scores) {
        this.scores = scores;
    }

    public GraphPanel(){
        GraphPanel mainPanel = new GraphPanel(scores);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public List<Double> getScores() {
        return scores;
    }

    private static void createAndShowGui(int Laenge, int Aktie) {
        List<Double> scores = new ArrayList<>();
        String AktienName;

        if (Aktie == 1){
            AktienName = "Accenture";
        } else if (Aktie == 2){
            AktienName = "SAP";
        } else {
            AktienName = "Tesla";
        }

        int maxDataPoints = Laenge;
        double maxScore = 200;
        double wert;
        for (int i = 0; i < maxDataPoints ; i++) {
            if (i == 0){
                wert = 100;
            } else {
                wert = scores.get(i-1);
            }
            scores.add((double) randomGenerator(Aktie, wert));
        }
        GraphPanel mainPanel = new GraphPanel(scores);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Aktienkurs von " + AktienName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public void MinMax(int Laenge){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Werte zum Aktienkurs");
        frame.setSize(500, 200);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);

        LaengeText = new JLabel("Minimum ist: " + String.valueOf(GraphPanel.min));
        LaengeText.setBounds(10, 20, 360 ,25);
        panel.add(LaengeText);

        LaengeText = new JLabel("Maximum ist: " + String.valueOf(GraphPanel.max));
        LaengeText.setBounds(10, 50, 360 ,25);
        panel.add(LaengeText);


        LaengeText = new JLabel("Durchschnitt ist: " + String.valueOf(GraphPanel.durchschnitt/Laenge));
        LaengeText.setBounds(10, 80, 360 ,25);
        panel.add(LaengeText);

        frame.setVisible(true);
    }

    public static void Abfrage(){

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 200);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);

        LaengeText = new JLabel("Wie viele Tage sollen Simuliert werden?");
        LaengeText.setBounds(10, 20, 360 ,25);
        panel.add(LaengeText);

        Dauer = new JTextField();
        Dauer.setBounds(400, 20, 50, 25);
        panel.add(Dauer);

        AktienText = new JLabel("Welche Aktie spricht Sie an? Accenture = 1 ; SAP = 2 ; Tesla = 3");
        AktienText.setBounds(10, 50, 360 ,25);
        panel.add(AktienText);

        AktienEingabe = new JTextField();
        AktienEingabe.setBounds(400, 50, 50, 25);
        panel.add(AktienEingabe);

        Button = new JButton("Verlauf Simulieren!");
        Button.setBounds(10, 80, 150 , 25);
        Button.addActionListener(new GraphPanel());
        panel.add(Button);

        frame.setVisible(true);
    }

    public static double randomGenerator(int Aktie, double Current){

        if (Current != 100){
            if (Current > GraphPanel.max){
                GraphPanel.max = Current;
            }
            if (Current < GraphPanel.min){
                GraphPanel.min = Current;
            }
        }

        Random random = new Random();
        double plusMinus = random.nextInt(3);
        double veraenderung = (double)(random.nextGaussian()*3);
        if (Current <= 0){
            GraphPanel.durchschnitt += 1;
            return 1;

        }
        if (Current >= 200){
            double crash = random.nextInt(10)*10;
            GraphPanel.durchschnitt += Current -crash;
            return Current-crash;
        }
        if (Aktie == 1) {
            if (plusMinus == 1 || plusMinus == 2){
                GraphPanel.durchschnitt += Current + veraenderung+1;
                return Current + veraenderung + 1;
            } else {
                GraphPanel.durchschnitt += Current + veraenderung;
                return Current + veraenderung;
            }
        } else if (Aktie == 2){
            if (plusMinus == 1 || plusMinus == 2){
                GraphPanel.durchschnitt +=Current + veraenderung-1;
                return Current + veraenderung - 1;
            } else {
                GraphPanel.durchschnitt += Current + veraenderung;
                return Current + veraenderung;
            }
        } else if (Aktie == 3) {
            GraphPanel.durchschnitt += Current + veraenderung*3;
            return Current + veraenderung*3;
        } else {
            GraphPanel.durchschnitt += Current + veraenderung;
            return Current + veraenderung;
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Abfrage();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int Laenge = Integer.parseInt(Dauer.getText());
        int Aktie = Integer.parseInt(AktienEingabe.getText());
        createAndShowGui(Laenge,Aktie);
        MinMax(Laenge);
    }
}
