import java.util.Scanner;

public class Duke {
    public static String inputValue[] = new String [100];
    public static boolean inputStatus[] = new boolean [100];
    public static String inputState[] = new String [100];
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
        "Hello! I'm Duke\n"+
        "What can I do for you?\n"+
       "____________________________________________________________\n");
        interaction();
    }

    public static void interaction(){
        int listIndex = 0;
        String deleteString = "done ";
        String stateTodo = "todo ";
        String stateDeadline = "deadline ";
        String stateEvent = "event ";
        do{
            Scanner input = new Scanner(System.in);
            input.useDelimiter("\n");
            inputValue[listIndex] = input.next();
            inputStatus[listIndex] = false;
            inputState[listIndex] = "T";
            if(inputValue[listIndex].equals("bye")){
                System.out.println("____________________________________________________________\n"+
                "Bye. Hope to see you again soon!"+"\n"+
                "____________________________________________________________\n");
            }else if(inputValue[listIndex].equals("list")){
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i<listIndex; i++){
                    int index = i+1;
                    if (inputStatus[i]==true){
                        System.out.println(index +".["+inputState[i]+"]"+"[x] "+ inputValue[i]);
                    }else{
                        System.out.println(index +".["+inputState[i]+"]"+"[ ] "+ inputValue[i]);
                    }
                }
                System.out.println("____________________________________________________________\n");
            }else if(inputValue[listIndex].startsWith(deleteString)){
                inputValue[listIndex] = deletePrefix(inputValue[listIndex],deleteString);
                int doneIndex = Integer.parseInt(inputValue[listIndex]);
                System.out.println("____________________________________________________________\n"+
                "Nice! I've marked this task as done: \n"+
                "["+inputState[doneIndex-1]+"]"+"[X] "+ inputValue[doneIndex-1]+"\n"+
                "____________________________________________________________\n");
                inputStatus[doneIndex-1] = true;
            }else{
                if(inputValue[listIndex].startsWith(stateEvent)){
                    inputState[listIndex] = "E";
                    inputValue[listIndex] = deletePrefix(inputValue[listIndex],stateEvent);
                    inputValue[listIndex] = inputValue[listIndex].replace("/", "(");
                    inputValue[listIndex] = inputValue[listIndex].replace("at", "at:");
                    inputValue[listIndex] = inputValue[listIndex]+")";
                }else if(inputValue[listIndex].startsWith(stateTodo)){
                    inputState[listIndex] = "T";
                    inputValue[listIndex] = deletePrefix(inputValue[listIndex],stateTodo);
                }else if(inputValue[listIndex].startsWith(stateDeadline)){
                    inputState[listIndex] = "D";
                    inputValue[listIndex] = deletePrefix(inputValue[listIndex],stateDeadline);
                    inputValue[listIndex] = inputValue[listIndex].replace("/", "(");
                    inputValue[listIndex] = inputValue[listIndex].replace("by", "by:");
                    inputValue[listIndex] = inputValue[listIndex]+")";
                }
                System.out.println("____________________________________________________________\n"+
                "Got it. I've added this task:\n"+
                "["+inputState[listIndex]+"]"+"[ ] "+inputValue[listIndex]+"\n"+
                "Now you have "+(listIndex+1)+" tasks in the list.\n"+
                "____________________________________________________________\n");
                listIndex+=1;
                inputValue[listIndex]=inputValue[listIndex-1];
            }
           }while(!inputValue[listIndex].equals("bye"));
    }

    public static String deletePrefix(String input, String prefix){
        int prefixLength = prefix.length();
        input = input.substring(prefixLength);
        return input;
    }
    // public static String inputState(int state){
    //     String returnValue = null;
    //     if(state == 0){
    //         returnValue = 
    //     }else if(state == 1){

    //     }else if(state ==2){

    //     }
    // }
}
