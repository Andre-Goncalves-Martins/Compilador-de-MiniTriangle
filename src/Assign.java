public class Assign extends Command {
    public Expression auxExpression;
    public Token identifier;
    
    public Assign(Expression auxExpression, Token identifier) {
        this.auxExpression = auxExpression;
        this.identifier = identifier;
    }

    @Override
    public void visit(Visitor v) {
        v.visitAssignCommand(this);
    }
       
}
