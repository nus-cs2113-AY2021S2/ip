package duke.tasksmanager;

import java.util.Scanner;

public class Duke {

    public static String line = "____________________________________________________________";
    public static final int INPUT_PHRASES_COUNT = 2;

    public static Tasks[] tasks = new Tasks[100]; //for storing (all types of) tasks
    public static int taskCount = 0; //for counting tasks
    public static String taskInputString; //contains taskName and taskDate (from user's input)
    public static String taskName;
    public static String taskDate;

    /**
     * Prints some lines to welcome the user:
     */
    public static void saysHiToUser() {
        //Greeting:
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints some lines when user exits:
     */
    public static void saysByeToUser() {
        //exits with "bye":
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!"); //exits
        System.out.println(line);
    }

    /**
     * Removes taskType from user's entire input string
     * Stores remaining string (with taskName and taskDate combined) in static variable taskInputString
     *
     * @param input - entire input string of the user, made of taskType + taskName + taskDate
     */
    public static void separateTypeOfTaskAndTaskInputString(String input) {
        //find position between taskType and rest of task description
        int taskInputStringPosition = input.indexOf(" ") + 1;
        taskInputString = input.substring(taskInputStringPosition);
    }

    /**
     * Takes in the remaining 'taskInputString' of the user's input
     * Splits it into two parts, the name and date of the task
     * and stores it in two static variables, taskName & taskDate respectively
     *
     * @param taskInput - essentially taskInputString, which does not include taskType
     */
    public static void splitTaskNameAndDate(String taskInput) {
        int beforeDatePosition = taskInput.indexOf("/");
        int datePosition = beforeDatePosition + 4;
        taskName = taskInput.substring(0, beforeDatePosition);
        taskDate = taskInput.substring(datePosition);
    }

    /**
     * Prints out the taskType, status, taskName and taskDate of the task added by the user
     * and prints the current total number of tasks in the user's list
     * Add to total taskCount (since new task is added)
     */
    public static void printAddedTask() {
        System.out.println("  " + tasks[taskCount].convertToTaskOutputString()); //prints task added
        taskCount++;
        //prints current total number of tasks (in the list of tasks):
        System.out.print("Now you have " + taskCount + " task");
        if (taskCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
    }

    public static void main(String[] args) {

        saysHiToUser();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); //take in User's first input:
        //list, mark or add user's tasks (until user inputs "bye"):
        while (!input.equals("bye")) {

            System.out.println(line); //start of current response to User

            //add and store task to list of tasks
            if (input.startsWith("todo")) {
                System.out.println("Got it. I've added this task:");

                separateTypeOfTaskAndTaskInputString(input);
                taskName = taskInputString;

                tasks[taskCount] = new ToDos(taskName); //add task to list
                printAddedTask();
            } else if (input.startsWith("deadline")) {
                System.out.println("Got it. I've added this task:");

                separateTypeOfTaskAndTaskInputString(input);
                splitTaskNameAndDate(taskInputString);

                tasks[taskCount] = new Deadlines(taskName, taskDate); //add task to list
                printAddedTask();
            } else if (input.startsWith("event")) {
                System.out.println("Got it. I've added this task:");

                separateTypeOfTaskAndTaskInputString(input);
                splitTaskNameAndDate(taskInputString);

                tasks[taskCount] = new Events(taskName, taskDate); //add task to list
                printAddedTask();
            }
            //OR: lists all the user's current tasks in the format of taskType,taskStatus,taskName(and taskDate):
            else if (input.startsWith("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    int taskNumber = i+1; //stores the current numbering of the bulleted tasks
                    System.out.println(taskNumber + "." + tasks[i].convertToTaskOutputString());
                }
            }
            //OR: mark current task as 'done' & outputs the taskType,taskStatus,taskName(and taskDate):
            else if (input.startsWith("done")) {
                String[] commandAndTaskNumber = input.split(" ");
                int index = Integer.parseInt(commandAndTaskNumber[1]) - 1; //obtain task number(which starts from 1)

                tasks[index].markAsDone(); //mark task given by current command as 'done'

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks[index].convertToTaskOutputString());
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println(line); //end of current response to User
            //takes in next input:
            input = in.nextLine();

        }

        saysByeToUser();
    }

}
