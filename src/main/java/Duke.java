import java.util.Scanner;

public class Duke {
    public static String inputValue[] = new String [100];
    public static boolean inputStatus[] = new boolean [100];
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
        do{
            Scanner input = new Scanner(System.in);
            input.useDelimiter("\n");
            inputValue[listIndex] = input.next();
            inputStatus[listIndex] = false;
            if(inputValue[listIndex].equals("bye")){
                System.out.println("____________________________________________________________\n"+
                "Bye. Hope to see you again soon!"+"\n"+
                "____________________________________________________________\n");
            }else if(inputValue[listIndex].equals("list")){
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i<listIndex; i++){
                    int index = i+1;
                    if (inputStatus[i]==true){
                        System.out.println(index +".[x] "+ inputValue[i]);
                    }else{
                        System.out.println(index +".[ ] "+ inputValue[i]);
                    }
                }
                System.out.println("____________________________________________________________\n");
            }else if(inputValue[listIndex].startsWith(deleteString)){
                int delStrLength = deleteString.length();
                inputValue[listIndex] = inputValue[listIndex].substring(delStrLength);
                int doneIndex = Integer.parseInt(inputValue[listIndex]);
                System.out.println("____________________________________________________________\n"+
                "Nice! I've marked this task as done: \n"+
                "[X] "+ inputValue[doneIndex-1]+"\n"+
                "____________________________________________________________\n");
                inputStatus[doneIndex-1] = true;
            }else{
                System.out.println("____________________________________________________________\n"+
                "added: "+inputValue[listIndex]+"\n"+
                "____________________________________________________________\n");
                listIndex+=1;
                inputValue[listIndex]=inputValue[listIndex-1];
            }
           }while(!inputValue[listIndex].equals("bye"));
    }
}
