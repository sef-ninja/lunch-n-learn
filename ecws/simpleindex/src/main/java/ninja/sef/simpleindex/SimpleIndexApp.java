package ninja.sef.simpleindex;

public class SimpleIndexApp {

    public static final String INDEX_DIR =
            "/home/hourglass/LunchNLearn/TextSearch/demo/index/simpleindex";

    public static void main(String[] args) {
        
        if(args.length != 1){
            printUsageStatement();
            System.exit(1);
        }
        
        OperationFactory.createOperation(args[0]).execute(INDEX_DIR);
    }

    private static void printUsageStatement() {
        System.out.println("");
        System.out.println("Usage:");
        System.out.println("    demoapp <operation>");
        System.out.println("");
        System.out.println("    Valid operations:");
        System.out.println("            -buildindex");
        System.out.println("            -termquery");
        System.out.println("");
    }
}
