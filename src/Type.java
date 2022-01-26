public class Type extends AST{   
    Token auxtype;

    public Type(Token auxtype) {
        this.auxtype=auxtype;
    }

    @Override
    public void visit(Visitor v) {
        v.visitType(this);  
    }
}
