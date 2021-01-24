import java.util.Scanner;
public class Duke {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("Hello! I'm Julia");
        System.out.println("What can I do for you?");
        System.out.println();
        String input = "";
        do{
            System.out.println("*********************************************");
            input = in.nextLine();
            System.out.println("*********************************************");
            System.out.println(input);
            System.out.println();

        }while(!input.equals("bye"));
        System.out.println("*********************************************");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        System.out.println("*********************************************");
    }
}
