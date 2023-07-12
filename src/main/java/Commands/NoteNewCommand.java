package commands;

import domain.IdGenerator;
import domain.Note;
import domain.ValidationUtils;
import java.util.List;
import java.util.Scanner;

public class NoteNewCommand implements Command {
    private List<Note> notes;

    public NoteNewCommand(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void execute(String[] arguments) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите заметку: ");
        String text = scanner.nextLine();
        ValidationUtils.validateNoteText(text);

        System.out.print("Добавить метки? Метки состоят из одного слова и могут содержать только буквы. Для добавления нескольких меток разделяйте слова пробелом: ");
        String labelsInput = scanner.nextLine();
        List<String> labels = ValidationUtils.validateLabels(labelsInput);

        int nextId = IdGenerator.getNextId();
        addNote(text, labels, nextId);
        System.out.println("Заметка добавлена.");
    }

    public void addNote(String text, List<String> labels, int nextId) {
        int id = nextId;
        Note note = new Note(id, text, labels);
        notes.add(note);
        System.out.println("Заметка добавлена.");
    }
}
