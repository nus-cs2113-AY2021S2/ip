import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Output.greet();
        while (true){
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            // Simple Echo Function
            Output.printLine();
            System.out.println(userInput);
            Output.printLine();
        }
        Output.exit();
    }
}