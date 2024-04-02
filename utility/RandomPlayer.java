package utility;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.*;
import java.io.*;
import java.lang.*;

// da controllare se sono stati presi i personaggi (sennò c'è il loop)

public class RandomPlayer {

    FileReader f;
    BufferedReader fIN;

    public RandomPlayer(){}

    public boolean alredyTaken(String s){

        try{

            FileReader f = new FileReader("potenziamenti_ottenuti.txt");
            BufferedReader fIN = new BufferedReader(f);

            String line = fIN.readLine();

            while(line != null){

                if(line.contains(s)){
                    f.close();
                    return true;
                }

                line = fIN.readLine();

            }

            f.close();

            return false;

        }catch (Exception e){
            System.out.println("Errore: " + e);
        }

        return false;

    }

    public String getRandomPlayer(){

        // apre il file "potenziamenti_evocabili.txt"

        try{
            f = new FileReader("potenziamenti_evocabili.txt");
            fIN = new BufferedReader(f);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }

        // crea vettore dinamico

        Vector<String> v = new Vector<String>();

        // inserisce nel vettore tutti i personaggi presenti nel file .txt

        try{

            String line = fIN.readLine();

            while(line != null) {
                v.addElement(line);

                line = fIN.readLine();

            }

            f.close();

        }catch (Exception e){
            System.out.println("Errore: " + e);
        }

        // sceglie randomicamente un personaggio

        int scelta = (int)(Math.random() * v.size());

        // check:


        // che non sia stato già evocato

        boolean check = true;

        while(check){

            scelta = (int)(Math.random() * v.size());

            StringTokenizer st = new StringTokenizer(v.elementAt(scelta), "-");
            st.nextToken();
            String nome = st.nextToken();

            if(!alredyTaken(nome)){
                check = false;
            }

            
        }


        // che non abbia già 10 stelle


        // salva il potenziamento sul file potenziamenti_ottenuti.txt

        try{

            // apro il file per la scrittura

            FileWriter f1 = new FileWriter("potenziamenti_ottenuti.txt", true);
            PrintWriter fOUT = new PrintWriter(f1);

            // prendo dalla riga il nome, rarità e numero di stelle

            String player = v.elementAt(scelta);

            fOUT.println(player);

            // chiudo il file
            f1.close();
            
            // ritorna la riga del potenziamento in base al numero random
            return "E' stato trovato il potenziamento: " + player;

        }catch (Exception e){
            System.out.println("Errore2: " + e);
        }

        return "Errore";
        

    }


}
