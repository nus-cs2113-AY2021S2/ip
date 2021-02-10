public class todov2 {
    private Taskv2[] taskList = new Taskv2[100];
    private int totalTasks;
    private int leftoverTasks;



    public todov2(){
        this.totalTasks = 0;
        this.leftoverTasks = 0;
    }

    public String addTask(String desc, listTypes type, String date){
        taskList[totalTasks] = new Taskv2(desc, type, date);
        totalTasks++;
        leftoverTasks++;
        return "Task: [" + desc + "] has been added.";
    }
    public String addTask(String desc, listTypes type){
        taskList[totalTasks] = new Taskv2(desc, type);
        totalTasks++;
        leftoverTasks++;
        return "Task: [" + desc + "] has been added.";
    }

    public String listItems(){
        String output = "";
        if(this.totalTasks == 0){
            return "Great! There are no tasks to show!";
        }
        for(int i = 0; i < totalTasks; i++){
            if(i < totalTasks - 1) {
                output += Integer.toString(i + 1)
                        + ". " + taskList[i].displayType()
                        + " " + taskList[i].displayResolved()
                        + " " + taskList[i].displayDescription()
                        + " " + taskList[i].displayDate() + "\n";
            }else{
                output += Integer.toString(i + 1)
                        + ". " + taskList[i].displayType()
                        + " " + taskList[i].displayResolved()
                        + " " + taskList[i].displayDescription()
                        + " " + taskList[i].displayDate();
            }
        }
        return output;
    }

    public String resolveTask(String num){
        Integer taskNum = Integer.parseInt(num) - 1;
        if (taskList[taskNum].resolved){
            return "Task is already done!";
        }
        taskList[taskNum].resolve();
        this.leftoverTasks--;
        return "Great! Task ["+ taskList[taskNum].displayDescription() + "] has been marked as done!";

    }

    public int tasksLeft(){
        return this.leftoverTasks;
    }







}
