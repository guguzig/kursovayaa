package org.example;


import Commands.CommandExecutor;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteBook {
    private static final String FILE_PREFIX = "notes_";
    private static final String FILE_EXTENSION = ".txt";
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH:mm:ss");

    private static final Logger logger = Logger.getLogger(NoteBook.class.getName());

    private List<Note> notes;
    private IdGenerator idGenerator;

    public NoteBook() {
        notes = new ArrayList<>();
        idGenerator = new IdGenerator();

        try {
            FileHandler fileHandler = new FileHandler("log.txt");
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize logger", e);
        }
    }

    public void run() {
        System.out.println("Это Ваша записная книжка. Вот список доступных команд: help, note-new, note-list, note-remove, note-export, exit.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine();
            logCommand(command);

            try {
                CommandExecutor.execute(this, command);
            } catch (Exception e) {
                logError(e);
                System.out.println("Ошибка выполнения команды: " + e.getMessage());
            }
        }
    }

    private void logCommand(String command) {
        logger.log(Level.INFO, "Заметка добавлена.");
    }

    private void logError(Exception e) {
        logger.log(Level.SEVERE, "Ошибка: " + e.getMessage());
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(String text, List<String> labels, int nextId) {
        int id = nextId;
        Note note = new Note(id, text, labels);
        notes.add(note);
        System.out.println("Заметка добавлена.");
    }

    public void listNotes(List<String> labels) {
        for (Note note : notes) {
            if (labels.isEmpty() || note.hasLabels(labels)) {
                System.out.println(note);
            }
        }
    }

    public boolean removeNoteById(int id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                notes.remove(note);
                System.out.println("Заметка удалена.");
                return true;
            }
        }
        System.out.println("Заметка не найдена.");
        return false;
    }

    public void exportNotes() {
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
    public void validateNoteText(String text) {
        if (text.length() < 3) {
            throw new IllegalArgumentException("Текст заметки должен быть длиннее 3 символов.");
        }
    }

    public List<String> validateLabels(String labelsInput) {
        List<String> labels = new ArrayList<>();
        if (!labelsInput.isEmpty()) {
            String[] labelArray = labelsInput.split(" ");
            for (String label : labelArray) {
                validateLabel(label);
                labels.add(label.toUpperCase());
            }
        }
        return labels;
    }

    private void validateLabel(String label) {
        if (!label.matches("^[а-яА-Я]+$")) {
            throw new IllegalArgumentException("Метка может содержать только буквы.");
        }
    }
    public int getNextId() {
        return idGenerator.getNextId();
    }
}