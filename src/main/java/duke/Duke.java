package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    public static Storage storage = new Storage();

    /**
     * Code to run the entire program
     */
    public static void main(String[] args) {
        Messages.welcomeMessage();
        Ui.runProgram(storage);
        Messages.goodbyeMessage();
    }
}
