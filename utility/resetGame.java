package utility;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import java.lang.*;

public class resetGame {
    
    public resetGame(){
        
        try{

            FileWriter f = new FileWriter("user.txt");
            PrintWriter fOUT = new PrintWriter(f);

            fOUT.println("Player-50-false-false");

            f.close();

        }catch(Exception e){
            System.out.println("Errore: " + e);
        }



    }

}
