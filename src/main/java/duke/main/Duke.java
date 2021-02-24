package duke.main;


public class Duke {
    public static void main(String[] args) {
        UI.welcomeMessage();
        UI.printLine();
        Parser.fileHandling();
        Parser.run();
        UI.byeMessage();
    }
}
