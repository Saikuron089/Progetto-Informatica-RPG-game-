import java.util.*;
import java.io.*;
import java.lang.*;

public class Compito_2_Carone{
    
    public static void statistiche(){
        
        try{
            FileReader f = new FileReader("pers_ottenuti.txt");
            BufferedReader fIN = new BufferedReader(f);
            
            String line;
            
            while((line = fIN.readLine()) != null){
                System.out.println(line);
            }
            
            fIN.close();
        }catch(IOException e){
            System.out.println("Errore");
        }
        
        
        
    }
    
    public static void main(String [] args){
        statistiche();
    }
}
