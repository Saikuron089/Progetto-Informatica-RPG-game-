package screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

// input

import inputs.keyboardInput;
// player

import utility.playerLoad;
import utility.draw;

public class Map extends JPanel implements Runnable {

    BufferedImage img;
    Thread gameThread;
    final int FPS = 60;
    JFrame f = new JFrame("Gioco");

    // setup the map

    draw d = new draw();

    // player info

    playerLoad p = new playerLoad();;
    int x = 100;
    int y = 100;
    final int SPEED = 3;

    // screen settings

    keyboardInput k = new keyboardInput();
    final int WIDTH = 1024;
    final int HEIGHT = 768;

    // camera settings

    int camX = 0;
    int camY = 0;

    public Map() {

        f.add(this);
        f.addKeyListener(k);
        f.setSize(WIDTH + 16, HEIGHT + 38);
        f.setVisible(true);
        f.setResizable(false);

        // setup the image

        img = p.std;
        
        // start the game thread

        startGameThread();

        // close the window

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run(){

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            
            update();

            repaint();
            
            try{

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;


            }catch(Exception e){
                
                System.out.println("Erorre: " + e);

            }

        }

    }

    public void update(){

        // controlla che non esca dai bordi

        if(k.w){
            if(y > 0 - 8)
                y-= SPEED;
            // set the up image
            img = p.up;
        }else if(k.a){
            if(x > 0 - 12)
                x-= SPEED;
            // set the left image
            img = p.left;
        }else if(k.s){
            if(y < HEIGHT - 92)
                y+= SPEED;
            // set the down image
            img = p.down;
        }else if(k.d){
            if(x < WIDTH - 64)
                x+= SPEED;
            // set the right image
            img = p.right;
        }else{
            // set the standard image
            img = p.std;
        }


    }


    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // draw the map

        d.drawTile(g2, WIDTH, HEIGHT, camX, camY);

        // draw the player

        d.drawPlayer(g2, img, 500, 500);

        g2.dispose();

    }



}