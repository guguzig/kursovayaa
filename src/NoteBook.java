import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoteBook {
    private static final String FILE_PREFIX = "notes_";
    private static final String FILE_EXTENSION = ".txt";
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH:mm:ss");

    private List<Note> notes;
    private int nextId;

    public NoteBook() {
        notes = new ArrayList<>();
        nextId = 1;
    }

    public void run() {
        System.out.println("Это Ваша записная книжка. Вот список доступных команд: help, note-new, note-list, note-remove, note-export, exit.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine();
            logCommand(command);

            try {
                switch (command) {
                    case "help":
                        help();
                        break;
                    case "note-new":
                        newNote();
                        break;
                    case "note-list":
                        listNotes();
                        break;
                    case "note-remove":
                        removeNote();
                        break;
                    case "note-export":
                        exportNotes();
                        break;
                    case "exit":
                        System.out.println("Выход из приложения.");
                        return;
                    default:
                        System.out.println("Команда не найдена.");
                }
            } catch (Exception e) {
                logError(e);
                System.out.println("Ошибка выполнения команды: " + e.getMessage());
            }
        }
    }

    private void help() {
        System.out.println("Список доступных команд:");
        System.out.println("help - выводит на экран список доступных команд с их описанием");
        System.out.println("note-new - создать новую заметку");
        System.out.println("note-list - выводит все заметки на экран");
        System.out.println("note-remove - удаляет заметку");
        System.out.println("note-export - сохраняет все заметки в текстовый файл и выводит имя сохраненного файла");
        System.out.println("exit - выход из приложения.");
    }

    private void newNote() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите заметку: ");
        String text = scanner.nextLine();
        validateNoteText(text);

        System.out.print("Добавить метки? Метки состоят из одного слова и могут содержать только буквы. Для добавления нескольких меток разделяйте слова пробелом: ");
        String labelsInput = scanner.nextLine();
        List<String> labels = validateLabels(labelsInput);

        Note note = new Note(nextId, text, labels);
        notes.add(note);
        System.out.println("Заметка добавлена.");
        nextId++;
    }

    private void listNotes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите метки, чтобы отобразить определенные заметки или оставьте пустым для отображения всех заметок: ");
        String labelsInput = scanner.nextLine();
        List<String> labels = validateLabels(labelsInput);

        for (Note note : notes) {
            if (labels.isEmpty() || note.hasLabels(labels)) {
                System.out.println(note);
            }
        }
    }

    private void removeNote() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите id удаляемой заметки: ");
        int id = validateNoteId(scanner.nextLine());

        boolean removed = false;
        for (Note note : notes) {
            if (note.getId() == id) {
                notes.remove(note);
                System.out.println("Заметка удалена.");
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.out.println("Заметка не найдена.");
        }
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

    private void validateNoteText(String text) {
        if (text.length() < 3) {
            throw new IllegalArgumentException("Текст заметки должен быть длиннее 3 символов.");
        }
    }

    private List<String> validateLabels(String labelsInput) {
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
        if (!label.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Метка может содержать только буквы.");
        }
    }

    private int validateNoteId(String idString) {
        try {
            int id = Integer.parseInt(idString);
            if (id < 1 || id > nextId) {
                throw new IllegalArgumentException("Некорректный id заметки.");
            }
            return id;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некорректный id заметки.");
        }
    }

    private void logCommand(String command) {
        // Логирование вызываемых команд с уровнем fine
        System.out.println("Вызвана команда: " + command);
    }

    private void logError(Exception e) {
        // Логирование ошибок и исключений с уровнем warning
        System.out.println("Ошибка: " + e.getMessage());
    }

    public List<Note> getNotes() {
        return notes;
    }

    public int getNextId() {
        return nextId;
    }
}

