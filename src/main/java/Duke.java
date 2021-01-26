import java.util.Scanner;

public class Duke {
    public static void printGreetings() {
        System.out.println(
                "__________________________________________\n"
                        + " Welcome. I am your assistant Friday. ✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧ \n"
                        + " Just FYI, I am developed by Song Yu.\n"
                        + " May I know what I can help you?\n"
                        + "__________________________________________"
        );
    }

    public static void printExitGreetings() {
        System.out.println(
                "__________________________________________\n"
                        + " Thank you for getting in touch.\n"
                        + " See you next time. \n"
                        + "✧( ु•⌄• )◞ᴴᴬᵛᴱ ᴬ ᴳᴼᴼᴰ ᵀᴵᴹᴱ\n"
                        + "__________________________________________"
        );
    }

    public static void echoInput(String userInput) {
        System.out.println(
                "__________________________________________\n"
                        + userInput + "\n"
                        + "__________________________________________\n");
    }

    public static void main(String[] args) {
        printGreetings();

        Scanner sc = new Scanner(System.in);
        String userInput;
        // boolean variable to control the input loop
        boolean isRunning = true;
        while (isRunning) {
            // get user input
            userInput = sc.nextLine();

            // exit if "bye" is received
            if (userInput.equals("bye")) {
                printExitGreetings();
                return;
            }

            echoInput(userInput);
        }

        printExitGreetings();
    }
}
