import java.util.*;
import java.io.*;
import java.lang.*;

public class Compito_4_Carone {
    public static void main(String[] args) {
        int moneteOroAttuali = leggiMoneteOro();

        if (moneteOroAttuali >= 100) {
            System.out.println("Hai abbastanza monete d'oro per l'evocazione!");
        } else {
            System.out.println("Non hai abbastanza monete d'oro per l'evocazione.");
            System.out.println("Ti servono almeno 100 monete d'oro.");
        }
    }

    private static int leggiMoneteOro() {
        int moneteOro = 0;
        try{
            FileReader f = new FileReader("oro.txt");
            BufferedReader fIN = new BufferedReader(f);
            String line;

            while ((line = fIN.readLine()) != null) {
                moneteOro = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file.");
        }
        return moneteOro;
    }
}
