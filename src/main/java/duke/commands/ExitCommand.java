package duke.commands;

/**
 * <code>duke.commands.ExitCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the job of exiting duke.
 */
public class ExitCommand extends Command {
    /**
     * Returns <code>false</code> so that the program will stop running.
     * @return <code>false</code>
     */
    public boolean execute() {
        return false;
    }
}