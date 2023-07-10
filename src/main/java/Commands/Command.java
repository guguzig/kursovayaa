package Commands;

import org.example.NoteBook;

public interface Command {
    void execute(NoteBook noteBook, String[] arguments);
}