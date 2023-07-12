package domain;

import commands.CommandExecutor;
import java.util.Scanner;

public class Context {
    public void run() {
        System.out.println("Это ваша записная книжка. Вот список доступных команд: help, note-new, note-list, note-remove, note-export, exit.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine();

            try {
                CommandExecutor.execute(command);
            } catch (Exception e) {
                System.out.println("Ошибка выполнения команды: " + e.getMessage());
            }
        }
    }
}