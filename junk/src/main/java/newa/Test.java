package newa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;

public class Test extends JFrame {

    private JButton buttonOk;
    private JLabel commonLabel;
    private JPanel mainPanel;
    private JButton button2;
    private JButton btnFont;
    private JLabel labelPNG;
    private JButton button1;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;

    public Test() {
        createUIComponents();
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (commonLabel.getText().equals("0")) {
                    commonLabel.setText(button2.getText());
                } else {
                    commonLabel.setText(commonLabel.getText() + "2");
                }
            }
        });
    }

    private void createUIComponents () {
        setContentPane(mainPanel);
        setTitle("Here");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonOk.setBackground(Color.BLUE);
                buttonOk.setOpaque(true);
                buttonOk.setForeground(Color.YELLOW);
                if (commonLabel.getText().equals("0")) {
                    commonLabel.setText("Hello");
                } else {
                    commonLabel.setText(commonLabel.getText() + "Hello");
                }
            }
        });

        btnFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFont.setFont(new Font("Arial", Font.BOLD, 25));
                commonLabel.setFont(new Font("Arial", Font.BOLD, 25));
                Color c1 = new Color(255, 0, 0);
                btnFont.setBackground(Color.BLUE);
                btnFont.setOpaque(true);
                btnFont.setForeground(c1);
                commonLabel.setForeground(Color.YELLOW);

                ImageIcon ic1 = new ImageIcon("1.jpeg");
                labelPNG.setIcon(ic1);
//                labelPNG.setLocation(300, 300);
            }
        });
    }

    public static void main(String[] args){
        Test test = new Test();
    }
}