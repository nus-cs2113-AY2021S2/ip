import java.util.*;

public class Duke {

    public static void main(String[] args) {
        String[] to_do_list= new String[100];
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
        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();
        int num_of_goals = 0;
        while(!user_input.equals("bye")){
            if(user_input.equals("list")){
                for(int i=0; i< num_of_goals;i++){
                    System.out.println((i+1) +". "+ to_do_list[i]);


                }

            }
            else{
                System.out.println( "___________________________________________________________\n" +
                    "Added: "+ user_input +  "____________________________________________________________" );
                to_do_list[num_of_goals] = user_input;
                num_of_goals++;

            }
            user_input = sc.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");



    }

}
