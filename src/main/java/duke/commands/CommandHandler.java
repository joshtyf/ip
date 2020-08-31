package duke.commands;

/**
 * duke.commands.CommandHandler is the class that will handle all the duke.commands.
 */
public class CommandHandler {
    /**
     * Parses the <code>cmd</code> and returns
     * an appropriate <code>duke.commands.Command</code> object.
     * @param cmd the string command to be parsed.
     * @return a <code>duke.commands.Command</code>.
     */
    public static Command parseCommand(String cmd) {
        switch (cmd) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new TodoCommand();
            case "deadline":
                return new DeadlineCommand();
            case "event":
                return new EventCommand();
            case "complete":
                return new CompleteCommand();
            case "delete":
                return new DeleteCommand();
            case "find":
                return new FindCommand();
            default:
                return new ErrorCommand();
        }
    }
}