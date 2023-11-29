package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.Util;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.PaymentDto;
import edu.ansvistunov.service.PaymentService;

import java.util.Date;
import java.util.List;

public class GetAllPaymentsByDateCommand implements Command {
    @Override
    public void execute() {

        Date paymentDate;
        try {
            System.out.print("Введите дату в формате дд.мм.гггг: ");
            String paymentDateString = Main.SCANNER.nextLine();
            paymentDate = Util.stringToDate(paymentDateString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        List<PaymentDto> payments = PaymentService.getAllPaymentsByDate(paymentDate);

        System.out.println("Количество выплат: " + payments.size());
        payments.forEach(System.out::println);
    }
}
