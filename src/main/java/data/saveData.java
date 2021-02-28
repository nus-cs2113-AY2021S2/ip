package data;

import duke.todoList;

import java.io.IOException;

public class saveData extends writeData{

    public saveData(){
        super();

    }
    public saveData(String path){
        super(path);
    }

    public static void execute() throws IOException { // Updates data file with current list of tasks
        writeToFile("");
        String out = inputList.tasksUpdate();
        try {
            appendToFile(out);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
