package utility;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class playerLoad {
    
    public BufferedImage std;
    public BufferedImage up;
    public BufferedImage down;
    public BufferedImage left;
    public BufferedImage right;

    public playerLoad(){

        try{

            std = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerStd.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerUp.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerStd.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerLeft.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerRight.png"));

        }catch(Exception e){
            System.out.println("Errore: " + e);
        }


    }

}
