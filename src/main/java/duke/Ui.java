package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {
    public Ui(){

    }

    public static void runProgram(Storage storage) {
        Scanner in = new Scanner(System.in);
        String userInput;

        TaskList taskList = new TaskList();

        // Check if there is such a file. Otherwise, create a new file.
        try{
            storage.loadDataFromDisk(taskList);
        } catch (FileNotFoundException e){
            storage.createNewFile();
        }

        do {
            userInput = in.nextLine();

            String[] command = userInput.split(" ");

            if(Parser.isSpecialCharacterPresent(userInput)){
                continue;
            }

            Task newItem = taskList.populateArrayList(command, taskList.getTasks());
            if(newItem != null){
                Messages.newItemMessage(taskList.getTasks(), newItem);
                storage.wrapWriteToDisk(taskList.getTasks());
            }
        } while (!userInput.equals("bye"));
    }
}
