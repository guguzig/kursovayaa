package Commands;
import org.example.NoteBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteListCommand implements Command {
    @Override
    public void execute(NoteBook noteBook, String[] arguments) {
        List<String> labels = new ArrayList<>();
        if (arguments.length > 0) {
            labels.addAll(Arrays.asList(arguments));
        }

        noteBook.listNotes(labels);
    }
}

