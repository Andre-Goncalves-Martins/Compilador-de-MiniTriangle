public class Program extends AST{
  public Body b;

  public Program(Body b){
      this.b=b;
  }

  @Override
  public void visit(Visitor v) {
    v.visitProgram(this); 
  } 
}
