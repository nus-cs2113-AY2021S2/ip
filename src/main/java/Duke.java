import java.util.Scanner;

public class Duke {

    public static String readPrompt(Scanner in){
        return in.nextLine();
    }

    public static void echoPrompt(String input){
        System.out.println(input);
    }

    public static void displayList(String[] list, int promptIndex){
        if (promptIndex==0){
            System.out.println("\tYou haven't noted down anything yet.");
        }
        for (int i=0; i<promptIndex; i++){
            System.out.println("\t" + (i+1) + "." + list[i]);
        }
    }

    private static void addToList(String[] list, String prompt, int promptIndex) {
        list[promptIndex] = prompt;
        System.out.println("\tadded: " + prompt);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String division_line = "\t____________________________________________________________\n";
        String say_hi = "\tHello! I'm Ayanga, your personal task manager.\n" +
                        "\tWhat can I do for you?\n" +
                        "\tWave \"bye\" to me if you don't need me for now;\n" +
                        "\tSay \"list\" and I will display your tasks.\n";
        String[] list = new String[100]; // list of task items

        System.out.print(division_line + say_hi + division_line);
        Scanner in = new Scanner(System.in);
        int promptIndex = 0;
        String prompt = readPrompt(in);
        while (!prompt.equals("bye")) {
            System.out.print(division_line);
            if (prompt.equals("list")){
                displayList(list, promptIndex);
            } else{
                addToList(list, prompt, promptIndex);
                promptIndex++;
            }
            System.out.print(division_line);
            prompt = readPrompt(in);
        }
        String say_bye = "\tBye. Hope you have done your work next time I see you!\n";

        System.out.println(division_line + say_bye + division_line);
    }


}
