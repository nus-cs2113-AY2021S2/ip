package parser;

import exceptions.DukeException;
import preparetask.PrepareDeadline;
import preparetask.PrepareEvent;
import preparetask.PrepareTodo;
import tasklist.TaskList;

public class Parser {

    public static void parseCommand(String userInput){

        try {
            if(!(userInput.contains("bye")||userInput.contains("todo")||userInput.contains("deadline")||userInput.contains("event")
                    ||userInput.contains("list")||userInput.contains("done")||userInput.contains("delete")||userInput.contains("find"))){
                throw new DukeException();
            }
            if (userInput.contains("todo")) { //prepareTodo
                new PrepareTodo(userInput);
            } else if (userInput.contains("deadline")) {
                new PrepareDeadline(userInput);
            } else if (userInput.contains("event")) {
                new PrepareEvent(userInput);
            } else if (userInput.contains("list")) {
                TaskList.list();
            } else if (userInput.contains("done")) {
                TaskList.done(userInput);
            } else if (userInput.contains("delete")){
                TaskList.delete(userInput);
            } else if(userInput.contains("find")){
                TaskList.find(userInput);
            }
        }catch(DukeException e){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
