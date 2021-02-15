import java.util.Scanner;
import Controller.DukeController;
import Tasks.*;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        DukeController dc = new DukeController();
        dc.welcomeMessage();
        ArrayList <Task> tasks = dc.loadFile();
        if (tasks == null) {
            tasks = new ArrayList<Task>();
        }
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        String[] strings = new String[3];
        strings = dc.processInput(in);
        int count1 = 0;
        do {
            int numerate = 0;
            if (in.equals("list")) {
                dc.printList(tasks);
            }

            else if (in.contains("delete")) {
                dc.deleteTask(tasks, in);
            }

            else if (in.contains("done")) {
                dc.printDone(tasks, in);
            }

            else if (in.contains("todo")) {
                dc.printTodo(tasks, in, strings, count1);
                count1++;

            }

            else if (in.contains("deadline")) {
                dc.printDeadline(tasks, in, strings, count1);
                count1++;
            }

            else if (in.contains("event")) {
                dc.printEvent(tasks, in, strings, count1);
                count1++;
            }

            else if (in.contains("save")) {
                dc.saveOutput(tasks);
            }

            else {
                dc.printDK(in);
            }
            in = sc.nextLine();
            strings = dc.processInput(in);

        } while (!in.equals("bye"));

        System.out.println("--------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------");
    }
}





