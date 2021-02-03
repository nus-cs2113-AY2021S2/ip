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
        int taskIndex = -1;
        try {
            taskIndex = (Integer.parseInt(inputPart[1])) - 1;
        } catch (NumberFormatException e){
            for(int i=0; i<taskList.getTaskCount(); i++){
                if(inputPart[1].equals(taskList.getTask(i).getTaskName())){
                    taskIndex = i;
                }
            }
        }
        if(taskIndex == -1){
            System.out.println(LINE_SEPERATOR + "\nThe task doesn't exsit.\n" + LINE_SEPERATOR);
            return;
        }
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
        int taskIndex = -1;
        boolean taskExist = false;
        for(int i=0; i<taskList.getTaskCount(); i++){
            if(taskList.getTask(i).getTaskName().equals(inputPart[1])){
                taskExist = true;
                break;
            }
        }
        if(taskExist){
            System.out.println(LINE_SEPERATOR + "\nThe ToDo already exists.\n" + LINE_SEPERATOR);
            return;
        }
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
        int taskIndex = -1;
        boolean taskExist = false;
        for(int i=0; i<taskList.getTaskCount(); i++){
            if(taskList.getTask(i).getTaskName().equals(name)){
                taskExist = true;
                break;
            }
        }
        if(taskExist){
            System.out.println(LINE_SEPERATOR + "\nThe Deadline already exists.\n" + LINE_SEPERATOR);
            return;
        }
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
        int taskIndex = -1;
        boolean taskExist = false;
        for(int i=0; i<taskList.getTaskCount(); i++){
            if(taskList.getTask(i).getTaskName().equals(name)){
                taskExist = true;
                break;
            }
        }
        if(taskExist){
            System.out.println(LINE_SEPERATOR + "\nThe Deadline already exists.\n" + LINE_SEPERATOR);
            return;
        }
        Event newEvent = new Event(name,false,at);
        taskList.addTask(newEvent);
        System.out.println(LINE_SEPERATOR +
                "\n\tEvent added:\n\t  " +  newEvent.getTaskInfoFormat() +
                "\n\tNow you have " + taskList.getTaskCount() + " tasks in the list\n" + LINE_SEPERATOR);
    }
}
