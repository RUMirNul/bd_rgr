package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.InsuranceTypeDto;
import edu.ansvistunov.service.InsuranceTypeService;

public class GetInsuranceTypeCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите код вида страхования: ");
        String codeString = Main.SCANNER.nextLine();

        int code = -1;
        try {
            code = Integer.parseInt(codeString);
        } catch (Exception e) {
            System.out.println("Это не целое число!");
            return;
        }

        InsuranceTypeDto insuranceTypeDto = InsuranceTypeService.getInsuranceTypeByCode(code);
        if (insuranceTypeDto.getName() == null) {
            System.out.println("Не удалось найти тип страхования с таким кодом.");
            return;
        }

        System.out.println(insuranceTypeDto);
    }
}
