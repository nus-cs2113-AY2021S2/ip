import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.printf("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        String[] items = new String[100];
        int itemIndex = 0;
        String line;
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();
        while(!line.equals("bye")){
            items[itemIndex] = line;
            System.out.println("added: " + items[itemIndex]);
            itemIndex++;
            line = sc.nextLine();
            while(line.equals("list")){
                for(int i=0; i<itemIndex; i++){
                    System.out.println(i + ". " + items[i]);
                }
                line = sc.nextLine();
            }
        }
        System.out.printf("Bye. Hope to see you again soon!\n");
    }
}
