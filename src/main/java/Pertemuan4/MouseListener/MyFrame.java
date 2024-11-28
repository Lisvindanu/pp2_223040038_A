package main.java.Pertemuan4.MouseListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame implements MouseListener {
    private JLabel label1;
    private JLabel label;
    private JFrame frame;
    private ImageIcon nokotan1 = new ImageIcon("src/main.java.Pertemuan4/MouseListener/img/Nokotan1.jpg");
    private ImageIcon nokotan2 = new ImageIcon("src/main.java.Pertemuan4/MouseListener/img/Nokotan2.jpg");
    private ImageIcon nokotan3 = new ImageIcon("src/main.java.Pertemuan4/MouseListener/img/Nokotan3.jpg");
    private ImageIcon nokotan4 = new ImageIcon("src/main.java.Pertemuan4/MouseListener/img/Nokotan4.jpg");

    public MyFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
       setTitle("Mouse Listener Example");
        setSize(500,500);

       label1 = new JLabel("nokotan sedih, arahkan Mouse agar Dia tersenyum");
       label1.setPreferredSize(new Dimension(400,50));

       label = new JLabel(nokotan1);
       label.setPreferredSize(new Dimension(400,400));

       add(label1);
        add(label);

       label.addMouseListener(this);
       setVisible(true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        label1.setText("Dia Melepaskan Kepala, lepas mouse agar dia cantik ");
        label.setIcon(nokotan2);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        label1.setText("Rtx On");
        label.setIcon(nokotan3);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label1.setText("Nokotan sekarang tersenyum.");
        label.setIcon(nokotan4);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        label1.setText("nokotan sedih, arahkan Mouse agar Dia tersenyum");
        label.setIcon(nokotan1);
    }
}
