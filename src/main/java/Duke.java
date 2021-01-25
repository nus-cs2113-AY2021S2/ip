import java.io.IOException;
import java.util.Scanner;

public class Duke {

	private static final String border = "____________________________________________________________";
	private static final String HelpMessage = "----------HELP COMMANDS----------\n"
			+ " list - view timetable\n"
			+ " done [index] - tick as done\n"
			+ " bye  - terminate program\n"
			+ border;

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
		System.out.println("Added to Timetable: " + input + "\n" + border);
	}

	private static void checkTask(int Index) {
		System.out.println("Good Job, I will mark this as done!");
		Timetable[Index].markAsDone();
		System.out.println("[" + Timetable[Index].getStatusIcon() + "] " + Timetable[Index].getName() + "\n" + border);
	}

	private static void printTask() {
		int i;
		System.out.println("----------TIMETABLE----------");
		for(i=0;i<counter;i++) {
			System.out.println(i+1 + ". [" + Timetable[i].getStatusIcon() + "] " + Timetable[i].getName() );
		}
		System.out.println(border);
	}

	public static void main(String[] args) {
		printGreeting();
		while(true){
			String input = readInput();
			if(Timetable.length>100){
				System.out.println("Timetable exceeds 100.");
				System.exit(1);
			}
			else if(input.equalsIgnoreCase("bye")){
				printExit();
				System.exit(1);
			}
			else if(input.equalsIgnoreCase("help")) {
				System.out.println(HelpMessage);
			}
			else if(input.equalsIgnoreCase("list")) {
				printTask();
			}
			else if(input.startsWith("done")){
				int Index = Integer.parseInt(input.substring(5));
				checkTask(Index-1);
			}
			else{
				addTask(input);
			}
		}
	}

}