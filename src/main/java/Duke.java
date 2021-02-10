import java.util.Scanner;

public class Duke {
    public static void printList(Task[] Tasks, Integer totalTasks){
        for (Integer i=0; i<totalTasks; ++i){
            Task task = Tasks[i];
            Integer taskNumber = i+1;
            System.out.println(taskNumber + "." + task.toString());
        }
    }

    public static int printTaskAdded(Task[] Tasks, Integer totalTasks){
        printDashLine();
        System.out.println(" Got it. I've added this task:\n" + Tasks[totalTasks].toString());
        totalTasks++;
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printDashLine();
        return totalTasks;
    }

    public static void printDashLine(){
        System.out.println("____________________________________________________________");
    }

    public static void validateInput(String[] words) throws DukeException {
        boolean isList = words[0].equals("list");
        boolean isDone = words[0].equals("done");
        boolean isTodo = words[0].equals("todo");
        boolean isDeadline = words[0].equals("deadline");
        boolean isEvent = words[0].equals("event");
        boolean invalidCommand = !(isList || isDone || isTodo || isDeadline || isEvent);
        if(invalidCommand) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    public static void validateToDoInput(String[] words) throws DukeException {
        boolean invalidToDoInput = words.length == 1;
        if(invalidToDoInput) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void main(String[] args) {
        Integer totalTasks = 0;
        Task[] Tasks =  new Task[100];
        String line;
        String INTRO_MESSAGE = " Hello! I'm Duke\n" + " What can I do for you?";
        String OUTRO_MESSAGE = "Bye. Hope to see you again soon!";
        Scanner Input = new Scanner(System.in);
        printDashLine();
        System.out.println(INTRO_MESSAGE);
        printDashLine();
        line = Input.nextLine();
        boolean inSystem = !line.equals("bye");
        while(inSystem){
            String[] words = line.split(" ");
            boolean isList = words[0].equals("list");
            boolean isDone = words[0].equals("done");
            boolean isTodo = words[0].equals("todo");
            boolean isDeadline = words[0].equals("deadline");
            boolean isEvent = words[0].equals("event");
            try {
                validateInput(words);
            } catch (Exception e){
                System.out.println(e);
                line = Input.nextLine();
                inSystem = !line.equals("bye");
                continue;
            }
            if(isList){
                printDashLine();
                System.out.println("Here are the tasks in your list:");
                printList(Tasks, totalTasks);
                printDashLine();
            }
            else if(isDone) {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                Tasks[taskNumber].isDone = true;
                printDashLine();
                System.out.println("Nice! I've marked this task as done:\n" + " " + Tasks[taskNumber].getStatus() + " " + Tasks[taskNumber].getDescription());
                printDashLine();
            }
            else if (isTodo){
                try {
                    validateToDoInput(words);
                } catch (Exception e) {
                    System.out.println(e);
                    line = Input.nextLine();
                    inSystem = !line.equals("bye");
                    continue;
                }
                line = line.replace("todo ", "");
                ToDo toDo = new ToDo(line);
                Tasks[totalTasks] = toDo;
                totalTasks = printTaskAdded(Tasks,totalTasks);
            }
            else if (isDeadline){
                line = line.replace("deadline ", "");
                words = line.split("/by ");
                Deadline deadline = new Deadline(words[0], words[1]);
                Tasks[totalTasks] = deadline;
                totalTasks = printTaskAdded(Tasks,totalTasks);
            }
            else if (isEvent) {
                line = line.replace("event ", "");
                words = line.split("/at ");
                Event event = new Event(words[0], words[1]);
                Tasks[totalTasks] = event;
                totalTasks = printTaskAdded(Tasks, totalTasks);
            }
            line = Input.nextLine();
            inSystem = !line.equals("bye");
        }
        printDashLine();
        System.out.println(OUTRO_MESSAGE);
        printDashLine();
    }
}