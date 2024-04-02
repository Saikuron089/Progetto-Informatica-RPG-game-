package utility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import java.util.*;
import java.io.*;
import java.lang.*;

import javax.swing.JLabel;

public class draw {

        // screen dimension

        public final int WIDTH = 1024;
        public final int HEIGHT = 768;
        public int finishedGame = 0;

        // classi

        public fight f = new fight();
        public playerLoad p = new playerLoad();
        object o = new object();
        public collision c = new collision();
        worldMap w = new worldMap();

        // for opacity

        Color col = new Color(0f, 0f, 0f, .6f);
        Color col1 = new Color(0f, 0f, 0f, .3f);

        // for fight screen

        public final int baseXDialog = 225;
        public final int baseYDialog = 500;

        public final int baseXEnemy = 365;
        public final int baseYEnemy = 50;

        // (NPC)

        Vector<String> botText = new Vector<String>();
        public boolean dialogOpen = false;
        public boolean dialogBlock = false;

        public draw() {

                // carica frasi

                try{

                        FileReader f = new FileReader("frasiNPC.txt");
                        BufferedReader fIN = new BufferedReader(f);

                        String line = fIN.readLine();

                        while(line != null) {
                                botText.addElement(line);
                                line = fIN.readLine();
                        }

                        f.close();


                }catch(Exception e) {
                        System.out.println("Errore caricamento frasi");
                }

        }

        public void drawPlayer(Graphics2D g2, BufferedImage img, int x, int y) {

                g2.setColor(Color.WHITE);
                g2.fillOval(x + 5, y - 10, 80, 20);
                g2.setColor(Color.BLACK);
                g2.drawString("Exp: " + p.exp, x + 18, y + 5);

                if (p.firstKey)
                        g2.drawImage(o.key, x + 90, y - 10, 20, 20, null);

                if (p.secondKey)
                        g2.drawImage(o.key, x + 110, y - 10, 20, 20, null);

                g2.drawImage(img, x, y, 92, 92, null);

        }

        public void drawBot(Graphics2D g2, BufferedImage img, int x, int y, String text, boolean isFight) {
                if(dialogOpen && !dialogBlock)
                        drawDialog(g2, x + 30, y - 80, text);
                
                if(isFight)
                        drawDialog(g2, x + 30, y - 80, text);

                g2.drawImage(img, x, y, 64, 64, null);

                // check if is NPC is near the player

                if(x > (WIDTH / 2 - 140) && x < (WIDTH / 2 + 20) && y > (HEIGHT / 2 - 130) && y < (HEIGHT / 2 + 10) && !dialogBlock) {
                        dialogOpen = true;
                }
        }

        public void drawDialog(Graphics2D g2, int x, int y, String text) {
                g2.setColor(Color.WHITE);
                g2.fillRect(x, y, 300, 80);

                // inserire scritta dentro il rettangolo

                g2.setColor(Color.BLACK);
                g2.drawString(text, x + 10, y + 20);

        }

        public void drawTile(Graphics2D g2, int camX, int camY, int actualX, int actualY, boolean isUP, boolean isDOWN, boolean isLEFT, boolean isRIGHT) {

                int a = 0; // x
                int b = 0; // y

                int map[][] = w.returnMap();

                // set open chest if the player has the key

                if (p.firstKey)
                        map[60][7] = 8;

                int numRows = map.length;
                int numCols = map[0].length;

                for (int i = 0; i < (numCols * 64); i += 64) {
                        for (int j = 0; j < (numRows * 64); j += 64) {

                                if (map[b][a] == 0)
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                else if (map[b][a] == 1) {
                                        g2.drawImage(o.acqua, i + camX, j + camY, 64, 64, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT);
                                } else if (map[b][a] == 2) {
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.montagna, i + camX, j + camY, 64, 64, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT);
                                } else if (map[b][a] == 3) {
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.strada, i + camX, j + camY, 64, 64, null);
                                } else if (map[b][a] == 4) {
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.albero, i + camX, j + camY, 64, 64, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT);
                                } else if (map[b][a] == 5) {
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.grano, i + camX, j + camY, 64, 64, null);
                                }else if (map[b][a] == 7) {
                                        // wall verticale
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.wall, i + camX + 18, j + camY, 32, 64, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT);
                                } else if (map[b][a] == 8) {
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.chest_opened, i + camX, j + camY, 64, 64, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN, isLEFT, isRIGHT);
                                } else if (map[b][a] == 9) {
                                        // wall orizzontale
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.wall, i + camX, j + camY, 92, 32, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT);
                                }else if (map[b][a] == 6) {
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.chest_closed, i + camX, j + camY, 64, 64, null);
                                        finishedGame = c.checkChest(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT, p);                                
                                }

                                if (b < numRows)
                                        b++;
                        }
                        a++;
                        b = 0;
                }

                if(finishedGame == 1){
                        p.firstKey = true;
                        drawDialog(g2, actualX, actualY - 150, "hai vinto! Premi invio per uscire");
                }else if(finishedGame == 2){
                        drawDialog(g2, actualX, actualY - 150, "Non hai 1000xp");
                }

        }

        public void drawFight(Graphics2D g2, JLabel enemyLabel) {

                g2.setColor(col);
                g2.fillRect(0, 0, 2024, 2768);

                g2.setColor(Color.WHITE);

                // render enemy box

                g2.fillRect(baseXEnemy, baseYEnemy, 320, 400);

                g2.drawImage(o.back1, 365, 50, 320, 400, null);

                g2.setColor(col1);
                g2.fillRect(baseXEnemy, baseYEnemy, 320, 400);

                g2.drawImage(f.imgBot, baseXEnemy + 58, baseYEnemy + 180, 204, 204, null);

                g2.setColor(Color.WHITE);

                g2.fillRect(baseXEnemy + 62, baseYEnemy + 110, 200, 50);

                g2.setColor(Color.BLACK);

                enemyLabel.setBounds(baseXEnemy + 72, baseYEnemy + 130, 200, 10);
                enemyLabel.setText("PE: " + f.pe + " - Difesa: " + f.activeDefenseEnemy);
                enemyLabel.setVisible(true);

                // render dialog box

                g2.setColor(Color.white);
                g2.fillRect(baseXDialog, baseYDialog, 600, 250);

        }

        public void drawNPC(Graphics2D g2, int camX, int camY) {

                drawBot(g2, o.bots1, 1050 + camX, 1750 + camY, botText.elementAt(0), false);
                drawBot(g2, o.bots2, 2050 + camX, 3750 + camY, botText.elementAt(1), false);
                drawBot(g2, o.bots1, 1050 + camX, 3750 + camY, botText.elementAt(2), false);
                drawBot(g2, o.bots3, 2050 + camX, 1750 + camY, botText.elementAt(3), false);
                drawBot(g2, o.bots4, 1050 + camX, 2750 + camY, botText.elementAt(4), false);
                drawBot(g2, o.bots1, 2050 + camX, 2750 + camY, botText.elementAt(5), false);
                drawBot(g2, o.bots2, 1050 + camX, 4750 + camY, botText.elementAt(6), false);
                drawBot(g2, o.bots3, 2050 + camX, 4750 + camY, botText.elementAt(7), false);
                drawBot(g2, o.bots4, 1050 + camX, 5750 + camY, botText.elementAt(8), false);
                drawBot(g2, o.bots1, 2050 + camX, 5750 + camY, botText.elementAt(9), false);
                drawBot(g2, o.bots4, 1050 + camX, 6750 + camY, botText.elementAt(10), false);
                drawBot(g2, o.bots2, 2050 + camX, 6750 + camY, botText.elementAt(11), false);
                drawBot(g2, o.bots1, 1050 + camX, 7750 + camY, botText.elementAt(12), false);
                drawBot(g2, o.bots3, 2050 + camX, 7750 + camY, botText.elementAt(13), false);
                drawBot(g2, o.bots2, 1050 + camX, 8750 + camY, botText.elementAt(14), false);
                drawBot(g2, o.bots1, 2050 + camX, 8750 + camY, botText.elementAt(15), false);

        }

}
