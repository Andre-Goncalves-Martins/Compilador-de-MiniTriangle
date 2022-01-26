public class WhileCommand extends Command {
    public Expression auxExpression;
    public Command auxCommand1;


	public WhileCommand(Expression auxExpression, Command auxCommand1) {
		this.auxExpression=auxExpression;
		this.auxCommand1=auxCommand1;
	}

	@Override
	public void visit(Visitor v) {
		v.visitWhileCommand(this);	
	} 
}
