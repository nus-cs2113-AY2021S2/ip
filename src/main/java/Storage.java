import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
	private static final String PATH_TO_SAVE_FILE = "./data/duke.txt";
	private static final String SAVE_FILE_DIRECTORY= "./data";
	private static final int MAX_TASKS = 100;

	public static TaskList readFromSaveFile() {

		TaskList tasks = new TaskList();
		int indexOfTask = 0;
		String taskType;
		int isDone;
		String taskDescription;
		String taskBy;
		String taskAt;
		try {
			File directory = new File(SAVE_FILE_DIRECTORY);
			File saveFile = new File(PATH_TO_SAVE_FILE);
			if (!directory.exists()){
				directory.mkdirs();
				saveFile.createNewFile();
			}
			Scanner sc =  new Scanner(saveFile);
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
					break;
				}
				if (isDone == 1){
					tasks.get(indexOfTask).setDone();
				}
				indexOfTask++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public static void writeToSaveFile(TaskList tasks, int numberOfTasks) throws IOException {
		FileWriter fw = new FileWriter(PATH_TO_SAVE_FILE);
		for (int i = 0; i < numberOfTasks; i++) {
			String taskToSave = tasks.get(i).saveFormatString();
			fw.write(taskToSave + "\n");
		}
		fw.close();
	}
}
