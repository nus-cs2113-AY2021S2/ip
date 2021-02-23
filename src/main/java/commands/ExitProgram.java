package commands;

import java.io.IOException;

public class ExitProgram extends Command{

    public static void execute() {
        try {
            storage.saveFile();
        } catch (IOException e) {
            System.out.println(messages.MESSAGE_SAVE_FILE_ERROR);
        }
        textUi.showGoodbye();
        System.exit(0);
    }
}
