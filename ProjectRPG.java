import java.io.*;
import java.lang.*;
import java.util.*;

public class ProjectRPG {

	static FileReader f;
	static BufferedReader fIN;

	// Funzione che pulisce lo schermo (system("cls") su java)
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void MenuPrincipale(String[][] menu) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);

		int currentLine = 0;

		System.out.println("Usa W e S per sportarti su e giu'.");

		Thread.sleep(3000); // Programma in pausa per 3 secondi

		while (true) {
			clearScreen(); // Pulisce lo schermo

			// Stampa il menu principale, il quale e' gestito con una matrice
			for (int i = 0; i < menu.length; i++) {
				if (i == currentLine) {
					System.out.println("--> " + menu[i][0]);
				} else {
					System.out.println("     " + menu[i][0]);
				}
			}

			// toLoweCase() serve per traformare tutto in minuscolo, per evitare errori
			String scelta = scanner.nextLine().toLowerCase();

			if (scelta.equals("w")) {
				muoviFrecciaSu(currentLine);
			} else if (scelta.equals("s")) {
				muoviFrecciaGiu(currentLine, menu);
			} else if (scelta.equals("")) {
				// L'utente ha premuto INVIO
			}
		}
	}

	private static void muoviFrecciaSu(int currentLine) {
		if (currentLine > 0) {
			currentLine--;
		}
	}

	private static void muoviFrecciaGiu(int currentLine, String[][] menu) {
		if (currentLine < menu.length - 1) {
			currentLine++;
		}
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

	public static String getRandomPlayer() {

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
		return "E' stato trovato il personaggio: " + v.elementAt(scelta);
	}

	public static void controlloStelle(String s, int cont) {
		for (int i = 0; i < 10; i++) {
			if (s.charAt(i) != '*' && cont < 10) { // per contare quante stelle ci sono già
				cont++;
			}
		}

		if (cont >= 10) {
			return "";
		}
	}

	public static void aggiungiStella(int cont, String s) {
		if (cont < 10) {
			s = '*' + s; // se le stelle sono meno di 10, ne aggiungo una
			System.out.println("Il personaggio evocato aumenta di stelle!");
		}
	}

	public Vector<String> mostraMissioni() {

		// leggo tutte le missioni

		Vector<String> v = new Vector<String>();

		try {
			FileReader f = new FileReader("missioni.txt");
			BufferedReader fIN = new BufferedReader(f);

			String line = fIN.readLine();

			while (line != null) {
				v.addElement(line);
				line = fIN.readLine();
			}

			fIN.close();

		} catch (Exception e) {
			System.out.println("Errore: " + e);
			try {
				FileWriter f1 = new FileWriter("missioni.txt");
				PrintWriter f1OUT = new PrintWriter(f1);
				f1OUT.close();
			} catch (Exception e1) {
				System.out.println("Errore: " + e1);
			}
		}

		return v;
	}

	public String paginaMissione(Vector<String> v, int scelta) {

		// stampo dettagli missione

		int i = 1;

		try {
			FileReader f = new FileReader("missioni.txt");
			BufferedReader fIN = new BufferedReader(f);

			String line = fIN.readLine();
			String details = "";

			while (line != null) {
				if (i == scelta) {
					details = line;
					// fermo il ciclo
					line = null;
				} else {
					line = fIN.readLine();
					i++;
				}
			}

			fIN.close();

			return details;
		} catch (Exception e) {
			System.out.println("Errore: " + e);
		}

		return null;
	}

	public Vector<String> retPersonaggi() {
		try {
			FileReader f = new FileReader("personaggi_ottenuti.txt");
			BufferedReader fIN = new BufferedReader(f);

			Vector<String> v = new Vector<>();

			String line = fIN.readLine();

			while (line != null) {
				v.addElement(line);

				line = fIN.readLine();
			}
			fIN.close();

			return v;
		} catch (Exception e) {
			System.out.println("Errore: " + e);
		}

		return null;
	}

	public static void main(String args[]) throws InterruptedException {
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
				{ " |      |  1) Gioca         |     | " },
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

		MenuPrincipale(menu); // Funzione che stampa il menu principale

		scelta[0] = scanner.nextLine(); // Scegli una opzione

		switch (scelta[0]) {
			case "1": // Gioca

				break;
			case "2": // Negozio
				Negozio(oro, scelta_negozio);
				scelta_negozio = scanner.nextInt();

				// Se premi un tasto diverso da 1 torni al menu principale
				if (scelta_negozio != 1) {
					MenuPrincipale(menu);
				}
				// Altrimenti se premi 1 evochi un personaggio casuale
				else {
					// Evoca()
					getRandomPlayer();
				}
				break;
			case "3": // Visualizza titoli di coda

				break;
			default: // Esci dal gioco
				System.out.println("Sei uscito dal gioco.");
				break;
		}
	}
}