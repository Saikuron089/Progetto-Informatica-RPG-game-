package screen;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// input

import inputs.keyboardInput;

// draw

import utility.draw;

public class Map extends JPanel implements Runnable {

    BufferedImage img;
    Thread gameThread;
    final int FPS = 60;
    JFrame f = new JFrame("Gioco");
    Random r = new Random();

    // setup the map

    draw d = new draw();

    // screen settings

    keyboardInput k = new keyboardInput();
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

    // dialog settings

    boolean isDialog = false;
    boolean isDialogBlocked = false;
    int dialogIndex = 0;
    String dialogText = "Ho sentito parlare di te! \nSei il nuovo eroe del villaggio?";

    boolean isKeyDialog = false;
    boolean isKeyDialogBlocked = false;

    // fight

    boolean isFight = true; // for the screen render
    boolean isFound = false; // for the initial dialog
    JLabel title = new JLabel(); // title
    JLabel info = new JLabel(); // info during the fight
    Button firstUse = new Button();
    Button secondUse = new Button();
    Button thirdUse = new Button();
    Button backAction = new Button("<-");

    boolean isFirstUse = false;
    boolean isSecondUse = false;
    boolean isThirdUse = false;

    // camera settings

    int camX = -360;
    int camY = -168;

    public Map() {

        firstUse.setVisible(false);
        secondUse.setVisible(false);
        thirdUse.setVisible(false);
        backAction.setVisible(true);
        info.setVisible(false);

        f.add(firstUse);
        f.add(secondUse);
        f.add(thirdUse);
        f.add(title);
        f.add(backAction);
        f.add(info);

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

            // normal condition

            if (!isFight) {

                // check if dialog is open

                dialog();

                // take eventually the keys

                keys();

                // update

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

    public void keys() {

        if (camX < -1250 && camX > -1320 && camY < -640 && camY > -850 && d.p.exp >= 500 && !d.p.firstKey) {
            d.p.firstKey = true;
            d.p.getFirstKey();
        }

    }

    public void dialog() {

        // dialog

        if (camX < -700 && camX > -800 && camY < -180 && camY > -280 && dialogIndex == 0 && !isDialogBlocked) {
            isDialog = true;
            dialogIndex = 1;
        } else if (dialogIndex == 1 && k.enter) {
            dialogIndex++;
            k.enter = false;
        } else if (dialogIndex == 2 && k.enter) {
            dialogIndex++;
            k.enter = false;
        } else if (dialogIndex == 3) {
            dialogIndex = 0;
            isDialog = false;
            isDialogBlocked = true;
        }

        if (camX < -1200 && camX > -1300 && camY < -640 && camY > -850 && d.p.exp < 500) {
            isKeyDialog = true;
        }

    }

    public void update() {

        // System.out.println("CamX: " + camX + " CamY: " + camY);

        if (isDialog && dialogIndex == 1) {
            SPEED = 0;
            System.out.println("Dialog 1");
        } else if (isDialog && dialogIndex == 2) {
            dialogText = "Mi hanno detto che sei forte! \nMa stai attento, il nemico è vicino!";
            SPEED = 0;
            System.out.println("Dialog 2");
        } else if (isKeyDialog && !isKeyDialogBlocked) {
            SPEED = 0;
            if (k.enter) {
                isKeyDialog = false;
                isKeyDialogBlocked = true;
            }
            System.out.println("Dialog Key");
        }

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

            if (r.nextInt(100) == 69) {
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

        // draw the bots

        d.drawBot(g2, img, 1224 + camX, 572 + camY);
        if (!isDialogBlocked && isDialog) {
            StringTokenizer st = new StringTokenizer(dialogText, "\n");
            d.drawDialog(g2, 1250 + camX, 500 + camY, st.nextToken(), st.nextToken());
        }

        if (!isKeyDialogBlocked && isKeyDialog) {
            d.drawHeadDialog(g2, 1850 + camX, 1000 + camY, "Non puoi accedere alla chiave,",
                    "Se non hai raggiunto 500xp!");
        }

        // draw bot for fight

        if (isFound) {
            d.drawDialog(g2, WIDTH / 2 + 50, HEIGHT / 2 - 145, "Adesso ti uccido!", "");
            d.drawBot(g2, img, WIDTH / 2, HEIGHT / 2 - 55);
        }

        if (isFound && k.enter) {
            isFound = false;
            isFight = true;
            repaint();
            d.f.newFight();
        }

        // manage colliding

        isColliding = d.c.collision;
        isPlayerColliding();

        d.c.collision = false;

        if (isFight) {

            d.drawFight(g2);

            // scritte

            // title

            title.setVisible(true);
            title.setForeground(Color.BLACK);
            title.setBounds(d.baseXDialog + 15, d.baseYDialog - 20, 400, 100);
            title.setText("Scegli il combattimento");
            title.setFont(new Font("Arial", Font.PLAIN, 24));

            // info

            info.setVisible(true);
            info.setForeground(Color.BLACK);
            info.setBounds(d.baseXDialog + 320, d.baseYDialog + 70, 400, 100);
            info.setText("Hai usato 'attacco 1'! Non è efficace!");
            title.setFont(new Font("Arial", Font.PLAIN, 18));

            // bottoni

            backAction.setVisible(true);
            backAction.setBounds(d.baseXDialog + 550, d.baseYDialog + 10, 40, 40);

            backAction.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    isFirstUse = false;
                    isSecondUse = false;
                    isThirdUse = false;

                    repaint();

                }
            });

            // attack, defense, healing

            firstUse.setVisible(true);
            firstUse.setForeground(Color.BLACK);
            firstUse.setBounds(d.baseXDialog + 15, d.baseYDialog + 60, 250, 30);
            firstUse.setFont(new Font("Arial", Font.PLAIN, 24));

            firstUse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    isFirstUse = true;

                }
            });

            secondUse.setVisible(true);
            secondUse.setForeground(Color.BLACK);
            secondUse.setBounds(d.baseXDialog + 15, d.baseYDialog + 120, 250, 30);
            secondUse.setFont(new Font("Arial", Font.PLAIN, 24));

            secondUse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    isSecondUse = true;

                }
            });

            thirdUse.setVisible(true);
            thirdUse.setForeground(Color.BLACK);
            thirdUse.setBounds(d.baseXDialog + 15, d.baseYDialog + 180, 250, 30);
            thirdUse.setFont(new Font("Arial", Font.PLAIN, 24));

            thirdUse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    isThirdUse = true;

                }
            });

            // label to button

            if (!isFirstUse && !isSecondUse && !isThirdUse) {

                firstUse.setLabel("Attacco");
                secondUse.setLabel("Difesa");
                thirdUse.setLabel("Curati");

            } else if (isFirstUse) {

                firstUse.setLabel("Attacco1 - " + d.f.attack1);
                secondUse.setLabel("Attacco2 - " + d.f.attack2);
                thirdUse.setLabel("Attacco3 - " + d.f.attack3);

                firstUse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        d.f.attack(info, 1);

                    }
                });

                secondUse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        d.f.attack(info, 2);

                    }
                });

                thirdUse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        d.f.attack(info, 3);

                    }
                });

            } else if (isSecondUse) {
                firstUse.setLabel("Difesa1 - " + d.f.defense1);
                secondUse.setLabel("Difesa2 - " + d.f.defense2);
                thirdUse.setLabel("Difesa3 - " + d.f.defense3);

                secondUse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // gestire difesa

                    }
                });

            } else if (isThirdUse) {
                firstUse.setLabel("Cura1 - " + d.f.healing1);
                secondUse.setLabel("Cura2 - " + d.f.healing2);
                thirdUse.setLabel("Cura3 - " + d.f.healing3);

                thirdUse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // gestire cura

                    }
                });

            }

        }

        g2.dispose();

    }

    public static void main(String[] args) {
        new Map();
    }

}