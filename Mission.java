/*
 * Questa classe fa riferimento al file "missioni.txt"
 * Questa classe serve per ritornare la lista delle missioni
 * e gestisce la selezione della missione, tramite due pagine:
 * una per visualizzare tutte le missioni e una per vedere gli obiettivi
 * o difficoltà della missione.
 * Le missioni nel file devono essere scritte nel formato: "index-nome-Livello consigliato: x-monete-exp"
 * 
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

public class Mission {

   public Mission(){}

   public Vector<String> mostraMissioni(){
 
      // leggo tutte le missioni

      Vector<String> v = new Vector<String>();

      try{
         FileReader f = new FileReader("missioni.txt");
         BufferedReader fIN = new BufferedReader(f);

         String line = fIN.readLine();

         while (line != null) {
            
            v.addElement(line);

            line = fIN.readLine();

         }

         fIN.close();

      }catch(Exception e){
         System.out.println("Errore: " + e);
         try{
            FileWriter f1 = new FileWriter("missioni.txt");
            PrintWriter f1OUT = new PrintWriter(f1);
            f1OUT.close();
         }catch(Exception e1){
            System.out.println("Errore: " + e1);
         }
      }

      

      return v;
      

   }

   public String paginaMissione(Vector<String> v, int scelta){


      // stampo dettagli missione

      int i = 1;

      try{

         FileReader f = new FileReader("missioni.txt");
         BufferedReader fIN = new BufferedReader(f);

         String line = fIN.readLine();

         String details = "";

         while(line != null){

            if(i == scelta){

               details = line;
               // fermo il ciclo
               line = null;

            }else{
               line = fIN.readLine();
               i++;
            }

         }

         fIN.close();
         
         return details;


      }catch(Exception e){
         System.out.println("Errore: " + e);
      }

      return null;

   }


}