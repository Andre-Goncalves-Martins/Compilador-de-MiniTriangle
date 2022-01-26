public class Body extends AST{
    public Declarations d;
    public Comp c;
    
    public Body(Declarations d, Comp c){
        this.d=d;
        this.c=c;
    }

    @Override
    public void visit(Visitor v) {
      v.visitBody(this);  
    }
    
}
