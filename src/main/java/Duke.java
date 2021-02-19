import common.Constants;

import java.util.Scanner;

public class Duke {

    private static final Constants constants = new Constants();
    private static final Storage storage = new Storage();
    private static final Parser parser = new Parser();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        try {
            storage.loadFile();
            System.out.println(constants.MESSAGE_FILE_LOADED);
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_FILE_NOT_FOUND);
        }
        System.out.println(constants.MESSAGE_WELCOME);
        do {
            input = scanner.nextLine().trim();
            parser.processInput(input);
        } while (!parser.isEndProgramNow());
    }

}
