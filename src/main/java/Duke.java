import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI.printStartUpMessage();
        DukeController.run(scanner);
        UI.printExitMessage();
    }
}
