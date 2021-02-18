package TaskClass;

import Exception.TaskAlreadyDoneException;

import Exception.DoneFormatException;
import Exception.DeleteFormatException;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;
    public static final String LINE_SEPERATOR = "    ____________________________________________________________";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskCount = tasks.size();
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public int getTaskCount(){
        return tasks.size();
    }


    public void addTask(Task task){
        tasks.add(task);
        task.newTaskOutput();
        System.out.println("    Now you have " + tasks.size() + " tasks in the list\n" + LINE_SEPERATOR);
    }


    public void setTaskDone(String index) throws IndexOutOfBoundsException, DoneFormatException, TaskAlreadyDoneException {
        int indexInt;
        try {
            indexInt = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DoneFormatException();
        }
        if(tasks.get(indexInt).getDone()){
            throw new TaskAlreadyDoneException();
        }
        tasks.get(indexInt).setDone(true);
        System.out.print(LINE_SEPERATOR +
                "\n    Nice! I've marked this task as done:\n        ");
        tasks.get(indexInt).printTaskInfo();
        System.out.println(LINE_SEPERATOR);
    }

    public void printTaskList(){
        System.out.println(LINE_SEPERATOR + "\n    Here are the tasks in your list:");
        for(int i=0; i<tasks.size(); i++){
            System.out.print("    " + (i+1) + ".");
            tasks.get(i).printTaskInfo();
        }
        System.out.println(LINE_SEPERATOR);
    }

    public void deleteTask(String index) throws DeleteFormatException{
        int indexInt;
        try {
            indexInt = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DeleteFormatException();
        }
        System.out.print(LINE_SEPERATOR +
                "\n    Noted! I've removed this task:\n        ");
        tasks.get(indexInt).printTaskInfo();
        tasks.remove(indexInt);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + LINE_SEPERATOR);
    }

}
