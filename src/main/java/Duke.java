import java.util.Scanner;

public class Duke {
    public static void printList(Task[] Tasks, Integer IDCounter){
        for (int i=1; i<=IDCounter-1; ++i){
            Task task = Tasks[i];
            System.out.println(task.getID() + "." + task.getStatus() + " " + task.getDescription() + "\n");
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
                System.out.println(DashLine + "Here are the tasks in your list:\n");
                printList(Tasks, IDCounter);
                System.out.println(DashLine);
            }
            else if (Line.contains("done")){
                String[] words = Line.split(" ");
                int i = Integer.parseInt(words[1]);
                Tasks[i].isDone = true;
                System.out.println(DashLine + "Nice! I've marked this task as done:\n" + " " + Tasks[i].getStatus() + " " + Tasks[i].getDescription() + "\n" + DashLine);
            }
            else {
                Task t = new Task(Line, IDCounter);
                Tasks[IDCounter] = t;
                IDCounter++;
                System.out.println(DashLine + " added: " + Line + "\n" + DashLine);
            }
            Line = Input.nextLine();
        }
        System.out.println(DashLine + OutroMessage + DashLine);
    }
}