public class SimpleExpression extends AST{
    Term auxTerm;
    SimpleExpression auxSimpleExpression;

    public SimpleExpression(Term auxTerm, SimpleExpression auxSimpleExpression) {
        this.auxTerm = auxTerm;
        this.auxSimpleExpression = auxSimpleExpression;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitSimpleExpression(this);
    } 
}
