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
        try{
            taskIndex = (Integer.parseInt(inputPart[1])) - 1;
        } catch (NumberFormatException e){
            for(int i=0; i<taskList.getTaskCount(); i++){
                if(inputPart[1].equals(taskList.getTaskName(i))){
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
        if(taskList.getTaskDone(taskIndex)){
            System.out.println(LINE_SEPERATOR + "\nThe task is done.\n" + LINE_SEPERATOR);
            return;
        }
        taskList.setTaskDone(taskIndex);
        System.out.println(LINE_SEPERATOR +
                "\n\tNice! I've marked this task as done:\n\t\t[X] " + taskList.getTaskName(taskIndex) + "\n" +
                LINE_SEPERATOR);
    }

    public static void addOutput(TaskList taskList, String input){
        int taskIndex = -1;
        boolean taskExist = false;
        for(int i=0; i<taskList.getTaskCount(); i++){
            if(taskList.getTaskName(i).equals(input)){
                taskExist = true;
                break;
            }
        }
        if(taskExist){
            System.out.println(LINE_SEPERATOR + "\nThe task already exists.\n" + LINE_SEPERATOR);
            return;
        }
        taskList.addTask(new Task(input,false));
        System.out.println(LINE_SEPERATOR +
                "\n\tadded: " + input +
                "\n" + LINE_SEPERATOR);
    }
}
