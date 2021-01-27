import java.util.Scanner;

public class Bob {
    public static void echoLine(String input) {
        String echoString = "____________________________________________________________\n" +
                input + "\n" +
                "____________________________________________________________\n";
        System.out.println(echoString);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String welcome = "____________________________________________________________\n" +
                " Hello! I'm Bob 😀\n" +
                " If you need anything hit me up fam 😌\n";
        System.out.println(welcome);

        while (true) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }

            echoLine(line);
        }
        String goodbye = "____________________________________________________________\n" +
                " Chao 👌\n" +
                "____________________________________________________________";
        System.out.println(goodbye);
    }
}
