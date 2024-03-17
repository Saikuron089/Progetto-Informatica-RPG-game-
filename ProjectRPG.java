import java.io.*;
import java.lang.*;
import java.util.*;

public class ProjectRPG {

	FileReader f;
	BufferedReader fIN;

	// Funzione che pulisce lo schermo (system("cls") su java)
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void MenuPrincipale(String[][] menu, int key, int posY) {
		Scanner scanner = new Scanner(System.in);
		
		posY = 1; // Il cursore si trova su "1) Gioca"

		System.out.println("Usa W e S per sportarti su e giu'.");

		Thread.sleep(3000); // Programma in pausa per 3 secondi

		do{
			clearScreen(); // Pulisce lo schermo

			// Stampa il menu principale, il quale e' gestito con una matrice
			for (int i = 0; i < menu.length; i++) {
				for (int j = 0; j < menu[i].length; j++) {
					System.out.print(menu[i][j]);
				}
				System.out.println();
			}
			key = System.in.read();

			if(key==(char)'s'){
				
			}
		}while();
	}

	// Funzione che mostra il layout del negozio
	public static void Negozio(int oro, int scelta_negozio) {
		Scanner scanner = new Scanner(System.in);

		clearScreen(); // Pulisce lo schermo

		System.out.println("                ___________________  ");
		System.out.println("               |                   | ");
		System.out.println("               |      Negozio      | ");
		System.out.println("               |___________________| ");
		System.out.println("  __________________________________________________ ");
		System.out.println(" |                                                  |");
		System.out.println(" |  Costo evocazione:                     100 G     |");
		System.out.println(" |  ______________________________________________  |");
		System.out.println(" | |                                              | |");
		System.out.println(" | |  Premi 1 per evocare un personaggio (100 G)  | |");
		System.out.println(" | |                                              | |");
		System.out.println(" | |  Premi 0 per tornare al menu' principale     | |");
		System.out.println(" | |______________________________________________| |");
		System.out.println(" |__________________________________________________|");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}

	// Funzione che evoca un peronaggio in modo casuale da fare

	public String getRandomPlayer() {

		// apre il file "personaggi_evocabili.txt"
		try {
			f = new FileReader("personaggi_evocabili.txt");
			fIN = new BufferedReader(f);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

		// crea vettore dinamico
		Vector<String> v = new Vector<String>();

		// inserisce nel vettore tutti i personaggi presenti nel file .txt
		try {
			String line = fIN.readLine();

			while (line != null) {

				v.addElement(line);

				line = fIN.readLine();
			}

			f.close();

		} catch (Exception e) {
			System.out.println("Errore: " + e);
		}

		// sceglie randomicamente un personaggio
		int scelta = (int) (Math.random() * v.size());

		// salva il personaggio sul file personaggi_ottenuti.txt
		try {

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

		} catch (Exception e) {
			System.out.println("Errore: " + e);
		}
		// ritorna la riga del personaggio in base al numero random
		return "Hai trovato:  " + v.elementAt(scelta);
	}

	public void Salva() {

	}

	public void Carica() {

	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);

		// Variabili
		int oro = 100, scelta_negozio = 0, posY = 1;
		String scelta[] = new String[1];
		String key[] = new String[1];

		// Inserisco il menu' principale del gioco in una matrice
		String menu[][] = {
				{ "  ________________________________  " },
				{ " |   __________________________   | " },
				{ " |  |                          |  | " },
				{ " |  |       PROJECT RPG        |  | " },
				{ " |  |__________________________|  | " },
				{ " |                                | " },
				{ " |       ___________________      | " },
				{ " |      |                   |     | " },
				{ " |      |  ---> 1) Gioca    |     | " },
				{ " |      |                   |     | " },
				{ " |      |  2) Negozio       |     | " },
				{ " |      |                   |     | " },
				{ " |      |  3) Credits       |     | " },
				{ " |      |                   |     | " },
				{ " |      |  4) Esci          |     | " },
				{ " |      |___________________|     | " },
				{ " |                                | " },
				{ " |________________________________| " },
				{},
				{},
				{},
		};

		clearScreen(); // Pulisce lo schermo

		MenuPrincipale(menu, key, posY); // Funzione che stampa il menu principale

		scelta[0] = scanner.nextLine(); // Scegli una opzione

		switch (scelta[0]) {
			case "1": // Gioca

				break;
			case "2": // Negozio
				Negozio(oro, scelta_negozio);
				scelta_negozio = scanner.nextInt();

				// Se premi un tasto diverso da 1 torni al menu principale
				if (scelta_negozio != 1) {
					MenuPrincipale(menu, key, posY);
				}
				// Altrimenti se premi 1 evochi un personaggio casuale
				else {
					// Evoca()
				}
				break;
			case "3": // Visualizza titoli di coda

				break;
			default: // Esci dal gioco
				System.out.println("Sei uscito dal gioco.");
				break;
		}
	}

	private static void MenuPrincipale(String[][] menu, String[] key, int posY) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'MenuPrincipale'");
	}
}