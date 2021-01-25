import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String helloMessage = "Hello! I\'m Diuk \n" + "What can I do for you?\n";
        String byeMessage = "Bye. Hope to see you again soon!\n";
        String line = "____________________________________________________________\n";

        // Start - Greet user
        System.out.print(line);
        System.out.print(helloMessage);
        System.out.print(line);

        Scanner in = new Scanner(System.in);

        String[] list = new String[100];
        int count = 0;

         //Loop to receive response
        while (true){
            String input = in.nextLine();

            // EXIT command
            if(input.toUpperCase().equals("BYE")){
                break;
            }

            // LIST command
            if(input.toUpperCase().equals("LIST")){
                int numbering = 1;
                for(String item : list){
                    if(item == null)
                        break;
                    System.out.println(numbering + ". " + item);
                    numbering++;
                }
                System.out.println();
            }

            // DEFAULT
            else {
                // store user command as job
                list[count] = input;
                count++;
                System.out.println("Added to list: " + input + '\n');
            }
        }

        System.out.print(line);
        System.out.print(byeMessage);
        System.out.print(line);
    }

}
