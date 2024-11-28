package main.java.pertemuan5.html;

import javax.swing.*;

public class JLabelHTMLExample extends JFrame {
    private JLabel label;

    public JLabelHTMLExample() {
        setTitle("HTML Example");
        label = new JLabel("<html> <b> Bold Text </b>, <i>italic text</i>, dan <u>Underlined Text</u> </html>");

        add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JLabelHTMLExample();
    }
}
