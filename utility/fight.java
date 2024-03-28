package utility;

import java.awt.Button;
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
            return "Hai usato l'attacco " + (type == 1 ? "attacco1" : type == 2 ? "attacco2" : "attacco3") + ". Non ha avuto effetto (- " + (type == 1 ? attack1 : type == 2 ? attack2 : attack3) + " PE)";
        } else {

            // effect (toglie pe al nemico = a quello dell'attacco scelto)

            System.out.println("Effetto");

            pe -= ((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy) < 0 ? 0 : (type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy;

            // toglie la difesa attiva del nemico

            activeDefenseEnemy = 0;

            return "Hai usato l'attacco " + (type == 1 ? "attacco1" : type == 2 ? "attacco2" : "attacco3") + ". Ha avuto effetto (- " + (((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy) < 0 ? 0 : (type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy) + " PE)";
        }

    }

    public String defense(int type){

        Random r = new Random();

        int hasEffect = r.nextInt(100);

        if(hasEffect == 45){

            // no effect (toglie pe al player = a quello della difesa scelta)

            System.out.println("No effetto");
            playerPE -= type == 1 ? defense1 : type == 2 ? defense2 : defense3;
            return "Hai usato la difesa " + (type == 1 ? "difesa1" : type == 2 ? "difesa2" : "difesa3") + ". Non ha avuto effetto (- " + (type == 1 ? defense1 : type == 2 ? defense2 : defense3) + " PE)";
        } else {

            // effect (toglie pe al nemico = a quello della difesa scelta)

            System.out.println("Effetto");
            activeDefensePlayer = type == 1 ? defense1 : type == 2 ? defense2 : defense3;
            return "Hai usato l'attacco " + (type == 1 ? "difesa1" : type == 2 ? "difesa2" : "difesa3") + ". Ha avuto effetto";
        }

    }

    public String healing(int type){

        if(isHealingBlocked){
            return "Hai usato la cura! Non ha avuto effetto.";
        }else{

            // healing (aggiunge pe al player = a quello della cura scelta)

            playerPE += type == 1 ? healing1 : type == 2 ? healing2 : healing3;
            return "Ti sei curato! (+ " + (type == 1 ? healing1 : type == 2 ? healing2 : healing3) + " PE)";
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

                return "Il nemico ha usato l'attacco. Ha avuto effetto (- " + (((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefensePlayer) < 0 ? 0 : ((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefensePlayer)) + " PE)";
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
                return "Il nemico ha usato la difesa. Ha avuto effetto (+ " + (type == 1 ? defense1 : type == 2 ? defense2 : defense3) + " PE)";
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
                return "Il nemico si è curato. Ha avuto effetto (+ " + (type == 1 ? defense1 : type == 2 ? defense2 : defense3) + " PE)";
            }

        }

    }

    public void fighting(Map m, JFrame f, Button b1, Button b2, Button b3, Button back, JLabel info){

        // base option **********************************

        if(!isFirstUse && !isSecondUse && !isThirdUse){

            b1.setLabel("Attacco");
            b2.setLabel("Difesa");
            b3.setLabel("Cura");
            info.setVisible(false);

            b1.addActionListener(e -> {
                
                isFirstUse = true;
                m.f.requestFocus();

            });

            b2.addActionListener(e -> {
                
                isSecondUse = true;
                m.f.requestFocus();

            });

            b3.addActionListener(e -> {

                isThirdUse = true;
                m.f.requestFocus();
                
            });
        }

        // end base option **********************************

        // attack option ************************************

        if(isFirstUse){
            b1.setLabel("Attacco1");
            b2.setLabel("Attacco2");
            b3.setLabel("Attacco3");

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
        }
        

    }



}
