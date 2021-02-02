import java.util.Scanner;

public class Duke {

    //to store the sentence
    private static String line;
    //to keep track of the number of task
    private static int count = 0;
    private static Scanner in = new Scanner(System.in);
    //Array of object
    private static Task[] tasks = new Task[100];


    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");

        while(true){

            line = in.nextLine();

            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if(line.contains("todo")){
                String task = line.substring(5,line.length());
                tasks[count]= new ToDos(task);
                System.out.println("Got it. I've added this task");
                System.out.print("  ");
                tasks[count++].printStatus();
                System.out.println("Now you have "+count+" tasks in the list");

            } else if(line.contains("deadline")){
                String task = line.substring(9,line.indexOf("/"));
                String by = line.substring(line.indexOf("/")+4,line.length());
                tasks[count]= new DeadLines(task,by);
                System.out.println("Got it. I've added this task:");
                System.out.print("  ");
                tasks[count++].printStatus();
                System.out.println("Now you have "+count+" tasks in the list");
            } else if(line.contains("event")){
                String task = line.substring(6,line.indexOf("/"));
                String by = line.substring(line.indexOf("/")+4,line.length());
                tasks[count]=new Events(task,by);
                System.out.println("Got it. I've added this task:");
                System.out.print("  ");
                tasks[count++].printStatus();
                System.out.println("Now you have "+count+" tasks in the list:");
            } else if(line.contains("list")){
                for(int i=0;i<count;i++){
                    System.out.print(i+1+".");
                    tasks[i].printStatus();
                }
            } else if(line.contains("done")){
                int index = Integer.parseInt(line.substring(5,line.length()));
                tasks[index-1].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                tasks[index-1].printStatus();
            }
        }
    }
}
