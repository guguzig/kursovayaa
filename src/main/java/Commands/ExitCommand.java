package commands;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] arguments) {
        System.out.println("Выход из приложения.");
        System.exit(0);
    }
}
