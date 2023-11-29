package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.service.InsuranceCompanyService;

public class DeleteCompanyCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите КПП компании: ");
        String kppString = Main.SCANNER.nextLine();

        int kppCompany = -1;
        try {
            kppCompany = Integer.parseInt(kppString);
        } catch (Exception e) {
            System.out.println("Это не целое число!");
            return;
        }

        InsuranceCompanyService.deleteCompanyByKpp(kppCompany);
    }
}
