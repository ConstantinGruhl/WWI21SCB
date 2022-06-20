package Constantin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Abgabe4 {
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton berechnenButton;
    private JTextField iJTextField;
    private JPanel test;


    public Abgabe4() {

        berechnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long erg = Long.parseLong(textArea1.getText()) + Long.parseLong(textArea2.getText());

                iJTextField.setText(String.valueOf(erg));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Taschenrechner");
        frame.setContentPane(new Abgabe4().test);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
