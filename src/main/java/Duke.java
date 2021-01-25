import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner in = new Scanner(System.in);
        boolean exit = false;
        String[] storeList = new String[100];
        int inputIndex = 0;
        while (!exit){
            String line = in.nextLine();
            if (!line.equals("bye") && !line.equals("list")){
                System.out.println("added: " + line);
                storeList[inputIndex] = line;
                inputIndex++;
                continue;
            }
            else if (line.equals("list")){
                for (int i=0; i<inputIndex; i++){
                    System.out.println(i+1 + ": " + storeList[i]);
                }
                continue;
            }
            else if (line.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                exit = true;
            }
        }
    }
}
