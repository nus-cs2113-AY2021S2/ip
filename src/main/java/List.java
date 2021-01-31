public class List {
    protected String name;
    protected int tasksCounter=0;


    public List(String name){
        this.name=name;
    }

    public void printHelp(){
        String help ="           help           :prints list of all commands"+System.lineSeparator()
                +"           list           :prints all lists ordered by category"+System.lineSeparator()
                +"           exit           :shuts down Arthur"+System.lineSeparator()
                +"       todo <task>        :add task to To Do list"+System.lineSeparator()
                +"   event <task> /<time>   :add task to Event List with time"+System.lineSeparator()
                +"   deadline <task /<by>   :add task to Deadline List"+System.lineSeparator()
                +"    done todo <index>     :mark task <index> as done in To Do List"+System.lineSeparator()
                +"    done event <index>    :mark task <index> as done in Event List"+System.lineSeparator()
                +"  done deadline <index>   :mark task <index> as done in Deadline List"+System.lineSeparator();

        System.out.println(help);
        printDottedLines();

    }


    public void incrementTaskCounter(){
        this.tasksCounter++;
    }

    public int getTasksCounter() {
        return tasksCounter;
    }

    public void printListName() {
        System.out.println("ATTENTION, Here's your list of tasks Crewmate!!!");
    }

    public void printDottedLines() {
        System.out.println("____________________________________________________________________________________");
    }
    public void printDottedLineWithNewLine() {
        System.out.println("____________________________________________________________________________________"
                + System.lineSeparator());
    }


    public void printCommandDoesNotExist(){
        System.out.println("There's no such command!!! You look SUS!!!  (ー_ーゞ ");
        printDottedLines();
    }

    public void printWrongListName(){
        System.out.println("This chosen list doesn't exist crewmate!! Watchu doin??? (¬_¬)");
    }


    public void printNoTasksDone() {
        System.out.println("Are you really a Crewmate??? You haven't done any work in this list! （○｀Ｏ´○）");
    }

    public void printCompletedTasks() {
        System.out.println("Good job Crewmate! You completed all your tasks! (─‿─)");
    }

    public void printEmptyList() {
        System.out.println("This list is empty!!! YEEEEEEET!!!");
    }

    public void printNoTasksDoneInAllList(){
        System.out.println("Are you really a Crewmate??? You haven't done any work AT ALL! （○｀Ｏ´○）");
    }

    public void printSomeTasksRemaining(int counter){
        System.out.println("You still have "
                +counter+" task(s) left in all your Lists ! Hurry up!! ＼(｀0´)／");
    }

    public void printGoodEnding(){
       printDottedLines();

       System.out.println("Thanks for your help Crewmate!!"+ System.lineSeparator()
               + "We wouldn't have done this without your help!!"+System.lineSeparator()
               + "Goodbye!!!! (￣▽￣)ノ"+System.lineSeparator());
       printDottedLines();

    }

    public void printBadEnding(){
        printDottedLines();
        System.out.println("You have been kicked out! Bye Impostor!!!  (๑>ᴗ<๑)" + System.lineSeparator());
        printDottedLines();

    }

    public void printTraitor(){
        printDottedLines();
        System.out.println("You are abandoning us!!! I trusted you!!!  (　ﾟдﾟ)" + System.lineSeparator());
        printDottedLines();
    }




}
