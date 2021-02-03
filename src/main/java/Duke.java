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
    public static void main(String[] args) {
        Integer totalTasks = 0;
        Task[] Tasks =  new Task[100];
        String Line;
        String IntroMessage = " Hello! I'm Duke\n" + " What can I do for you?";
        String OutroMessage = "Bye. Hope to see you again soon!";
        Scanner Input = new Scanner(System.in);
        printDashLine();
        System.out.println(IntroMessage);
        printDashLine();
        Line = Input.nextLine();
        boolean notBye = !Line.equals("bye");
        while(notBye){
            String[] words = Line.split(" ");
            boolean isList = Line.equals("list");
            boolean isDone = words[0].equals("done");
            boolean isTodo = words[0].equals("todo");
            boolean isDeadline = words[0].equals("deadline");
            boolean isEvent = words[0].equals("event");
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
                Line = Line.replace("todo ", "");
                ToDo toDo = new ToDo(Line);
                Tasks[totalTasks] = toDo;
                totalTasks = printTaskAdded(Tasks,totalTasks);
            }
            else if (isDeadline){
                Line = Line.replace("deadline ", "");
                words = Line.split("/by ");
                Deadline deadline = new Deadline(words[0], words[1]);
                Tasks[totalTasks] = deadline;
                totalTasks = printTaskAdded(Tasks,totalTasks);
            }
            else if (isEvent){
                Line = Line.replace("event ", "");
                words = Line.split("/at ");
                Event event = new Event(words[0], words[1]);
                Tasks[totalTasks] = event;
                totalTasks = printTaskAdded(Tasks,totalTasks);
            }
            else {
                Task t = new Task(Line);
                Tasks[totalTasks] = t;
                totalTasks++;
                printDashLine();
                System.out.println(" added: " + Line);
                printDashLine();
            }
            Line = Input.nextLine();
            notBye = !Line.equals("bye");
        }
        printDashLine();
        System.out.println(OutroMessage);
        printDashLine();
    }
}