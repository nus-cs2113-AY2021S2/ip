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
        printDottedLines();
        System.out.println("added: "+getTaskDescription(tasksCounter));
        printDottedLines();
        tasksCounter++;

    }

    private void printDottedLines() {
        System.out.println("____________________________________________________________");
    }


    //This instance method prints the whole list of tasks
    public void printList(){
        printDottedLines();
        if(tasksCounter==0){
            printEmptyList();
        }
        else {
            printListOfTasks();
        }
        printDottedLines();

    }


    private void printListOfTasks() {
        System.out.println("ATTENTION, Here's your list of tasks Crewmate!!!");
        for (int index = 0; index < tasksCounter; index++) {
            System.out.println((index + 1) + ". "+"["+getStatusIcon(index) +"]" + getTaskDescription(index));
        }
        printNumberOfTasksLeft();
    }

    private void printNumberOfTasksLeft() {
        if(getAreAllTasksDone()){
            printCompletedTasks();
        }
        else if(getAreAllTasksNotDone()){
            printNoTasksDone();
        }
        else{
            printSomeTasksLeft();
        }
    }

    private void printSomeTasksLeft() {
        System.out.println("You still have "+getNumberTasksLeft()+" task(s) left Crewmate! Hurry up!! ＼(｀0´)／");
    }

    private void printNoTasksDone() {
        System.out.println("Are you really a Crewmate??? You haven't done any work! （○｀Ｏ´○）");
    }

    private void printCompletedTasks() {
        System.out.println("Good job Crewmate! You completed all your tasks! (─‿─)");
    }

    private void printEmptyList() {
        System.out.println("This list is empty!!! YEEEEEEET!!!");
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
        printDottedLines();
        phrase = checkAndMarkTaskAsDone(index);
        System.out.println(phrase);
        printDottedLineWithNewLine();
    }

    private String checkAndMarkTaskAsDone(int index) {
        String phrase;
        if((index >= tasksCounter)||(index < 0)){
            if(areAllTasksNotDone) {
                phrase ="There's no such task?! Are you really a Crewmate??  (╬⓪益⓪)";
            }
            else{
                phrase = "There's no such task?! Focus Crewmate!!  (╬⓪益⓪)";
            }
        }
        else if (this.isDone[index]) {
            if(getAreAllTasksDone()){
                phrase = "This job was already completed!!!!"+System.lineSeparator()
                        + "Good job Crewmate! You completed all your tasks! (─‿─)";

            }
            else{
                phrase ="What are you doing??? This job was already completed!! (;¬_¬)"+System.lineSeparator()
                        + "You still have " + getNumberTasksLeft() + " task(s) left Crewmate! Hurry up!! ＼(｀0´)／";
            }

        }
        else{
            (this.isDone)[index] = true;
            areAllTasksNotDone = false;
            if (getAreAllTasksDone()) {
                phrase =" Nice! I've marked this task as done:"+ System.lineSeparator()
                        +(index +1)+"["+getStatusIcon(index)+"]"+"."+getTaskDescription(index)+System.lineSeparator()
                        + "Good job Crewmate! You completed all your tasks! (─‿─)" ;
            }
            else {
                phrase =" Nice! I've marked this task as done:"+ System.lineSeparator()
                        +(index +1)+"["+getStatusIcon(index)+"]"+"."+getTaskDescription(index)+System.lineSeparator()
                        + "You still have " + getNumberTasksLeft() + " task(s) left Crewmate! Hurry up!! ＼(｀0´)／";

            }
        }
        return phrase;
    }


    private void printDottedLineWithNewLine() {
        System.out.println("____________________________________________________________"
                + System.lineSeparator());
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
            return calculateNumberOfTasksLeft();
        }
    }

    private int calculateNumberOfTasksLeft() {
        int taskLeftCounter=0;
        for(int index =0; index<tasksCounter;index++){
            if(!getIsTaskDone(index)){
                taskLeftCounter++;
            }
        }
        return taskLeftCounter;
    }
}