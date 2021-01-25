import java.util.*;


public class Duke {
    //public static String[] to_do_list= new String[100];
    //public static Task finish_goal = new Task("finish goal in list");
    public static Task t[] = new Task[100];
    public static void show_welcome_msg(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }
    public static void show_list(int num){
        System.out.println(" ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for(int i=0; i< num;i++){
            System.out.println((i+1) +". "+ "[" + t[i].getStatusIcon()+"] "+ t[i].getDescription());
        }
    }


    public static void main(String[] args) {

        show_welcome_msg();
        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();
        int num_of_goals = 0;
        while(!user_input.equals("bye")){
            if(user_input.equals("list")){
                show_list(num_of_goals);
            }

            else{
                int spacing = user_input.indexOf(" ");
                String user_done = user_input.substring(0,spacing);
                if(user_done.equals("done")){
                    String number = user_input.substring(spacing+1);
                    int number_converted = Integer.parseInt(number);
                    t[number_converted-1].markAsDone();
                    System.out.println("____________________________________________________________\n" +
                            "     Nice! I've marked this task as done: " );
                    System.out.println("[" + t[number_converted-1].getStatusIcon()+"] "+ t[number_converted-1].getDescription());
                    System.out.println("____________________________________________________________");
                }
                else{
                    System.out.println( "___________________________________________________________\n" +
                            "Added: "+ user_input +  "____________________________________________________________" );
                    //to_do_list[num_of_goals] = user_input;
                    t[num_of_goals] = new Task(user_input);
                    num_of_goals++;
                }
            }
            user_input = sc.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");



    }

}
