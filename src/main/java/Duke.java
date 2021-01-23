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

    public static void displaytaskList(Task[] taskList, int promptIndex){
        if (promptIndex==0){
            System.out.println("\tYou haven't noted down anything yet.");
        }
        System.out.println("\tHere are the tasks in your taskList:");
        for (int i=0; i<promptIndex; i++){
            System.out.println("\t" + (i+1) + "." +
                                "[" + taskList[i].getStatusIcon() + "]" +
                                taskList[i].getDescription());
        }
    }

    private static void addTotaskList(Task[] taskList, String prompt, int promptIndex) {
        taskList[promptIndex] = new Task(prompt);
        System.out.println("\tadded: " + prompt);
    }

    private static void completeTask(Task[] taskList, String substring) {
        int taskIndex = Integer.parseInt(substring) - 1;
        taskList[taskIndex].markAsDone();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divLine = "\t____________________________________________________________\n";
        String greetings = "\tHello! I'm Ayanga, your personal task manager.\n" +
                        "\tWhat can I do for you?\n" +
                        "\tWave \"bye\" to me if you don't need me for now;\n" +
                        "\tSay \"list\" and I will display your tasks.\n";
        Task[] taskList = new Task[100]; // taskList of task items

        System.out.print(divLine + greetings + divLine);
        Scanner in = new Scanner(System.in);
        int promptIndex = 0;
        String prompt = readPrompt(in);
        while (!prompt.equals("bye")) {
            System.out.print(divLine);
            if (prompt.equals("list")){
                displaytaskList(taskList, promptIndex);
            } else if (prompt.startsWith("done")){
                completeTask(taskList, prompt.substring(5));
            } else{
                addTotaskList(taskList, prompt, promptIndex);
                promptIndex++;
            }
            System.out.print(divLine);
            prompt = readPrompt(in);
        }
        String exitPhrase = "\tBye. Hope you have done your work next time I see you!\n";

        System.out.println(divLine + exitPhrase + divLine);
    }


}
