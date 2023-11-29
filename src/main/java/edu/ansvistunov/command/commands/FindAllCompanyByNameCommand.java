package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.CompanyDto;
import edu.ansvistunov.service.InsuranceCompanyService;

import java.util.List;

public class FindAllCompanyByNameCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите название компании: ");
        String companyName = Main.SCANNER.nextLine();

        List<CompanyDto> result = InsuranceCompanyService.findAllByCompanyName(companyName);

        if (result.isEmpty()) {
            System.out.println("Компании с названием: " + companyName + " не найдены.");
            return;
        }
        System.out.printf("Найдено %d компаний\n", result.size());
        result.forEach(System.out::println);
    }
}
