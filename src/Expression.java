public class Expression extends AST {
    SimpleExpression auxSimpleExpression1, auxSimpleExpression2;

    public Expression(SimpleExpression auxSimpleExpression1, SimpleExpression auxSimpleExpression2) {
        this.auxSimpleExpression1 = auxSimpleExpression1;
        this.auxSimpleExpression2 = auxSimpleExpression2;
    }

    @Override
    public void visit(Visitor v) {
        v.visitExpression(this);
    }
}
