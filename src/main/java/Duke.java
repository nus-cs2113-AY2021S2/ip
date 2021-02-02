import java.util.Scanner;


public class Duke {





    public static String command_parser(todo list, String command){
        String[] tokens = command.split(" ");
        int startofitem = command.indexOf(" ") + 1;
        int endofitem;
        int startofdate;
        String out = "";
        switch(tokens[0]){
        case "list":
            out = list.listItems();
            break;
        case "deadline":
            endofitem = command.indexOf("/by") - 1;
            startofdate = endofitem + 5;
            out = list.addItems(command.substring(startofitem,endofitem),listTypes.deadline,command.substring(startofdate));
            out += "\nNow you have " + list.getItems() + " item(s) in the list!";
            break;
        case "event":
            endofitem = command.indexOf("/at") - 1;
            startofdate = endofitem + 5;
            out = list.addItems(command.substring(startofitem,endofitem),listTypes.event,command.substring(startofdate));
            out += "\nNow you have " + list.getItems() + " item(s) in the list!";
            break;
        case "todo":
            out = list.addItems(command.substring(startofitem),listTypes.todo);
            out += "\nYou have " + list.getItems() + " item(s) in the list!";
            break;
        case "done":
            out = list.resolveItem(tokens[1]) ;
            out += "\nNow you have " + list.getItems() + " item(s) in the list!";
        }

        return out;

    }




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
        todo listTest = new todo();
        
        while(ContinueChat){
            String input = in.nextLine();
            if(input.equals("bye")){
                output = "Bye. Hope to see you again soon!";
                ContinueChat = false;
            }else {
                /*String[] tokens = input.split(" ");
                if (tokens[0].equals("bye")) {
                    output = "Bye. Hope to see you again soon!";
                    ContinueChat = false;
                } else if (tokens[0].equals("list")) {
                    output = initialList.listItems();
                } else if (tokens[0].equals("done")) {
                    output = initialList.resolveItem(tokens[1]);
                } else {
                    output = "added: " + initialList.addItems(input);
                }*/
                output = command_parser(listTest,input);
            }







            System.out.println("____________________________________________________________\n" +
            output + "\n" +
            "____________________________________________________________\n");


        }




    }
}
