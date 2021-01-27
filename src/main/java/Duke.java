import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
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
        List<task> tasks = new ArrayList<>();
        do{
            userInput = sc.nextLine();
            int taskIndex = 0;
            if(userInput.contains("done")){
                String[] splits = userInput.split(" ");
                userInput = splits[0];
                taskIndex = Integer.valueOf(splits[1]) - 1;

            }
            switch (userInput) {
                case "bye":
                    String output = "Bye. Hope to see you again soon!";
                    System.out.println(output);
                    break;
                case "list":
                    int i = 1;
                    for(task task : tasks){
                        System.out.println(i +  "." + task.getStatusIcon() + " " + task.description);
                        i++;
                    }
                    break;
                case "done":
                    task task_ = tasks.get(taskIndex);
                    task_.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" +
                                        task_.getStatusIcon() + task_.description);
                    break;
                default:
                    // create a new task
                    task newTask = new task(userInput);
                    // added to list
                    tasks.add(newTask);
                    System.out.println("added: " + userInput);
            }
        } while(!userInput.equalsIgnoreCase("bye"));
    }
}
