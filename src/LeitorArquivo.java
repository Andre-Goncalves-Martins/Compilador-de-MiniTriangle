import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {

    public static String Reader(){ 
        
        String arquivo = "";
        try { 
            BufferedReader in = new BufferedReader(new FileReader("src\\EntradaTeste.txt"));//Caminho do arquivo de entrada
            String line = in.readLine();            
            while ( line != null){
                arquivo += line+"\n";
                line = in.readLine();
            }
            in.close();
        }   catch (IOException e){ 
                System.out.println(e.getMessage()); 
            }
            return arquivo;
        }
}

