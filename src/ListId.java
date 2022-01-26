public class ListId extends AST{
    public ListId auxListId;

    
    public ListId(ListId auxListId) {
        this.auxListId = auxListId;
    }

    @Override
    public void visit(Visitor v) {
        v.visitListId(this);
    }
}
