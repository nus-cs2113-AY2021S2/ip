package duke;

import java.util.Scanner;

import duke.parser.Parser;

public class Duke {

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser.handleCommand(scanner);
    }
}