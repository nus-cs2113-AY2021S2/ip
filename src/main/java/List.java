import java.util.Arrays;

public class List {
    private static String[] data = new String[100];
    private static int dataCount = 0;

    public static void addList(String input){
        data[dataCount] = input;
        dataCount++;
    }

    public static void printList(){
        String listTemplate = "***************************************\n"
                + "*                                     *\n"
                + "*   [Objectives]-[Missions]-[Tasks]   *\n"
                + "*                                     *\n"
                + "***************************************\n";
        System.out.println(listTemplate);
        for (int i = 0; data[i] != null; i++ ) {
            System.out.println("[" + (i+1) + "] " + data[i]); // +1 to make it user readable
        }
        System.out.println(); // To make UI look better
    }
}
