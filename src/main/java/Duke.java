import java.util.Scanner;

public class Duke {
    public static void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello there! I'm Duke.\nWhat can I do for my G?\n");
    }

    public static void main(String[] args) {
        displayWelcome();

        Task[] taskList = new Task[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);
        Boolean isSame = true;

        while (isSame) {
            String input = sc.nextLine();
            String stringTask;
            String stringDate = null;

            /*Processes the input of the user.
            The type of task is indicated by the first substring and the task description is found after the first blank space.
            If a forward slash present,  ths substring is split into the task and the end date which is indicated by the slash.
             */
            int indexOfSpace = input.indexOf(" ");
            String subString = input.substring(indexOfSpace+1);

            if (subString.contains("/")) {
                int indexOfSlash = subString.indexOf("/");
                stringTask = subString.substring(0, indexOfSlash-1);
                String subStringDate = subString.substring(indexOfSlash);
                int indexNext = subStringDate.indexOf(" ");
                String subStringEnd = subStringDate.substring(indexNext+1);
                stringDate = subStringEnd;
            } else {
                stringTask = subString;
            }

            int taskNumber = 0;

            // If input is "bye", system exits with message.
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isSame = false;
                System.exit(0);
            }

            // If input is "list", list of tasks will be displayed.
            else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i=0; i< taskList.length; i++) {
                    if (taskList[i] != null){
                        System.out.println(i+1 + ". " + taskList[i].printDescription());
                    }
                }
            }

            // Identifies the task number to be marked as done.
            else if (input.contains("done")) {
                for (int i = 0; i <input.length(); i++) {
                    char character = input.charAt(i);
                    int index = i;
                    if (++index == input.length()) {
                        if(Character.isDigit(character)) {
                            taskNumber = Character.getNumericValue(character);
                            break;
                        }
                    }
                    char characterTwo = input.charAt(index);
                    if(Character.isDigit(character) && Character.isDigit(characterTwo)) {
                        taskNumber = Character.getNumericValue(character)*10 + Character.getNumericValue(characterTwo);
                        break;
                    }
                }
                System.out.println("Nice! I've marked this task as done: ");
                taskList[taskNumber-1].markAsDone();
                System.out.println(taskList[taskNumber-1].printDescription());
            }

            // Classify task as ToDo.
            else if (input.contains("todo")) {
                taskList[count] = new toDo(stringTask);
                System.out.println("Got it. I've added this task: ");
                System.out.println(taskList[count].printDescription());
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
            }

            // Classify task as Deadline.
            else if (input.contains("deadline")) {
                taskList[count] = new Deadline(stringTask, stringDate);
                System.out.println("Got it. I've added this task: ");
                System.out.println(taskList[count].printDescription());
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
            }

            // Classify task as Event.
            else if (input.contains("event")) {
                taskList[count] = new Event(stringTask, stringDate);
                System.out.println("Got it. I've added this task: ");
                System.out.println(taskList[count].printDescription());
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
            }

            // Adds in a new task.
            else {
                taskList[count] = new Task(input);
                System.out.println("Added new task: " + taskList[count].getDescription());
                count++;
                System.out.println("You now have " + count + " tasks in your list.");
            }
        }
    }
}
