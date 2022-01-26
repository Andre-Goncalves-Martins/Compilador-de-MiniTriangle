public class Factor extends AST{
    Expression auxExpression;
    Token type;


    public Factor(Token type, Expression auxExpression ){
        this.type = type;
        this.auxExpression=auxExpression;
    }

    @Override
    public void visit(Visitor v) {
        v.visitFactor(this);     
    }
    
}
