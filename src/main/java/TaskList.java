import Exception.TaskAlreadyDoneException;

public class TaskList {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;
    public static final String LINE_SEPERATOR = "    ____________________________________________________________";

    public int getTaskCount(){
        return taskCount;
    }


    public void addTask(Task task){
        if(taskCount == 100){
            System.out.println("Duke can store 100 task at most.");
            return;
        }
        tasks[taskCount] = task;
        taskCount++;
        task.newTaskOutput();
        System.out.println("    Now you have " + taskCount + " tasks in the list\n" + LINE_SEPERATOR);
    }

    public void setTaskDone(int taskIndex){
        tasks[taskIndex].setTaskDone(true);
    }

    public Task getTask(int taskIndex){
        return tasks[taskIndex];
    }

    public void printTaskList(){
        System.out.println(LINE_SEPERATOR + "\n    Here are the tasks in your list:");
        for(int i=0; i<taskCount; i++){
            System.out.print("    " + (i+1) + ".");
            tasks[i].printTaskInfo();
        }
        System.out.println(LINE_SEPERATOR);
    }

    public int getTasIndex(String taskName){
        for(int i=0; i<taskCount; i++){
            if(taskName.equals(tasks[i].getTaskName())){
                return i;
            }
        }
        return -1;
    }

    public void setTaskDone(String index) throws IndexOutOfBoundsException, NumberFormatException, TaskAlreadyDoneException {
        int indexInt = Integer.parseInt(index) - 1;
        if(indexInt > taskCount){
            throw new IndexOutOfBoundsException();
        }
        if(this.getTask(indexInt).getTaskDone()){
            throw new TaskAlreadyDoneException();
        }
        this.setTaskDone(indexInt);
        System.out.print(LINE_SEPERATOR +
                "\n    Nice! I've marked this task as done:\n        ");
        this.getTask(indexInt).printTaskInfo();
        System.out.println(LINE_SEPERATOR);
    }


}
