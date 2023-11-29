package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.CompanyDto;
import edu.ansvistunov.service.InsuranceCompanyService;

public class GetCompanyCommand implements Command {
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

        CompanyDto companyDto = InsuranceCompanyService.getCompanyByKpp(kppCompany);
        if (companyDto.getName() == null || companyDto.getAddress() == null) {
            System.out.println("Компании с таким КПП не найдено.");
            return;
        }

        System.out.println(companyDto);
    }
}
