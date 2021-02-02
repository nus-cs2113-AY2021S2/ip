import java.util.Scanner;


public class Duke {





    public static String command_parser(todo list, String command){
        String[] tokens = command.split(" ");
        int itemStartIndex = command.indexOf(" ") + 1;
        int itemEndIndex;
        int dateStartIndex;
        String out = "";
        switch(tokens[0]){
        case "list":
            out = list.listItems();
            break;
        case "deadline":
            itemEndIndex = command.indexOf("/by") - 1;
            dateStartIndex = itemEndIndex + 5;
            out = list.addItems(command.substring(itemStartIndex,itemEndIndex),listTypes.deadline,command.substring(dateStartIndex));
            out += "\nNow you have " + list.getItems() + " item(s) in the list!";
            break;
        case "event":
            itemEndIndex = command.indexOf("/at") - 1;
            dateStartIndex = itemEndIndex + 5;
            out = list.addItems(command.substring(itemStartIndex,itemEndIndex),listTypes.event,command.substring(dateStartIndex));
            out += "\nNow you have " + list.getItems() + " item(s) in the list!";
            break;
        case "todo":
            out = list.addItems(command.substring(itemStartIndex),listTypes.todo);
            out += "\nYou have " + list.getItems() + " item(s) in the list!";
            break;
        case "done":
            out = list.resolveItem(tokens[1]) ;
            out += "\nNow you have " + list.getItems() + " item(s) in the list!";
        }

        return out;

    }

    public static void dukeResponse(String output){
        System.out.println("____________________________________________________________\n" +
                output + "\n" +
                "____________________________________________________________\n");

    }
    public static void dukeStartup(){
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

    }




    public static void main(String[] args) {

        Boolean continueChat = true;
        Scanner in = new Scanner(System.in);
        String output = "Nothing.";
        todo todoList = new todo();



        dukeStartup();
        while(continueChat){
            String input = in.nextLine();
            if(input.equals("bye")){
                output = "Bye. Hope to see you again soon!";
                continueChat = false;
            }else {
                output = command_parser(todoList,input);
            }
            dukeResponse(output);

        }



    }
}
