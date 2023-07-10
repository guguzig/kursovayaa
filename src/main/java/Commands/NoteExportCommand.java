package Commands;
import org.example.NoteBook;

public class NoteExportCommand implements Command {
    @Override
    public void execute(NoteBook noteBook, String[] arguments) {
        noteBook.exportNotes();
    }
}