import java.util.Scanner;


public class Duke {


    public static String command_parser(todov2 list, String command){
        String[] tokens = command.split(" ");
        int itemStartIndex = command.indexOf(" ") + 1;
        int itemEndIndex;
        int dateStartIndex;
        String out = "";
        String desc = "";
        String date = "";
        switch(tokens[0]){
        case "list":
            out = list.listItems();
            break;
        case "deadline":
            itemEndIndex = command.indexOf("/by") - 1;
            dateStartIndex = itemEndIndex + 5;
            try {
                desc = command.substring(itemStartIndex, itemEndIndex);
                date = command.substring(dateStartIndex);
            }catch(StringIndexOutOfBoundsException e) {
                out = "Invalid deadline task format!"
                    + " Try the following: deadline [task] /by [date] ";
                break;
            }
            out = list.addTask(desc, listTypes.deadline, date);
            out += "\nYou now have " + list.tasksLeft() + " task(s) left in the list!";
            break;
        case "event":
            itemEndIndex = command.indexOf("/at") - 1;
            dateStartIndex = itemEndIndex + 5;
            try {
                desc = command.substring(itemStartIndex, itemEndIndex);
                date = command.substring(dateStartIndex);
            }catch(StringIndexOutOfBoundsException e) {
                out = "Invalid command format!"
                    + " Try the following: event [task] /at [date] ";
                break;
            }
            out = list.addTask(desc, listTypes.event, date);
            out += "\nYou now have " + list.tasksLeft() + " task(s) left in the list!";
            break;
        case "todo":

            desc = command.substring(itemStartIndex);
            if(desc.equals("todo")){
                out = "Invalid todo task!";
                break;
            }
            out = list.addTask(desc,listTypes.todo);
            out += "\nYou now have " + list.tasksLeft() + " task(s) left in the list!";
            break;
        case "done":
            try {
                out = list.resolveTask(tokens[1]);
            }catch(ArrayIndexOutOfBoundsException e){
                out = "Invalid selection!";
                break;
            }
            out += "\nYou now have " + list.tasksLeft() + " task(s) left in the list!";
            break;
        default:
            out = "Invalid task command!";
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
        todov2 todoList = new todov2();



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
