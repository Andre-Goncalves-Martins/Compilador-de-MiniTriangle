public class IfCommand extends Command {
    public Expression auxExpression;
    public Command auxCommand1,auxCommand2; 
   
    public IfCommand(Expression auxExpression, Command auxCommand1, Command auxCommand2) {
        this.auxExpression = auxExpression;
        this.auxCommand1= auxCommand1;
        this.auxCommand2 = auxCommand2;
    }

    @Override
	public void visit(Visitor v) {
		v.visitIfCommand(this);	
    }


}
