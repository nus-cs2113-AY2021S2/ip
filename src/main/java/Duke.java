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

            // DONE command
            else if(startsWith(input, "done")){
                runDone(taskList, input);
            }

            // TO-DO COMMAND
            else if(startsWith(input, "todo")){
                runTodo(taskList, input);
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
        // stores user command as job
        Todo newTask = new Todo(input);

        taskList[Task.taskCount] = newTask;
        Task.taskCount++;

        printTaskAdded(newTask);
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
        System.out.println("Wrong format! Enter in the format: \"done [number]\"");
        System.out.println("Make sure number is a valid integer! \n");
    }

    private static void printNoTaskWarning() {
        System.out.println("You don't have any tasks yet! Enter a task \n");
    }

    private static void printInvalidTaskWarning(int jobNumber) {
        String smaller = "Enter a valid job number. Use the list command to view your current tasks.\n";
        String larger = "You don't have that many jobs! Use the list command to view your current tasks.\n";
        System.out.println(jobNumber < 0 ? smaller : larger);
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
