package screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;

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

    // screen settings

    keyboardInput k = new keyboardInput();
    final int WIDTH = 1024;
    final int HEIGHT = 768;

    // player info

    final int HITBOX_X =  WIDTH/2 - 17; 
    final int HITBOX_Y = HEIGHT/2 + 10;
    playerLoad p = new playerLoad();;
    int SPEED = 4;
    boolean isColliding = false;
    boolean isUP = false;
    boolean isDOWN = false;
    boolean isLEFT = false;
    boolean isRIGHT = false;
    boolean getKey = false;

    // dialog settings

    boolean isDialog = false;
    boolean isDialogBlocked = false;
    int dialogIndex = 0;
    String dialogText = "Ho sentito parlare di te! \nSei il nuovo eroe del villaggio?";

    boolean isKeyDialog = false;
    boolean isKeyDialogBlocked = false;

    // random 


    // camera settings

    int camX = -360;
    int camY = -168;

    public Map() {

        f.setIconImage(p.std);
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

            // check if dialog is open

            dialog();

            // update 

            repaint();

            update();
            
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

    public void dialog(){

        // dialog

        if(camX < -700 && camX > -800 && camY < -180 && camY > -280 && dialogIndex == 0 && !isDialogBlocked){
            isDialog = true;
            dialogIndex = 1;
        }else if(dialogIndex == 1 && k.enter){
            dialogIndex++;
            k.enter = false;
        }else if(dialogIndex == 2 && k.enter){
            dialogIndex++;
            k.enter = false;
        }else if(dialogIndex == 3){
            dialogIndex = 0;
            isDialog = false;
            isDialogBlocked = true;
        }

        if(camX < -1200 && camX > -1300 && camY < -640 && camY > -850){
            isKeyDialog = true;
        }

    }

    public void update(){

        //System.out.println("CamX: " + camX + " CamY: " + camY);

        if(isDialog && dialogIndex == 1){
            SPEED = 0;
            System.out.println("Dialog 1");
        }else if(isDialog && dialogIndex == 2){
            dialogText = "Mi hanno detto che sei forte! \nMa stai attento, il nemico è vicino!";
            SPEED = 0;
            System.out.println("Dialog 2");
        }else if(isKeyDialog && !isKeyDialogBlocked){
            SPEED = 0;
            if(k.enter){
                isKeyDialog = false;
                isKeyDialogBlocked = true;
            }
            System.out.println("Dialog Key");
        }

        // controlla che non esca dai bordi

        if(k.w){
            camY+= SPEED;
            // set the up image
            img = p.up;
            isUP = true;
            isDOWN = false;
            isLEFT = false;
            isRIGHT = false;
        }else if(k.a){
            camX+= SPEED;
            // set the left image
            img = p.left;
            isLEFT = true;
            isUP = false;
            isDOWN = false;
            isRIGHT = false;
        }else if(k.s){
            camY-= SPEED;
            // set the down image
            img = p.down;
            isDOWN = true;
            isUP = false;
            isLEFT = false;
            isRIGHT = false;
        }else if(k.d){
            camX-= SPEED;
            // set the right image
            img = p.right;
            isRIGHT = true;
            isUP = false;
            isDOWN = false;
            isLEFT = false;
        }else{
            // set the standard image
            img = p.std;
        }

    }

    public void isPlayerColliding(){

        if(isColliding){    
            SPEED = 0;
        }else{
            SPEED = 4;
        }

    }


    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // draw the map

        d.drawTile(g2, camX, camY, HITBOX_X, HITBOX_Y, isUP, isDOWN, isLEFT, isRIGHT);

        // draw the player

        d.drawPlayer(g2, img, WIDTH/2 - 55, HEIGHT/2 - 55);

        // draw the bots

        d.drawBot(g2, img, 1224 + camX, 572 + camY);
        if(!isDialogBlocked && isDialog){
            StringTokenizer st = new StringTokenizer(dialogText, "\n");
            d.drawDialog(g2, 1250 + camX, 500 + camY, st.nextToken(), st.nextToken());
        }

        if(!isKeyDialogBlocked && isKeyDialog){
            d.drawHeadDialog(g2, 1850 + camX, 1000 + camY, "Non puoi accedere alla chiave," , "Se non hai raggiunto 500xp!");
        }
            

        g2.dispose();

        // manage colliding

        isColliding = d.c.collision;
        isPlayerColliding();

        d.c.collision = false;

    }



}