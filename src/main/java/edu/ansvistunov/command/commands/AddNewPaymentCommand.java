package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.Util;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.PaymentDto;
import edu.ansvistunov.service.PaymentService;

import java.util.Date;

public class AddNewPaymentCommand implements Command {
    @Override
    public void execute() {
        int policyNumber = 0;
        double paymentAmount = 0;
        Date paymentDate = null;

        try {
            System.out.print("Введите номер полиса: ");
            String policyNumberString = Main.SCANNER.nextLine();
            policyNumber = Integer.parseInt(policyNumberString);

            System.out.print("Введите сумму выплаты: ");
            String paymentAmountString = Main.SCANNER.nextLine();
            paymentAmount = Double.parseDouble(paymentAmountString);

        } catch (Exception e) {
            System.out.println("Должно быть числом!");
            return;
        }

        try {
            System.out.print("Введите дату выплаты в формате дд.мм.гггг: ");
            String paymentDateString = Main.SCANNER.nextLine();
            paymentDate = Util.stringToDate(paymentDateString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPolicyNumber(policyNumber);
        paymentDto.setPaymentAmount(paymentAmount);
        paymentDto.setPaymentDate(paymentDate);

        int id = PaymentService.addNewPayment(paymentDto);
        if (id == -1) {
            System.out.println("Не удалось добавить выплату в базу данных.");
            return;
        }
        System.out.println("Выплата добавлена базу данных с id = " + id);
    }
}
