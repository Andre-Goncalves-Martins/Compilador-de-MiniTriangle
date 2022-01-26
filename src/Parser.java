public class Parser {

    private Token currentToken;
    private Scanner scanner= new Scanner();
    
    private void accept(byte expectKind){
        if(currentToken.kind == expectKind)
            currentToken = scanner.scan();
        else{
            System.out.println("ERRO SINTATICO: Token inesperado");
            currentToken.kind = Token.ERRO;
            acceptIt();
        }
            
    }
    
    private void acceptIt(){
        if(currentToken.kind!=Token.ERRO)
            currentToken = scanner.scan();
        else
            System.exit(0);  
    }
    
    public Program parse(){
        System.out.println("-----Iniciando análise sintática do código-----");
        System.out.println(LeitorArquivo.Reader());
        currentToken= scanner.scan();
        return parseProgram();
    }
       
    private Program parseProgram(){       
        Body auxb=null;
        if(currentToken.kind==Token.PROGRAM){
            acceptIt();
            if(currentToken.kind==Token.IDENTIFIER){
                acceptIt();
                accept(Token.SEMICOLON);
                auxb=parseBody();
                accept(Token.DOT);
            }else{
                System.out.println("ERRO SINTATICO: token <id> nao identificado");
                currentToken.kind = Token.ERRO;
                acceptIt();
            }
        }else{
            System.out.println("ERRO SINTATICO: token <program> nao encontrado");
            currentToken.kind = Token.ERRO;
            acceptIt();
        }
        System.out.println("-----Fim da análise sintática ! Sem erros----\n");
        return new Program(auxb); 
    }  

    private Body parseBody(){
        Declarations auxd=null;
        Comp auxc=null;
        auxd=parseDeclarations();
        auxc=parseComp();
        return new Body(auxd, auxc);
    }


    private Declarations parseDeclarations(){
        
        ListId auxListId=null;
        Type auxType=null;
        Declarations auxDeclarations=null;

        //System.out.println("entrou no Declarations\n");
        if(currentToken.kind==Token.VAR){
            acceptIt();
            auxListId = parseListId();
            accept(Token.COLON);
            auxType = parseType();
            if(currentToken.kind == Token.SEMICOLON){
                acceptIt();
                auxDeclarations = parseDeclarations();
            }else{
                System.out.println("ERRO SINTATICO: Esperava-se ';' ao final da declaracao\n");
                currentToken.kind = Token.ERRO;
                acceptIt();
            }
        }
        return new Declarations(auxListId, auxType, auxDeclarations);
    }
    
    private ListId parseListId(){
        ListId auxListId=null;
        
        //System.out.println("entrou no ListId\n");
        if(currentToken.kind==Token.IDENTIFIER){
            acceptIt();
                if(currentToken.kind==Token.COMMA){
                    acceptIt();
                    if(currentToken.kind==Token.IDENTIFIER)
                    auxListId=parseListId();
                }
            }else{
                System.out.println("ERRO SINTATICO: Identificador invalido\n");
                currentToken.kind = Token.ERRO;
                acceptIt();
        }       
        return new ListId(auxListId);    
    }

    private Type parseType(){
        Token auxToken=currentToken;
        
        switch(currentToken.kind){
            
            case Token.INTEGER:{
                
                acceptIt();
                return new Type(auxToken);
                
            }
                
            case Token.REAL: {
                acceptIt();
                return  new Type(auxToken);
                           
            }

            case Token.BOOLEAN: {
                acceptIt();
                return new Type(auxToken);
            }
            
            default:{
                System.out.println("ERRO SINTATICO: Tipo invalido");     
                currentToken.kind = Token.ERRO;
                acceptIt();      
            }
        }
        return new Type(auxToken);
    }

    private Comp parseComp(){
        CommandList auxCommandList=null;
        if(currentToken.kind==Token.BEGIN){
            acceptIt();
            auxCommandList= parseCommandList();
            accept(Token.END);
        }else{
            System.out.println("ERRO SINTATICO: Esperava-se a palavra reservada 'begin'");
            currentToken.kind = Token.ERRO;
            acceptIt();
        }  
        return new Comp(auxCommandList);     
    }

    private CommandList parseCommandList(){
        Command aux1=null;
        CommandList aux2=null;
        
        //System.out.println("Entrou CommandList\n");

        if(currentToken.kind == Token.IDENTIFIER|| 
             currentToken.kind == Token.IF ||
             currentToken.kind == Token.WHILE||
             currentToken.kind == Token.BEGIN ){
                aux1 =  parseCommand();
                if(currentToken.kind == Token.SEMICOLON){
                    acceptIt();
                aux2 =  parseCommandList(); 
                }else{
                    System.out.println("ERRO SINTATICO: Esperava-se ';' ao final do comando\n");
                    currentToken.kind = Token.ERRO;
                    acceptIt();
                }
             }

        return new CommandList(aux1,aux2);
    }

    
    private Command parseCommand(){
        Expression auxExpression=null;
        Command auxCommand1=null;
        Command auxCommand2=null; 

        switch(currentToken.kind){
            //atribuição
            case Token.IDENTIFIER:{
                Token auxToken=currentToken;
                acceptIt();
                accept(Token.BECOMES);
                auxExpression=parseExpression();
                
                return new Assign(auxExpression,auxToken);
                
            }
            //condição
            case Token.IF:{
                acceptIt();
                auxExpression=parseExpression();
                accept(Token.THEN);
                auxCommand1=parseCommand();
                if(currentToken.kind==Token.ELSE){
                    acceptIt();
                    auxCommand2=parseCommand();
                }  
              return new IfCommand(auxExpression,auxCommand1,auxCommand2);

            }
            //iteração
            case Token.WHILE:{
                acceptIt();
                auxExpression= parseExpression();
                accept(Token.DO);
                auxCommand1= parseCommand();
            return new WhileCommand(auxExpression, auxCommand1);   
                            
            }  
            //comando composto
            case Token.BEGIN:    
              parseComp();
                break;
            
            default:
                System.out.println("ERRO SINTATICO: Comando invalido");
                currentToken.kind = Token.ERRO;
                acceptIt();
                break;
            }
            return null;
        }

    private Expression parseExpression(){
        SimpleExpression auxSimpleExpression1, auxSimpleExpression2 = null;

        auxSimpleExpression1 = parseSimpleExpression();
        if(currentToken.kind == Token.LESS_T || 
        currentToken.kind == Token.BIG_T ||
        currentToken.kind == Token.LESS_EQ ||
        currentToken.kind == Token.BIG_EQ ||
        currentToken.kind == Token.EQ ||
        currentToken.kind == Token.DIF){
            acceptIt();
            auxSimpleExpression2 = parseSimpleExpression();
        }
        return new Expression(auxSimpleExpression1, auxSimpleExpression2);
    }

    private SimpleExpression parseSimpleExpression(){
        Term auxTerm;
        SimpleExpression auxSimpleExpression = null;

        auxTerm = parseTerm();
        if(currentToken.kind == Token.SOMA || 
        currentToken.kind == Token.SUB ||
        currentToken.kind == Token.OR){
            acceptIt();
            auxSimpleExpression = parseSimpleExpression();
        }
        return new SimpleExpression(auxTerm, auxSimpleExpression);
    }
    
    private Term parseTerm(){
        Term auxTerm = null;
        Factor auxFactor;

        auxFactor = parseFactor();
        if(currentToken.kind == Token.MULTI || 
        currentToken.kind == Token.DIV ||
        currentToken.kind == Token.AND){
            acceptIt();
            auxTerm = parseTerm();
            
        }
        return new Term(auxFactor, auxTerm);
    }
    
    private Factor parseFactor(){
        Expression auxExpression;

        Token auxToken=currentToken;

        switch(currentToken.kind){
            case Token.IDENTIFIER:
                acceptIt();
                return new Factor(auxToken,null);
                
            //Literal daqui
            case Token.TRUE:
                acceptIt();
                return new Factor(auxToken,null);
            
            case Token.FALSE:
                acceptIt();
                return new Factor(auxToken,null);
            
            case Token.INTEGER:
                acceptIt();
                return new Factor(auxToken,null);
            
            case Token.REAL:
                acceptIt();
                return new Factor(auxToken,null);
               
            // até aq
            case Token.LPAREN:
                acceptIt();
               auxExpression= parseExpression();
                accept(Token.RPAREN);
               return new Factor(null, auxExpression);
                
            
            default:
                System.out.println("ERRO SINTATICO: Fator invalido");
                currentToken.kind = Token.ERRO;
                acceptIt();
                break;
        }
        return new Factor(auxToken,null);
    }
}
