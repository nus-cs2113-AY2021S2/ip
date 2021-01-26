import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static Scanner in = new Scanner(System.in);
    private static int taskNumber = 0;
    private static Task[] t = new Task[100];

    public static void addToList(String word){
        Task task = new Task(word);
        t[taskNumber] = task;
        taskNumber++;
    }
    public static void printList(Task[]t){
        int count=1;
        System.out.println("Here are the tasks in your list: ");
        Task[] newTasks= Arrays.copyOf(t,taskNumber);
        for(Task newTask: newTasks) {
            System.out.println(count+"."+"["+newTask.getStatusIcon()+"]"+newTask.getDescription());
            count++;
        }
    }

    public static void completedTask(int number){
        System.out.println("Nice! I've marked this task as done:");
        t[number-1].markAsDone();
        System.out.println("["+t[number-1].getStatusIcon()+"]"+t[number-1].getDescription());
        System.out.println("*********************************************");
    }


    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("Hello! I'm Julia");
        System.out.println("What can I do for you?");
        System.out.println();
        System.out.println("*********************************************");
        String input ="";
        while(true){
            input = in.nextLine();
            System.out.println("*********************************************");
            if(input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                System.out.println("*********************************************");
                System.exit(0);
            }
            else if(input.equals("list")) {
                printList(t);
                System.out.println("*********************************************");
            }
            else if(input.startsWith("done")) {
                int num = Integer.parseInt(input.substring(5));
                completedTask(num);
            }
            else{
                addToList(input);
                System.out.println("added: "+ input);
                System.out.println();
                System.out.println("*********************************************");
            }

        }

    }
}