package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.CompanyDto;
import edu.ansvistunov.service.InsuranceCompanyService;

public class AddNewCompanyCommand implements Command {
    @Override
    public void execute() {
        CompanyDto companyDto = new CompanyDto();

        System.out.print("Введите КПП компании: ");
        String kppString = Main.SCANNER.nextLine();

        int kppCompany = 0;
        try {
            kppCompany = Integer.parseInt(kppString);
        } catch (Exception e) {
            System.out.println("Это не целое число!");
            return;
        }

        System.out.print("Введите название компании: ");
        String name = Main.SCANNER.nextLine();

        System.out.print("Введите адрес компании: ");
        String address = Main.SCANNER.nextLine();

        companyDto.setCompanyKpp(kppCompany);
        companyDto.setName(name);
        companyDto.setAddress(address);

        InsuranceCompanyService.addCompany(companyDto);
        System.out.println("Компания добавлена в бд.");
    }
}
