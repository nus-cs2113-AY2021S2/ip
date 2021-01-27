import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        Boolean ContinueChat = true;

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

        todolist initialList = new todolist();
        
        while(ContinueChat){
            String input = in.nextLine();
            String[] tokens = input.split(" ");


            if(tokens[0].equals("bye")){
                output = "Bye. Hope to see you again soon!";
                ContinueChat = false;
            }else if(tokens[0].equals("list")) {
                output = initialList.itemLister();
            }else if(tokens[0].equals("done")){
                output = initialList.itemResolver(tokens[1]);
            }else{
                output = initialList.itemAdder(input);
            }





            System.out.println("____________________________________________________________\n" +
            output + "\n" +
            "____________________________________________________________\n");


        }




    }
}
