public class Declarations extends AST{
    public ListId auxListId;
    public Type auxType;
    public Declarations auxDeclarations;


    public Declarations(ListId auxListId, Type auxType, Declarations auxDeclarations) {
        this.auxListId = auxListId;
        this.auxType = auxType;
        this.auxDeclarations = auxDeclarations;
    }

    @Override
    public void visit(Visitor v) {
        v.visitDeclarations(this);  
    }
}
