import java.io.IOException;
import java.util.Scanner;

public class Duke {

	public static final String border = "____________________________________________________________";
	public static String logo =  " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

	public static void printGreeting(){
		System.out.println(border + "\n" + "Hello! I'm\n" + logo + "What can I do for you?");
	}

	public static void printExit(){
		System.out.println(border + "\n" + "Bye. Hope to see you again soon!\n" + border);
	}

	public static String readInput(){
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}

	public static void echo(){
		while(true){
			String input = readInput();
			if(input.equals("bye")){
				printExit();
				System.exit(1);
			}
			else{
				System.out.println(border + "\n" + input + "\n" + border);
			}
		}
	}

	public static void main(String[] args) {
		printGreeting();
		echo();
	}

}