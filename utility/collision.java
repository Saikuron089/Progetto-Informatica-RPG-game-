package utility;

public class collision {

    int x = 0;
    public boolean collision = false;

    public collision(){}

    public void checkCollision(int camX, int camY, int actualX, int actualY, int objX, int objY){

        int iX = -(camX - objX);    // posizione statica
        int iY = -(camY - objY);    // posizione statica

        //System.out.println("camX: " + actualX + " camY: " + actualY + " objX: " + objX + " objY: " + objY + " iX: " + iX + " iY: " + iY);

        if(actualX > iX && actualX < iX + 32 && actualY > iY && actualY < iY + 64){
            System.out.println("Collision detected: " + x);
            x++;
        }

    }  

}
