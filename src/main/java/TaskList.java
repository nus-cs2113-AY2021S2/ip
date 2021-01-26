public class TaskList {
    protected String[] description;
    protected boolean[] isDone;
    protected static int tasksCounter=0;
    protected static boolean areAllTasksDone = true;
    protected static boolean areAllTasksNotDone = true;

    //This the constructor of TaskList
    public TaskList(int size){
        this.description = new String[size];
        this.isDone =new boolean[size];
    }

    //This instance method add a new task to the list
    public void addNewTask(String description) {
        areAllTasksDone = false;
        (this.description)[tasksCounter] = description;
        (this.isDone)[tasksCounter] = false;
        System.out.println("____________________________________________________________");
        System.out.println("added: "+getTaskDescription(tasksCounter));
        System.out.println("____________________________________________________________");
        tasksCounter++;

    }

    //This instance method prints the whole list of tasks
    public void printList(){
        System.out.println("____________________________________________________________");
        if(tasksCounter==0){
            System.out.println("This list is empty!!! YEEEEEEET!!!");
        }
        else {
            System.out.println("ATTENTION, Here's your list of tasks Crewmate!!!");
            for (int index = 0; index < tasksCounter; index++) {
                System.out.println((index + 1) + ". "+"["+getStatusIcon(index) +"]" + getTaskDescription(index));
            }
            if(getAreAllTasksDone()){
                System.out.println("Good job Crewmate! You completed all your tasks! (─‿─)");
            }
            else if(getAreAllTasksNotDone()){
                System.out.println("Are you really a Crewmate??? You haven't done any work! （○｀Ｏ´○）");
            }
            else{
                System.out.println("You still have "+getNumberTasksLeft()+" task(s) left Crewmate! Hurry up!! ＼(｀0´)／");
            }


        }
        System.out.println("____________________________________________________________");

    }

    //This instance method is used to get status icon of a task
    public String getStatusIcon(int index) {
        return ((this.isDone)[index] ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //This instance method is used verify whether all tasks are done
    public boolean getAreAllTasksDone(){
        areAllTasksDone = true;
        for(int index = 0; index < tasksCounter;index++){
            if(!getIsTaskDone(index)){
                areAllTasksDone = false;
                break;
            }
        }
        return areAllTasksDone;
    }

    //This instance method is used to verify whether all tasks have not been done
    public boolean getAreAllTasksNotDone(){
        areAllTasksNotDone=true;
        for(int index = 0; index < tasksCounter;index++){
            if(getIsTaskDone(index)){
                areAllTasksNotDone = false;
                break;
            }
        }
        return areAllTasksNotDone;
    }

    //This instance method is used to return the Class-level attribute tasksCounter
    public int getTasksCounter() {
        return tasksCounter;
    }

    //This instance method is used to mark a task as done
    public void markAsDone(int index){
        String phrase;
        if((index >= tasksCounter)||(index< 0)){
            if(areAllTasksNotDone) {
                phrase = "____________________________________________________________" + System.lineSeparator()
                        + "There's no such task?! Are you really a Crewmate??  (╬⓪益⓪)" + System.lineSeparator()
                        + "____________________________________________________________" + System.lineSeparator();
            }
            else{
                phrase = "____________________________________________________________" + System.lineSeparator()
                        + "There's no such task?! Focus Crewmate!!  (╬⓪益⓪)" + System.lineSeparator()
                        + "____________________________________________________________" + System.lineSeparator();
            }
        }
        else {
            if (!this.isDone[index]) {
                (this.isDone)[index] = true;
                areAllTasksNotDone = false;
                if (getAreAllTasksDone()) {
                    phrase = "____________________________________________________________" + System.lineSeparator()
                            +" Nice! I've marked this task as done:"+ System.lineSeparator()
                            +(index+1)+"["+getStatusIcon(index)+"]"+"."+getTaskDescription(index)+System.lineSeparator()
                            + "Good job Crewmate! You completed all your tasks! (─‿─)" + System.lineSeparator()
                            + "____________________________________________________________" + System.lineSeparator();
                } else {
                    phrase = "____________________________________________________________" + System.lineSeparator()
                            +" Nice! I've marked this task as done:"+ System.lineSeparator()
                            +(index+1)+"["+getStatusIcon(index)+"]"+"."+getTaskDescription(index)+System.lineSeparator()
                            + "You still have " + getNumberTasksLeft() + " task(s) left Crewmate! Hurry up!! ＼(｀0´)／"
                            + System.lineSeparator()
                            + "____________________________________________________________" + System.lineSeparator();
                }

            }
            else{
                if(getAreAllTasksDone()){
                    phrase = "____________________________________________________________" + System.lineSeparator()
                            +"This job was already completed!!!!"+System.lineSeparator()
                            + "Good job Crewmate! You completed all your tasks! (─‿─)" + System.lineSeparator()
                            + "____________________________________________________________" + System.lineSeparator();

                }
                else{
                    phrase = "____________________________________________________________" + System.lineSeparator()
                            +"What are you doing??? This job was already completed!! (;¬_¬)"+System.lineSeparator()
                            + "You still have " + getNumberTasksLeft() + " task(s) left Crewmate! Hurry up!! ＼(｀0´)／"
                            + System.lineSeparator()
                            + "____________________________________________________________" + System.lineSeparator();

                }
            }
        }
        System.out.println(phrase);
    }

    //This instance method is used to verify whether a task has been completed
    public boolean getIsTaskDone(int index){
        return (this.isDone)[index];
    }

    //This instance method is used to get the attribute description for a particular task
    public String getTaskDescription(int index){
        return (this.description)[index];
    }

    //This instance method is used to get the number of tasks yet to be completed
    public int getNumberTasksLeft(){
        if(areAllTasksDone){
            return 0;
        }
        else if(areAllTasksNotDone){
            return getTasksCounter();
        }
        else{
            int taskLeftCounter=0;
            for(int index =0; index<tasksCounter;index++){
                if(!getIsTaskDone(index)){
                    taskLeftCounter++;
                }
            }
            return taskLeftCounter;
        }
    }
}