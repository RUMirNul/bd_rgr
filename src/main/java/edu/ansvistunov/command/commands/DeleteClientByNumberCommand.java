package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.service.ClientService;

public class DeleteClientByNumberCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите номер клиента: ");
        String numberString = Main.SCANNER.nextLine();

        int number = -1;
        try {
            number = Integer.parseInt(numberString);
        } catch (Exception e) {
            System.out.println("Это не целое число!");
            return;
        }

        ClientService.deleteClientByNumber(number);
    }
}
