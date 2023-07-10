package Commands;
import org.example.NoteBook;

public class NoteRemoveCommand implements Command {
    @Override
    public void execute(NoteBook noteBook, String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Ошибка: не указан id заметки для удаления.");
            return;
        }

        try {
            int id = Integer.parseInt(arguments[0]);
            noteBook.removeNoteById(id);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный id заметки.");
        }
    }
}
