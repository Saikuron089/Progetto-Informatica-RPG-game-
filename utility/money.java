package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import java.lang.*;

public class money {
    
    private int money;

    public money(){

        try{

            FileReader f = new FileReader("oro.txt");
            BufferedReader fIN = new BufferedReader(f);

            String line = fIN.readLine();
            System.out.println(line);

            money = Integer.parseInt(line);

            f.close();


        }catch(Exception e){
            System.out.println("Errore: " + e);
        }

    }

    public int getMoney(){
        return money;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public void removeMoney(int money){
        try{
                
            if (this.money - money < 0){
                throw new Exception("Non hai abbastanza monete");
            }

            this.money -= money;

            // scrive su file

            FileWriter f = new FileWriter("oro.txt");
            PrintWriter fOUT = new PrintWriter(f);
            fOUT.write(Integer.toString(this.money));
            f.close();
        }catch(Exception e){
            System.out.println("Errore: " + e);
        }
    }



}
