package data;

import commands.*;
import duke.IncorrectFormatException;
import duke.todoList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class importData extends Data{


    protected static String MESSAGE_LIST_IMPORTED = "Task List has been imported! Here are your tasks: \n";


    public importData(){
        super();

    }
    public importData(String path){
        super(path);
    }

    public void execute() throws FileNotFoundException {
        Command command;
        Scanner s = new Scanner(importedList);
        try {
            do {
                String userCommandText = s.nextLine();
                command = new Parser().parseImportTasks(userCommandText);
                command.setData(inputList);
                CommandResult result = command.execute();
                if(userCommandText.charAt(2) == 'X'){
                    command = new doneCommand(inputList);
                    result = command.execute();
                }
            } while (s.hasNext());
        }catch(NoSuchElementException | IncorrectFormatException ignored){
        }
        String MESSAGE_LIST_IMPORTED = "Task List has been imported! Here are your tasks: \n";

    }

}
