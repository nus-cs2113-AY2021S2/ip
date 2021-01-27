import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
        boolean isOn = true;
        int numOfCompletedTasks = 0;
        int numOfTasks = 0;
        Task[] listArray = new Task[100];
        int n = 0;
        while(isOn) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            String[] words = line.split(" ");
            if(line.equals("bye")){
                isOn = false;
            }
            else if(words[0].equals("done")){
                int taskNum = Integer.parseInt(words[1])-1;
                listArray[taskNum].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + "\u2718" + "] " + listArray[taskNum].getTask());
            }
            else if(line.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<n;i++){
                    System.out.println((i + 1) + ". [" + listArray[i].getStatusIcon() + "] " + listArray[i].getTask());
                }
            }
            else {
                listArray[n] = new Task(line);
                n++;
                System.out.println("added: " + line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}