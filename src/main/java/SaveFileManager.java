import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFileManager {
	private static final String pathToSaveFile = "./data/duke.txt";
	private static final int MAX_TASKS = 100;
	private static final String stringSeparator = " | ";

	public static ArrayList<Task> readFromSaveFile() {
		ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS);
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
				tasks.add(new Todo(taskDescription));
				break;
			case ("D"):
				taskBy = savedTaskSplit[3];
				tasks.add(new Deadline(taskDescription,taskBy));
				break;
			case ("E"):
				taskAt = savedTaskSplit[3];
				tasks.add(new Event(taskDescription,taskAt));
				indexOfTask++;
				break;
			}
		}
		return tasks;
	}

	public static void writeToSaveFile(ArrayList<Task> tasks, int numberOfTasks) throws IOException {
		FileWriter fw = new FileWriter(pathToSaveFile);
		for (int i = 0; i < numberOfTasks; i++) {
			String taskToSave = tasks.get(i).saveFormatString();
			fw.write(taskToSave + "\n");
		}
		fw.close();
	}
}
