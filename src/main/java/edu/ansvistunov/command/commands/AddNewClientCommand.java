package edu.ansvistunov.command.commands;

import edu.ansvistunov.Main;
import edu.ansvistunov.command.Command;
import edu.ansvistunov.dto.ClientDto;
import edu.ansvistunov.service.ClientService;

public class AddNewClientCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Введите ФИО клиента: ");
        String fullName = Main.SCANNER.nextLine();

        System.out.print("Введите адрес проживания клиента: ");
        String address = Main.SCANNER.nextLine();

        System.out.print("Введите номере телефона клиента: ");
        String phoneNumber = Main.SCANNER.nextLine();

        ClientDto clientDto = new ClientDto();
        clientDto.setFullName(fullName);
        clientDto.setAddress(address);
        clientDto.setPhoneNumber(phoneNumber);

        int id = ClientService.addNewClient(clientDto);
        if (id == -1) {
            System.out.println("Не удалось добавить запись о клиенте.");
            return;
        }

        System.out.println("Клиент сохранён с номер = " + id);
    }
}
