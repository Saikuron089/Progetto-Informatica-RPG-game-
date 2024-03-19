import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

/*
* FILE A CUI FA RIFERIMENTO QUESTA CLASSE: "personaggi_evocabili.txt" e "personaggi_ottenuti.txt"
* Questa classe serve per scegliere un personaggio random da dare all'utente
* quando vuole aqcuistare un nuovo personaggio dallo shop e salvarlo.
* La classe non richiede un numero statico di personaggi nel file di conseguenza
* si possono aggiungere nel file "personaggi_evocabili.txt" quanti personaggi si vogliono
* purchè occupino una riga ciascuno.
* I personaggi nel file "personaggi_evocabili.txt" devono essere scritti nel formato "index-nome-rarità-stelle".
* Nel file "personaggi_ottenuti.txt" i personaggi vengono scritti nel formato "nome rarità stelle"
*/

public class Miglioramento_compito_1_Rodella {

    FileReader f;
    BufferedReader fIN;

    public RandomPlayer(){}

    public String getRandomPlayer(){

        // apre il file "personaggi_evocabili.txt"

        try{
            f = new FileReader("personaggi_evocabili.txt");
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

        // salva il personaggio sul file personaggi_ottenuti.txt

        try{

            // apro il file per la scrittura

            FileWriter f1 = new FileWriter("personaggi_ottenuti.txt", true);
            PrintWriter fOUT = new PrintWriter(f1);

            // prendo dalla riga il nome, rarità e numero di stelle

            String player = v.elementAt(scelta);
            StringTokenizer st = new StringTokenizer(player, "-");
            String nome, rarita, stelle;

            st.nextToken();
            nome = st.nextToken();
            rarita = st.nextToken();
            stelle = st.nextToken();

            fOUT.println(nome + " " + rarita + " " + stelle);

            // chiudo il file
            f1.close();

        }catch (Exception e){
            System.out.println("Errore: " + e);
        }

        // ritorna la riga del personaggio in base al numero random
        return "E' stato trovato il personaggio: " + v.elementAt(scelta);

    }


}
