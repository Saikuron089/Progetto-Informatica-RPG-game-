import java.util.*;
import java.io.*;
import java.lang.*;

public class Miglioramento_Compito_1_Carone {
    public static void aggiungiStella(String lista_pers[][], int cont) {

        for(int i=0; i<30; i++){
            for(int j=0; j<30; j++){
                if(lista_pers[i][j] != "*" && cont != 10 && i==0 && j==20){
                    cont++;                                 // per contare quante stelle ci sono già
                }

                if(lista_pers[0][j] == null){
                    i=31;
                    j=31;
                }
            }
        }

        int fatto1 = 0;
       // int fatto2 = 0;

        if(cont != 10){
            for(int i=0; i<30; i++){
                for(int j=0; j<30; j++){
                    if(lista_pers[0][j] == null && fatto1==0){
                        fatto1 = 1;
                        lista_pers[0][j+1] = "*";
                        cont++;
                        if(cont!=10){
                            System.out.println("----CONGRATULAZIONI, AGGIUNTA UNA STELLA----");
                        }
                        else{
                            System.out.println("----HAI RAGGIUNTO IL MASSIMO NUMERO DI STELLE(10)----");
                        }
                        
                    }
                }
            }
        }else{
            System.out.println("----HAI GIA' 10 STELLE----");
        }

    }

    public static void main(String[] args) {
        String lista_pers[][] = new String[30][30];
        int cont = 0;

        //PRIMO PERSONAGGIO
        lista_pers[0][0] = "P";
        lista_pers[0][1] = "i";
        lista_pers[0][2] = "p";
        lista_pers[0][3] = "p";
        lista_pers[0][4] = "o";
        lista_pers[0][5] = " ";
        lista_pers[0][6] = " ";
        lista_pers[0][7] = " ";
        lista_pers[0][8] = "L";
        lista_pers[0][9] = "v";
        lista_pers[0][10] = "l";
        lista_pers[0][11] = " ";
        lista_pers[0][12] = "1";
        lista_pers[0][13] = " ";
        lista_pers[0][14] = " ";
        lista_pers[0][15] = " ";
        lista_pers[0][16] = "S";
        lista_pers[0][17] = "S";
        lista_pers[0][18] = "R";
        lista_pers[0][19] = " ";

        aggiungiStella(lista_pers, cont);
    }
}
