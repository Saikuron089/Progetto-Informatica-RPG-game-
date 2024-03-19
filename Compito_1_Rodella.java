import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Vector;

/*
* FILE A CUI FA RIFERIMENTO QUESTA CLASSE: "personaggi_evocabili.txt"
* Questa classe serve per scegliere un personaggio random da dare all'utente
* quando vuole aqcuistare un nuovo personaggio dallo shop.
* La classe non richiede un numero statico di personaggi nel file di conseguenza
* si possono aggiungere nel file "personaggi_evocabili.txt" quanti personaggi si vogliono
* purchè occupino una riga ciascuno.
*/

public class Compito_1_Rodella {

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

        // ritorna la riga del personaggio in base al numero random
        return v.elementAt(scelta);

    }


}
