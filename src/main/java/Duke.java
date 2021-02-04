import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Duke {

    static Task[] tasks = new Task[100];
    static int taskPosition = 0;
    static String description;
    static String by;
    static int indexOfBy;

    public static void storeTask(Task t){
        tasks[taskPosition] = t;
        taskPosition++;
        System.out.println("Got it! I've added this task!");
        System.out.println(t.getStatusIcon() +" " + t.getDescription());
        System.out.println("Now you have " + countArray(tasks) + " tasks!");
    }

    public static int countArray(Task[] tasks){
        int counter = 0;
        for (Task task:tasks){
            if (task != null){
                counter++;
            }
        }
        return counter;
    }

    public static void markAsDone(int taskIndex){
        System.out.println("Nice! I've marked this task as done: ");
        tasks[taskIndex-1].isDone = true;
        listArray(tasks);
    }

    public static void listArray(Task[] tasks){
        int textNumber = 1;
        for(Task t:tasks){
            if(t != null){
                System.out.println(textNumber + "." + t.getStatusIcon() +" " + t.getDescription());
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
            //Task t = new Task(text);
            String[] arr = text.split(" "); //split command input into words
            String command = arr[0];
            switch (command){
            case "deadline":
            case "Deadline":
            case "DEADLINE":
                int indexOfBy = text.indexOf('/');
                description = text.substring(9,indexOfBy-1);
                by = text.substring(indexOfBy + 1);
                Deadline deadlineTask = new Deadline(description,by);
                storeTask(deadlineTask);
                break;
            case "todo":
            case "Todo":
            case "TODO":
                description = text.substring(5);
                Todo todoTask = new Todo(description);
                storeTask(todoTask);
                break;
            case "event":
            case "Event":
            case "EVENT":
                indexOfBy = text.indexOf('/');
                String description = text.substring(6,indexOfBy-1);
                String by = text.substring(indexOfBy + 1);
                Event eventTask = new Event(description,by);
                storeTask(eventTask);
                break;
            case "List":
            case "list":
            case "LIST":
                listArray(tasks);
                break;
            case"Done":
            case "done":
            case "DONE":
                Integer taskIndex = Integer.parseInt(arr[1]);
                markAsDone(taskIndex);
                break;
            }


        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
