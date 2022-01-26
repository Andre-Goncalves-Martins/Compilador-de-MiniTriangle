public class Printer implements Visitor {
    int cont = 0;

    public void print(Program p) {

        System.out.println("-----Inicio da impressão da Árvore----");
        p.visit(this);
    }

    @Override
    public void visitProgram(Program p) {

        if (p != null) {
            cont++;
            if (p.b != null)
                p.b.visit(this);
                cont--;
        }
    }

    @Override
    public void visitBody(Body b) {
        if (b != null) {
            cont++;
            if (b.d != null)
                b.d.visit(this);
            if (b.c != null)
                b.c.visit(this);
                cont--;
        }
    }

    @Override
    public void visitDeclarations(Declarations ds) {
        if (ds != null) {
            cont++;
            if (ds.auxListId != null)
                ds.auxListId.visit(this);
            if (ds.auxType != null)
                ds.auxType.visit(this);
            if (ds.auxDeclarations != null)
                ds.auxDeclarations.visit(this);
                cont--;
        }
    }

    @Override
    public void visitListId(ListId li) {
        if (li != null) {
            cont++;
            if (li.auxListId != null)
                li.auxListId.visit(this);
                cont--;
        }
    }

    public void ident() {
        for (int i = 0; i < cont; i++) {
            System.out.print(" ");
        }
    }

    @Override
    public void visitType(Type tp) {
        if (tp != null) {
            ident();
            System.out.println(tp.auxtype + "");
            cont--;

        }
    }

    @Override
    public void visitComp(Comp comp) {
        if (comp != null) {
            cont++;
            if (comp.auxCommandList != null)
                comp.auxCommandList.visit(this);
                cont--;
        }
    }

    @Override
    public void visitCommandList(CommandList cl) {
        if (cl != null) {
            cont++;
            if (cl.auxCommand1 != null)
                cl.auxCommand1.visit(this);
            if (cl.auxCommandList != null)
                cl.auxCommandList.visit(this);
                cont--;
        }
    }

    @Override
    public void visitCommand(Command c) {
        cont++;
        c.visit(this);
    }

    @Override
    public void visitExpression(Expression e) {

        if (e != null) {
            cont++;
            if (e.auxSimpleExpression1 != null)
                e.auxSimpleExpression1.visit(this);
            if (e.auxSimpleExpression2 != null)
                e.auxSimpleExpression2.visit(this);
                cont--;
        }
            
    }

    @Override
    public void visitSimpleExpression(SimpleExpression se) {
        if (se != null) {
            cont++;
            if (se.auxTerm != null)
                se.auxTerm.visit(this);
            if (se.auxSimpleExpression != null)
                se.auxSimpleExpression.visit(this);
                cont--;
        }
        
    }

    @Override
    public void visitTerm(Term t) {
        if (t != null) {
            cont++;
            if (t.auxFactor != null)
                t.auxFactor.visit(this);
            if (t.auxTerm != null)
                t.auxTerm.visit(this);
                cont--;
        }
    }

    @Override
    public void visitFactor(Factor f) {
        cont++;
        if (f.auxExpression != null) f.auxExpression.visit(this);
            ident();
        System.out.println(f.type.spelling);
        cont--;

    }

    @Override
    public void visitIfCommand(IfCommand i) {
        cont++;
        if (i.auxExpression != null)
            i.auxExpression.visit(this);
        if (i.auxCommand1 != null)
            i.auxCommand1.visit(this);
        if (i.auxCommand2 != null)
            i.auxCommand2.visit(this);
            cont--;

    }

    @Override
    public void visitWhileCommand(WhileCommand w) {
        cont++;
        if (w.auxExpression != null)
            w.auxExpression.visit(this);
        if (w.auxCommand1 != null)
            w.auxCommand1.visit(this);
        cont--;

    }

    @Override
    public void visitAssignCommand(Assign a) {
        cont++;
        if (a.identifier!=null) {
            ident();
            System.out.println(a.identifier.spelling);
            
        }
        if (a.auxExpression != null)
            a.auxExpression.visit(this);
        
        cont--;
    }

}
