package duke;

import duke.commands.Command;
import duke.commands.CommandHandler;
import duke.tasks.TaskManager;

/**
 * Main class that will oversee the running of the program.
 */
public class Duke {
    private Ui ui;
    private TaskManager taskManager;
    private CommandHandler commandHandler;
    private Command cmd;

    Duke() {
        this.ui = new Ui();
        this.commandHandler = new CommandHandler();
        try {
            this.taskManager = new TaskManager(new Storage().load());
        } catch (DukeException e) {
            System.out.println("OOPS error loading storage: " + e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        // If a command has not been set yet or if the ongoing command has been completed
        if (cmd == null || cmd.isDone()) {
            Command cmd = commandHandler.parseCommand(input);
            cmd.init(taskManager, ui);
            setCommand(cmd);
            return cmd.toString();
        } else {
            try {
                cmd.execute(input);
                return cmd.toString();
            } catch (DukeException e) {
                return "OOPS! Something went wrong while executing: " + e.toString();
            }
        }
    }

    private void setCommand(Command cmd) {
        this.cmd = cmd;
    }
}
