import java.util.*;


public class Duke {
    //public static String[] to_do_list= new String[100];
    //public static Task finish_goal = new Task("finish goal in list");
    public static Task t[] = new Task[100];

    public static void show_welcome_msg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public static void show_list(int num) {
        System.out.println(" ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (int i = 0; i < num; i++) {
            System.out.println((i + 1) + ". " + "[" + t[i].getSymbol() + "]" + "[" + t[i].getStatusIcon() + "] " + t[i].getDescription());
        }
    }

    public static String userDone(String user_done) {
        switch (user_done) {
            case "todo":
                return "T";
            case "event":
                return "E";
            case "deadline":
                return "D";
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
        }

        return "";


    }

    public static boolean noInput(String input) {
        if (input.equals("")) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return true;
        }
        return false;

    }

    public static void printTask(String taskKind, String substring, int num_of_goals, String ddl, String prep) {
        System.out.println("Got it. I've added this task: ");
        if (ddl == null) {
            System.out.println("[" + taskKind + "]" + "[" + " " + "]" + " " + substring);
        } else {
            System.out.println("[" + taskKind + "]" + "[" + " " + "]" + " " + substring + "(" + prep + ":" + ddl + ")");
        }

        System.out.println("Now you have " + num_of_goals + " task in your list!");
    }


    public static void main(String[] args) {

        show_welcome_msg();
        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();
        int num_of_goals = 0;

        while (!user_input.equals("bye")) {
            if (user_input.equals("list")) {
                show_list(num_of_goals);
            } else {
                int spacing = user_input.indexOf(" ");
                int slash = user_input.indexOf("/");
                //System.out.println(slash);
                String user_done = user_input.substring(0, spacing);
                if (user_done.equals("done")) {
                    String number = user_input.substring(spacing + 1);
                    int number_converted = Integer.parseInt(number);
                    t[number_converted - 1].markAsDone();
                    System.out.println("____________________________________________________________\n" +
                            "     Nice! I've marked this task as done: ");
                    System.out.println("[" + t[number_converted - 1].getSymbol() + "]" + "[" + t[number_converted - 1].getStatusIcon() + "] " + t[number_converted - 1].getDescription() + "(" + t[number_converted - 1].getPrep() + ":" + t[number_converted - 1].getDdl() + ")");
                    System.out.println("____________________________________________________________");
                } else if (slash != -1) {
                    String taskKind = userDone(user_done);
                    if (!taskKind.equals("")) {
                        // System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        //break;

                        String taskName = user_input.substring(spacing + 1, slash);

                        if (!noInput(taskName)) {
                            String period = user_input.substring(slash + 1);
                            //System.out.println(period);
                            String prep = period.substring(0, period.indexOf(" "));
                            // System.out.println(prep);
                            String ddl = period.substring(period.indexOf(" ") + 1);
                            //System.out.println(ddl);

                            t[num_of_goals] = new Task(taskName);
                            t[num_of_goals].setSymbol(taskKind);
                            t[num_of_goals].setDdl(ddl);
                            t[num_of_goals].setPrep(prep);
                            num_of_goals++;

                            printTask(taskKind, taskName, num_of_goals, ddl, prep);
                        }
                    }


                } else {

                    String taskKind = userDone(user_done);
                    if (!taskKind.equals("")) {
                        //System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        //break;

                        String taskName = user_input.substring(spacing + 1);

                        if (!noInput(taskName)) {
                            t[num_of_goals] = new Task(taskName);
                            t[num_of_goals].setSymbol(taskKind);
                            //t[num_of_goals].setDdl(ddl);
                            num_of_goals++;

                            printTask(taskKind, taskName, num_of_goals, null, null);
                        }


                        //String ddl= user_input.substring(slash+1);
                        //String ddl = null;

                    }

                }
                user_input = sc.nextLine();
            }


            //else if (user_input.equals("todo")){
            //  String todo= user_input.substring(spacing+1);

            //}
            //else{
            //  System.out.println( "___________________________________________________________\n" +
            //        "Added: "+ user_input +  "____________________________________________________________" );
            //to_do_list[num_of_goals] = user_input;
            //t[num_of_goals] = new Task(user_input);
            //num_of_goals++;
            //}
            //   }

            //}
        }
        System.out.println("____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");


    }


}