import testing.CollectionTester;

public class Main {

    public static void main(String[] args) {
        CollectionTester tester = new CollectionTester();

        tester.runTest(50_000);
        tester.runTest(500_000);
        tester.runTest(1_000_000);

        System.out.println("\n"+ WordSpinner.spinWords("This string is going to be spinned"));

    }




}