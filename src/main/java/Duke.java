import java.util.Scanner;

public class Duke {

    public static String readPrompt(Scanner in){
        return in.nextLine();
    }
/*
    public static void echoPrompt(String input){
        System.out.println(input);
    }
*/

    public static void displayList(Task[] taskList, int taskIndex){
        if (taskIndex==0){
            System.out.println("\tYou haven't noted down anything yet.");
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<taskIndex; i++){
            System.out.println("\t" + (i+1) + "." +
                                "[" + taskList[i].getStatusIcon() + "]" +
                                taskList[i].getDescription());
        }
    }

    private static void addToList(Task[] taskList, String prompt, int taskIndex) {
        taskList[taskIndex] = new Task(prompt);
        System.out.println("\tGot it. I've added this task:\n" +
                            "  " + taskList[taskIndex].toString());
    }

    private static void completeTask(Task[] taskList, String substring) {
        int taskIndex = Integer.parseInt(substring) - 1;
        taskList[taskIndex].markAsDone();
        System.out.println("\tNice! I've marked this task as done: \n" +
                            "\t" + taskList[taskIndex].toString());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divLine = "\t__________________________________________________________________\n";
        String greetings = "\tHello! I'm Ayanga, your personal task manager.\n" +
                        "\tWhat can I note down for you?\n" +
                        "\tWave \"bye\" to me if you don't need me for now;\n" +
                        "\tSay \"list\" and I will display your tasks.\n" +
                        "\tSay \"done\" and a number to let me know you completed which task.\n";
        Task[] taskList = new Task[100]; // taskList of task items

        System.out.print(divLine + greetings + divLine);
        Scanner in = new Scanner(System.in);
        int taskIndex = 0;
        String prompt = readPrompt(in);
        while (!prompt.equals("bye")) {
            System.out.print(divLine);
            if (prompt.equals("list")){
                displayList(taskList, taskIndex);
            } else if (prompt.startsWith("done")){
                completeTask(taskList, prompt.substring(5));
            } else{
                addToList(taskList, prompt, taskIndex);
                taskIndex++;
            }
            System.out.print(divLine);
            prompt = readPrompt(in);
        }
        String exitPhrase = "\tBye. Hope you have done your work next time I see you!\n" +
                            "\tAh, and also remember to take care of yourself and sleep early :)\n";

        System.out.println(divLine + exitPhrase + divLine);
    }


}
