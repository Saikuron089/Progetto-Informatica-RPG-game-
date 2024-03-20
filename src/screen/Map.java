package screen;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// input

import inputs.keyboardInput;

public class Map extends Frame {
    Image img;

    public Map() {
        build();
    }

    public void build() {

        img = Toolkit.getDefaultToolkit().getImage("playerSingle.png");
        MediaTracker track = new MediaTracker(this);
        track.addImage(img, 0);
        try {
            track.waitForID(0);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    public void keyPressed(KeyEvent e, Graphics g) {
        int x = 100;
        int y = 100;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            y -= 10;
            g.drawImage(img, x, y, this);
        }
    }

    public static void main(String[] args) {
        Map frame = new Map();
        keyboardInput k = new keyboardInput();

        frame.addKeyListener(k);

        frame.setTitle("Immagine in Frame");
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}