package parser;

import command.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Parser {

    public static Command parseCommand(String userInput) {
        Command cmd = null;
        if (userInput.contains("LIST")) {
            cmd = new PrintCommand();
        } else if (userInput.contains("BYE")) {
            cmd = new ByeCommand();
        }else if (userInput.contains("DONE")){
            cmd = new DoneCommand();
        } else if (!userInput.contains("BYE")) {
            cmd = new AddCommand();
        }
        return cmd;
    }

    public static int getTaskNoToBeMarkDone(String userInput){
        String[] inputSplit = userInput.split("DONE ");
        System.out.println(inputSplit[1]);
        int taskNoDone = Integer.parseInt(inputSplit[1]);
        return taskNoDone;
    }


    public static Task getTask(String userInput){
        Task taskToAdd = null;
        if (userInput.contains("TODO")) {
            taskToAdd = getToDo(userInput);
        }else if(userInput.contains("DEADLINE")){
            taskToAdd = getDeadline(userInput);
        }else if(userInput.contains("EVENT")){
            taskToAdd = getEvent(userInput);
        }
        return taskToAdd;
    }

    private static Event getEvent(String userInput) {
        String removeKeyword = userInput.replaceAll("EVENT","");
        String[] inputSplit = removeKeyword.split("/AT");
        //create task.Event
        return new Event(inputSplit[0], inputSplit[1]);
    }

    private static Todo getToDo(String userInput) {
        String removeKeyword = userInput.replaceAll("TODO","");
        //create task.Todo
        return new Todo(removeKeyword);
    }

    private static Deadline getDeadline(String userInput) {
        String removeKeyword = userInput.replaceAll("DEADLINE","");
        String[] inputSplit = removeKeyword.split("/BY");
        //create task.Event
        return new Deadline(inputSplit[0], inputSplit[1]);
    }
}
