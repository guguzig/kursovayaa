package Commands;

import org.example.Note;
import org.example.NoteBook;

import java.util.List;
import java.util.Scanner;

public class NoteNewCommand implements Command {
    @Override
    public void execute(NoteBook noteBook, String[] arguments) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите заметку: ");
        String text = scanner.nextLine();
        noteBook.validateNoteText(text);

        System.out.print("Добавить метки? Метки состоят из одного слова и могут содержать только буквы. Для добавления нескольких меток разделяйте слова пробелом: ");
        String labelsInput = scanner.nextLine();
        List<String> labels = noteBook.validateLabels(labelsInput);

        int nextId = noteBook.getNextId();
        noteBook.addNote(text, labels, nextId);
        System.out.println("Заметка добавлена.");
    }
}
