package screen;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;

public class Main extends JFrame{

    public void build(){

        // Dimension size = f.getBounds().getSize();
        // int larghezza = size.width;

        JFrame f = this;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Button l1 = new Button("Gioca");
        l1.setBounds(140, 100, 200, 30);


        l1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map m = new Map();
                f.dispose();
            }
        });

        this.add(l1);

        Button l2 = new Button("Negozio");
        l2.setBounds(140, 150, 200, 30);
        l2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Negozio n = new Negozio();
                n.build();
            }
        });


        this.add(l2);

        Button l3 = new Button("Esci");
        l3.setBounds(140, 200, 200, 30);
        l3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        this.add(l3);

        this.setResizable(false);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Menu");

    }

    public static void main(String[] args)
    {

        Main m = new Main();
        m.build();

    }
}