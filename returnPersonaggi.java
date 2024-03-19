import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class returnPersonaggi {

    public Vector<String> retPersonaggi(){
        
        try{

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

        }catch(Exception e){
            System.out.println("Errore: " + e);
        }

        return null;

    }
    
}
