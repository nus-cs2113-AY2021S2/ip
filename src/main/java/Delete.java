//import java.util.ArrayList;
//import java.util.Scanner;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class Delete {
//
//    private static void writeToFile(String filePath, String textToAdd) throws IOException {
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(textToAdd);
//        fw.close();
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        String line;
//        String elem;
//        ArrayList<Task> tasks = new ArrayList<>();
//
//        Scanner in = new Scanner(System.in);
//
//        System.out.println(
//                "\t____________________________________________________________\n" +
//                "\tHello! I'm Duke\n" +
//                "\tWhat can I do for you?\n" +
//                "\t____________________________________________________________\n");
//
//        while (true) {
//
//            line = in.nextLine();
//
//            if (line.startsWith("deadline")) {
//                try {
//                    line.substring(line.indexOf(" "));
//                } catch (java.lang.StringIndexOutOfBoundsException e) {
//                    System.out.print(
//                            "\t____________________________________________________________\n" +
//                            "\t☹ OOPS!!! The description of a " + line + " cannot be empty.\n" +
//                            "\t____________________________________________________________\n");
//                    continue;
//                }
//                elem = line.substring(line.indexOf(" ")+1, line.indexOf("/")-1);
//                Deadline t = new Deadline(elem, line.substring(line.indexOf("/")+4));
//                tasks.add(t);
//                System.out.print(
//                        "\t____________________________________________________________\n" +
//                        "\tGot it. I've added this task: \n" +
//                        "\t" + t + "\n" +
//                        "\tNow you have " + tasks.size() + " tasks in the list.\n" +
//                        "\t____________________________________________________________\n");
//            } else if (line.charAt(0) == 'e') {
//                try {
//                    line.substring(line.indexOf(" "));
//                } catch (java.lang.StringIndexOutOfBoundsException e) {
//                    System.out.print(
//                            "\t____________________________________________________________\n" +
//                            "\t☹ OOPS!!! The description of a " + line + " cannot be empty.\n" +
//                            "\t____________________________________________________________\n");
//                    continue;
//                }
//                elem = line.substring(line.indexOf(" ")+1, line.indexOf("/")-1);
//                event t = new event(elem, line.substring(line.indexOf("/")+4));
//                tasks.add(t);
//                System.out.print(
//                        "\t____________________________________________________________\n" +
//                        "\tGot it. I've added this task: \n" +
//                        "\t" + t + "\n" +
//                        "\tNow you have " + tasks.size() + " tasks in the list.\n" +
//                        "\t____________________________________________________________\n");
//            } else if (line.startsWith("todo")) {
//                try {
//                    line.substring(line.indexOf(" "));
//                } catch (java.lang.StringIndexOutOfBoundsException e) {
//                    System.out.print(
//                            "\t____________________________________________________________\n" +
//                            "\t☹ OOPS!!! The description of a " + line + " cannot be empty.\n" +
//                            "\t____________________________________________________________\n");
//                    continue;
//                }
//                elem = line.substring(5);
//                todo t = new todo(elem);
//                tasks.add(t);
//                System.out.print(
//                        "\t____________________________________________________________\n" +
//                        "\tGot it. I've added this task: \n" +
//                        "\t" + t + "\n" +
//                        "\tNow you have " + tasks.size() + " tasks in the list.\n" +
//                        "\t____________________________________________________________\n");
//            } else if (line.equals("list")) {
//                System.out.print(
//                        "\t____________________________________________________________\n" +
//                        "\tHere are the tasks in your list:\n");
//                int number = 1;
//                for (Task t : tasks) {
//                    System.out.print("\t" + number + "." + t + "\n");
//                    number++;
//                }
//                System.out.print(
//                        "\t____________________________________________________________\n");
//            } else if (line.startsWith("done")) {
//                int num = Integer.parseInt(line.substring(line.indexOf(" ")+1));
//                tasks.get(num-1).getDone(true);
//                System.out.println(
//                        "\t____________________________________________________________\n" +
//                        "\tNice! I've marked this task as done: \n" + "\t" +
//                        tasks.get(num-1)  + "\n" +
//                        "\t____________________________________________________________\n");
//            }  else if (line.startsWith("delete")) {
//                int num = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
//                Task item = tasks.get(num - 1);
//                tasks.remove(item);
//                System.out.println(
//                        "\t____________________________________________________________\n" +
//                        "\tNoted. I've removed this task: \n" +
//                        "\t" + item + "\n" +
//                        "\tNow you have " + tasks.size() + " tasks in the list.\n" +
//                        "\t____________________________________________________________");
//            } else if (line.equals("bye")){
//                System.out.print(
//                        "\t____________________________________________________________\n" +
//                        "\tBye. Hope to see you again soon!\n" +
//                        "\t____________________________________________________________\n");
//                System.exit(0);
//            } else {
//                System.out.print(
//                        "\t____________________________________________________________\n" +
//                        "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
//                        "\tPlease start with the following title:\n\t[T][ ]todo\n\t[D][ ]deadline\n\t[E][ ]event\n" +
//                        "\t____________________________________________________________\n");
//            }
//            Files.delete(Paths.get("D:/OneDrive - National University of Singapore/Study/NUS/Year 2/Semester 2/CS2101/ip/list.txt"));
//            FileWriter list = new FileWriter("D:/OneDrive - National University of Singapore/Study/NUS/Year 2/Semester 2/CS2101/ip/list.txt",true);
//            try {
//                int number = 1;
//                for (Task t : tasks) {
//                    list.write("\t" + number + "." + t + "\n");
//                    number++;
//                }
//                list.close();
//            } catch (IOException e) {
//                System.out.println("Something went wrong: " + e.getMessage());
//            }
//        }
//    }
//}
