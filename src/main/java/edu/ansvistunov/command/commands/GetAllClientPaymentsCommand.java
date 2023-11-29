package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.PaymentDto;
import edu.ansvistunov.service.ClientService;

import java.util.List;

public class GetAllClientPaymentsCommand implements Command {
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

        List<PaymentDto> payments = ClientService.getAllClientPayments(number);
        if (payments.isEmpty()) {
            System.out.println("У клиента нет выплат по страховке.");
            return;
        }
        System.out.printf("Найдено %d выплат\n", payments.size());
        payments.forEach(System.out::println);
    }
}
