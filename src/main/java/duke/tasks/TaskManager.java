package duke.tasks;

import duke.DukeException;
import duke.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>TaskManager</code> handles every operation related to tasks.
 */
public class TaskManager {
    private List<Task> tasks;

    /**
     * Constructs a new <code>TaskManager</code> object
     * and initialises its <code>tasks</code> field as
     * an empty <code>ArrayList</code>.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new <code>TaskManager</code> object
     * and initialises its <code>tasks</code> field using the provided
     * argument.
     * @param tasks the list of duke.tasks to be initialised with
     */
    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;   
    }

    /**
     * Adds a task.
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
        save();
    }

    /**
     * Gets the "save" text represenation of each <code>Task</code>
     * and creates a <code>String</code> of the information to be saved.
     * This string information is then passed on to the <code>Storage</code>
     * class where it will handle the saving of the information.
     */
    private void save() {
        assert tasks != null : "the task list was never initialised";
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.saveText()).append("\n");
        }
        Storage.writeTasksFile(sb.toString());
    }

    /**
     * Creates a string representation of all the duke.tasks.
     * @return the string representation of all the duke.tasks
     */
    public String listTasks() {
        int i = 1;
        StringBuffer sb = new StringBuffer("Here are your duke.tasks\n");
        for (Task task: tasks) {
            sb.append("\n").append(i).append(". ").append(task);
            i++;
        }
        return sb.toString();
    }

    /**
     * Marks a particular task as done. The task to be marked as done
     * is identified by the <code>taskNum</code>. <code>taskNum - 1</code>
     * is the index position of the task in the <code>ArrayList</code>.
     * @param taskNum the number of the task in the task list
     * @return the <code>Task</code> that is marked done
     * @throws DukeException if the provided task number is out of bounds of the range of the <code>ArrayList</code>
     */
    public Task markDone(int taskNum) throws DukeException {
        try {
            Task task = tasks.get(taskNum - 1);
            task.setCompleted();
            save();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("you gave an invalid task number!");
        }
        
    }

    /**
     * Deletes a task from the list.
     * @param taskNum the task number of the task to be deleted
     * @throws DukeException if the provided task number is out of bounds of the range of the <code>ArrayList</code>
     */
    public Task deleteTask(int taskNum) throws DukeException {
        try {
            Task task = tasks.remove(taskNum - 1);
            save();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("you gave an invalid task number!");
        }
    }

    /**
     * Finds a task with a given keyword in its name.
     * @param keyword the keyword to search for
     * @return the string represenation of the list of duke.tasks found. If none found, it will
     * return a message indicating that no asks were found.
     */
    public String findTask(String keyword) {
        List<Task> temp = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                temp.add(task);
            }
        }
        if (temp.isEmpty()) {
            return "Sorry there are no tasks that matches your keyword";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks that match your keyword\n");
            for (int i = 0; i < temp.size(); i++) {
                sb.append("\n").append(i + 1).append(". ").append(temp.get(i));
            }
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        return "This is an instance of a task manager";
    }
}