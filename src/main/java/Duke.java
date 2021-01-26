import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                        "Hello! I'm Duke\n"+
                        "What can I do for you?\n"+
                        "____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        int i=0;
        Task[] tasks = new Task[100];
        while(true){
            String command = sc.nextLine();
            if(command.equals("bye")){
                System.out.println("____________________________________________________________\n"+
                        "Bye. Hope to see you again soon!\n"+
                        "____________________________________________________________");
                break;
            }else if(command.equals("list")){
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for(int index=0; index<i;index++){
                    System.out.print(index+1+". ");
                    System.out.print(tasks[index].getStatusIcon()+" ");
                    System.out.println(tasks[index].description);
                }
                System.out.println("____________________________________________________________");
            }else if(command.contains("done")){
//                System.out.print(command.substring(4));
//                System.out.print("ddd");
                int doneNumber=Integer.parseInt(command.substring(5));
                System.out.println("Nice! I've marked this task as done: ");
                tasks[doneNumber-1].markAsDone();
                System.out.println("____________________________________________________________");
            }
            else{
                Task t = new Task(command);
                tasks[i]= t;
                System.out.println("____________________________________________________________");
                System.out.println("added: "+t.description);
                i++;
                System.out.println("____________________________________________________________");
            }
        }


    }
}
