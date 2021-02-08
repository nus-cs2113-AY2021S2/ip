public class TaskList extends List {
    protected String[] description;
    protected boolean[] isDone;

    protected static boolean areAllTasksDone = true;
    protected static boolean areAllTasksNotDone = true;


    public TaskList(int size) {
        super(null);
        this.description = new String[size];
        this.isDone = new boolean[size];

    }


    public void addNewTask(String description) {
        areAllTasksDone = false;
        (this.description)[tasksCounter] = description;
        (this.isDone)[tasksCounter] = false;
        printDottedLines();
        printTaskDescription();
        tasksCounter++;
        printNumberOfTasksLeft();
        printDottedLines();
    }

    public void printTaskDescription() {
        System.out.println("added: " + getTaskDescription(tasksCounter));
    }


    public void printList() {
        printDottedLines();
        if (tasksCounter == 0) {
            printEmptyList();
        } else {
            printListOfTasks();
        }
        printDottedLines();

    }

    public void printListOfTasks() {
        printListName();
        for (int index = 0; index < tasksCounter; index++) {
            printTask(index);
        }
        printNumberOfTasksLeft();
    }

    public void printTask(int index) {
        System.out.println((index + 1) + ". " + "[" + getStatusIcon(index) + "]" + getTaskDescription(index));
    }

    public void printNumberOfTasksLeft() {
        if (getAreAllTasksDone()) {
            printCompletedTasks();
        } else if (getAreAllTasksNotDone()) {
            printNoTasksDone();
        } else {
            printSomeTasksLeft();
        }
    }

    public void printSomeTasksLeft() {
        System.out.println("You still have " + getNumberTasksLeft() + " task(s) left Crewmate! Hurry up!! ＼(｀0´)／");
    }

    public String getStatusIcon(int index) {
        return ((this.isDone)[index] ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getAreAllTasksDone() {
        areAllTasksDone = true;
        for (int index = 0; index < tasksCounter; index++) {
            if (!getIsTaskDone(index)) {
                areAllTasksDone = false;
                break;
            }
        }
        return areAllTasksDone;
    }

    public boolean getAreAllTasksNotDone() {
        areAllTasksNotDone = true;
        for (int index = 0; index < tasksCounter; index++) {
            if (getIsTaskDone(index)) {
                areAllTasksNotDone = false;
                break;
            }
        }
        return areAllTasksNotDone;
    }


    public void markAsDone(int index) throws IllegalTaskException {
        String phrase;
        printDottedLines();
        try {
            phrase = checkAndMarkTaskAsDone(index);
        } catch (IllegalTaskException e) {
            throw new IllegalTaskException();
        }
        System.out.println(phrase);
        printDottedLines();
    }

    private String checkAndMarkTaskAsDone(int index) throws IllegalTaskException {
        String phrase;
        if ((index >= tasksCounter) || (index < 0)) {
            //phrase = getInvalidTaskPhrase();
            throw new IllegalTaskException();
        } else if (this.isDone[index]) {
            phrase = getTaskAlreadyCompletedPhrase();

        } else {
            (this.isDone)[index] = true;
            areAllTasksNotDone = false;
            phrase = getTaskMarkedAsDoneMessage(index);
        }
        return phrase;
    }

    private String getTaskMarkedAsDoneMessage(int index) {
        String phrase;
        if (getAreAllTasksDone()) {
            phrase = "Nice! I've marked this task as done:" + System.lineSeparator()
                    + (index + 1) + "[" + getStatusIcon(index) + "]" + ". " + getTaskDescription(index) + " " + System.lineSeparator()
                    + "Good job Crewmate! You completed all your tasks! in this list(─‿─) " + System.lineSeparator()
                    + "Don't forget to check the other lists as well!!!!";
        } else {
            phrase = " Nice! I've marked this task as done:" + System.lineSeparator()
                    + (index + 1) + "[" + getStatusIcon(index) + "]" + ". " + getTaskDescription(index) + " " + System.lineSeparator()
                    + "You still have " + getNumberTasksLeft()
                    + " task(s) left in this list Crewmate! Hurry up!! ＼(｀0´)／";

        }
        return phrase;
    }

    public String getTaskAlreadyCompletedPhrase() {
        String phrase;
        if (getAreAllTasksDone()) {
            phrase = "This job was already completed!!!!" + System.lineSeparator()
                    + "Good job Crewmate! You completed all your tasks in this list! (─‿─)" + System.lineSeparator()
                    + "Don't forget to check the other lists as well!!!!";


        } else {
            phrase = "What are you doing??? This job was already completed!! (;¬_¬)" + System.lineSeparator()
                    + "You still have " + getNumberTasksLeft()
                    + " tasks left in this list Crewmate! Hurry up!! ＼(｀0´)／";
        }
        return phrase;
    }

    public String getInvalidTaskPhrase() {
        String phrase;
        if (getAreAllTasksNotDone()) {
            phrase = "There's no such task?! Are you really a Crewmate??  (╬⓪益⓪)";
        } else {
            phrase = "There's no such task?! Focus Crewmate!!  (╬⓪益⓪)";
        }
        return phrase;
    }

    public boolean getIsTaskDone(int index) {
        return (this.isDone)[index];
    }

    public String getTaskDescription(int index) {
        return (this.description)[index];
    }

    public int getNumberTasksLeft() {
        if (getAreAllTasksDone()) {
            return 0;
        } else if (getAreAllTasksNotDone()) {
            return getTasksCounter();
        } else {
            return calculateNumberOfTasksLeft();
        }
    }

    private int calculateNumberOfTasksLeft() {
        int taskLeftCounter = 0;
        for (int index = 0; index < tasksCounter; index++) {
            if (!getIsTaskDone(index)) {
                taskLeftCounter++;
            }
        }
        return taskLeftCounter;
    }
}