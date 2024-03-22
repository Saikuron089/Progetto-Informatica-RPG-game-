package utility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class draw {
    
    playerLoad p = new playerLoad();
    object o = new object();

    public draw(){}

    public void drawPlayer(Graphics2D g2, BufferedImage img, int x, int y){

        g2.drawImage(img, x, y, 92, 92, null);

    }

    public void drawTile(Graphics2D g2, int WIDTH, int HEIGHT, int camX, int camY){

        //g2.drawImage(o.erba, i, j, 64, 64, null);

        int a = 0; // x
        int b = 0; // y

        int[][] map = {
            {4, 4, 4, 4, 4, 4, 4, 0, 4, 4, 2, 2, 2, 2, 2, 2},
            {4, 4, 3, 4, 4, 4, 0, 0, 0, 4, 4, 4, 2, 2, 2, 2},
            {4, 4, 3, 3, 4, 0, 0, 0, 0, 0, 4, 4, 4, 2, 2, 2},
            {4, 4, 3, 3, 3, 3, 0, 0, 0, 0, 0, 4, 4, 4, 2, 2},
            {0, 0, 5, 5, 3, 3, 0, 0, 0, 0, 0, 0, 4, 4, 2, 2},
            {0, 0, 0, 5, 5, 3, 3, 5, 5, 0, 0, 0, 0, 4, 4, 2},
            {1, 0, 0, 0, 5, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 4},
            {1, 1, 0, 0, 0, 0, 5, 5, 3, 3, 3, 0, 5, 5, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 5, 5},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 5, 3, 3, 3, 5},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 5, 5, 5, 3, 3},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}
        };



        for(int i = 0; i < WIDTH; i+=64){
            for(int j = 0; j < HEIGHT; j+=64){
                
                if(map[b][a] == 0)
                    g2.drawImage(o.erba, i, j, 64, 64, null);
                else if(map[b][a] == 1)
                    g2.drawImage(o.acqua, i, j, 64, 64, null);
                else if(map[b][a] == 2){
                    g2.drawImage(o.erba, i, j, 64, 64, null);
                    g2.drawImage(o.montagna, i, j, 64, 64, null);
                }
                else if(map[b][a] == 3){
                    g2.drawImage(o.erba, i, j, 64, 64, null);
                    g2.drawImage(o.strada, i, j, 64, 64, null);
                }
                    
                else if(map[b][a] == 4){
                    g2.drawImage(o.erba, i, j, 64, 64, null);
                    g2.drawImage(o.albero, i, j, 64, 64, null);
                }
                else if(map[b][a] == 5){
                    g2.drawImage(o.erba, i, j, 64, 64, null);
                    g2.drawImage(o.grano, i, j, 64, 64, null);
                }

                if(b < 12)
                    b++;
            }
            a++;
            b = 0;
        }

    }

}
