import java.util.Scanner;

public class Duke {
    public static void printList(Task[] list, Integer counter){
        for (int i=1; i<=counter-1; ++i){
            Task task = list[i];
            System.out.println(task.getID() + "." + task.getStatus() + " " + task.getDescription() + "\n");
        }
    }
    public static void main(String[] args) {
        Integer counter = 1;
        Task[] tasks =  new Task[101];
        String line;
        String dashline = "____________________________________________________________\n";
        String IntroMsg = " Hello! I'm Duke\n" + " What can I do for you?\n";
        String OutMsg = "Bye. Hope to see you again soon!\n";
        Scanner in = new Scanner(System.in);
        System.out.println(dashline + IntroMsg + dashline);
        line = in.nextLine();
        while(!line.equals("bye")){
            if(line.equals("list")){
                System.out.println(dashline + "Here are the tasks in your list:\n");
                printList(tasks, counter);
                System.out.println(dashline);
            }
            else if (line.contains("done")){
                String[] words = line.split(" ");
                int i = Integer.parseInt(words[1]);
                tasks[i].isDone = true;
                System.out.println(dashline + "Nice! I've marked this task as done:\n" + " " + tasks[i].getStatus() + " " + tasks[i].getDescription() + "\n" + dashline);
            }
            else {
                Task t = new Task(line, counter);
                tasks[counter] = t;
                counter++;
                System.out.println(dashline + " added: " + line + "\n" + dashline);
            }
            line = in.nextLine();
        }
        System.out.println(dashline + OutMsg + dashline);
    }
}