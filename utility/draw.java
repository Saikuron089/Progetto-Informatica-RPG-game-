package utility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

// map

import utility.worldMap;

public class draw {

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

        public draw() {
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

        public void drawTile(Graphics2D g2, int camX, int camY, int actualX, int actualY, boolean isUP, boolean isDOWN, boolean isLEFT, boolean isRIGHT) {

                int a = 0; // x
                int b = 0; // y

                int map[][] = w.returnMap();

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
                                } else if (map[b][a] == 6) {
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.chest_closed, i + camX, j + camY, 64, 64, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT);
                                } else if (map[b][a] == 7) {
                                        // wall verticale
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.wall, i + camX + 18, j + camY, 32, 64, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT);
                                } else if (map[b][a] == 8) {
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.chest_opened, i + camX, j + camY, 64, 64, null);
                                } else if (map[b][a] == 9) {
                                        // wall orizzontale
                                        g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                                        g2.drawImage(o.wall, i + camX, j + camY, 92, 32, null);
                                        c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN,
                                                        isLEFT, isRIGHT);
                                }

                                if (b < numRows)
                                        b++;
                        }
                        a++;
                        b = 0;
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

        public void drawBot(Graphics2D g2, BufferedImage img, int x, int y) {

                g2.drawImage(p.std, x, y, 88, 88, null);

        }

        public void drawDialog(Graphics2D g2, int x, int y, String text1, String text2) {
                g2.setColor(Color.WHITE);
                g2.fillRect(x, y, 220, 80);

                // inserire scritta dentro il rettangolo

                g2.setColor(Color.BLACK);
                g2.drawString(text1, x + 10, y + 20);
                g2.drawString(text2, x + 10, y + 40);

        }

        public void drawHeadDialog(Graphics2D g2, int x, int y, String text1, String text2) {

                g2.setColor(Color.WHITE);
                g2.fillRect(x, y, 220, 80);

                // inserire scritta dentro il rettangolo

                g2.setColor(Color.BLACK);
                g2.drawString(text1, x + 10, y + 20);
                g2.drawString(text2, x + 10, y + 40);

        }

}
