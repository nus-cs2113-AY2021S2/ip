import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        Boolean command_in = true;

        Scanner in = new Scanner(System.in);

        String output = "Nothing.";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" );

        todolist list1 = new todolist();
        
        while(command_in){
            String input = in.nextLine();
            String[] tokens = input.split(" ");


            if(tokens[0].equals("bye")){
                output = "Bye. Hope to see you again soon!";
                command_in = false;
            }else if(tokens[0].equals("list")) {
                output = list1.list_items();
            }else if(tokens[0].equals("done")){
                output = list1.mark_done(tokens[1]);
            }else{
                output = list1.add_items(input);
            }





            System.out.println("____________________________________________________________\n" +
            output + "\n" +
            "____________________________________________________________\n");


        }




    }
}
