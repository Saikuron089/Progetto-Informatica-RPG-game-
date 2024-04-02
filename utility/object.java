package utility;

import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import java.lang.*;
import javax.imageio.ImageIO;

public class object {

    public BufferedImage erba;
    public BufferedImage montagna;
    public BufferedImage albero;
    public BufferedImage grano;
    public BufferedImage acqua;
    public BufferedImage strada;
    public BufferedImage chest_closed;
    public BufferedImage chest_opened;
    public BufferedImage wall;
    public BufferedImage key;

    // background

    public BufferedImage back1;

    // bots

    public BufferedImage bots1;
    public BufferedImage bots2;
    public BufferedImage bots3;
    public BufferedImage bots4;

    public object() {

        try {

            erba = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/erba.png"));
            montagna = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/montagna.png"));
            albero = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/alberi.png"));
            grano = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/grano.png"));
            acqua = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/acqua.png"));
            strada = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/strada.png"));
            chest_closed = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/chest_closed.png"));
            chest_opened = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/chest_open.png"));
            wall = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/wall.png"));
            key = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/key.png"));
            back1 = ImageIO.read(getClass().getResourceAsStream("/assets/background/backgroundFight.png"));
            bots1 = ImageIO.read(getClass().getResourceAsStream("/assets/bots/bot1.png"));
            bots2 = ImageIO.read(getClass().getResourceAsStream("/assets/bots/bot2.png"));
            bots3 = ImageIO.read(getClass().getResourceAsStream("/assets/bots/bot3.png"));
            bots4 = ImageIO.read(getClass().getResourceAsStream("/assets/bots/bot4.png"));

        } catch (Exception e) {
            System.out.println("Errore: " + e);
        }

    }

}
