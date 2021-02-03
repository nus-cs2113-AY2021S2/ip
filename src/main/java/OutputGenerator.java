import java.util.Locale;

public class OutputGenerator {
    private static final String LINE_SEPERATOR = "\t____________________________________________________________";

    public static void listOutput(TaskList taskList){
        System.out.println(LINE_SEPERATOR + "\n\tHere are the tasks in your list:");
        taskList.printTaskList();
        System.out.println(LINE_SEPERATOR);
    }

    public static void doneOutput(TaskList taskList, String input){
        String[] inputPart = input.trim().split("\\s+",2);
        int taskIndex = Integer.parseInt(inputPart[1]) - 1;
        if(taskIndex > taskList.getTaskCount()-1){
            System.out.println(LINE_SEPERATOR + "\nIndex out of bound.\n" + LINE_SEPERATOR);
            return;
        }
        if(taskList.getTask(taskIndex).getTaskDone()){
            System.out.println(LINE_SEPERATOR + "\nThe task is done.\n" + LINE_SEPERATOR);
            return;
        }
        taskList.setTaskDone(taskIndex);
        System.out.println(LINE_SEPERATOR +
                "\n\tNice! I've marked this task as done:\n\t\t" + taskList.getTask(taskIndex).getTaskInfoFormat()+ "\n" +
                LINE_SEPERATOR);
    }

    public static void addTodoOutput(TaskList taskList, String input){
        String[] inputPart = input.trim().split("\\s+",2);
        ToDo newTodo = new ToDo(inputPart[1],false);
        taskList.addTask(newTodo);
        System.out.println(LINE_SEPERATOR +
                "\n\tToDo added:\n\t  " +  newTodo.getTaskInfoFormat() +
                "\n\tNow you have " + taskList.getTaskCount() + " tasks in the list\n" + LINE_SEPERATOR);
    }

    public static void addDeadlineOutput(TaskList taskList, String input){
        String[] inputPart = input.trim().split("\\s+",2);
        String[] nameDoby = inputPart[1].split("/by");
        String name = nameDoby[0].trim();
        String doby = nameDoby[1].trim();
        Deadline newDeadline = new Deadline(name,false,doby);
        taskList.addTask(newDeadline);
        System.out.println(LINE_SEPERATOR +
                "\n\tDeadline added:\n\t  " +  newDeadline.getTaskInfoFormat() +
                "\n\tNow you have " + taskList.getTaskCount() + " tasks in the list\n" + LINE_SEPERATOR);
    }

    public static void addEventOutput(TaskList taskList, String input){
        String[] inputPart = input.trim().split("\\s+",2);
        String[] nameAt = inputPart[1].split("/at");
        String name = nameAt[0].trim();
        String at = nameAt[1].trim();
        Event newEvent = new Event(name,false,at);
        taskList.addTask(newEvent);
        System.out.println(LINE_SEPERATOR +
                "\n\tEvent added:\n\t  " +  newEvent.getTaskInfoFormat() +
                "\n\tNow you have " + taskList.getTaskCount() + " tasks in the list\n" + LINE_SEPERATOR);
    }

    public static void defaultOutput(){
        System.out.println(LINE_SEPERATOR + "\n\tInvalid input format.");
        System.out.println("\tInput format:\n\ttodo todo name\n\tdeadline deadline name /by time of deadline" +
                "\n\tevent event name /at time of the event\n" + LINE_SEPERATOR);
    }

}
