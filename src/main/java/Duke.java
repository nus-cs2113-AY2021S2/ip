import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.printf("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        String line;
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();
        while(!line.equals("bye")){
            System.out.println(line);
            line = sc.nextLine();
        }
        System.out.printf("Bye. Hope to see you again soon!\n");
    }
}
