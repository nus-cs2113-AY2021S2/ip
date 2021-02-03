public class TaskList {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public int getTaskCount(){
        return taskCount;
    }


    public void addTask(Task task){
        if(taskCount == 100){
            System.out.println("Duke can store 100 task at most.");
        } else{
            tasks[taskCount] = task;
            taskCount++;
        }
    }

    public void setTaskDone(int taskIndex){
        tasks[taskIndex].setTaskDone(true);
    }

    public Task getTask(int taskIndex){
        return tasks[taskIndex];
    }

    public void printTaskList(){
        for(int i=0; i<taskCount; i++){
            System.out.println("\t" + (i+1) + "." + tasks[i].getTaskInfoFormat());
        }
    }

    public int getTasIndex(String taskName){
        for(int i=0; i<taskCount; i++){
            if(taskName.equals(tasks[i].getTaskName())){
                return i;
            }
        }
        return -1;
    }


}
