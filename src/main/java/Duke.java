import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> tasks = new ArrayList<>();
    static Boolean notBye = true;
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello I'm Duke\n"
                + "What can I do for you?";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        String userInput;
        while(notBye) {
            userInput = sc.nextLine();
            try {
                processUserInput(userInput);
            }catch (DukeException e){
                System.out.println(e.toString());
            }
            System.out.println("-----------------------------");

        }
    }

    /**
     * process the user input and understand the command
     * @param userInput: value input by a user
     */
    private static void processUserInput(String userInput) throws DukeException {
        String description = null;
        if(userInput.startsWith("todo")){
            if(userInput.equals("todo")){
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            else {
                description = userInput.substring(5);
                tasks.add(new Todo(description));
            }
            }
        else if(userInput.startsWith("deadline")){
            String[] split = userInput.split("/");
            description = split[0].substring(8);
            String by = split[1];
            tasks.add(new Deadline(description, by));
        }
        else if(userInput.startsWith("event")){
            String[] split = userInput.split("/");
            description = split[0].substring(5);
            String at = split[1];
            tasks.add(new Event(description, at));
        }
        else if(userInput.equalsIgnoreCase("list")){
            int i = 1;
            for(Task task : tasks){
                System.out.println(i +  "." + task.toString());
                i++;
            }
        }
        else if(userInput.startsWith("done")){
            int taskIndex = Integer.valueOf(userInput.split(" ")[1]) - 1;
            Task task_ = tasks.get(taskIndex);
            task_.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" +
                    task_.toString());
        }
        else if(userInput.equalsIgnoreCase("bye")){
                String bye = "Bye. Hope to see you again soon!";
                System.out.println(bye);
                notBye = false;
        }
        else{
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
