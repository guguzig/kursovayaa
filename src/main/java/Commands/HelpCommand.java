package commands;

public class HelpCommand implements Command {
    @Override
    public void execute(String[] arguments) {
        System.out.println("Список доступных команд:");
        System.out.println("help - выводит на экран список доступных команд с их описанием");
        System.out.println("note-new - создать новую заметку");
        System.out.println("note-list - выводит все заметки на экран");
        System.out.println("note-remove - удаляет заметку");
        System.out.println("note-export - сохраняет все заметки в текстовый файл и выводит имя сохраненного файла");
        System.out.println("exit - выход из приложения.");
    }
}
