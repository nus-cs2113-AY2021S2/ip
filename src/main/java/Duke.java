import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        taskTracker();
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void taskTracker() {
        ArrayList<Task> tasks = SaveFileManager.readFromSaveFile();
        int indexOfTask = tasks.size();
        String userInput;
        Scanner sc = new Scanner(System.in);

        running:
        while (sc.hasNextLine()) {
            userInput = sc.nextLine();
            //Split user input to retrieve task type (i.e. event, deadline) and task description.
            String[] strings = userInput.split(" ",2);
            String taskType = strings[0].toLowerCase();
            String taskDescription = null;
            switch (taskType) {
            case ("todo"):
                try{
                    taskDescription = strings[1];
                } catch (ArrayIndexOutOfBoundsException missingInput){
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    break;
                }
                tasks.add(new Todo(taskDescription));
                System.out.println("\t____________________________________________________________");
                System.out.println("\tGot it! I've added this task:");
                System.out.println("\t" + tasks.get(indexOfTask).toString());
                indexOfTask++;
                System.out.printf("\tNow you have %d tasks in the list.\n", indexOfTask);
                System.out.println("\t____________________________________________________________");
                break;

            case ("deadline"):
                // taskDescription now contains task description and it's deadline date and/or time
                // Split task description and deadline date and/or time
                try{
                    taskDescription = strings[1];
                } catch (ArrayIndexOutOfBoundsException missingInput){
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    break;
                }
                strings = taskDescription.split(" /by ",2);
                taskDescription = strings[0];
                String deadlineDate = strings[1];
                tasks.add(new Deadline(taskDescription, deadlineDate));
                System.out.println("\t____________________________________________________________");
                System.out.println("\tGot it! I've added this task:");
                System.out.println("\t" + tasks.get(indexOfTask).toString());
                indexOfTask++;
                System.out.printf("\tNow you have %d tasks in the list.\n", indexOfTask);
                System.out.println("\t____________________________________________________________");
                break;

            case ("event"):
                // taskDescription now contains task description and it's event date and/or timeslot
                // Split task description and event's date and/or timeslot
                try{
                    taskDescription = strings[1];
                } catch (ArrayIndexOutOfBoundsException missingInput){
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                    break;
                }
                strings = taskDescription.split(" /at ",2);
                taskDescription = strings[0];
                String eventDate = strings[1];
                tasks.add(new Event(taskDescription,eventDate));
                System.out.println("\t____________________________________________________________");
                System.out.println("Got it! I've added this task:");
                System.out.println("\t" + tasks.get(indexOfTask).toString());
                indexOfTask++;
                System.out.printf("Now you have %d tasks in the list.\n", indexOfTask);
                System.out.println("\t____________________________________________________________");
                break;

            case ("list"):
                System.out.println("\t____________________________________________________________");
                System.out.println("Here are the tasks in your tasks:");
                if(indexOfTask > 1) {
                    for (int i = 0; i < indexOfTask; i++) {
                        System.out.printf("\t%d. %s\n", i + 1, tasks.get(i).toString());
                    }
                } else {
                    System.out.println("You have no tasks to complete.");
                }
                System.out.println("\t____________________________________________________________");
                break;

            case ("done"):
                try{
                    taskDescription = strings[1];
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("OOPS!!! Please specify task number.");
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task number not recognised.");
                } catch (NumberFormatException e) {
                    System.out.println("Task number not recognised.");
                }
                int indexOfTaskDone = Integer.parseInt(taskDescription) - 1;
                tasks.get(indexOfTaskDone).setDone();
                System.out.println("\t____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + tasks.get(indexOfTaskDone).toString());
                System.out.println("\t____________________________________________________________");
                break;

            case("delete"):
                try{
                    taskDescription = strings[1];
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("OOPS!!! Please specify task number.");
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task number not recognised.");
                } catch (NumberFormatException e) {
                    System.out.println("Task number not recognised.");
                }
                int indexOfTaskToDelete = Integer.parseInt(taskDescription) - 1;
                Task taskToDelete = tasks.get(indexOfTaskToDelete);
                tasks.remove(indexOfTaskToDelete);
                System.out.println("\t____________________________________________________________");
                System.out.println("Noted. I've removed this task.");
                System.out.println("\t" + taskToDelete.toString());
                indexOfTask--;
                System.out.printf("Now you have %d tasks in the list.\n", indexOfTask);
                System.out.println("\t____________________________________________________________");
                break;

            case ("bye"):
                try {
                    SaveFileManager.writeToSaveFile(tasks, indexOfTask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break running;

            default:
                System.out.println("Sorry! Input not recognised, please try again.");
            }
        }
    }
}
