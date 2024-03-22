package utility;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class object {
    
    public BufferedImage erba;
    public BufferedImage montagna;
    public BufferedImage albero;
    public BufferedImage grano;
    public BufferedImage acqua;
    public BufferedImage strada;

    public object(){
        
        try{

            erba = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/erba.png"));
            montagna = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/montagna.png"));
            albero = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/alberi.png"));
            grano = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/grano.png"));
            acqua = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/acqua.png"));
            strada = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/strada.png"));

        }catch(Exception e){
            System.out.println("Errore: " + e);
        }
        

    }

}
