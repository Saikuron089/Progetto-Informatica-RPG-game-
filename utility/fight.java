package utility;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

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

    public fight() {}

    public void newFight() {

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
            System.out.println("Errore");
        }

    }

    public void attack(JLabel l, int type){

        Random r = new Random();

        boolean hasEffect = r.nextBoolean();

        if(!hasEffect){
            playerPE -= type == 1 ? attack1 : type == 2 ? attack2 : attack3;
            l.setText("Hai usato l'attacco " + (type == 1 ? "attacco1" : type == 2 ? "attacco2" : "attacco3") + ". Non ha avuto effetto (- " + (type == 1 ? attack1 : type == 2 ? attack2 : attack3) + " PE)");
        } else {
            pe -= type == 1 ? attack1 : type == 2 ? attack2 : attack3;
            l.setText("Hai usato l'attacco " + (type == 1 ? "attacco1" : type == 2 ? "attacco2" : "attacco3") + ". Ha avuto effetto (- " + (type == 1 ? attack1 : type == 2 ? attack2 : attack3) + " PE)");
        }

        // enemy turn

    }

}
