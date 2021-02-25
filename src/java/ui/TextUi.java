package ui;

import java.util.Scanner;

public class TextUi {

    Scanner in = new Scanner(System.in);

    public TextUi() {
    }

    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
    }

    public void showGoodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }


}
