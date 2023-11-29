package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.service.PolicyService;

public class DeletePolicyByIdCommand implements Command {
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

        PolicyService.deletePolicyById(policyNumber);
    }
}
