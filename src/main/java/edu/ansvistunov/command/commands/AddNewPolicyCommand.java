package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.Util;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.PolicyDto;
import edu.ansvistunov.service.PolicyService;

import java.util.Date;

public class AddNewPolicyCommand implements Command {
    @Override
    public void execute() {
        int clientNumber = 0;
        int insuranceCode = 0;
        int kppCompany = 0;
        try {
            System.out.print("Введите номер клиента: ");
            String clientNumberString = Main.SCANNER.nextLine();
            clientNumber = Integer.parseInt(clientNumberString);

            System.out.print("Введите код страхования: ");
            String insuranceCodeString = Main.SCANNER.nextLine();
            insuranceCode = Integer.parseInt(insuranceCodeString);

            System.out.print("Введите КПП компании: ");
            String kppString = Main.SCANNER.nextLine();
            kppCompany = Integer.parseInt(kppString);
        } catch (Exception e) {
            System.out.println("Это не целое число!");
            return;
        }

        Date startDate = new Date();
        Date endDate = new Date();
        try {
            System.out.print("Введите дату начала действия полиса в формате дд.мм.гггг: ");
            startDate = Util.stringToDate(Main.SCANNER.nextLine());

            System.out.print("Введите дату окончания действия полиса в формате дд.мм.гггг: ");
            endDate = Util.stringToDate(Main.SCANNER.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        PolicyDto policyDto = new PolicyDto();
        policyDto.setClientNumber(clientNumber);
        policyDto.setInsuranceCode(insuranceCode);
        policyDto.setCompanyKpp(kppCompany);
        policyDto.setStartDate(startDate);
        policyDto.setEndDate(endDate);

        int id = PolicyService.addNewPolicy(policyDto);
        if (id == -1) {
            System.out.println("Не удалось добавить полис в базу данных.");
            return;
        }
        System.out.println("Полис добавлен базу данных с id = " + id);
    }
}
