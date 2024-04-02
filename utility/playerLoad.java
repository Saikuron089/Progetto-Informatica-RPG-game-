package utility;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;
import java.lang.*;

import javax.imageio.ImageIO;

public class playerLoad {
    
    public BufferedImage std;
    public BufferedImage up;
    public BufferedImage down;
    public BufferedImage left;
    public BufferedImage right;

    // user data

    public int exp;
    public String nome;
    public boolean firstKey = false;
    public boolean secondKey = false;

    public playerLoad(){

        try{

            std = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerStd.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerUp.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerStd.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerLeft.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerRight.png"));

            // get user info

            try{

                FileReader f = new FileReader("user.txt");
                BufferedReader fIN = new BufferedReader(f);

                String userData = fIN.readLine();

                StringTokenizer st = new StringTokenizer(userData , "-");

                nome = st.nextToken();
                exp = Integer.parseInt(st.nextToken());
                firstKey = Boolean.parseBoolean(st.nextToken());
                secondKey = Boolean.parseBoolean(st.nextToken());

                f.close();

            }catch(Exception e){
                System.out.println("Errore: " + e);
            }

        }catch(Exception e){
            System.out.println("Errore: " + e);
        }


    }

    public void getFirstKey(){
        
        try{

            FileWriter f = new FileWriter("user.txt");
            PrintWriter fOUT = new PrintWriter(f);
            
            fOUT.write(nome + "-" + exp + "-" + true + "-" + secondKey);

            f.close();

        }catch(Exception e){
            System.out.println("Errore: " + e);
        }

    }

    public void updateExp(int newExp){

        try{

            // get actual exp

            FileReader f1 = new FileReader("user.txt");
            BufferedReader fIN = new BufferedReader(f1);

            String userData = fIN.readLine();

            StringTokenizer st = new StringTokenizer(userData , "-");
            st.nextToken();
            exp = Integer.parseInt(st.nextToken());

            f1.close();

            FileWriter f = new FileWriter("user.txt");
            PrintWriter fOUT = new PrintWriter(f);

            if(exp + newExp < 0){
                fOUT.write(nome + "-" + 0 + "-" + firstKey + "-" + secondKey);
                this.exp = 0;
                f.close();
                return;
            }
            
            fOUT.write(nome + "-" + (exp + newExp) + "-" + firstKey + "-" + secondKey);

            this.exp = (exp + newExp);

            f.close();

        }catch(Exception e){
            System.out.println("Errore: " + e);
        }

    }

}
