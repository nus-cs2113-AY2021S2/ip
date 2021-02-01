import java.util.Scanner;

public class Duke {

    private static final int MAX_TASK = 100;

    public static void main(String[] args) {
        printHello();
        runProgram();
        printBye();
    }

    private static void runProgram() {
        Task[] taskList = new Task[MAX_TASK];
        Scanner in = new Scanner(System.in);

        //Loop to receive response.
        while (true){

            String input = in.nextLine();

            // BYE command
            if(input.equalsIgnoreCase("bye")){
                return;
            }

            // LIST command
            if(input.equalsIgnoreCase("list")){
                runList(taskList);
            }

            //HELP COMMAND
            else if(input.equalsIgnoreCase("help")){
                printHelp();
            }

            // DONE command
            else if(startsWith(input, "done")){
                runDone(taskList, input);
            }

            // TO-DO COMMAND
            else if(startsWith(input, "todo")){
                runTodo(taskList, parseJob(input));
            }

            // DEADLINE COMMAND
            else if(startsWith(input, "deadline")){
                runDeadline(taskList, parseJob(input), parseDeadline(input));
            }

            // ADD command
            else {
                runAdd(taskList, input);
            }
        }


    }

    private static void runAdd(Task[] taskList, String input) {
        // stores user command as job
        Task newTask = new Task(input);

        taskList[Task.taskCount] = newTask;
        Task.taskCount++;

        printTaskAdded(newTask);
    }

    private static void runDone(Task[] taskList, String input) {
        String[] word = input.split(" ");

        // check invalid input
        if(word.length != 2){
            printInvalidInputWarning();
            return;
        }

        int jobNumber = Integer.parseInt(word[1]) - 1;

        if(jobNumber < Task.taskCount && jobNumber >= 0){
            markJobAsDone(taskList[jobNumber]);
        }
        else if(Task.taskCount == 0){
            printNoTaskWarning();
        }
        else{
            printInvalidTaskWarning(jobNumber);
        }
    }

    private static void runList(Task[] taskList) {
        int numbering = 1;

        // error handling - no jobs
        if(Task.taskCount == 0){
            System.out.println("No tasks yet! What would you like to do today?\n");
        }
        else {
            for (int i = 0; i < Task.taskCount; i++) {
                System.out.print(numbering + ". ");
                taskList[i].printTask();
                numbering++;
            }
            System.out.println();
        }
    }

    private static void runTodo(Task[] taskList, String input) {

        if(input == null){
            printInvalidInputWarning();
            return;
        }

        Todo newTask = new Todo(input);

        taskList[Task.taskCount] = newTask;
        Task.taskCount++;

        printTaskAdded(newTask);
    }

    private static void runDeadline(Task[] taskList, String input, String by){

        if(input == null || by == null){
            printInvalidInputWarning();
            return;
        }

        Deadline newTask = new Deadline(input, by);
        taskList[Task.taskCount] = newTask;
        Task.taskCount++;

        printTaskAdded(newTask);

    }

    private static String parseJob(String input) {

        String[] words = input.split(" ");

        if(words.length < 2){
            return null;
        }

        String job = words[1];

        for(int i=2; i<words.length; i++){
            if(words[i].equalsIgnoreCase("/by")){
                break;
            }
            job += " " + words[i];
        }

        return job;
    }

    private static String parseDeadline(String input) {

        String[] words = input.split("/by");

        if(words.length == 1){
            return null;
        }

        return words[1].trim();
    }

    private static boolean startsWith(String input, String command){
        return input.toUpperCase().startsWith(command.toUpperCase());
    }

    private static void markJobAsDone(Task task) {
        task.setDone(true);
        System.out.print("Congrats! You've completed: \n   ");
        task.printTask();
        System.out.println();
    }

    private static void printTaskAdded(Task task) {
        System.out.println("Added to list: ");
        task.printTask();
        printNumTasks();
        System.out.println();
    }

    private static void printNumTasks(){
        String output = Integer.toString(Task.taskCount);
        output += (Task.taskCount == 1)? " task" : " tasks";
        output += " in the list";

        System.out.println(output);
    }

    private static void printInvalidInputWarning() {
        System.out.println("Wrong format! Enter \"help\" for a list of available commands and format");
    }

    private static void printNoTaskWarning() {
        System.out.println("You don't have any tasks yet! Enter a task \n");
    }

    private static void printInvalidTaskWarning(int jobNumber) {
        String smaller = "Enter a valid job number. Use the list command to view your current tasks.\n";
        String larger = "You don't have that many jobs! Use the list command to view your current tasks.\n";
        System.out.println(jobNumber < 0 ? smaller : larger);
    }

    private static void printHelp(){
        String commandList = "LIST - \n" +
                "FORMAT: list";

        String commandDone = "DONE - \n" +
                "FORMAT: done [(int) number]";

        String commandTodo = "TODO - \n" +
                "FORMAT: todo [(str) job]";

        String commandDeadline = "DEADLINE - \n" +
                "FORMAT: deadline [(str) job] /by [(str) deadline]";
        String commandAdd = "ADD - \n" +
                "FORMAT: [(str) job]";


        System.out.println("COMMAND LIST:");
        System.out.println("-------------");
        System.out.println(commandAdd + '\n');
        System.out.println(commandTodo + '\n');
        System.out.println(commandDeadline + '\n');
        System.out.println(commandList + '\n');
        System.out.println(commandDone + '\n');
        System.out.println("To exit, enter \"bye\"");

    }

    private static void printHello() {
        // Start - Greets user
        String line = "____________________________________________________________\n";
        String hello_message = "Hello I'm Diuk! \nWhat would you like to do today?\n";

        System.out.print(line);
        System.out.print(hello_message);
        System.out.print(line);
    }

    private static void printBye() {
        String line = "____________________________________________________________\n";
        String bye_message = "Bye! Hit me up if you feel like being productive again ;)\n";

        System.out.print(line);
        System.out.print(bye_message);
        System.out.print(line);
    }
}
