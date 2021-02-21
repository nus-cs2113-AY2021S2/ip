package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DukeController {

    private static Task[] tasks = new Task[100];
    private static int currentTaskLength = 0;

    public static void run() throws IOException {
        DukeStorage.initialize();
        DukeUI.run();
    }

    public static void listTasks() throws FileNotFoundException {
        DukeStorage.readDukeData();
        for(int i=0; i<currentTaskLength; i++){
            System.out.printf("%d.", i+1);
            tasks[i].printTask();
        }
    }

    public static void deleteTask(String input) throws IOException {
        DukeStorage.readDukeData();
        // ensure task index to delete is in range
        int taskNumberToDelete = Integer.parseInt(input.substring(7));
        if(taskNumberToDelete>currentTaskLength || taskNumberToDelete < 1) {
            System.out.println("The task number is out of range! Please check the " +
                    "current tasks using the list command and enter a valid task number.");
            return;
        }
        // delete the task
        Task[] copy = new Task[100];
        for (int i = 0, j = 0; i < currentTaskLength; i++) {
            if (i != taskNumberToDelete - 1) {
                copy[j++] = tasks[i];
            }else{
                System.out.println("Noted. I've removed this task:");
                System.out.println("  ["
                        + tasks[i].getStatusIcon() + "] "
                        + tasks[i].getDescription());
            }
        }
        tasks = copy;
        currentTaskLength--;
        System.out.printf("Now you have %d tasks in the list.\n", currentTaskLength);
        DukeStorage.writeDukeData();
    }

    public static void markAsDone(String input) throws IOException {
        DukeStorage.readDukeData();
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if(taskIndex >= currentTaskLength || taskIndex < 0){
            System.out.println("The task index you have entered is out of bound!");
            return;
        }
        else{
            tasks[taskIndex].setIsDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("["
                    + tasks[taskIndex].getStatusIcon() + "] "
                    + tasks[taskIndex].getDescription());
        }
        DukeStorage.writeDukeData();
    }

    public static void addTask(String input) throws IOException {
        DukeStorage.readDukeData();
        if(DukeCommandValidator.isToDoValid(input)==true){
            tasks[currentTaskLength] = new ToDo(input);
        }else if(DukeCommandValidator.isDeadlineValid(input)==true){
            tasks[currentTaskLength] = new Deadline(input);;
        }else if(DukeCommandValidator.isEventValid(input)){
            tasks[currentTaskLength] = new Event(input);
        }else{
            System.out.println("Invalid syntax for add commands! Please try again!");
            DukeUI.printMenu();
            return;
        }
        System.out.printf("Got it. I've added this task:\n  ");
        tasks[currentTaskLength].printTask();
        System.out.printf("Now you have %d tasks in the list.\n", currentTaskLength + 1);
        currentTaskLength++;
        DukeStorage.writeDukeData();
    }

    public static int getCurrentTaskLength(){
        return currentTaskLength;
    }

    public static void setCurrentTaskLength(int newLength){
        currentTaskLength = newLength;
    }

    public static Task[] getTasks(){
        return tasks;
    }

    public static void setTasks(Task[] newTasks){
        tasks = newTasks;
    }
}
