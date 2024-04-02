package utility;
import java.util.*;
import java.io.*;
import java.lang.*;

public class collision {

    int x = 0;
    public boolean collision = false;

    public collision(){}

    public void checkCollision(int camX, int camY, int actualX, int actualY, int objX, int objY, boolean isUP, boolean isDOWN, boolean isLEFT, boolean isRIGHT){

        //System.out.println("ActualX: " + actualX + " ActualY: " + actualY + " ObjX: " + objX + " ObjY: " + objY);

        if(actualX + (isRIGHT ? 8 : isLEFT ? -8 : 0) > objX && actualX + (isRIGHT ? 8 : isLEFT ? -8 : 0) < objX + 64 && actualY + (isDOWN ? 12 : isUP ? -12 : 0) > objY && actualY + (isDOWN ? 12 : isUP ? -12 : 0) < objY + 64){
            collision = true;
        }
        
    }  

    public int checkChest(int camX, int camY, int actualX, int actualY, int objX, int objY, boolean isUP, boolean isDOWN, boolean isLEFT, boolean isRIGHT, playerLoad p){

        //System.out.println("ActualX: " + actualX + " ActualY: " + actualY + " ObjX: " + objX + " ObjY: " + objY);

        if(actualX + (isRIGHT ? 8 : isLEFT ? -8 : 0) > objX && actualX + (isRIGHT ? 8 : isLEFT ? -8 : 0) < objX + 64 && actualY + (isDOWN ? 12 : isUP ? -12 : 0) > objY && actualY + (isDOWN ? 12 : isUP ? -12 : 0) < objY + 64 && p.exp > 1000){
            return 1;
        }else if(actualX + (isRIGHT ? 18 : isLEFT ? -18 : 0) > objX && actualX + (isRIGHT ? 18 : isLEFT ? -18 : 0) < objX + 92 && actualY + (isDOWN ? 20 : isUP ? -20 : 0) > objY && actualY + (isDOWN ? 20 : isUP ? -20 : 0) < objY + 92){
            return 2;
        }
        
        return 0;

    }  


}
