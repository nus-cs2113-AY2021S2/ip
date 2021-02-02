import java.util.Arrays;

public class TaskManager {
    private static Task[] tasksObjectsArray = new Task[100];

    public static void addTask(String input){
        String firstWord = StringManipulator.getFirstWord(input);
        if (firstWord.equals("todo")){
            String taskDescription = StringManipulator.getStringAfterWhiteSpaceFor(input);
            Todo t = new Todo(taskDescription);
            tasksObjectsArray[Task.getTaskCount()-1] = t;
            printMessageAfterTaskIsAdded(t);
        }
        else if(firstWord.equals("deadline")){
            String dueDate = StringManipulator.getStringAfterSlash(input);
            String taskDescription = StringManipulator.getStringAfterWhiteSpaceAndBeforeSlash(input);
            Deadline d = new Deadline(taskDescription,dueDate);
            tasksObjectsArray[Task.getTaskCount()-1] = d;
            printMessageAfterTaskIsAdded(d);
        }
        else if(firstWord.equals("event")){
            String eventPeriod = StringManipulator.getStringAfterSlash(input);
            String taskDescription = StringManipulator.getStringAfterWhiteSpaceAndBeforeSlash(input);
            Event e = new Event(taskDescription,eventPeriod);
            tasksObjectsArray[Task.getTaskCount()-1] = e;
            printMessageAfterTaskIsAdded(e);
        }
    }

    private static void printMessageAfterTaskIsAdded(Task task) {
        MainUI.printDivider();
        System.out.println("Got it. I have added this task:");
        System.out.println("\t"+task);
        printTaskCount();
        MainUI.printDivider();
    }

    private static void printTaskCount() {
        System.out.println("Now you have " + Task.getTaskCount() + " task(s) in the list.");
    }

    public static void markTaskAsDone(int taskNumber){
        System.out.println("Nice! I've marked this task as done:");
        tasksObjectsArray[taskNumber-1].markAsDone();
        System.out.println("[" + tasksObjectsArray[taskNumber-1].getStatusIcon() + "]"
                + tasksObjectsArray[taskNumber-1].getDescription());

    }

    public static Task[] removeNullFromList(){
        return Arrays.copyOf(tasksObjectsArray, Task.getTaskCount());
    }

    public static void printTaskList(Task[] tasks){
        int TaskCount = 0;
        MainUI.printDivider();
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks){
            TaskCount +=1;
            System.out.println(TaskCount + "." + task);
        }
        MainUI.printDivider();
    }
}