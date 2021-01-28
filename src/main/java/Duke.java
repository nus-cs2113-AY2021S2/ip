import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello from Duke!");
        String task;
        Set<String> list = new HashSet<String>();
        Scanner sc = new Scanner(System.in);
        System.out.println("What can I do for you?");
        while (!(task = sc.nextLine()).equals("Bye")){
            if(task.equals("list")) {
                int taskNumber =1;
                for (String todo : list){
                    System.out.println(taskNumber+". " + todo);
                    taskNumber++;
                }
                System.out.println("What can I do for you?");
            }
            else {
                list.add(task);
                System.out.println("added: " + task);
                System.out.println("What can I do for you?");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

// TODO: make it case insensitive
// TODO: add tasks in chronological order