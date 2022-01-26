public class Comp extends AST{
    CommandList auxCommandList;

 
    public Comp(CommandList auxCommandList) {
        this.auxCommandList = auxCommandList;
    }

    @Override
    public void visit(Visitor v) {
        v.visitComp(this);
    }
}
