package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <code>Storage</code> handles all file read and writes.
 */
public class Storage {
    final static String TASKS_TEXT_FILE_PATH = "src/main/resources/tasks.txt";

    /**
     * Loads the information from the file "duke.tasks.txt" into a
     * <code>List</code>. Since each task from the file is
     * comma seperated, it splits each string into a string array using ","
     * as a delimiter. Then it creates objects of the child classes of
     * <code>Task</code> according to the information provided.
     * @return a list of tasks as a <code>List</code>
     * @throws DukeException if there is an IOexception while creating a new empty file
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        try {
            File f = new File(TASKS_TEXT_FILE_PATH);
            Scanner sc = new Scanner(f);
            
            while (sc.hasNext()) {
                String taskInfo = sc.nextLine();
                String[] taskArr = taskInfo.split(",");
                Task task;
                switch (taskArr[0]) {
                case "T":
                    task = new Todo(taskArr[2]);
                    break;
                case "D":
                    task = new Deadline(taskArr[2], taskArr[3]);
                    break;
                case "E":
                    task = new Event(taskArr[2], taskArr[3], taskArr[4]);
                    break;
                default:
                    throw new DukeException("Unrecognised category character when loading from tasks.txt");
                }
                if (isMarkedDone(taskArr)) {
                    task.setCompleted();
                }
                tasks.add(task);
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            // TODO: Customise font
            try {
                System.out.println("File \"tasks.txt\" does not exist. Attempting to create one for you.");
                File file = new File(TASKS_TEXT_FILE_PATH);
                file.createNewFile();
                System.out.println("Successfully created file tasks.txt");
                return tasks;
            } catch (IOException io) {
                throw new DukeException(io.getMessage());
            }
        }
    }

    private boolean isMarkedDone(String[] taskArr) {
        return taskArr[1].equals("1");
    }

    /**
     * Writes the list of tasks to the file "tasks.txt".
     * @param fileContent the comma seperated string information to be written to the file
     */
    public static void writeTasksFile(String fileContent) throws DukeException {
        try {
            FileWriter fw = new FileWriter(TASKS_TEXT_FILE_PATH, false);
            fw.write(fileContent);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong while saving to the backend: " + e.getMessage());
        }
    }
}
