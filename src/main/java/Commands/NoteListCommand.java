package commands;

import domain.Note;
import java.util.List;

public class NoteListCommand implements Command {
    private List<Note> notes;

    public NoteListCommand(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void execute(String[] arguments) {
        List<String> labels = List.of(arguments);

        for (Note note : notes) {
            if (labels.isEmpty() || note.hasLabels(labels)) {
                System.out.println(note);
            }
        }
    }
}



