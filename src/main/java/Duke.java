import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                        "Hello! I'm Duke\n"+
                        "What can I do for you?\n"+
                        "____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        int i=0;
        Task[] tasks = new Task[100];
        while(true){
            String command = sc.nextLine();
            if(command.equals("bye")){
                System.out.println("____________________________________________________________\n"+
                                    "Bye. Hope to see you again soon!\n"+
                                    "____________________________________________________________");
                break;
            }else if(command.equals("list")){
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for(int index=0; index<i;index++){
                    System.out.print(index+1+". ");
                    System.out.println(tasks[index]);
                }
                System.out.println("____________________________________________________________");
            }else if(command.contains("done")){
                int doneNumber=Integer.parseInt(command.substring(5));
                System.out.println("Nice! I've marked this task as done: ");
                tasks[doneNumber-1].markAsDone();
                System.out.println("____________________________________________________________");
            } else if(command.contains("todo")){
                System.out.println("____________________________________________________________");
                tasks[i] = new Todo(command.substring(5));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[i]);
                System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
                System.out.println("____________________________________________________________");
                i++;
            }else if(command.contains("deadline")) {
                System.out.println("____________________________________________________________");
                //Deadline t = new Deadline(8,command.indexOf("/by"));
                String description = command.substring(8, command.indexOf("/by"));
                String by = command.substring(command.indexOf("/by") + 4);
                tasks[i] = new Deadline(description, by);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[i]);
                System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
                System.out.println("____________________________________________________________");
                i++;
            }else if(command.contains("event")) {
                System.out.println("____________________________________________________________");
                //Deadline t = new Deadline(8,command.indexOf("/by"));
                String description = command.substring(6, command.indexOf("/at"));
                String at = command.substring(command.indexOf("/at") + 4);
                tasks[i] = new Event(description, at);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[i]);
                System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
                System.out.println("____________________________________________________________");
                i++;
            }else{
                System.out.println("____________________________________________________________");
                System.out.println("Invalid command please enter again");
                System.out.println("____________________________________________________________");
            }
        }


    }
}
