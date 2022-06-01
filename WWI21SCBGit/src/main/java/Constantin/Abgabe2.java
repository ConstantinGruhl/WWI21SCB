package Constantin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

@SuppressWarnings("serial")
public class Abgabe2 extends JPanel {
    private static int MAX_SCORE = 200;
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.blue;
    private static final Color GRAPH_POINT_COLOR = new Color(0, 0, 0, 100);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private static final int GRAPH_POINT_WIDTH = 4;
    private static final int Y_HATCH_CNT = 19;
    private List<Integer> scores;

    public static int min = 100;
    public static int max = 100;
    public static int mean;

    public Abgabe2(List<Integer> scores) {
        this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    private static void createAndShowGui(int Laenge, int Aktie) {
        List<Integer> scores = new ArrayList<Integer>();
        Random random = new Random();
        int maxDataPoints = Laenge;
        int maxScore = 200;
        int wert;
        for (int i = 0; i < maxDataPoints ; i++) {
            if (i == 0){
                wert = 100;
            } else {
                wert = scores.get(i-1);
            }
            scores.add(randomGenerator(Aktie, wert));
        }
        Abgabe2 mainPanel = new Abgabe2(scores);

        JFrame frame = new JFrame("Aktienkurs");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static int randomGenerator(int Aktie, int Current){
        if (Current != 100){
            if (Current > Abgabe2.max){
                Abgabe2.max = Current;
            }
            if (Current < Abgabe2.min){
                Abgabe2.min = Current;
            }
        }

        Random random = new Random();
        int plusMinus = random.nextInt(3);
        int veraenderung = (int)(random.nextGaussian()*3);
        if (Current <= 0){
            return 1;
        }
        if (Current >= 200){
            return Current-random.nextInt(10)*10;
        }
        if (Aktie == 1) {
            if (plusMinus == 1 || plusMinus == 2){
                Abgabe2.mean += Current + veraenderung+1;
                return Current + veraenderung + 1;

            } else {
                Abgabe2.mean += Current + veraenderung;
                return Current + veraenderung;
            }
        } else if (Aktie == 2){
            if (plusMinus == 1 || plusMinus == 2){
                Abgabe2.mean +=Current + veraenderung-1;
                return Current + veraenderung - 1;

            } else {
                Abgabe2.mean += Current + veraenderung;
                return Current + veraenderung;
            }
        } else if (Aktie == 3) {
            Abgabe2.mean += Current + veraenderung*3;
                return Current + veraenderung*3;

        } else {
            Abgabe2.mean += Current + veraenderung;
            return Current + veraenderung;

        }



    }

    private int getMaxScore(int laenge){
        int maxScore = 100;
        for (int i = 0; i < laenge; i++){
            if (scores.get(i) > maxScore){
                maxScore = maxScore;
            }
        }
        return maxScore;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Scanner Scanner = new Scanner(System.in);
                System.out.println("Wie viele Tage sollen Simuliert werden?");
                int laenge = Scanner.nextInt();
                int aktie = 1;
                boolean gueltig = true;
                while (gueltig) {
                    System.out.println("Welche Aktie spricht Sie an? Accenture = 1 ; SAP = 2 ; Tesla = 3");
                    aktie = Scanner.nextInt();
                    if (!(aktie != 1 && aktie != 2 && aktie != 3)){
                        gueltig = false;
                    } else {
                        gueltig = true;
                    }
                }
                createAndShowGui(laenge, aktie);
                System.out.println("Minimum ist: " + Abgabe2.min);
                System.out.println("Maximum ist: " + Abgabe2.max);
                System.out.println("Durschnitt ist: " + Abgabe2.mean/laenge);
            }
        });
    }
}
