package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.PolicyDto;
import edu.ansvistunov.service.PolicyService;

public class GetPolicyByIdCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите номер полиса: ");
        String policyNumberString = Main.SCANNER.nextLine();

        int policyNumber = -1;
        try {
            policyNumber = Integer.parseInt(policyNumberString);
        } catch (Exception e) {
            System.out.println("Это не целое число!");
            return;
        }

        PolicyDto policyDto = PolicyService.getPolicyById(policyNumber);
        if (policyDto.getStartDate() == null || policyDto.getEndDate() == null) {
            System.out.println("Полис с таким номером не найден.");
            return;
        }

        System.out.println(policyDto);
    }
}
