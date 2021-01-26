import java.util.Scanner;

public class Duke {

    static String texts[] = new String[100];
    static int textPosition = 0;


    public static void storeText(String text){
        texts[textPosition] = text;
        textPosition++;
        System.out.println("added: " + text);
    }

    public static void listArray(String[] texts){
        int textNumber = 1;
        for(String text:texts){
            if(text != null){
                System.out.println(textNumber + ". " + text);
                textNumber++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm 343 Guilty Spark! You may call me Spark!");
        System.out.println("What can I do for you?");
        String text = "hi";
        while(!text.equalsIgnoreCase("Bye")){
            Scanner in = new Scanner(System.in);
            text = in.nextLine();
            if(text.equalsIgnoreCase("List")){
                listArray(texts);
            }
            else {
                storeText(text);
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
