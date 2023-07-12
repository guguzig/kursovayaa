package commands;

import domain.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandExecutor {
    private static final Map<String, Command> commands = new HashMap<>();
    private static final List<Note> notesList = new ArrayList<>();

    static {
        commands.put("help", new HelpCommand());
        commands.put("note-new", new NoteNewCommand(notesList));
        commands.put("note-list", new NoteListCommand(notesList));
        commands.put("note-remove", new NoteRemoveCommand(notesList));
        commands.put("note-export", new NoteExportCommand(notesList));
        commands.put("exit", new ExitCommand());
    }

    public static void execute(String command) {
        String[] tokens = command.split(" ");
        String commandName = tokens[0].trim();
        String[] arguments = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, arguments, 0, tokens.length - 1);

        Command cmd = commands.get(commandName);
        if (cmd != null) {
            cmd.execute(arguments);
        } else {
            System.out.println("Команда не найдена.");
        }
    }
}