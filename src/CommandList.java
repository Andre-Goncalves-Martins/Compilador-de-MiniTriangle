public class CommandList extends AST{
    Command auxCommand1;
    CommandList auxCommandList;

    
    public CommandList(Command auxCommand1, CommandList auxCommandList) {
        this.auxCommand1 = auxCommand1;
        this.auxCommandList = auxCommandList;
    }

    @Override
    public void visit(Visitor v) {
        v.visitCommandList(this);
    }  
}
