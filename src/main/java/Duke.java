import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /**
         * String logo = " ____        _        \n"
         *       + "|  _ \\ _   _| | _____ \n"
         *       + "| | | | | | | |/ / _ \\\n"
         *       + "| |_| | |_| |   <  __/\n"
         *       + "|____/ \\__,_|_|\\_\\___|\n";
         * System.out.println("Hello from\n" + logo);
         */

        String logo = "**********************************\n"
                + "*       Systems: [Online]        *\n"
                + "*      Protocol: [Dominion]      *\n"
                + "*          Race: [Terran]        *\n"
                + "**********************************\n";

        String greeting = "______________________________________________________\n"
                + "Systems Accessed...\n"
                + "Decrypting Overwrite...\n"
                + "Welcome Commander, can I be of assistance, commander?\n"
                + "______________________________________________________\n";

        String exit = "______________________________________________________\n"
                + "Good Bye Commander.\n"
                + "______________________________________________________\n";

        System.out.println(logo);
        System.out.println(greeting);

        String input;
        Scanner in = new Scanner(System.in);

        while(true){
            input = in.nextLine();
            if(input.equalsIgnoreCase("bye")){
                break;
            }
            String echo = "______________________________________________________\n"
                    + "[Orders received] " + input + "\n"
                    + "______________________________________________________\n";
            System.out.println(echo);
        }
        System.out.println(exit);
    }
}
