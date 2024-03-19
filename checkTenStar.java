import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class checkTenStar {
    public static boolean isTenStars(String nome){

        try{

            FileReader f = new FileReader("personaggi_ottenuti.txt");
            BufferedReader fIN = new BufferedReader(f);

            String line = fIN.readLine();

            while(line != null){

                StringTokenizer st = new StringTokenizer(line, "-");
                st.nextToken();

                if(st.nextToken().equals(nome)){
                    st.nextToken();
                    if(st.nextToken().length() == 10){
                        return true;
                    }else{
                        return false;
                    }
                }

                line = fIN.readLine();

            }
            fIN.close();

            return false;

        }catch(Exception e){
            System.out.println("Errore: " + e);
        }
        return false;

    }

}
