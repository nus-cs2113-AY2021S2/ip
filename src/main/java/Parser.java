public class Parser {


    public void processUserInput(Ui ui, TaskList tasks, String input) {


        if (input.contains("todo")) {
            int dividerPosition = input.indexOf(" ");
            String t = input.substring(dividerPosition + 1);
            if (dividerPosition == -1 || t.equals("") || !input.contains(" ")) {
                ui.printErrorMessage();
            } else {
                tasks.add(new Todo(t));
                ui.printTask(new Todo(t), tasks.size());
            }
        } else if (input.contains("deadline")) {
            int dividerPosition = input.indexOf(" ");
            int dividerPosition_1 = input.indexOf("/");
            String d = input.substring(dividerPosition + 1, dividerPosition_1);
            String by = input.substring(dividerPosition_1 + 4);
            if (dividerPosition == -1 || d.equals("") || !input.contains(" ")) {
                ui.printErrorMessage();
            } else {
                tasks.add(new Deadline(d, by));
                ui.printTask(new Deadline(d, by), tasks.size());
            }

        } else if (input.contains("event")) {
            int dividerPosition = input.indexOf(" ");
            int dividerPosition_1 = input.indexOf("/");
            String e = input.substring(dividerPosition + 1, dividerPosition_1);
            String at = input.substring(dividerPosition_1 + 4);
            if (dividerPosition == -1 || e.equals("") || !input.contains(" ")) {
                ui.printErrorMessage();
            } else {
                tasks.add(new Event(e, at));
                ui.printTask(new Event(e, at), tasks.size());
            }

        } else if (input.equals("list")) {
            if (tasks.size() == 0) {
                ui.printZeroTask();
            } else {

                ui.printList(tasks.getArr());
            }

        } else if (input.contains("done")) {

            int dividerPosition = input.indexOf(" ");
            String taskNo = input.substring(dividerPosition + 1);
            try {
                int taskIndex = Integer.parseInt(taskNo) - 1;
                if (taskIndex >= tasks.size()) {
                    ui.printNoSuchTask();
                } else {
                    tasks.get(taskIndex).markAsDone();
                    ui.printTaskAsDone(tasks,taskIndex);
                }
            } catch (NumberFormatException e) {
                ui.printErrorMessage();
            }
        } else if (input.contains("delete")) {
            int dividerPosition = input.indexOf(" ");
            String taskNo = input.substring(dividerPosition + 1);
            try {
                int taskIndex = Integer.parseInt(taskNo) - 1;
                if (taskIndex >= tasks.size()) {
                    ui.printNoSuchTask();
                } else {
                    ui.printDeleteTask(tasks, taskIndex);
                    tasks.remove(taskIndex);
                    ui.printNumberOfTask(tasks);
                }
            } catch (NumberFormatException e) {
                ui.printErrorMessage();
            } 


        } else {
            ui.printErrorMessage();
        }
    }
}
