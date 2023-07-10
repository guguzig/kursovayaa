package Commands;
import org.example.NoteBook;


public class ExitCommand implements Command {
    @Override
    public void execute(NoteBook noteBook, String[] arguments) {
        System.out.println("Выход из приложения.");
        System.exit(0);
    }
}

