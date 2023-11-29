package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.service.PaymentService;

public class DeletePaymentByIdCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите номер выплаты: ");
        String paymentNumberString = Main.SCANNER.nextLine();

        int paymentNumber = -1;
        try {
            paymentNumber = Integer.parseInt(paymentNumberString);
        } catch (Exception e) {
            System.out.println("Это не целое число!");
            return;
        }

        PaymentService.deletePaymentById(paymentNumber);
    }
}
