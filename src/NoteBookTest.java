import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class NoteBookTest {
    private NoteBook noteBook;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        noteBook = new NoteBook();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testNewNote() {
        String input = "note-new\nTest note\nlabel1 label2\nexit";
        provideInput(input);
        noteBook.run();

        String expectedOutput = "Заметка добавлена.\nВыход из приложения.";
        Assertions.assertEquals(expectedOutput, getOutput());
        Assertions.assertEquals(1, noteBook.getNotes().size());
        Assertions.assertEquals("Test note", noteBook.getNotes().get(0).getText());
        Assertions.assertEquals(Arrays.asList("LABEL1", "LABEL2"), noteBook.getNotes().get(0).getLabels());
    }

    @Test
    public void testListNotes() {
        String input = "note-new\nTest note 1\nlabel1\nnote-new\nTest note 2\nlabel2\nnote-list\nexit";
        provideInput(input);
        noteBook.run();

        String expectedOutput = "Заметка добавлена.\nЗаметка добавлена.\nID: 1, Текст: Test note 1, Метки: [LABEL1]\nID: 2, Текст: Test note 2, Метки: [LABEL2]\nВыход из приложения.";
        Assertions.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void testRemoveNote() {
        noteBook.getNotes().add(new Note(2, "Test note", Arrays.asList("LABEL1")));
        String input = "note-remove\n2\nnote-list\nexit";
        provideInput(input);
        noteBook.run();

        String expectedOutput = "Заметка удалена.\nID: 1, Текст: Test note 1, Метки: [LABEL1]\nВыход из приложения.";
        Assertions.assertEquals(expectedOutput, getOutput());
        Assertions.assertEquals(1, noteBook.getNotes().size());
        Assertions.assertEquals(2, noteBook.getNextId());
    }

    @Test
    public void testExportNotes() {
        noteBook.getNotes().add(new Note(1, "Test note 1", Arrays.asList("LABEL1")));
        noteBook.getNotes().add(new Note(2, "Test note 2", Arrays.asList("LABEL2")));
        String input = "note-export\nexit";
        provideInput(input);
        noteBook.run();

        String expectedOutput = "Заметки сохранены в файл: notes_2023.06.12_12:34:56.txt\nВыход из приложения.";
        Assertions.assertEquals(expectedOutput, getOutput());
    }

    private void provideInput(String data) {
        InputStream input = new ByteArrayInputStream(data.getBytes());
        System.setIn(input);
    }

    private String getOutput() {
        return outputStream.toString().trim();
    }
}
