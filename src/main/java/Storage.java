import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class handles all reading and writing of data from the save file.
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */
public class Storage {
	private static final String PATH_TO_SAVE_FILE = "./data/duke.txt";
	private static final String SAVE_FILE_DIRECTORY= "./data";
	private static final int MAX_TASKS = 100;

	/**
	 *  Reads user's saved data from previous session.
	 *
	 * @return ArrayList of task objects from user's previous session from a txt file.
	 */
	public TaskList readFromSaveFile() {

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

	/**
	 * Writes Array list of task from user's current session into a txt file for future sessions.
	 *
	 * @param tasks TaskList object created and used during the session.
	 */
	public void writeToSaveFile(TaskList tasks) {
		int numberOfTasks = tasks.size();
		FileWriter fw = null;
		try {
			fw = new FileWriter(PATH_TO_SAVE_FILE);
			for (int i = 0; i < numberOfTasks; i++) {
				String taskToSave = tasks.get(i).saveFormatString();
				fw.write(taskToSave + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
