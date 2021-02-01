import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm \n" + logo );
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String text = "";
        Boolean isContinue = true;

        String[] lists = new String[100];
        Boolean[] isDones = new Boolean[100];
        Integer index = 0;

        while (isContinue) {
            text = scanner.nextLine();
            String[] words = text.split(" ");
            System.out.println(java.util.Arrays.toString(words));
            String firstWord = words[0];

            if (firstWord.equals("done")) {
                Integer number = 0;
                number = number.valueOf(words[1]);

                if (number > index) {
                    continue;
                }

                isDones[number-1] = true;
                System.out.println("____________________________________________________________");
                System.out.println("I marked task " + number + " as done!");
                System.out.println(">> [X] " + lists[number]);
                System.out.println("____________________________________________________________");
            } else if (firstWord.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i=0; i<index; i++) {
                    String status = "[ ]";
                    if (isDones[i]) {
                        status = "[X]";
                    }
                    System.out.println((i+1) + "." + status + " " + lists[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (firstWord.equals("bye")) {
                isContinue = false;

                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
            } else {
                lists[index] = text;
                isDones[index++] = false;

                System.out.println("____________________________________________________________");
                System.out.println("added: " + text + " to the list");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();

    }
}
