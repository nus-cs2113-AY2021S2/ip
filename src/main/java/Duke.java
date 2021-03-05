import java.io.File;
import java.io.FileNotFoundException;

/**
 * Starts Duke for user.
 */
public class Duke {

    public Duke(String filePath) {
        try {
            Storage.loadFile(filePath + "/data.txt");
        } catch (FileNotFoundException e) {
            Ui.printFileCreatedMessage(filePath);
        }
    }

    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            isExit = Parser.processInput();
        }
        Storage.saveFile();
    }

    /**
     * Runs Duke and takes in user input until exit command is called.
     */
    public static void main(String[] args) {
        String filePath = new File("").getAbsolutePath();
        new Duke(filePath).run();
    }
}




