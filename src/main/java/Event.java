

import java.util.ArrayList;

public class Event extends Tasks{
    protected String mark = "E";
    private String time;
    protected String modifiedInput;

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    public Event(String userInput){
        super(userInput, "E");
        int startIndex = userInput.indexOf("t");
        int endIndex = userInput.indexOf("/at");
        String preciseInput = userInput.substring(startIndex+2,endIndex-1).trim();
        String timing = userInput.substring(endIndex+4);
        this.time = timing;
        this.modifiedInput = preciseInput + "(at: "+timing+")";
        mark = "E";
        Tasks obj = new Tasks(modifiedInput,mark);
        super.setCommandDescription(modifiedInput);
    }

    public Event(String input, String input2) {
        super(input, "E");
        this.modifiedInput = input + "(at: " + input2 + ")";
        this.time = input2;
    }

    public void toPrintEvent(){
        displayLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("["+mark+"]"+"[ ] "+this.modifiedInput);
    }

    public String toString() {
        return "[" + mark + "]" + super.toString() + "(at: " + this.time + ")";
    }
}
