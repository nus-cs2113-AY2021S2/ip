import java.util.Scanner;

public class Duke {

    // Method to create a new array of size n+1
    public static String[] addToArray(int n, String arr[], String x)
    {
        String newarr[] = new String[n + 1];
        for (int i = 0; i < n; i++) {
            newarr[i] = arr[i];
        }
        newarr[n] = x;
        return newarr;
    }

    public static void main(String[] args) {
        String[] list = new String[0];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
            while (true) {
                String line;
                line = in.nextLine();
                if (line.equals("bye")) {
                    break;
                } else if (line.equals("list")) {
                    for (int i=0; i<list.length; i++) {
                        System.out.println(i+1 + ". " + list[i] );
                    }
                } else {
                    list = addToArray(list.length, list, line);
                    System.out.println("added: " + line);
                }
            }

        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }
}
