package utility;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class fight {

    public int pe;
    public BufferedImage imgBot;
    public int playerPE;

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

        } catch (Exception e) {
            System.out.println("Errore");
        }

    }

}
