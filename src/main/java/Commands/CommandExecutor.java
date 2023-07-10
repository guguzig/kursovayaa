package Commands;

import org.example.NoteBook;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("help", new HelpCommand());
        commands.put("note-new", new NoteNewCommand());
        commands.put("note-list", new NoteListCommand());
        commands.put("note-remove", new NoteRemoveCommand());
        commands.put("note-export", new NoteExportCommand());
        commands.put("exit", new ExitCommand());
    }

    public static void execute(NoteBook noteBook, String command) {
        String[] tokens = command.split(" ");
        String commandName = tokens[0].trim();
        String[] arguments = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, arguments, 0, tokens.length - 1);

        Command cmd = commands.get(commandName);
        if (cmd != null) {
            cmd.execute(noteBook, arguments);
        } else {
            System.out.println("Команда не найдена.");
        }
    }
}
