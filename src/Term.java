public class Term extends AST {
    Factor auxFactor;
    Term auxTerm;

    public Term(Factor auxFactor, Term auxTerm) {
        this.auxFactor = auxFactor;
        this.auxTerm = auxTerm;
    }

    @Override
    public void visit(Visitor v) {
        v.visitTerm(this);
    }  
}
