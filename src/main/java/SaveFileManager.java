import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveFileManager {
	private static final String pathToSaveFile = "./data/duke.txt";
	private static final int MAX_TASKS = 100;
	private static final String stringSeparator = " | ";

	public static Task[] readFromSaveFile() {
		Task[] tasks = new Task[MAX_TASKS];
		File saveFile = new File(pathToSaveFile);
		Scanner sc = null;
		int indexOfTask = 0;
		String taskType = null;
		int isDone;
		String taskDescription = null;
		String taskBy = null;
		String taskAt = null;
		try {
			sc = new Scanner(saveFile);
		} catch (FileNotFoundException e) {
			System.out.println("Save file not found! Creating one...");
		}
		try {
			saveFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (sc.hasNext()){
			String savedTaskObject = sc.nextLine();
			String[] savedTaskSplit = savedTaskObject.split(" \\| ",4);
			taskType = savedTaskSplit[0];
			isDone = Integer.parseInt(savedTaskSplit[1]);
			taskDescription = savedTaskSplit[2];
			switch (taskType){
			case ("T"):
				tasks[indexOfTask] = new Todo(taskDescription);
				indexOfTask++;
				break;
			case ("D"):
				taskBy = savedTaskSplit[3];
				tasks[indexOfTask] = new Deadline(taskDescription,taskBy);
				indexOfTask++;
				break;
			case ("E"):
				taskAt = savedTaskSplit[3];
				tasks[indexOfTask] = new Event(taskDescription,taskAt);
				indexOfTask++;
				break;
			}
		}
		return tasks;
	}

	public static void writeToSaveFile(Task[] tasks, int numberOfTasks) throws IOException {
		FileWriter fw = new FileWriter(pathToSaveFile);
		for (int i = 0; i < numberOfTasks; i++) {
			String taskToSave = tasks[i].saveFormatString();
			fw.write(taskToSave);
		}
		fw.close();
	}
}
