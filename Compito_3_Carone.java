import java.util.*;
import java.io.*;
import java.lang.*;

public class Compito_3_Carone {
    private static final String[][] opzioniMenu = {
        {"1) Gioca"},
        {"2) Negozio"},
        {"3) Credits"},
        {"4) Esci"}
    };
    private static int opzioneSelezionata = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            visualizzaMenu();
            System.out.print("Usa 'w' per muovere su, 's' per muovere giù e INVIO per selezionare: ");
            String scelta = scan.nextLine().toLowerCase(); //toLoweCase() serve per traformare tutto in minuscolo, per evitare errori

            if (scelta.equals("w")) {
                muoviFrecciaSu();
            } else if (scelta.equals("s")) {
                muoviFrecciaGiu();
            } else if (scelta.equals("")) {
                // L'utente ha premuto INVIO, seleziono l'opzione chiesta dall'utente
                System.out.println("\nHai selezionato: " + opzioniMenu[opzioneSelezionata][0]);
                break;
            }
        }
    }

    //metto la freccia affianco alla scelta in base ai comandi (w/s)

    private static void visualizzaMenu() {
        for (int i = 0; i < opzioniMenu.length; i++) {
            if (i == opzioneSelezionata) {
                System.out.println("--> " + opzioniMenu[i][0]);
            } else {
                System.out.println("     " + opzioniMenu[i][0]);
            }
        }
    }

    private static void muoviFrecciaSu() {
        if (opzioneSelezionata > 0) {
            opzioneSelezionata--;
        }
    }

    private static void muoviFrecciaGiu() {
        if (opzioneSelezionata < opzioniMenu.length - 1) {
            opzioneSelezionata++;
        }
    }
}
