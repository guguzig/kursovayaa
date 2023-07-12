package test;

import commands.NoteNewCommand;
import domain.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NoteNewCommandTest {

    @Test
    public void testAddNote() {
        List<Note> notes = new ArrayList<>();
        NoteNewCommand noteNewCommand = new NoteNewCommand(notes);

        String text = "Test note";
        List<String> labels = List.of("Label1", "Label2");

        noteNewCommand.addNote(text, labels, 1);

        Assertions.assertEquals(1, notes.size());

        Note note = notes.get(0);
        Assertions.assertEquals(1, note.getId());
        Assertions.assertEquals(text, note.getText());
        Assertions.assertEquals(labels, note.getLabels());
    }
}