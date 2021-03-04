import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    //collection
    static List<Task> tasks = new ArrayList<>();
    static Boolean notBye = true;
    static fileManager fm = new fileManager();
    public static void main(String[] args) throws FileNotFoundException {

        // get the path of project_root
        String home = System.getProperty("user.dir");
        // concatenate folder data
        Path path = Paths.get(home, "data", "duke.txt");
        // set the path
        fm.setPath(path.toString());
        // load data
        tasks = fm.readFile();

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
            }catch (DukeException | IOException e){
                System.out.println(e.toString());
            }
            System.out.println("-----------------------------");

        }
    }

    /**
     * process the user input and understand the command
     * @param userInput: value input by a user
     */
    private static void processUserInput(String userInput) throws DukeException, IOException {
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
            // save data to file
            fm.writeFile((ArrayList<Task>) tasks);
        }
        else if(userInput.startsWith("done")){
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
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
        else if(userInput.startsWith("delete")){
            int deleteIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task removedTask = tasks.remove(deleteIndex);
            System.out.println("-----------------------------");
            System.out.println("Noted. I've removed this task: \n" +
                    removedTask.toString() + "\n" +
                    "Now you have " + tasks.size() + " in the list.");
        }
        else{
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
