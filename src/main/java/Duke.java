import java.util.Scanner;

public class Duke {
    static String sectionDivider = "____________________________________________________________";

    static String[] tasks = new String[100];
    static int numberOfTasks = 0;

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
            if(line.equals("list")){
                for(int i= 0; i!=numberOfTasks; i++){
                    String output = String.format("%02d. %s", i+1, tasks[i]);
                    System.out.println("\t" + output);
                }
            }else {
                System.out.println("\tadded: " + line);
                tasks[numberOfTasks] = line;
                numberOfTasks++;
            }
            System.out.println("\t" + sectionDivider);
            line = in.nextLine();
        }
        System.out.println(sign_off);
    }


}
