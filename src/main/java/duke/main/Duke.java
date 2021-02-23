package duke.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.exceptions.*;

import static duke.main.Storage.loadFile;
import static duke.main.Storage.createFile;

public class Duke {
    public static void main(String[] args) {
        UI.welcomeMessage();
        UI.printLine();
        Parser.fileHandling();
        Parser.run();
        UI.byeMessage();
    }
}