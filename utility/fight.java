package utility;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;
import java.lang.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import screen.Map;

public class fight {

    public int pe;
    public BufferedImage imgBot;
    public int playerPE;

    // for the player

    public int attack1;
    public int defense1;
    public int healing1;

    public int attack2;
    public int defense2;
    public int healing2;

    public int attack3;
    public int defense3;
    public int healing3;

    // for the enemy

    public int attack1Enemy;
    public int defense1Enemy;
    public int healing1Enemy;

    public int attack2Enemy;
    public int defense2Enemy;
    public int healing2Enemy;

    public int attack3Enemy;
    public int defense3Enemy;
    public int healing3Enemy;

    // manage the fight

    public boolean isHealingBlocked = false;
    public int activeDefenseEnemy = 0;
    public int activeDefensePlayer = 0;

    public boolean playerTurn = true;
    public boolean enemyTurn = false;

    // GUI

    boolean isFirstUse = false;
    boolean isSecondUse = false;
    boolean isThirdUse = false;
    boolean closeInfo = false;
    boolean isFinished = false;


    public fight() {}

    public void newFight() {

        System.out.println("New fight");

        playerTurn = true;
        enemyTurn = false;
        isHealingBlocked = false;
        activeDefenseEnemy = 0;
        activeDefensePlayer = 0;
        isFirstUse = false;
        isSecondUse = false;
        isThirdUse = false;
        closeInfo = false;
        isFinished = false;

        try {

            FileReader f = new FileReader("user.txt");
            BufferedReader fIN = new BufferedReader(f);

            String line = fIN.readLine();

            StringTokenizer st = new StringTokenizer(line, "-");

            st.nextToken();
            playerPE = Integer.parseInt(st.nextToken());

            Random r = new Random();
            pe = playerPE + (r.nextInt(55) - 20);
            System.out.println("Player: " + playerPE);
            System.out.println("enemy: " + pe);

            imgBot = ImageIO.read(getClass().getResourceAsStream("/assets/bots/bot1.png"));

            f.close();

            // update attack, defense and healing based of exp

            int baseExp = playerPE / 3;

            if(playerPE <= 0){
                attack1 = 20;
                defense1 = 20;
                healing1 = 20;
                attack2 = 20;
                defense2 = 20;
                healing2 = 20;
                attack3 = 20;
                defense3 = 20;
                healing3 = 20;
            }else{

                attack1 = baseExp / (r.nextInt(3) + 1);
                defense1 = (int) ((baseExp / (r.nextInt(3) + 1)) * 1.3);    // più svantaggioso
                healing1 = (int) (baseExp / (r.nextInt(3) + 1) * 1.2);                    // medio vantaggioso
                
                do{
                    attack2 = baseExp / (r.nextInt(3) + 1);
                    defense2 = baseExp / (r.nextInt(3) + 1);                    // più vantaggioso
                    healing2 = baseExp / (r.nextInt(3) + 1);                    // più vantaggioso
                }while(attack2 != attack1 && defense2 != defense1 && healing2 != healing1);

                do{
                    attack3 = baseExp / (r.nextInt(3) + 1);
                    defense3 = (int) ((baseExp / (r.nextInt(3) + 1)) * 1.2);    // medio svantaggioso
                    healing3 = (int) (baseExp / (r.nextInt(3) + 1) * 1.3);      // più svantaggioso
                }while(attack3 != attack1 && defense3 != defense1 && healing3 != healing1 && attack3 != attack2 && defense3 != defense2 && healing3 != healing2);

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
                        attack1 += attack / 2;
                        defense2 += defense / 2;
                        healing3 += healing / 2;
                    } else if(r.nextInt(3) == 1){
                        attack3 += attack / 2;
                        defense1 += defense / 2;
                        healing2 += healing / 2;
                    } else {
                        attack2 += attack / 2;
                        defense3 += defense / 2;
                        healing1 += healing / 2;
                    }

                    line = fIN.readLine();
                    
                }

                f.close();
            }

            // setup enemy attack, defense and healing

            int Basepe = pe / 2;

            attack1Enemy = (int) ((Basepe / (r.nextInt(3) + 1)));
            defense1Enemy = (int) ((Basepe / (r.nextInt(3) + 1)) * 1.15);
            healing1Enemy = (int) ((Basepe / (r.nextInt(3) + 1)));

            attack2Enemy = (int) ((Basepe / (r.nextInt(3) + 1)));
            defense2Enemy = (int) ((Basepe / (r.nextInt(3) + 1)));
            healing2Enemy = (int) ((Basepe / (r.nextInt(3) + 1)) * 1.15);

            attack3Enemy = (int) ((Basepe / (r.nextInt(3) + 1)));
            defense3Enemy = (int) ((Basepe / (r.nextInt(3) + 1)) * 1.15);
            healing3Enemy = (int) ((Basepe / (r.nextInt(3) + 1)) * 1.15);

        } catch (Exception e) {
            System.out.println("Errore: " + e);
        }

    }

    public String attack(int type){

        Random r = new Random();

        int hasEffect = r.nextInt(100);

        if(hasEffect < 10){

            // no effect (toglie pe al player = a quello dell'attacco scelto)

            System.out.println("No effetto");
            playerPE -= type == 1 ? attack1 : type == 2 ? attack2 : attack3;
            return "<html>Hai attaccato ma non ha avuto effetto (- " + (type == 1 ? attack1 : type == 2 ? attack2 : attack3) + " PE)<br>Premi invio.</html>";
        } else {

            // effect (toglie pe al nemico = a quello dell'attacco scelto)

            System.out.println("Effetto");

            //pe -= ((type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy) < 0 ? 0 : (type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy;

            if(activeDefenseEnemy > 0){
                int realAttack = (type == 1 ? attack1 : type == 2 ? attack2 : attack3) - activeDefenseEnemy;
                if(realAttack >= 0){
                    pe -= realAttack;
                    // toglie la difesa attiva del nemico
                    activeDefenseEnemy = 0;
                    return "<html>Hai attaccato e ha avuto effetto <br>(- " + realAttack + " PE nemico - 0 scudo nemico)<br>Premi invio.</html>";
                }else {
                    activeDefenseEnemy = 0;
                    return "<html>Hai attaccato ma lo scudo del nemico<br>ha neutralizzato l'attacco<br>Premi invio.</html>";
                }
            
            }

            pe -= type == 1 ? attack1 : type == 2 ? attack2 : attack3;
            return "<html>Hai attaccato e ha avuto effetto <br>(- " + (type == 1 ? attack1 : type == 2 ? attack2 : attack3) + " PE nemico)<br>Premi invio.</html>";
        }

    }

    public String defense(int type){

        Random r = new Random();

        int hasEffect = r.nextInt(100);

        if(hasEffect == 45){

            // no effect (toglie pe al player = a quello della difesa scelta)

            System.out.println("No effetto");
            playerPE -= type == 1 ? defense1 : type == 2 ? defense2 : defense3;
            return "<html>Hai usato la difesa e non ha avuto effetto <br>Premi invio.</html>";
        } else {

            if(type == 1){
                // 100% difesa al player e 25% al nemico
                activeDefensePlayer = defense1;
                activeDefenseEnemy = defense1 / 4;
                return "<html>Hai usato la difesa e ha avuto effetto <br>(+ " + defense1 + " Defense)<br>Il nemico ha ricevuto " + defense1/4 +" Defense<br>Premi invio.</html>";
                
            }else if(type == 2){
                // riduce la difesa del player
                activeDefensePlayer = defense2;
                defense1 *= 0.95;
                defense2 *= 0.95;
                defense3 *= 0.95;
                return "<html>Hai usato la difesa e ha avuto effetto <br>(+ " + defense2 + " Defense)<br>Le tue difese sono state ridotte del 5%<br>Premi invio.</html>";
            }else{
                // toglie 5% di pe al player
                activeDefensePlayer = defense3;
                playerPE *= 0.95;
                return "<html>Hai usato la difesa e ha avuto effetto <br>(+ " + defense3 + " Defense)<br>I tuoi PE sono stati ridotti del 5%<br>Premi invio.</html>";
            }
        }

    }

    public String healing(int type){

        if(isHealingBlocked){
            return "<html>Hai usato la cura! Non ha avuto effetto. <br>Premi invio.</html>";
        }else{

            if(type == 1){
                // 25% cura al nemico
                playerPE += healing1;
                pe += healing1 / 4;
                return "<html>Ti sei curato! <br>(+ " + healing1 + " PE)<br>Il nemico ha ricevuto " + healing1/4 +" PE<br>Premi invio.</html>";
            }else if(type == 2){
                // toglie la difesa al player
                playerPE += healing2;
                activeDefensePlayer = 0;
                return "<html>Ti sei curato! <br>(+ " + healing2 + " PE)<br>La tua difesa è stata rimossa<br>Premi invio.</html>";
            }else{
                // riduce gli attacchi del player
                playerPE += healing3;
                attack1 *= 0.9;
                attack2 *= 0.9;
                attack3 *= 0.9;
                return "<html>Ti sei curato! <br>(+ " + healing3 + " PE)<br>I tuoi attacchi sono stati ridotti del 10%<br>Premi invio.</html>";
            }

        }

    }

    public String enemyTurn(){
        Random r = new Random();
        
        // Valuta la situazione attuale
        if(pe <= playerPE * 0.3) {
            // Se il nemico ha pochi PE, prioritizza la cura
            if(r.nextInt(100) < 80) {
                int type = r.nextInt(3) + 1;
                pe += type == 1 ? healing1Enemy : type == 2 ? healing2Enemy : healing3Enemy;
                return "<html>Il nemico si è curato.<br>(+ " + (type == 1 ? healing1Enemy : type == 2 ? healing2Enemy : healing3Enemy) + " PE nemico) <br>Premi invio.</html>";
            }
        } 
        else if(playerPE <= pe * 0.5) {
            // Se il giocatore ha pochi PE, attacca
            int type = r.nextInt(3) + 1;
            if(activeDefensePlayer > 0){
                int realAttack = (type == 1 ? attack1Enemy : type == 2 ? attack2Enemy : attack3Enemy) - activeDefensePlayer;
                if(realAttack >= 0){
                    playerPE -= realAttack;
                    activeDefensePlayer = 0;
                    return "<html>Il nemico ha attaccato e ha avuto effetto <br>(- " + realAttack + " PE - 0 scudo)<br>Premi invio.</html>";
                }else {
                    activeDefensePlayer = 0;
                    return "<html>Il nemico ha attaccato ma il tuo scudo <br>ha neutralizzato l'attacco<br>Premi invio.</html>";
                }
            }
            playerPE -= type == 1 ? attack1Enemy : type == 2 ? attack2Enemy : attack3Enemy;
            return "<html>Il nemico ha usato l'attacco. <br>Ha avuto effetto (- " + (type == 1 ? attack1Enemy : type == 2 ? attack2Enemy : attack3Enemy) + " PE) <br>Premi invio.</html>";
        }
        else {
            // Altrimenti, valuta la strategia migliore
            if(activeDefensePlayer == 0 && r.nextInt(100) < 70) {
                // Se il giocatore non ha difese attive, attacca con il 70% di probabilità
                int type = r.nextInt(3) + 1;
                playerPE -= type == 1 ? attack1Enemy : type == 2 ? attack2Enemy : attack3Enemy;
                return "<html>Il nemico ha usato l'attacco. <br>Ha avuto effetto (- " + (type == 1 ? attack1Enemy : type == 2 ? attack2Enemy : attack3Enemy) + " PE) <br>Premi invio.</html>";
            } else if(activeDefenseEnemy == 0 && r.nextInt(100) < 50) {  
                // Se il nemico non ha difese attive, si difende con il 50% di probabilità
                int type = r.nextInt(3) + 1;
                activeDefenseEnemy = type == 1 ? defense1Enemy : type == 2 ? defense2Enemy : defense3Enemy;
                return "<html>Il nemico ha usato la difesa. <br>Ha avuto effetto (+ " + (type == 1 ? defense1Enemy : type == 2 ? defense2Enemy : defense3Enemy) + " Defense nemico) <br>Premi invio.</html>";
            } else {
                // Altrimenti attacca
                int type = r.nextInt(3) + 1;
                if(activeDefensePlayer > 0){
                    int realAttack = (type == 1 ? attack1Enemy : type == 2 ? attack2Enemy : attack3Enemy) - activeDefensePlayer;
                    if(realAttack >= 0){
                        playerPE -= realAttack;
                        activeDefensePlayer = 0;
                        return "<html>Il nemico ha attaccato e ha avuto effetto <br>(- " + realAttack + " PE - 0 scudo)<br>Premi invio.</html>";
                    }else {
                        activeDefensePlayer = 0;
                        return "<html>Il nemico ha attaccato ma il tuo scudo <br>ha neutralizzato l'attacco<br>Premi invio.</html>";
                    }
                }
                playerPE -= type == 1 ? attack1Enemy : type == 2 ? attack2Enemy : attack3Enemy;
                return "<html>Il nemico ha usato l'attacco. <br>Ha avuto effetto (- " + (type == 1 ? attack1Enemy : type == 2 ? attack2Enemy : attack3Enemy) + " PE) <br>Premi invio.</html>";
            }
        }

        return "<html>Il nemico ha passato.</html>";

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

        // check winning

        if(pe <= 0 && !isFinished){
            // ridurre exp

            m.d.p.updateExp(10);

            // messaggio di sconfitta

            info.setText("<html>Hai vinto! <br>Il tuo punteggio è aumentato di 10 PE.<br>Premi invio.</html>");
            info.setVisible(true);

            money mo = new money();
            mo.addMoney(10);

            isFinished = true;

        }else if(playerPE <= 0 && !isFinished){
            // ridurre exp

            m.d.p.updateExp(-10);

            // messaggio di sconfitta

            info.setText("<html>Hai perso! <br>Il tuo punteggio è diminuito di 10 PE.<br>Premi invio.</html>");
            info.setVisible(true);

            isFinished = true;

        }

        // tornare al menu

        if(isFinished && m.k.enter){
            m.isFight = false;
            m.isFound = false;
            m.actionBlocked = false;
            b1.setVisible(false);
            b2.setVisible(false);
            b3.setVisible(false);
            back.setVisible(false);
            info.setVisible(false);
            enemyLabel.setVisible(false);
            return;
        }

        if(closeInfo && m.k.enter){
            info.setVisible(false);
            closeInfo = false;
        }

        if(enemyTurn && !m.actionBlocked){

            info.setText(enemyTurn());
            System.out.println(info.getText());
            info.setVisible(true);
            m.actionBlocked = true;
            enemyTurn = false;
            playerTurn = true;
            closeInfo = true;
            m.k.enter = false;
            m.f.requestFocus();

        }

        /***************************************************** */

        // CLEAN ACTION LISTENER

        cleanActionListener(b1, b2, b3);

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
            b1.setLabel("Attacco 1 (" + attack1 + ")");
            b2.setLabel("Attacco 2 (" + attack2 + ")");
            b3.setLabel("Attacco 3 (" + attack3 + ")");

            b1.addActionListener(e -> {

                if(m.actionBlocked)
                    return;
                info.setText(attack(1));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });

            b2.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(attack(2));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });

            b3.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(attack(3));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });
        }else if(isSecondUse){ 

            cleanActionListener(b1, b2, b3);
            // defense option ************************************

            b1.setLabel("Difesa 1 (" + defense1 + ")");
            b2.setLabel("Difesa 2 (" + defense2 + ")");
            b3.setLabel("Difesa 3 (" + defense3 + ")");

            b1.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(defense(1));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });

            b2.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(defense(2));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });

            b3.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(defense(3));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });

        }else if(isThirdUse){   

            cleanActionListener(b1, b2, b3);
            // healing option ************************************

            b1.setLabel("Cura 1 (" + healing1 + ")");
            b2.setLabel("Cura 2 (" + healing2 + ")");
            b3.setLabel("Cura 3 (" + healing3 + ")");

            b1.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(healing(1));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });

            b2.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(healing(2));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });

            b3.addActionListener(e -> {
                if(m.actionBlocked)
                    return;
                info.setText(healing(3));
                info.setVisible(true);
                m.actionBlocked = true;
                enemyTurn = true;
                playerTurn = false;
                m.f.requestFocus();
            });
        }
        


    }



}
