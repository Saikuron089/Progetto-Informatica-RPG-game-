package utility;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import screen.Map;

public class fight {

    public int pe;
    public BufferedImage imgBot;
    public int playerPE;

    public int attack1;
    public int defense1;
    public int healing1;

    public int attack2;
    public int defense2;
    public int healing2;

    public int attack3;
    public int defense3;
    public int healing3;

    public boolean isHealingBlocked = false;
    public int activeDefenseEnemy = 0;
    public int activeDefensePlayer = 0;

    // GUI

    boolean isFirstUse = false;
    boolean isSecondUse = false;
    boolean isThirdUse = false;


    public fight() {}

    public void newFight() {

        System.out.println("New fight");

        isHealingBlocked = false;
        activeDefenseEnemy = 0;
        activeDefensePlayer = 0;
        isFirstUse = false;
        isSecondUse = false;
        isThirdUse = false;

        try {

            FileReader f = new FileReader("user.txt");
            BufferedReader fIN = new BufferedReader(f);

            String line = fIN.readLine();

            StringTokenizer st = new StringTokenizer(line, "-");

            st.nextToken();
            playerPE = Integer.parseInt(st.nextToken());

            Random r = new Random();
            pe = playerPE + (r.nextInt(30) - 15);
            System.out.println("Player: " + playerPE);
            System.out.println("enemy: " + pe);

            imgBot = ImageIO.read(getClass().getResourceAsStream("/assets/bots/bot1.png"));

            f.close();

            // update attack, defense and healing based of exp

            int baseExp = playerPE / 2;

            attack1 = baseExp / (r.nextInt(3) + 1);
            defense1 = baseExp / (r.nextInt(3) + 1);
            healing1 = baseExp / (r.nextInt(3) + 1);

            attack2 = baseExp / (r.nextInt(3) + 1);
            defense2 = baseExp / (r.nextInt(3) + 1);
            healing2 = baseExp / (r.nextInt(3) + 1);

            attack3 = baseExp / (r.nextInt(3) + 1);
            defense3 = baseExp / (r.nextInt(3) + 1);
            healing3 = baseExp / (r.nextInt(3) + 1);

            // add or update player exp on attack, defense and healing



            f = new FileReader("potenziamenti_ottenuti.txt");
            fIN = new BufferedReader(f);

            line = fIN.readLine();

            while (line != null) {

                st = new StringTokenizer(line, "-");

                st.nextToken();
                st.nextToken();

                int attack = Integer.parseInt(st.nextToken());
                int defense = Integer.parseInt(st.nextToken());
                int healing = Integer.parseInt(st.nextToken());

                if(r.nextInt(3) == 0){
                    attack1 += attack;
                    defense1 += defense;
                    healing1 += healing;
                } else if(r.nextInt(3) == 1){
                    attack2 += attack;
                    defense2 += defense;
                    healing2 += healing;
                } else {
                    attack3 += attack;
                    defense3 += defense;
                    healing3 += healing;
                }

                line = fIN.readLine();
                
            }


            f.close();

        } catch (Exception e) {
            System.out.println("Errore: " + e);
        }

    }

    public String attack(int type){

        Random r = new Random();

        int hasEffect = r.nextInt(100);

        if(hasEffect == 45){

            // no effect (toglie pe al player = a quello dell'attacco scelto)

            System.out.println("No effetto");
            playerPE -= type == 1 ? attack1 : type == 2 ? attack2 : attack3;
            return "Hai attaccato ma non ha avuto effetto (- " + (type == 1 ? attack1 : type == 2 ? attack2 : attack3) + " PE)";
        } else {

            // effect (toglie pe al nemico = a quello dell'attacco scelto)

            System.out.println("Effetto");

            //pe -= ((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy) < 0 ? 0 : (type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy;

            pe -= type == 1 ? attack1 : type == 2 ? attack2 : attack3;

            // toglie la difesa attiva del nemico

            activeDefenseEnemy = 0;

            //return "Hai usato l'attacco " + (type == 1 ? "attacco1" : type == 2 ? "attacco2" : "attacco3") + ". Ha avuto effetto (- " + (((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy) < 0 ? 0 : (type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy) + " PE)";
            return "<html>Hai attaccato e ha avuto effetto <br>(- " + (type == 1 ? attack1 : type == 2 ? attack2 : attack3) + " PE nemico)</html>";
        }

    }

    public String defense(int type){

        Random r = new Random();

        int hasEffect = r.nextInt(100);

        if(hasEffect == 45){

            // no effect (toglie pe al player = a quello della difesa scelta)

            System.out.println("No effetto");
            playerPE -= type == 1 ? defense1 : type == 2 ? defense2 : defense3;
            return "Hai usato la difesa e non ha avuto effetto";
        } else {

            // effect (toglie pe al nemico = a quello della difesa scelta)

            System.out.println("Effetto");
            activeDefensePlayer = type == 1 ? defense1 : type == 2 ? defense2 : defense3;
            return "<html>Hai usato la difesa e ha avuto effetto <br>(+ " + (type == 1 ? defense1 : type == 2 ? defense2 : defense3) + " Defense)</html>";
        }

    }

    public String healing(int type){

        if(isHealingBlocked){
            return "Hai usato la cura! Non ha avuto effetto.";
        }else{

            // healing (aggiunge pe al player = a quello della cura scelta)

            playerPE += type == 1 ? healing1 : type == 2 ? healing2 : healing3;
            return "<html>Ti sei curato! <br>(+ " + (type == 1 ? healing1 : type == 2 ? healing2 : healing3) + " PE)</html>";
        }

    }

    public String enemyTurn(){

        // choose attack, defense or healing

        Random r = new Random();

        int type = r.nextInt(3) + 1;

        if(type == 0){

            // attack

            type = r.nextInt(3) + 1;

            int hasEffect = r.nextInt(100);

            if(hasEffect == 45){
                System.out.println("No effetto");
                return "Il nemico ha usato l'attacco. Non ha avuto effetto.";
            } else {
                System.out.println("Effetto");

                playerPE -= ((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefensePlayer) < 0 ? 0 : ((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefensePlayer);

                activeDefensePlayer = 0;

                return "<html>Il nemico ha usato l'attacco. <br>Ha avuto effetto (- " + (type == 1 ? attack1 : type == 2 ? attack2 : attack3) + " PE)</html>";
            }

        } else if(type == 1){
            
            // defense

            type = r.nextInt(3) + 1;

            int hasEffect = r.nextInt(100);

            if(hasEffect == 45){
                System.out.println("No effetto");
                return "Il nemico ha usato la difesa. Non ha avuto effetto.";
            } else {

                // defense (aggiunge pe al nemico = a quello della difesa scelta)

                System.out.println("Effetto");
                activeDefenseEnemy = type == 1 ? defense1 : type == 2 ? defense2 : defense3;
                return "<html>Il nemico ha usato la difesa. <br>Ha avuto effetto (+ " + (type == 1 ? defense1 : type == 2 ? defense2 : defense3) + " Defense nemico)</html>";
            }

        } else {
            
            // healing

            type = r.nextInt(3) + 1;

            int hasEffect = r.nextInt(100);

            if(hasEffect == 45){
                System.out.println("No effetto");
                return "Il nemico ha usato la cura. Non ha avuto effetto.";
            } else {
                System.out.println("Effetto");
                pe += type == 1 ? healing1 : type == 2 ? healing2 : healing3;
                return "<html>Il nemico si è curato.<br>(+ " + (type == 1 ? defense1 : type == 2 ? defense2 : defense3) + " PE)</html>";
            }

        }

    }

    public void cleanActionListener(Button b1, Button b2, Button b3){

        // reset action listener

        ActionListener[] listeners = b1.getActionListeners();
        for(ActionListener listener : listeners) {
            b1.removeActionListener(listener);
        }
        
        listeners = b2.getActionListeners();
        for(ActionListener listener : listeners) {
            b2.removeActionListener(listener);
        }

        listeners = b3.getActionListeners();
        for(ActionListener listener : listeners) {
            b3.removeActionListener(listener);
        }

    }

    public void fighting(Map m, JFrame f, Button b1, Button b2, Button b3, Button back, JLabel info, JLabel enemyLabel){


        /***************************************************** */
        // ACTION LISTENER

        ActionListener base1 = e -> {
            isFirstUse = true;
            m.f.requestFocus();
        };

        ActionListener base2 = e -> {
            isSecondUse = true;
            m.f.requestFocus();
        };

        ActionListener base3 = e -> {
            isThirdUse = true;
            m.f.requestFocus();
        };

        /*********************************************************** */

        // back button

        back.addActionListener(e -> {

            m.actionBlocked = false;

            isFirstUse = false;
            isSecondUse = false;
            isThirdUse = false;

            // reset action listener

            cleanActionListener(b1, b2, b3);

            // add base option

            b1.addActionListener(base1);
            b2.addActionListener(base2);
            b3.addActionListener(base3);

            m.f.requestFocus();
        });

        // base option **********************************

        if(!isFirstUse && !isSecondUse && !isThirdUse){

            b1.setLabel("Attacco");
            b2.setLabel("Difesa");
            b3.setLabel("Cura");
            info.setVisible(false);

            b1.addActionListener(base1);
            b2.addActionListener(base2);
            b3.addActionListener(base3);
        }

        // end base option **********************************

        // attack option ************************************

        if(isFirstUse){
            cleanActionListener(b1, b2, b3);
            b1.setLabel("Attacco1 (" + attack1 + ")");
            b2.setLabel("Attacco2 (" + attack2 + ")");
            b3.setLabel("Attacco3 (" + attack3 + ")");

            b1.addActionListener(e -> {

                if(m.actionBlocked)
                    return;
                info.setText(attack(1));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });

            b2.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(attack(2));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });

            b3.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(attack(3));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });
        }else if(isSecondUse){ 

            cleanActionListener(b1, b2, b3);
            // defense option ************************************

            b1.setLabel("Difesa1 (" + defense1 + ")");
            b2.setLabel("Difesa2 (" + defense2 + ")");
            b3.setLabel("Difesa3 (" + defense3 + ")");

            b1.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(defense(1));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });

            b2.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(defense(2));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });

            b3.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(defense(3));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });

        }else if(isThirdUse){   

            cleanActionListener(b1, b2, b3);
            // healing option ************************************

            b1.setLabel("Cura1 (" + healing1 + ")");
            b2.setLabel("Cura2 (" + healing2 + ")");
            b3.setLabel("Cura3 (" + healing3 + ")");

            b1.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(healing(1));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });

            b2.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(healing(2));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });

            b3.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(healing(3));
                info.setVisible(true);
                m.actionBlocked = true;
                m.f.requestFocus();
            });
        }
        


    }



}
