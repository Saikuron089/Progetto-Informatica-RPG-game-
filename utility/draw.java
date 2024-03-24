package utility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class draw {
    
    playerLoad p = new playerLoad();
    object o = new object();
    public collision c = new collision();

    public draw(){}

    public void drawPlayer(Graphics2D g2, BufferedImage img, int x, int y){

        g2.setColor(Color.WHITE);
        g2.fillOval(x + 5, y - 10, 80, 20);
        g2.setColor(Color.BLACK);
        g2.drawString("Exp: 1", x + 18, y + 5);

        g2.drawImage(img, x, y, 92, 92, null);

    }

    public void drawTile(Graphics2D g2, int camX, int camY, int actualX, int actualY, boolean isUP, boolean isDOWN, boolean isLEFT, boolean isRIGHT){

        //g2.drawImage(o.erba, i, j, 64, 64, null);

        int a = 0; // x
        int b = 0; // y

        int[][] map = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 0, 4, 4, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 3, 4, 4, 4, 0, 0, 0, 4, 4, 4, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 3, 3, 4, 0, 0, 0, 0, 0, 4, 4, 4, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 3, 3, 3, 3, 0, 0, 0, 0, 0, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 0, 0, 5, 5, 3, 3, 0, 0, 0, 0, 0, 0, 4, 4, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 5, 5, 3, 3, 5, 5, 0, 0, 0, 0, 4, 4, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 0, 0, 0, 5, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 0, 0, 0, 0, 5, 5, 3, 3, 3, 0, 5, 5, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 5, 3, 3, 3, 5, 7, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 5, 5, 5, 3, 3, 7, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 7, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
        };

        int numRows = map.length;
        int numCols = map[0].length;

        for(int i = 0; i < (numCols * 64); i+=64){
            for(int j = 0; j < (numRows * 64); j+=64){
                
                if(map[b][a] == 0)
                    g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                else if(map[b][a] == 1){
                    g2.drawImage(o.acqua, i + camX, j + camY, 64, 64, null);
                    c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN, isLEFT, isRIGHT);
                }
                else if(map[b][a] == 2){
                    g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                    g2.drawImage(o.montagna, i + camX, j + camY, 64, 64, null);
                    c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN, isLEFT, isRIGHT);
                }
                else if(map[b][a] == 3){
                    g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                    g2.drawImage(o.strada, i + camX, j + camY, 64, 64, null);
                }
                    
                else if(map[b][a] == 4){
                    g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                    g2.drawImage(o.albero, i + camX, j + camY, 64, 64, null);
                    c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN, isLEFT, isRIGHT);
                }
                else if(map[b][a] == 5){
                    g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                    g2.drawImage(o.grano, i + camX, j + camY, 64, 64, null);
                }else if(map[b][a] == 6){
                    g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                    g2.drawImage(o.chest_closed, i + camX, j + camY, 64, 64, null);
                    c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN, isLEFT, isRIGHT);
                }else if(map[b][a] == 7){
                    g2.drawImage(o.erba, i + camX, j + camY, 64, 64, null);
                    g2.drawImage(o.wall, i + camX + 18, j + camY, 32, 64, null);
                    c.checkCollision(camX, camY, actualX, actualY, i + camX, j + camY, isUP, isDOWN, isLEFT, isRIGHT);
                }

                if(b < numRows)
                    b++;
            }
            a++;
            b = 0;
        }

    }

    public void drawBot(Graphics2D g2, BufferedImage img, int x, int y){

        g2.drawImage(p.std, x, y, 88, 88, null);

    }

    public void drawDialog (Graphics2D g2, int x, int y, String text1, String text2){
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, 220, 80);
        
        // inserire scritta dentro il rettangolo

        g2.setColor(Color.BLACK);
        g2.drawString(text1, x + 10, y + 20);
        g2.drawString(text2, x + 10, y + 40);

    }

    public void drawHeadDialog(Graphics2D g2, int x, int y, String text1, String text2){

        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, 220, 80);
        
        // inserire scritta dentro il rettangolo

        g2.setColor(Color.BLACK);
        g2.drawString(text1, x + 10, y + 20);
        g2.drawString(text2, x + 10, y + 40);

    }

}
