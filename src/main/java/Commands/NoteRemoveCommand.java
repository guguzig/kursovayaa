package commands;

import domain.Note;
import java.util.List;

public class NoteRemoveCommand implements Command {
    private List<Note> notes;

    public NoteRemoveCommand(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void execute(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Ошибка: не указан id заметки для удаления.");
            return;
        }

        try {
            int id = Integer.parseInt(arguments[0]);
            removeNoteById(id);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный id заметки.");
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
}