import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];
    static int taskPosition = 0;


    public static void storeTask(Task t){
        tasks[taskPosition] = t;
        taskPosition++;
        System.out.println("added: " + t.description);
    }

    public static void markAsDone(int taskIndex){
        System.out.println("Nice! I've marked this task as done: ");
        tasks[taskIndex-1].isDone = true;
        System.out.println("[" + tasks[taskIndex-1].getStatusIcon()+ "]" + tasks[taskIndex-1].description);
    }

    public static void listArray(Task[] tasks){
        int textNumber = 1;
        for(Task t:tasks){
            if(t != null){
                System.out.println(textNumber + ".[" + t.getStatusIcon() + "] " + t.description);
                textNumber++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm 343 Guilty Spark! You may call me Spark!");
        System.out.println("What can I do for you?");
        String text = "hi";
        while(!text.equalsIgnoreCase("Bye")){
            Scanner in = new Scanner(System.in);
            text = in.nextLine();
            Task t = new Task(text);
            if(text.equalsIgnoreCase("List")){
                listArray(tasks);
            }
            else if(text.startsWith("Done") || text.startsWith("done")){
                Integer taskIndex = Integer.parseInt(text.substring(5));
                markAsDone(taskIndex);

            }
            else{
                storeTask(t);
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
