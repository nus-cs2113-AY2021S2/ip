import java.util.Arrays;

public class TaskManager {
    private static final int MAX_TASK_SIZE = 100;

    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static final String ADDED_TASK_MESSAGE = "Got it. I have added this task: ";
    private static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done: ";

    public TaskManager(){
        // TODO: 02-Feb-21
    }

    private static Task[] tasksObjectsArray = new Task[MAX_TASK_SIZE];

    public static void addTask(String input){
        String firstWord = StringManipulator.getFirstWord(input);
        if (firstWord.equals(TODO_COMMAND)){
            String taskDescription = StringManipulator.getStringAfterWhiteSpace(input);
            Todo t = new Todo(taskDescription);
            tasksObjectsArray[Task.getTaskCount()-1] = t;
            printMessageAfterTaskIsAdded(t);
        }
        else if(firstWord.equals(DEADLINE_COMMAND)){
            String dueDate = StringManipulator.getStringAfterSlash(input);
            String taskDescription = StringManipulator.getStringAfterWhiteSpaceAndBeforeSlash(input);
            Deadline d = new Deadline(taskDescription,dueDate);
            tasksObjectsArray[Task.getTaskCount()-1] = d;
            printMessageAfterTaskIsAdded(d);
        }
        else if(firstWord.equals(EVENT_COMMAND)){
            String eventPeriod = StringManipulator.getStringAfterSlash(input);
            String taskDescription = StringManipulator.getStringAfterWhiteSpaceAndBeforeSlash(input);
            Event e = new Event(taskDescription,eventPeriod);
            tasksObjectsArray[Task.getTaskCount()-1] = e;
            printMessageAfterTaskIsAdded(e);
        }
    }

    private static void printMessageAfterTaskIsAdded(Task task) {
        MainPage.printDivider();
        System.out.println(ADDED_TASK_MESSAGE);
        System.out.println("\t"+task);
        printTaskCount();
        MainPage.printDivider();
    }

    private static void printTaskCount() {
        System.out.println("Now you have " + Task.getTaskCount() + " task(s) in the list.");
    }

    public static void markTaskAsDone(int taskNumber){
        System.out.println(MARKED_TASK_AS_DONE_MESSAGE);
        tasksObjectsArray[taskNumber-1].markAsDone();
        System.out.println("[" + tasksObjectsArray[taskNumber-1].getStatusIcon() + "]"
                + tasksObjectsArray[taskNumber-1].getDescription());
    }

    public static Task[] removeNullFromList(){
        return Arrays.copyOf(tasksObjectsArray, Task.getTaskCount());
    }

    public static void printTaskList(){
        Task[] tasks = removeNullFromList();
        int TaskCount = 0;
        MainPage.printDivider();
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks){
            TaskCount +=1;
            System.out.println(TaskCount + "." + task);
        }
        MainPage.printDivider();
    }
}