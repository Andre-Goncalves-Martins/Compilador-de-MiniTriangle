public class Compiler {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner();
        Parser parser = new Parser();
        //parser.parse();
        Printer printer = new Printer(); 
        printer.print(parser.parse());

    }
}
