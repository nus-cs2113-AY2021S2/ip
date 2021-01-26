import java.util.Scanner;

public class Duke {

    public static void greet() {
        String logo = "             ⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀" + System.lineSeparator()
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

        System.out.println("Ssshhhhhh!!!!!" + System.lineSeparator() + logo);
        String greetings = "____________________________________________________________" + System.lineSeparator()
                + "Hello Crewmate! I'm Arthur, ( ͡° ͜ʖ ͡°)" + System.lineSeparator()
                + "Please assign me my tasks to complete!" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator();
        System.out.println(greetings);
    }

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


    public static void main(String[] args) {
        greet();
        boolean hasToExit = false;
        String line;
        TaskList tasks = new TaskList(100);
        Scanner in = new Scanner(System.in);
        while(!hasToExit){
            line = in.nextLine();
            switch (line){
            case "bye":
                exit(tasks);
                hasToExit = true;
                break;
            case "list":
                tasks.printList();
                break;

            default:
                String []sentence = line.split(" ");
                if(sentence[0].equals("done")){
                    Integer index;
                    try {
                        index= Integer.parseInt(sentence[1]);
                    } catch (NumberFormatException nfe) {
                        index=-1;
                    }
                    if((index>0) && (index<(tasks.getTasksCounter())) && (sentence.length==2)){
                        tasks.markAsDone(index-1);
                    }
                    else{
                        tasks.markAsDone(-1);
                    }

                }
                else {
                    tasks.addNewTask(line);
                }
                break;
            }
        }
    }
}
