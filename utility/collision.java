package utility;

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

}
