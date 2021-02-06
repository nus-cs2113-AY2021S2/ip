import java.util.Scanner;

public class Duke {
    public static void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Ay yo homie! You lookin PENGGGGGGGG today!\nIt's ya boi Duke the Dawg. What can I do for ma G?\n");
    }

    /*Methods to process the input of the user.
    The type of task is indicated by the first substring and the task description is found after the first blank space.
    If a forward slash present,  ths substring is split into the task and the end date which is indicated by the slash.*/
    public static String extractTask(String input) {
        String Task;
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (input.equalsIgnoreCase("todo") || input.equalsIgnoreCase("deadline") || input.equalsIgnoreCase("event")) {
            int indexOfSpace = input.indexOf(" ");
            if (indexOfSpace == -1) {
                return "retry";
            } else {
                String subString = input.substring(indexOfSpace + 1);
                if (subString.contains("/")) {
                    int indexOfSlash = subString.indexOf("/");
                    Task = subString.substring(0, indexOfSlash - 1);
                } else {
                    Task = subString;
                }
                return Task;
            }
        } else {
            return "nonsense";
        }
    }

    public static String extractDate(String input) {
        String Date;
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (input.equalsIgnoreCase("todo") || input.equalsIgnoreCase("deadline") || input.equalsIgnoreCase("event")) {
            int indexOfSpace = input.indexOf(" ");
            if (indexOfSpace == -1) {
                return "retry";
            } else {
                String subString = input.substring(indexOfSpace + 1);
                if (subString.contains("/")) {
                    int indexOfSlash = subString.indexOf("/");
                    String subStringDate = subString.substring(indexOfSlash);
                    int indexNext = subStringDate.indexOf(" ");
                    Date = subStringDate.substring(indexNext + 1);
                } else {
                    Date = null;
                }
                return Date;
            }
        } else {
            return "nonsense";
        }
    }

    public static void main(String[] args) {
        displayWelcome();

        Task[] taskList = new Task[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);
        Boolean isSame = true;
        while (isSame) {
            String input = sc.nextLine();
            String stringTask = extractTask(input);
            String stringDate = extractDate(input);

             /* If input is "bye", system exits with message.
             If input is "list", list of tasks will be displayed.
             If input is "done", the task number to be marked as done.
             If input is "todo", classify task as ToDo.
             If input is "Deadline", classify task as Deadline.
             If input is "Event", classify task as Event.
             If there is no task specified after specifying the type of task, system will prompt for another input*/

            if (stringTask.contains("retry")) {
                System.out.println("Oi allow it fam! Why you got no tasks? Are you dumb? Try again... you melon!");
                continue;
            }

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Ciao Ciao. See ya soon fam!");
                isSame = false;
                System.exit(0);
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tings in yo list: ");
                for (int i=0; i< taskList.length; i++) {
                    if (taskList[i] != null){
                        System.out.println(i+1 + ". " + taskList[i].printDescription());
                    }
                }
            } else if (input.contains("done")) {
                int indexSpace = input.indexOf(" ");
                String numberString = input.substring(indexSpace + 1);
                int taskNumber = Integer.parseInt(numberString);
                System.out.println("Awwww yeah! I've marked this task as done... brrrrrap brrrrrap: ");
                taskList[taskNumber - 1].markAsDone();
                System.out.println(taskList[taskNumber - 1].printDescription());
            } else if (input.contains("todo")) {
                taskList[count] = new toDo(stringTask);
                System.out.println("Ayy I got you my brother. I've added this ting: ");
                System.out.println(taskList[count].printDescription());
                count++;
                System.out.println("Dayuum son! You have " + count + " mad tings in the list.");
            } else if (input.contains("deadline")) {
                taskList[count] = new Deadline(stringTask, stringDate);
                System.out.println("I feel ya. I've added this ting: ");
                System.out.println(taskList[count].printDescription());
                count++;
                System.out.println("Jeeeeeeeez! You got " + count + " mad tings in the list.");
            } else if (input.contains("event")) {
                taskList[count] = new Event(stringTask, stringDate);
                System.out.println("No problem ma dude. I've added this ting: ");
                System.out.println(taskList[count].printDescription());
                count++;
                System.out.println("Wowza! You making it rain with " + count + " mad tings in the list.");
            } else {
                /*taskList[count] = new Task(input);
                System.out.println("Added new task: " + taskList[count].getDescription());
                count++;
                System.out.println("You now have " + count + " tasks in your list.");*/
                System.out.println("What are you tryna say to me? Chatting nonsense yea?");
                continue;
            }
        }
    }
}