public class Scanner {

    private String codigo;
    private char currentChar; 

    private byte currentKind;
    private StringBuffer currentSpelling = new StringBuffer("");

    private int index = 0; // index é a posição do caractere corrente na string do arquivo
    //private int indexAnt = -1;
    private char endOfile = 'F';

    public Scanner(){
        this.codigo = LeitorArquivo.Reader(); 
        this.currentChar = codigo.charAt(index); // É inicializada com o primeiro caracter do arquivo   
        /*while(indexAnt < index && index < (codigo.length()-1)){
             indexAnt = index;
             this.scan();
         }
         this.currentChar = endOfile;
         this.scan(); */   
    }

    private void take (char expectedChar){ // verificar se o caracter corrente é '\n'
        if (currentChar == expectedChar){
            currentSpelling.append(currentChar);
            if(index<codigo.length()-1){
                index++;
                currentChar = codigo.charAt(index);
            }else{
                currentChar=endOfile;
            }
        } else{
            System.out.println("Erro no método take");
        }            
    }

    private void takeIt(){ // Avanço incondicional para o próximo caracter
        currentSpelling.append(currentChar);
        if(index<codigo.length()-1){
            index++;
            currentChar = codigo.charAt(index);
        }else{
            currentChar=endOfile;
        }
    }

    private boolean isDigit(char c){
        return (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' 
        || c == '6' || c == '7' || c == '8' || c == '9' || c == '0');
    }

    private boolean isLetter(char c){
        return (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' 
        || c == 'f' || c == 'g' || c == 'h' || c == 'i' || c == 'j'
        || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' 
        || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't'
        || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' 
        || c == 'z' );
    }

    private boolean isGraphic(char c){
        return (c == '!' || c == '@' || c == '#' || c == '$' || c == '%' 
        || c == '&' || c == '^' || c == '_' || c == '?' || c == ' '
        || c == '\t'|| c == 'a' || c == 'b' || c == 'c' || c == 'd' 
        || c == 'e' || c == 'f' || c == 'g' || c == 'h' || c == 'i'
        || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n'
        || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's'
        || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' 
        || c == 'y' || c == 'z' || c == '1' || c == '2' || c == '3' 
        || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' 
        || c == '9' || c == '0' || c == '+' || c == '-' || c == '='
        || c == '/' || c == '*' || c == '\\'|| c == '>' || c == '<' 
        || c == '.' || c == ',' || c == ':' || c == ';' || c == '|' 
        || c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E'
        || c == 'F' || c == 'G' || c == 'H' || c == 'I' || c == 'J'
        || c == 'K' || c == 'L' || c == 'M' || c == 'N' || c == 'O' 
        || c == 'P' || c == 'Q' || c == 'R' || c == 'S' || c == 'T'
        || c == 'U' || c == 'V' || c == 'W' || c == 'X' || c == 'Y'
        || c == 'Z');
    }

    private byte scanToken(){
        switch (currentChar){
            case 'a': case 'b': case 'c': case 'd':
            case 'e': case 'f': case 'g': case 'h':
            case 'i': case 'j': case 'k': case 'l': 
            case 'm': case 'n': case 'o': case 'p': 
            case 'q': case 'r': case 's': case 't': 
            case 'u': case 'v': case 'w': case 'x':
            case 'y': case 'z':
                takeIt();
                while (isLetter(currentChar) || isDigit(currentChar))
                    takeIt();
                return Token.IDENTIFIER;

            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                takeIt();
                while (isDigit(currentChar))
                    takeIt();
                if(currentChar=='.'){
                    takeIt();
                    while (isDigit(currentChar))
                    takeIt();
                    return Token.REAL;   
                }else
                    return Token.INTEGER;
            
            case '+': 
                takeIt();
                return Token.SOMA;
            case '-':
                takeIt();
                return Token.SUB;
            case '=': 
                takeIt();
                return Token.EQ;
            case '*':
                takeIt();
                return Token.MULTI;
            case '/': 
                takeIt();
                return Token.DIV;
            case '>':
                takeIt();
                if(currentChar == '='){
                    takeIt();
                    return Token.BIG_EQ;
                }
                return Token.BIG_T;
            case '<':
                takeIt();
                if(currentChar == '='){
                    takeIt();
                    return Token.LESS_EQ;
                }
                else{
                    if(currentChar == '>' ){
                        takeIt();
                        return Token.DIF;

                    }else{
                        return Token.LESS_T;
                    }
                }
            
            case '.':
                takeIt();
                if(isDigit(currentChar)){
                    takeIt();
                    while (isDigit(currentChar))
                    takeIt();
                    return Token.REAL;
                }else
                    return Token.DOT;
            
            case ';':
                takeIt();
                return Token.SEMICOLON;
            
            case ',':
                takeIt();
                return Token.COMMA;

            case ':':
                takeIt();
                if (currentChar == '='){
                    takeIt();
                    return Token.BECOMES; //Atribuição          
                }
                else
                    return Token.COLON;   //Dois pontos comum

            case '(':
                takeIt();
                return Token.LPAREN;
            
            case ')':
                takeIt();
                return Token.RPAREN;
            
            case 'F':
                return Token.EOF;
                
            default:
                System.out.println("ERRO léxico: token inválido ou não identificado");
                return Token.ERRO;  // Retorna o byte de erro
        }
    }

    private void scanSeparator(){
        switch (currentChar){
            case '!':  // CASE DO COMENTARIO
                takeIt();
                while (isGraphic(currentChar))
                    takeIt();
                take('\n');
            break;

            case ' ': case '\n':
                takeIt();
                break;
        }
    }

    public Token scan(){
        while (currentChar == '!' || currentChar == ' ' || currentChar == '\n')
            scanSeparator();
        currentSpelling = new StringBuffer("");
        currentKind = scanToken();
       // System.out.println(currentSpelling.toString() + " aqui o spelling");
        return new Token(currentKind, currentSpelling.toString());        
    } 
}
