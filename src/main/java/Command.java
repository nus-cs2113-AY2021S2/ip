import java.util.Scanner;

public interface Command {
    public void execute(String string);
}
//    private static String getCommand(String input) {
//        String[] command = input.trim().split(" ");
//        return command[0];
//    }
//
//

//    public static void end() {
//        System.out.println("____________________________________________________________" + System.lineSeparator());
//    }
//
//    public static void echo(String input) {
//        System.out.println(input);
//        end();
//    }
//
//    private static Task[] tasks = new Task[100];
//    private static int numOfTasks = 0;
//
//    public static void addTasks(Task t) {
//        tasks[numOfTasks] = t;
//        numOfTasks++;
//        System.out.println("added: " + t);
//        end();
//    }
//
//    public static boolean checkDone (String input) {
//        String [] inputArray = input.split(" ");
//        if(inputArray.length != 2){
//            return false;
//        }
//        else {
//            try{
//                Integer.valueOf(inputArray[1]);
//                return true;
//            }
//            catch (Exception e){
//                return false;
//            }
//        }
//    }
//}
