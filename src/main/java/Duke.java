import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );
        Scanner in = new Scanner(System.in);
        String input;
        String[] data = new String[100];
        int a=0;
        input = in.nextLine();
        while(!input.contains("bye")){
            data[a++] = input;
            System.out.println("added: "+ input);
            input = in.nextLine();
            if(input.contains("list")){
                for(int i=0; i<a; i++) {
                    System.out.println(i + "." + " " + data[i]);
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
