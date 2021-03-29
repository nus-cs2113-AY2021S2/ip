import java.util.Scanner;

public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskList inputTasks;

    /**
     * Duke Constructor
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./duke.txt");
        inputTasks = new TaskList(storage.readFile("./duke.txt"));
    }

    /**
     * getting input from user
     * @param input the input string from user
     * @return ui from different commands
     */
    public String getInput(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this.inputTasks, this.storage, this.ui);
        } catch (DukeException e) {
            ui.clearReply();
            ui.addResponse(e.getMessage());
        }
        return ui.getReply();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(Ui.showWelcome());
        while (true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            System.out.println(duke.getInput(line));
        }
    }
}