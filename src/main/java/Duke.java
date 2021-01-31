import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greet();
        processCommands();
    }

    private static void processCommands() {
        String line;
        TaskList tasks = new TaskList(100);
        Scanner in = new Scanner(System.in);
        boolean hasToContinue = true;
        while(hasToContinue){
            line = in.nextLine();
            hasToContinue = selectCommand(line, tasks, hasToContinue);
        }
    }

    private static boolean selectCommand(String line, TaskList tasks, boolean hasToContinue) {
        switch (line){
        case "bye":
            exit(tasks);
            hasToContinue = false;
            break;
        case "list":
            tasks.printList();
            break;

        default:
            addOrMarkTasksInList(line, tasks);
            break;
        }
        return hasToContinue;
    }

    private static void addOrMarkTasksInList(String line, TaskList tasks) {
        String []sentence = line.split(" ");
        if(sentence[0].equals("done")){
            markTaskAsDone(tasks, sentence);

        }
        else {
            tasks.addNewTask(line);
        }
    }

    private static void markTaskAsDone(TaskList tasks, String[] sentence) {
        int index;
        try {
            index= Integer.parseInt(sentence[1]);
        } catch (NumberFormatException nfe) {
            index=-1;
        }
        if((index>0) && (index<=(tasks.getTasksCounter())) && (sentence.length==2)){
            tasks.markAsDone(index-1);
        }
        else{
            tasks.markAsDone(-1);
        }
    }

    //This method is called to greet the user
    public static void greet() {
        String logo = getLogo();
        System.out.println("Ssshhhhhh!!!!!" + System.lineSeparator() + logo);
        String greetings = "____________________________________________________________" + System.lineSeparator()
                + "Hello Crewmate! I'm Arthur, ( ͡°͜ ʖ ͡°)" + System.lineSeparator()
                + "Please assign me my tasks to complete!" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator();
        System.out.println(greetings);
    }

    private static String getLogo() {
        return  "             ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀" + System.lineSeparator()
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

    /**
     * This method is used to say farewell to the user
     * @param tasks is the list of tasks, the user input
     */
    public static void exit(TaskList tasks){
        String bye;
        if(tasks.getAreAllTasksDone() && tasks.getTasksCounter()>0){
            bye="____________________________________________________________"+ System.lineSeparator()
                    + "Thanks for your help Crewmate!!"+ System.lineSeparator()
                    + "We wouldn't have done without your help!!"+System.lineSeparator()
                    + "Goodbye!!!! (￣▽￣)ノ"+System.lineSeparator()
                    + "____________________________________________________________"+ System.lineSeparator();
        }
        else if(tasks.getAreAllTasksNotDone()) {
            bye = "____________________________________________________________" + System.lineSeparator()
                    + "You have been kicked out! Bye Impostor!!!  (๑>ᴗ<๑)" + System.lineSeparator()
                    + "____________________________________________________________" + System.lineSeparator();
        }
        else{
            bye = "____________________________________________________________" + System.lineSeparator()
                    + "You are abandoning us!!! I trusted you!!!  (　ﾟдﾟ)" + System.lineSeparator()
                    + "____________________________________________________________" + System.lineSeparator();

        }
        System.out.println(bye);
    }



}
