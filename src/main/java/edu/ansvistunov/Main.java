package edu.ansvistunov;


import edu.ansvistunov.command.CommandEnum;

import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("""
                Выберите какую команду выполнить:
                                
                Компании:
                a - Добавить компанию
                b - Найти компании по КПП
                c - Удалить компанию по КПП (Все полисы компании будут удалены)
                d - Найти все компании по имени
                
                Виды страхования:
                e - Добавить новый тип страхования
                f - Найти тип страхования по коду
                g - Удалить тип страхования по коду (Будет ошибка, если есть полисы с таким типом страхования)
                
                Клиенты:
                h - Добавить клиента
                i - Найти клиента по номеру
                j - Удалить клиента по его номеру (Все полисы клиента будут удалены)
                k - Получить все выплаты клиента
                
                Полисы:
                l - Добавить полис
                m - Получить полис по номеру
                n - Удалить полис по номеру
                
                Выплаты:
                o - Добавить новую выплату
                p - Получить запись о выплате по номеру
                r - Удалить запись о выплате по номеру
                s - Найти все выплаты за день по дате
                
                q - выйти
                """);

        boolean proceed = true;

        while (proceed) {
            System.out.println();
            System.out.print("Выберите команду: ");
            String choice = SCANNER.nextLine();
            switch (choice) {
                case "a" -> CommandEnum.ADD_NEW_COMPANY_COMMAND.getCommand().execute();
                case "b" -> CommandEnum.GET_COMPANY_BY_KPP.getCommand().execute();
                case "c" -> CommandEnum.DELETE_COMPANY_BY_KPP.getCommand().execute();
                case "d" -> CommandEnum.FIND_ALL_COMPANY_BY_NAME.getCommand().execute();
                case "e" -> CommandEnum.ADD_NEW_INSURANCE_TYPE.getCommand().execute();
                case "f" -> CommandEnum.GET_INSURANCE_TYPE_BY_CODE.getCommand().execute();
                case "g" -> CommandEnum.DELETE_INSURANCE_TYPE_BY_CODE.getCommand().execute();
                case "h" -> CommandEnum.ADD_NEW_CLIENT.getCommand().execute();
                case "i" -> CommandEnum.GET_CLIENT_BY_NUMBER.getCommand().execute();
                case "j" -> CommandEnum.DELETE_CLIENT_BY_NUMBER.getCommand().execute();
                case "k" -> CommandEnum.GET_ALL_CLIENT_PAYMENTS.getCommand().execute();
                case "l" -> CommandEnum.ADD_NEW_POLICY.getCommand().execute();
                case "m" -> CommandEnum.GET_POLICY_BY_ID.getCommand().execute();
                case "n" -> CommandEnum.DELETE_POLICY_BY_ID.getCommand().execute();
                case "o" -> CommandEnum.ADD_NEW_PAYMENT.getCommand().execute();
                case "p" -> CommandEnum.GET_PAYMENT_BY_ID.getCommand().execute();
                case "r" -> CommandEnum.DELETE_PAYMENT_BY_ID.getCommand().execute();
                case "s" -> CommandEnum.GET_ALL_PAYMENTS_BY_DATE.getCommand().execute();

                case "q" -> proceed = false;

                default -> System.out.println("Такой команды нет!");


            }
        }
    }
}