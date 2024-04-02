package screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;
import java.io.*;
import java.lang.*;

// input

import inputs.keyboardInput;

// draw

import utility.draw;
import utility.object;

public class Map extends JPanel implements Runnable {

    object o = new object();
    BufferedImage img;
    Thread gameThread;
    final int FPS = 60;
    public JFrame f = new JFrame("Gioco");
    Random r = new Random();
    final int tickDialog = (int) (FPS * 1.5);
    int countTickDialog = 0;

    boolean fightDialog = false;

    // setup the map

    public draw d = new draw();

    // screen settings

    public keyboardInput k = new keyboardInput();
    final int WIDTH = 1024;
    final int HEIGHT = 768;

    // player info

    final int HITBOX_X = WIDTH / 2 - 17;
    final int HITBOX_Y = HEIGHT / 2 + 10;
    int SPEED = 4;
    boolean isColliding = false;
    boolean isUP = false;
    boolean isDOWN = false;
    boolean isLEFT = false;
    boolean isRIGHT = false;
    boolean getKey = false;

    // fight

    int botProbability = 600;

    public boolean isFight = false; // for the screen render
    public boolean isFound = false; // for the initial dialog
    public JLabel title = new JLabel(); // title
    public JLabel info = new JLabel(); // info during the fight
    public Button firstUse = new Button();
    public Button secondUse = new Button();
    public Button thirdUse = new Button();
    public Button backAction = new Button("<-");
    public JLabel enemyLabel = new JLabel("PE: ");

    public boolean actionBlocked = false;

    // camera settings

    int camX = -360;
    int camY = -268;

    public Map() {

        firstUse.setVisible(false);
        secondUse.setVisible(false);
        thirdUse.setVisible(false);
        backAction.setVisible(false);
        info.setVisible(false);
        enemyLabel.setVisible(false);

        f.add(firstUse);
        f.add(secondUse);
        f.add(thirdUse);
        f.add(title);
        f.add(backAction);
        f.add(info);
        f.add(enemyLabel);

        f.setIconImage(d.p.std);
        f.add(this);
        f.addKeyListener(k);
        f.setSize(WIDTH + 16, HEIGHT + 38);
        f.setVisible(true);
        f.setResizable(false);

        // setup the image

        img = d.p.std;

        // start the game thread

        startGameThread();

        // close the window

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            if(d.finishedGame == 1 && k.enter){
                System.exit(0);
            }else if(d.finishedGame == 1 && !k.enter){
                SPEED = 0;
            }

            // block dialog

            if(d.dialogBlock && countTickDialog < tickDialog){
                countTickDialog++;
            }else if(d.dialogBlock && countTickDialog >= tickDialog){
                d.dialogBlock = false;
                countTickDialog = 0;
            }

            if(actionBlocked && k.enter){
                System.out.println("sbloccato");
                actionBlocked = false;
            }

            // normal condition

            if (!isFight) {

                // update

                dialog();

                repaint();

                update();
            }

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (Exception e) {

                System.out.println("Erorre: " + e);

            }

        }

    }

    public void dialog(){

        if(d.dialogOpen && !k.enter){
            SPEED = 0;
        }else if(d.dialogOpen && k.enter){
            System.out.println("Dialogo chiuso!");
            d.dialogOpen = false;
            d.dialogBlock = true;
            SPEED = 4;
        }

    }


    public void update() {

        //System.out.println("CamX: " + camX + " CamY: " + camY);

        // controlla che non esca dai bordi

        if (k.w) {
            camY += SPEED;
            // set the up image
            img = d.p.up;
            isUP = true;
            isDOWN = false;
            isLEFT = false;
            isRIGHT = false;
        } else if (k.a) {
            camX += SPEED;
            // set the left image
            img = d.p.left;
            isLEFT = true;
            isUP = false;
            isDOWN = false;
            isRIGHT = false;
        } else if (k.s) {
            camY -= SPEED;
            // set the down image
            img = d.p.down;
            isDOWN = true;
            isUP = false;
            isLEFT = false;
            isRIGHT = false;
        } else if (k.d) {
            camX -= SPEED;
            // set the right image
            img = d.p.right;
            isRIGHT = true;
            isUP = false;
            isDOWN = false;
            isLEFT = false;
        } else {
            // set the standard image
            img = d.p.std;
        }

        if (k.a || k.d || k.w || k.s) {

            // random scontro con il bot

            if (r.nextInt(botProbability) == 69) {
                System.out.println("Scontro con il bot!");
                isFound = true;
            }
        }

    }

    public void isPlayerColliding() {

        if (isColliding) {
            SPEED = 0;
        } else if (isFound) {
            SPEED = 0;
        } else {
            SPEED = 4;
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // draw the map

        d.drawTile(g2, camX, camY, HITBOX_X, HITBOX_Y, isUP, isDOWN, isLEFT, isRIGHT);

        // draw the player

        d.drawPlayer(g2, img, WIDTH / 2 - 55, HEIGHT / 2 - 55);

        // draw bot for fight

        if (isFound) {
            d.drawBot(g2, o.bots1 , WIDTH / 2, HEIGHT / 2 - 55, "Non puoi scappare ora!", isFound);
            d.dialogBlock = true;
        }

        if (isFound && k.enter) {
            isFound = false;
            isFight = true;
            repaint();
            d.f.newFight();
        }

        d.drawNPC(g2, camX, camY);

        // manage colliding

        isColliding = d.c.collision;
        isPlayerColliding();

        d.c.collision = false;

        if (isFight) {

            d.drawFight(g2, enemyLabel);

            // scritte

            // title

            title.setVisible(true);
            title.setForeground(Color.BLACK);
            title.setBounds(d.baseXDialog + 15, d.baseYDialog - 20, 535, 100);
            title.setText("Scegli il combattimento (PE: " + d.f.playerPE + ") - (Difesa: " + d.f.activeDefensePlayer + ")");
            title.setFont(new Font("Arial", Font.PLAIN, 20));

            // info

            info.setForeground(Color.BLACK);
            info.setBounds(d.baseXDialog + 320, d.baseYDialog + 70, 400, 100);

            // bottoni

            // backaction

            backAction.setVisible(true);
            backAction.setBounds(d.baseXDialog + 550, d.baseYDialog + 10, 40, 40);

            // attack or first

            firstUse.setVisible(true);
            firstUse.setForeground(Color.BLACK);
            firstUse.setBounds(d.baseXDialog + 15, d.baseYDialog + 60, 250, 30);
            firstUse.setFont(new Font("Arial", Font.PLAIN, 24));

            // defense or second 

            secondUse.setVisible(true);
            secondUse.setForeground(Color.BLACK);
            secondUse.setBounds(d.baseXDialog + 15, d.baseYDialog + 120, 250, 30);
            secondUse.setFont(new Font("Arial", Font.PLAIN, 24));

            // healing or third

            thirdUse.setVisible(true);
            thirdUse.setForeground(Color.BLACK);
            thirdUse.setBounds(d.baseXDialog + 15, d.baseYDialog + 180, 250, 30);
            thirdUse.setFont(new Font("Arial", Font.PLAIN, 24));

            // action

            d.f.fighting(this, f, firstUse, secondUse, thirdUse, backAction, info, enemyLabel);

        }

        g2.dispose();

    }

    public static void main(String[] args) {
        new Map();
    }

}