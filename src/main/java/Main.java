import java.util.Scanner;

public class Main {
    public static void printLine() {
        System.out.println("_____________________________________________________");
    }

    /*
    public static void echo(String input) {
        printLine();
        System.out.println(input);
        printLine();
    }*/

    public static void add(String input, Task[] list, int index) {
        list[index] = new Task(input);
        printLine();
        System.out.println("\"" + list[index].getDesc() + "\"" + " added to list");
        printLine();
    }

    public static void printList(Task[] list, int index) {
        printLine();
        for(int i=0; i<index; i++) {
            if (i<9) {
                System.out.print(" ");
            }
            System.out.print(i+1);
            System.out.print(list[i].getStatusSymbol());
            System.out.println(list[i].getDesc());
        }
        printLine();
    }

    public static void markAsDone(Task[] list, int taskNo, int max) {
        if (taskNo <= max+1 && taskNo > 0) { //no. is valid
            if (list[taskNo-1].getStatus() == true) {
                printLine();
                System.out.println("Task \"" + list[taskNo-1].getDesc() + "\" is already complete");
                printLine();
            }
            else {
                list[taskNo-1].check();
                printLine();
                System.out.println("Task completed!");
                System.out.println("  " + list[taskNo - 1].getStatusSymbol() + list[taskNo - 1].getDesc());
                printLine();
            }
        }
        else {
            printLine();
            System.out.println("Please provide a valid argument");
            printLine();
        }
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        Task[] list = new Task[100];
        int index = 0;

        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();


        String[] input = in.split(" ");


        while (!input[0].equalsIgnoreCase("bye")) {
            if(input.length != 0) {
                if(input[0].equalsIgnoreCase("list")) {
                    printList(list, index);
                }
                else if (input[0].equalsIgnoreCase("done")) {
                    if (input.length != 2) {
                        printLine();
                        System.out.println("Please provide 1 argument");
                        printLine();
                    }
                    else {
                        int ind = Integer.parseInt(input[1]);
                        markAsDone(list, ind, index);
                    }
                }
                else {
                    add(in, list, index);
                    index++;
                }
            }
            in = scan.nextLine();
            input = in.split(" ");
        }

        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
