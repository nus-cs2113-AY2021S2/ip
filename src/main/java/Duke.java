import java.util.Scanner;

import tasklist.TaskList;
import ui.Ui;
import constants.Constants;

public class Duke {
    private static TaskList tasks = new TaskList();
    private static final Ui ui = new Ui();
    private static Constants constants = new Constants();

    public static void main(String[] args) {
        ui.programStartUp(tasks);
        Scanner in = new Scanner(System.in);
        boolean isProgramRunning = true;
        while (isProgramRunning && in.hasNextLine()) {
            String line = in.nextLine();
            String[] inputs = line.split(" ");
            isProgramRunning = ui.userInterface(tasks, inputs);
        }
        System.out.println(constants.byeMessage);
    }
}
