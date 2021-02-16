import java.util.Scanner;


public class Duke {


    public static String command_parser(todo list, String command){
        String[] tokens = command.split(" ");
        int itemStartIndex = command.indexOf(" ") + 1;
        int itemEndIndex;
        int dateStartIndex;
        String out = "";
        String desc = "";
        String date = "";
        switch(tokens[0]){
        case "list": // If command is list, print all tasks in list
            out = list.listItems();
            break;
        case "deadline": // If command is deadline, parse the task and by date, print confirmation
            itemEndIndex = command.indexOf("/by") - 1;
            dateStartIndex = itemEndIndex + 5;
            try {
                desc = command.substring(itemStartIndex, itemEndIndex);
                date = command.substring(dateStartIndex);
            }catch(StringIndexOutOfBoundsException e) { // if task or by date was not entered, catch error
                out = "Invalid deadline task format!"
                    + " Try the following: deadline [task] /by [date] ";
                break;
            }
            out = list.addTask(desc, listTypes.deadline, date);
            out += "\nYou now have " + list.tasksLeft() + " task(s) left in the list!";
            break;
        case "event": // If command is event, parse the task and at date, print confirmation
            itemEndIndex = command.indexOf("/at") - 1;
            dateStartIndex = itemEndIndex + 5;
            try { // if task or at date was not entered, catch error
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
        case "todo": // If command is todo, parse the task, print confirmation

            desc = command.substring(itemStartIndex);
            if(desc.equals("todo")){ // if task was not entered, catch error
                out = "Invalid todo task!";
                break;
            }
            out = list.addTask(desc,listTypes.todo);
            out += "\nYou now have " + list.tasksLeft() + " task(s) left in the list!";
            break;
        case "done": // If command is done, mark task as done, print confirmation
            try {
                out = list.resolveTask(tokens[1]);
            }catch(ArrayIndexOutOfBoundsException e){
                out = "Invalid selection!";
                break;
            }
            out += "\nYou now have " + list.tasksLeft() + " task(s) left in the list!";
            break;
        default: // If command entered is any other unrecognised command, print invalid command
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
