package Baggie;

import Baggie.FileHandling.FileHandler;
import Baggie.Parser.ProcessInput;
import Baggie.Task.*;
import Baggie.UI.PrintMessages;

import java.util.List;
import java.util.ArrayList;

public class Baggie {
    public static String inputString;
    public static int taskCount = 0;
    public static List<Task> lists = new ArrayList<>();

    public static void main(String[] args) {
        PrintMessages.greetings();
        FileHandler.readFile();
        ProcessInput.processCommand();
        FileHandler.writeFile();
        PrintMessages.goodbye();
    }
}
