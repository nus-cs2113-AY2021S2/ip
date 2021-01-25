import java.io.IOException;
import java.util.Scanner;

public class Duke {

	private static final String border = "____________________________________________________________";
	private static final String HelpMessage = "HELP COMMANDS\n"
			+" list - view timetable\n"
			+" bye  - terminate program\n";
	public static int MAX_SIZE = 100;
	public static Task[] Timetable = new Task[MAX_SIZE];
	private static int counter = 0;

	public static void printGreeting(){
		String alfred = "⊂_ヽ\n"
				+"　 ＼ ＼ what\n"
				+"　　  ＼ ( ͡° ͜ʖ ͡°) can I \n"
				+"　　　 >　   ⌒    ヽ\n"
				+"　　　/ 　   へ      ＼\n"
				+"　　/　　  /　  ＼   do for you?\n"
				+"　　(　  (ヽ　　    ヽ _   つ\n"
				+"   |  　| \\ \n"
				+"　  | 丿 ＼ ⌒)\n"
				+"　  | |　　) /\n"
				+"   ノ )　　Lﾉ \n"
				+"  (_／\n";
		System.out.println(border + "\n" + "Good Day, I'm Alfred.\n" + alfred + border);
	}

	public static void printExit(){
		String wave = " __      __\n"
				+"( _\\    /_ )\n"
				+" \\ _\\  /_ / \n"
				+"  \\ _\\/_ /_ _\n"
				+"  |_____/_/ /|\n"
				+"  (  (_)__)J-)\n"
				+"  (  /`.,   /\n"
				+"   \\/  ;   /\n"
				+"    | === |See you again!\r\n";
		System.out.println(border + "\n" + "Pleasure serving you...\n" + wave + border);
	}

	public static String readInput(){
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		return input;
	}

	public static void addTask(String input) {

		Timetable[counter] = new Task(input);
		counter++;
		System.out.println("Added: " + input + "\n" + border);
	}


	public static void loop(){
		while(true){
			String input = readInput();
			if(input.equalsIgnoreCase("bye")){
				printExit();
				System.exit(1);
			}
			else if(input.equalsIgnoreCase("help")) {
				System.out.println(HelpMessage);
			}
			else if(input.equalsIgnoreCase("list")) {
				printTask(Timetable);
			}
			else{
				addTask(input);
			}
		}
	}

	private static void printTask(Task[] timetable) {
		int i;
		System.out.println("Timetable\n");
		for(i=0;i<counter;i++) {
			System.out.println(i+1 + ". " + timetable[i].getName());
		}
	}

	public static void main(String[] args) {
		printGreeting();
		loop();
	}

}