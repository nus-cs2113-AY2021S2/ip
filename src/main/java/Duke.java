import java.util.Scanner;

public class Duke {
    static String sectionDivider = "____________________________________________________________";
    public static void main(String[] args) {
        String greeting = "\t" + sectionDivider + "\n"
                + "\tHello! I'm Duke. \n"
                + "\tWhat can I do for you? \n"
                + "\t" + sectionDivider;
        String sign_off = "\t" + sectionDivider + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + sectionDivider;

        System.out.println(greeting);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(line.toLowerCase().equals("bye") != true){
            System.out.println("\t" + sectionDivider);
            System.out.println("\t" + line);
            System.out.println("\t" + sectionDivider);
            line = in.nextLine();
        }
        System.out.println(sign_off);
    }


}
