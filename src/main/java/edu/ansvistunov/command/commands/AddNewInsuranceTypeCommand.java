package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.InsuranceTypeDto;
import edu.ansvistunov.service.InsuranceTypeService;

public class AddNewInsuranceTypeCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите название вида страхования: ");
        String insuranceTypeName = Main.SCANNER.nextLine();

        System.out.print("Введите название описание вида страхования: ");
        String insuranceTypeDescription = Main.SCANNER.nextLine();

        InsuranceTypeDto insuranceTypeDto = new InsuranceTypeDto();
        insuranceTypeDto.setName(insuranceTypeName);
        insuranceTypeDto.setDescription(insuranceTypeDescription);

        int id = InsuranceTypeService.addNewInsuranceType(insuranceTypeDto);

        if (id == -1) {
            System.out.println("Не удалось добавить новый вид страхования.");
            return;
        }
        System.out.println("Добавлен новый вид страхования с id = " + id);
    }
}
