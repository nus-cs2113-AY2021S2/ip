package list;

public class List {
    protected String name;

    public List(String name) {
        this.name = name;
    }

    public void greet() {
        String logo = getLogo();
        System.out.println("Ssshhhhhh!!!!!" + System.lineSeparator() + logo);
        String greetings = "____________________________________________________________________________________"
                + System.lineSeparator()
                + "Hello Crewmate! I'm Arthur, ( ͡°͜ ʖ ͡°)" + System.lineSeparator()
                + "Please assign me my tasks to complete!" + System.lineSeparator()
                + "____________________________________________________________________________________"
                + System.lineSeparator();
        System.out.println(greetings);
    }

    private static String getLogo() {
        return "             ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠀ ⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠀ ⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀  ⢀⣀⣀⠈⢻⣿⣿⡄" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀ ⣸⣿⡏⠀⠀⠀ ⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀ ⣿⣿⠁⠀⠀ ⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀ ⠈⠙⢿⣷⡄" + System.lineSeparator()
                + " ⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀ ⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⣷" + System.lineSeparator()
                + " ⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀ ⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿" + System.lineSeparator()
                + " ⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀ ⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃" + System.lineSeparator()
                + " ⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀ ⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇" + System.lineSeparator()
                + " ⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⢸⣿⣧" + System.lineSeparator()
                + " ⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⣿" + System.lineSeparator()
                + " ⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⣿" + System.lineSeparator()
                + " ⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢸⣿⡇" + System.lineSeparator()
                + " ⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⣿⠃" + System.lineSeparator()
                + " ⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀  ⢸⣿⡇" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀  ⣸⣿⠇" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏" + System.lineSeparator()
                + " ⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁" + System.lineSeparator();

    }


    public void printHelp() {
        String help = "           help           :prints list of all commands" + System.lineSeparator()
                + "           list           :prints all lists ordered by category" + System.lineSeparator()
                + "           exit           :shuts down Arthur" + System.lineSeparator()
                + "       todo <task>        :add task to To Do list" + System.lineSeparator()
                + "   event <task> /<time>   :add task to Event List with time" + System.lineSeparator()
                + "   deadline <task /<by>   :add task to Deadline List" + System.lineSeparator()
                + "       done  <index>      :mark task <index> as done in List" + System.lineSeparator();

        System.out.println(help);
        printDottedLines();

    }


    public void printListName() {
        System.out.println("ATTENTION, Here's your list of tasks Crewmate!!!");
    }

    public void printDottedLines() {
        System.out.println("____________________________________________________________________________________");
    }


    public void printCommandDoesNotExist() {
        System.out.println("There's no such command!!! You look SUS!!!  (ー_ーゞ");
    }

    public void printWrongTaskDoneName() {
        System.out.println("This task has already been completed crewmate!! Watchu doin??? (¬_¬)");
    }

    public void printInvalidTaskPhrase() {
        System.out.println("There's no such task?! Focus Crewmate!!  (╬⓪益⓪)");
    }


    public void printNoTasksDone() {
        System.out.println("Are you really a Crewmate??? You haven't done any work in this list! （○｀Ｏ´○）");
    }

    public void printCompletedTasks() {
        System.out.println("Good job Crewmate! You completed all your tasks in  this list! (─‿─)");
    }

    public void printEmptyList() {
        System.out.println("This list is empty!!! YEEEEEEET!!!");
        printDottedLines();
    }


    public void printSomeTasksRemaining(int counter) {
        System.out.println("You still have "
                + counter + " task(s) left in your Lists ! Hurry up!! ＼(｀0´)／");
    }

    public void printGoodEnding() {
        printDottedLines();

        System.out.print("Thanks for your help Crewmate!!" + System.lineSeparator()
                + "We wouldn't have done this without your help!!" + System.lineSeparator()
                + "Goodbye!!!! (￣▽￣)ノ" + System.lineSeparator());
        printDottedLines();

    }

    public void printBadEnding() {
        printDottedLines();
        System.out.print("You have been kicked out! Bye Impostor!!!  (๑>ᴗ<๑)" + System.lineSeparator());
        printDottedLines();

    }

    public void printTraitor() {
        printDottedLines();
        System.out.print("You are abandoning us!!! I trusted you!!!  (　ﾟдﾟ)" + System.lineSeparator());
        printDottedLines();
    }

}
