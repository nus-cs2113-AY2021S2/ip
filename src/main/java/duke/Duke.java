import duke.taskexceptions.EmptyTaskDateException;
import duke.taskexceptions.NoTaskDateException;
import duke.taskexceptions.NoTaskNameException;
import duke.taskexceptions.TaskDateFormatException;
import duke.tasksmanager.*;

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
     * throws exception if user string does not contain task (taskName, taskDate etc.)
     * @param input - entire input string of the user, made of taskType + taskName + taskDate
     */
    public static void separateTypeOfTaskAndTaskInputString(String input) {
        //find position between taskType and rest of task description:
        int taskInputStringPosition = input.indexOf(" ") + 1;
        taskInputString = input.substring(taskInputStringPosition);
    }

    /**
     * Removes taskType from user's entire input string
     * Stores remaining string (with taskName and taskDate combined) in static variable taskInputString
     *
     * Checks if TaskName exists
     *
     * throws exception if user input string does not contain proper taskName
     * @param input - entire input string of the user, made of taskType + taskName + taskDate
     */
    public static void throwsExceptionForNoTaskName(String input) throws NoTaskNameException {
        int taskInputStringPosition = input.indexOf(" ") + 1;
        taskInputString = input.substring(taskInputStringPosition);

        boolean hasNoTaskInputString = false;
        if(taskInputStringPosition == 0){
            hasNoTaskInputString = true; //since input.indexOf(" ") returns -1 if no TaskName
        }

        if (hasNoTaskInputString || taskInputString.isBlank() || taskInputString.startsWith("/")){
            throw new NoTaskNameException();
        }
    }

    /**
     * Takes in the remaining 'taskInputString' of the user's input
     * Splits it into two parts, the TaskName and TaskDateString
     * Splits TaskDateString into complement word "by:" or "at:" & TaskDate
     *
     * End Results: TaskName and TaskDate
     * @param taskInput - essentially taskInputString, which does not include taskType
     */
    public static void splitTaskNameAndDate(String taskInput) {
        int beforeDateStringPosition = taskInput.indexOf("/");
        taskName = taskInput.substring(0, beforeDateStringPosition);

        int dateStringPosition = beforeDateStringPosition + 1;
        String taskDateString = taskInput.substring(dateStringPosition);

        int datePosition = taskDateString.indexOf(" ") + 1;
        taskDate = taskDateString.substring(datePosition);
    }

    /**
     * Takes in the remaining 'taskInputString' of the user's input
     * Splits it into two parts, then stores into TaskName
     *
     * and TaskDateString of the task
     *
     * Splits TaskDateString into 2 parts, complement word "by:" or "at:" & taskDate (further split by " ")
     * ArrayIndexOutOfBoundsException() - TaskDateString is empty - date is missing
     * TaskDateFormatException() - TaskDateString is not written in the Correct Format
     *
     * StringIndexOutOfBoundsException() - if date is missing
     * ArrayIndexOutOfBoundsException() - if date is made of empty spaces
     * @param taskInput - essentially taskInputString, which does not include taskType
     */
    public static void throwsExceptionForNoTaskDate(String taskInput) throws TaskDateFormatException, NoTaskDateException, EmptyTaskDateException {
        int beforeDatePosition = taskInput.indexOf("/");
        if (beforeDatePosition == -1){
            throw new EmptyTaskDateException();
        }
        taskName = taskInput.substring(0, beforeDatePosition);

        int dateStringPosition = beforeDatePosition + 1;
        String taskDateString = taskInput.substring(dateStringPosition);

        String[] taskDateStringWord = taskDateString.split(" "); //throws ArrayIndexOutOfBoundsException for emptyDateString
        if (!(taskDateStringWord[0].equals("at:") || taskDateStringWord[0].equals("by:"))){
            throw new TaskDateFormatException();
        }

        int datePosition = taskDateString.indexOf(" ")+1;
        if (datePosition == 0) {
            throw new NoTaskDateException(); //throws NoTaskDateException for missing TaskDate
        }
        taskDate = taskDateString.substring(datePosition);
        if (taskDate.isBlank()) {
            throw new EmptyTaskDateException(); //throws EmptyTaskDateException for empty TaskDate
        }
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

                addToDo(input);

            } else if (input.startsWith("deadline")) {

                addDeadline(input);

            } else if (input.startsWith("event")) {

                addEvent(input);

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

                markAndPrintsTaskAsDone(input);

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println(line); //end of current response to User
            //takes in next input:
            input = in.nextLine();

        }

        saysByeToUser();
    }

    public static void addToDo(String input) {
        try {
            throwsExceptionForNoTaskName(input);
            System.out.println("Got it. I've added this task:");

            separateTypeOfTaskAndTaskInputString(input);
            taskName = taskInputString;

            tasks[taskCount] = new ToDos(taskName); //add task to list
            printAddedTask();

        } catch (NoTaskNameException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void addDeadline(String input) {
        try {
            throwsExceptionForNoTaskName(input);
            throwsExceptionForNoTaskDate(taskInputString);

            System.out.println("Got it. I've added this task:");

            separateTypeOfTaskAndTaskInputString(input);
            splitTaskNameAndDate(taskInputString);

            tasks[taskCount] = new Deadlines(taskName, taskDate); //add task to list
            printAddedTask();

        } catch (NoTaskNameException e) {
            System.out.println("☹ OOPS!!! The description of a Deadline task cannot be empty.");
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Please add a date to the Deadline description. :))"); //missing date
        } catch (TaskDateFormatException e) {
            System.out.println("Please change the format to \"Task /at: Date\"."); //wrong date format
        } catch (NoTaskDateException e) {
            System.out.println("Please add a date to the Deadline description. :))"); //missing date
        } catch (EmptyTaskDateException e) {
            System.out.println("Please add a date to the Deadline task description. :))"); //empty date
        }
    }

    public static void addEvent(String input) {
        try {
            throwsExceptionForNoTaskName(input);
            throwsExceptionForNoTaskDate(taskInputString);

            System.out.println("Got it. I've added this task:");

            separateTypeOfTaskAndTaskInputString(input);
            splitTaskNameAndDate(taskInputString);

            tasks[taskCount] = new Events(taskName, taskDate); //add task to list
            printAddedTask();

        } catch (NoTaskNameException e) {
            System.out.println("☹ OOPS!!! The description of a Event task cannot be empty.");
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Please add a date to the Event description. :))"); //missing date
        } catch (TaskDateFormatException e) {
            System.out.println("Please change the format to \"Task /at: Date\"."); //wrong date format
        } catch (NoTaskDateException e) {
            System.out.println("Please add a date to the Event description. :))"); //missing date
        } catch (EmptyTaskDateException e) {
            System.out.println("Please add a date to the Event task description. :))"); //empty date
        }
    }

    public static void markAndPrintsTaskAsDone(String input){
        try{
            String[] commandAndTaskNumber = input.split(" ");
            int index = Integer.parseInt(commandAndTaskNumber[1]) - 1; //obtain task number(which starts from 1)
            //throws NumberFormatException() when user does not input a number after word 'done'

            tasks[index].markAsDone(); //mark task given by current command as 'done'

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + tasks[index].convertToTaskOutputString());
            //throws NullPointerException if taskNumber out of bounds

        } catch (NumberFormatException e) {
            System.out.println("Please input in the format of \'done taskNumber\'"); //wrong format for TaskNumber
        } catch (NullPointerException e) {
            System.out.println("Please input a smaller valid task number.");
            System.out.println("You can list all tasks to check the total number of tasks you have. :))"); //invalid TaskNumber
        }
    }

}
