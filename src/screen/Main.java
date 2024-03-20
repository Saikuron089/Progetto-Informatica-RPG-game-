package screen;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Main{

    public void build(){

        // Dimension size = f.getBounds().getSize();
        // int larghezza = size.width;

        Frame f = new Frame("Prova");

        f.setResizable(false);

        Label l = new Label("Menu principale");
        l.setBounds(200, 50, 200, 30);
        f.add(l);

        Button l1 = new Button("Gioca");
        l1.setBounds(140, 100, 200, 30);
        
        l1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map m = new Map();
                f.dispose();
            }
        });

        f.add(l1);

        Button l2 = new Button("Negozio");
        l2.setBounds(140, 150, 200, 30);
        l2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("scc");
                f.setVisible(false);
                Negozio n = new Negozio();
                n.build();
            }
        });


        f.add(l2);

        Button l3 = new Button("Esci");
        l3.setBounds(140, 200, 200, 30);
        l3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        f.add(l3);


        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

    }

    public static void main(String[] args)
    {

        Main m = new Main();
        m.build();

    }
}