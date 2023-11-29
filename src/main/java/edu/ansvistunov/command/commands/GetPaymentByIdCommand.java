package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.PaymentDto;
import edu.ansvistunov.service.PaymentService;

public class GetPaymentByIdCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите номер выплаты: ");
        String paymentNumberString = Main.SCANNER.nextLine();

        int paymentId = -1;
        try {
            paymentId = Integer.parseInt(paymentNumberString);
        } catch (Exception e) {
            System.out.println("Это не целое число!");
            return;
        }

        PaymentDto paymentDto = PaymentService.getPaymentById(paymentId);
        if (paymentDto.getPaymentDate() == null) {
            System.out.println("Полис с таким номером не найден.");
            return;
        }

        System.out.println(paymentDto);
    }
}
