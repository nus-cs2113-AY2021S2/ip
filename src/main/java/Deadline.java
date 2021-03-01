

import java.util.ArrayList;

public class Deadline extends Tasks{
    protected final String mark = "D";
    protected String modifiedInput;
    private String time;

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    public Deadline(String userInput){
        super(userInput, "D");
            int startIndex = userInput.indexOf("n");
            int endIndex = userInput.indexOf("/by");
            String preciseInput = userInput.substring(startIndex+3,endIndex-1).trim();
            String timing = userInput.substring(endIndex+4);
            this.time = timing;
            this.modifiedInput = preciseInput + "(by: "+timing+")";
//        Tasks obj = new Tasks(modifiedInput,mark);
            super.setCommandDescription(modifiedInput);
    }

    public Deadline(String input, String time) {
        super(input, "D");
        this.time = time;
    }
    public void toPrintDeadline(){
        displayLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("["+mark+"]"+"[ ] "+this.modifiedInput);

    }

    public String toString() {
        return "[" + mark + "]" + super.toString() + "(by: " + this.time + ")";
    }
}
