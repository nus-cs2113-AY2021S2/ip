import java.util.Scanner;

public class Duke {
    public static void printList(Task[] Tasks, Integer IDCounter){
        for (int i=1; i<=IDCounter-1; ++i){
            Task task = Tasks[i];
            System.out.println(i + "." + task.toString());
        }
    }
    public static void main(String[] args) {
        Integer IDCounter = 1;
        Task[] Tasks =  new Task[101];
        String Line;
        String DashLine = "____________________________________________________________\n";
        String IntroMessage = " Hello! I'm Duke\n" + " What can I do for you?\n";
        String OutroMessage = "Bye. Hope to see you again soon!\n";
        Scanner Input = new Scanner(System.in);
        System.out.println(DashLine + IntroMessage + DashLine);
        Line = Input.nextLine();
        while(!Line.equals("bye")){
            if(Line.equals("list")){
                System.out.println(DashLine + "Here are the tasks in your list:");
                printList(Tasks, IDCounter);
                System.out.println(DashLine);
            }
            else {
                String[] words = Line.split(" ");
                if (words[0].equals("done")) {
                    int i = Integer.parseInt(words[1]);
                    Tasks[i].isDone = true;
                    System.out.println(DashLine + "Nice! I've marked this task as done:\n" + " " + Tasks[i].getStatus() + "\n" + " " + Tasks[i].getDescription() + "\n" + DashLine);
                }
                else if (words[0].equals("todo")){
                    Line = Line.replace("todo ", "");
                    ToDo toDo = new ToDo(Line);
                    Tasks[IDCounter] = toDo;
                    IDCounter++;
                    String TaskCount = String.valueOf(IDCounter-1);
                    System.out.println(DashLine + " Got it. I've added this task:\n" + toDo.toString() + "\n" + "Now you have " + TaskCount + " tasks in the list.\n" + DashLine);
                }
                else if (words[0].equals("deadline")){
                    Line = Line.replace("deadline ", "");
                    words = Line.split("/by ");
                    Deadline deadline = new Deadline(words[0], words[1]);
                    Tasks[IDCounter] = deadline;
                    IDCounter++;
                    String TaskCount = String.valueOf(IDCounter-1);
                    System.out.println(DashLine + " Got it. I've added this task:\n" + deadline.toString() + "\n" + "Now you have " + TaskCount + " tasks in the list.\n" + DashLine);
                }
                else if (words[0].equals("event")){
                    Line = Line.replace("event ", "");
                    words = Line.split("/at ");
                    Event event = new Event(words[0], words[1]);
                    Tasks[IDCounter] = event;
                    IDCounter++;
                    String TaskCount = String.valueOf(IDCounter-1);
                    System.out.println(DashLine + " Got it. I've added this task:\n" + event.toString() + "\n" + "Now you have " + TaskCount + "tasks in the list.\n" + DashLine);
                }
                else {
                    Task t = new Task(Line);
                    Tasks[IDCounter] = t;
                    IDCounter++;
                    System.out.println(DashLine + " added: " + Line + "\n" + DashLine);
                }
            }
            Line = Input.nextLine();
        }
        System.out.println(DashLine + OutroMessage + DashLine);
    }
}