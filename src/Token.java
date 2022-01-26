public class Token{
    public byte kind;
    public String spelling;

    public Token (byte kind, String spelling) {
        //System.out.println("Dentro de TOKEN o spelling: "+spelling);
        //System.out.println("Dentro de TOKEN o kind: "+kind+"\n");        
        this.kind = kind; this.spelling = spelling;
        if( kind == IDENTIFIER){
        for (int k = 0; k < spellings.length; k++){
            if (spelling.equals(spellings[k])){
                this.kind = (byte)k; 
                //System.out.println("Saida do spelling: "+spellings[k]);
                //System.out.println("Saida do kind: "+this.kind+"\n");                
                break;
            }
        }
    }
}

    public final static byte 
        INTEGER = 0, BEGIN = 1, DO = 2, ELSE = 3, END = 4, IF = 5, OR = 6, AND = 7, 
        THEN = 8, VAR = 9, WHILE = 10, TRUE = 11, FALSE = 12, PROGRAM = 13, BOOLEAN = 14,
        REAL = 15, IDENTIFIER = 16,  SOMA = 17, SUB = 18, MULTI = 19, DIV = 20, 
        BIG_T = 21, LESS_T = 22, EQ = 23, SEMICOLON = 24, COLON = 25, BECOMES = 26,
        LPAREN = 27, RPAREN = 28, BIG_EQ = 29, LESS_EQ = 30, DIF = 31, DOT = 32,
        COMMA = 33, EOF = 34 ,ERRO = 40;
   
        private final static String[] spellings = {
        "integer","begin", "do", "else", "end", "if", "or", "and", "then",
        "var", "while","true","false", "program", "boolean", "real"
    };
}