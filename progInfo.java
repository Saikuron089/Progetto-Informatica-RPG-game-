import java.util.*;
import java.io.*;
import java.lang.*;

public class progInfo {
    public static void aggiungiStella(String s, int cont) {
        for (int i = 0; i < 10; i++) {
            if (s.charAt(i) != '*' && cont != 10) { // per contare quante stelle ci sono già
                cont++;
            }
        }

        if (cont != 10) {
            s = '*' + s; // se le stelle sono meno di 10, ne aggiungo una
            System.out.println("----CONGRATULAZIONI, HAI RAGGIUNTO LE DIECI STELLE----");
        } else {
            System.out.println("----HAI RAGGIUNTO IL MASSIMO NUMERO DI STELLE----");
        }

    }

    public static void main(String[] args) {
        String s = "";
        int cont = 0;

        aggiungiStella(s, cont);
    }
}