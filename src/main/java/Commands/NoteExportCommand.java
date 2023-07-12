package commands;

import domain.Note;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteExportCommand implements Command {
    private List<Note> notes;

    public NoteExportCommand(List<Note> notes) {
        this.notes = notes;
    }

    private static final String FILE_PREFIX = "notes_";
    private static final String FILE_EXTENSION = ".txt";
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH:mm:ss");
    private static final Logger logger = Logger.getLogger(NoteExportCommand.class.getName());

    private void logError(Exception e) {
        logger.log(Level.SEVERE, "Ошибка: " + e.getMessage());
    }

    private void exportNotes() {
        String fileName = generateFileName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Note note : notes) {
                writer.write(note.toString());
                writer.newLine();
            }
            System.out.println("Заметки сохранены в файл: " + fileName);
        } catch (IOException e) {
            logError(e);
            System.out.println("Ошибка сохранения заметок в файл: " + e.getMessage());
        }
    }

    private String generateFileName() {
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(FILE_DATE_FORMATTER);
        return FILE_PREFIX + formattedDateTime + FILE_EXTENSION;
    }

    @Override
    public void execute(String[] arguments) {
        exportNotes();
    }
}