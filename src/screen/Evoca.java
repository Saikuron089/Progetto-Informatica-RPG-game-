package screen;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Timer;

import utility.RandomPlayer;

public class Evoca extends Frame {

    private Frame f;
    private Button button;
    private int tickCount = 0;
    private final int TICK_INTERVAL_MS = 1; // Intervallo di tick in millisecondi
    private final int TOTAL_TICKS = 40 * 8; // Numero totale di ticks per il movimento (2 giri)
    private boolean movingRight = true;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;

    // loading position

    private int xB = 220;
    private int yB = 100;

    public Evoca() {
        build();
    }

    public void build() {
        f = new Frame("Evocazione");
        f.setResizable(false);
        f.setLayout(null);

        Label l = new Label("Sto evocando...");
        l.setBounds(200, 50, 200, 30);
        f.add(l);

        button = new Button(" ");
        button.setBounds(xB, yB, 30, 30);
        f.add(button);

        f.setSize(500, 500);
        f.setVisible(true);

        Label l1 = new Label(" BUGGGGG");

        l1.setBounds(80, 150, 500, 30);
        l1.setVisible(false);
        f.add(l1);

        Button b1 = new Button("Torna al negozio");

        b1.setBounds(80, 200, 100, 30);

        // funzione di chiusura

        b1.addActionListener(e -> {
            Negozio n = new Negozio();
            n.build();
            f.dispose();
        });

        f.add(b1);

        Timer timer = new Timer(TICK_INTERVAL_MS, e -> {

            // cambiare colore del bottone dopo 15 tick e mantenerlo per 15 tick (rosso, verde, blu)

            if (tickCount % 25 == 0) {
                button.setBackground(Color.RED);
            } else if (tickCount % 25 == 7) {
                button.setBackground(Color.GREEN);
            } else if (tickCount % 25 == 15) {
                button.setBackground(Color.BLUE);
            }

            tickCount++;
            moveButton();
            if (tickCount >= TOTAL_TICKS) {
                button.setVisible(false);

                // chiamare funzione di evocazione
                viewEvoca(l1);

                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void moveButton() {
        int x = button.getX();
        int y = button.getY();

        if (movingRight && x < xB + 10) {
            button.setLocation(x + 1, y);
        } else if (movingRight && x >= xB + 10) {
            movingRight = false;
            movingDown = true;
        } else if (movingDown && y < yB + 10) {
            button.setLocation(x, y + 1);
        } else if (movingDown && y >= yB + 10) {
            movingDown = false;
            movingLeft = true;
        } else if (movingLeft && x > xB) {
            button.setLocation(x - 1, y);
        } else if (movingLeft && x <= xB) {
            movingLeft = false;
            movingUp = true;
        } else if (movingUp && y > yB) {
            button.setLocation(x, y - 1);
        } else if (movingUp && y <= yB) {
            movingUp = false;
            movingRight = true;
        }
    }

    // da fare dopo il caricamento

    public void viewEvoca(Label l){

        // prende un personaggio casuale

        RandomPlayer rp = new RandomPlayer();

        String result = rp.getRandomPlayer();

        System.out.println(result);

        l.setText(result);
        l.setVisible(true);

    }

    public static void main(String[] args) {
        new Evoca();
    }
}