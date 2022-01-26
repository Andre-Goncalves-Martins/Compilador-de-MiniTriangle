
public interface Visitor {
    
    public void visitProgram(Program p); 
    public void visitBody(Body b);
    public void visitDeclarations(Declarations ds);    
    public void visitListId(ListId li);
    public void visitType(Type tp);
    public void visitComp(Comp comp);
    public void visitCommandList(CommandList cl);
    public void visitCommand(Command c);
    public void visitExpression(Expression e);
    public void visitSimpleExpression(SimpleExpression se);
    public void visitTerm(Term t);
    public void visitFactor(Factor f);
    public void visitIfCommand(IfCommand i);
    public void visitWhileCommand(WhileCommand w);
    public void visitAssignCommand(Assign a);
    
}
