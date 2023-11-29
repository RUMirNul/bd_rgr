package edu.ansvistunov.service;

import edu.ansvistunov.dto.ClientDto;
import edu.ansvistunov.dto.PaymentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientService {

    public static int addNewClient(ClientDto clientDto) {
        Connection connection = DatabaseService.connect();

        String query = "INSERT INTO clients (full_name, address, phone_number) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, clientDto.getFullName());
            preparedStatement.setString(2, clientDto.getAddress());
            preparedStatement.setString(3, clientDto.getPhoneNumber());
            preparedStatement.execute();

            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                return key.getInt("client_number");
            } else {
                throw new SQLException("Ошибка при добавлении нового вида клиента.");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось добавить клиента в базу данных. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
        return -1;
    }

    public static ClientDto getClientByNumber(int clientNumber) {
        Connection connection = DatabaseService.connect();

        String query = "SELECT client_number, full_name, address, phone_number FROM clients WHERE client_number = ?";

        ClientDto clientDto = new ClientDto();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientNumber);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int number = result.getInt("client_number");
                String fullName = result.getString("full_name");
                String address = result.getString("address");
                String phoneNumber = result.getString("phone_number");

                clientDto.setClientNumber(number);
                clientDto.setFullName(fullName);
                clientDto.setAddress(address);
                clientDto.setPhoneNumber(phoneNumber);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось получить клиента с таким номером. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
        return clientDto;
    }

    public static void deleteClientByNumber(int clientNumber) {
        Connection connection = DatabaseService.connect();

        String query = "DELETE FROM clients WHERE client_number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientNumber);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить клиента. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
    }

    public static List<PaymentDto> getAllClientPayments(int clientNumber) {
        Connection connection = DatabaseService.connect();

        String query = "SELECT payments.* FROM payments " +
                "JOIN policies ON payments.policy_number = policies.policy_number " +
                "JOIN clients ON policies.client_number = clients.client_number " +
                "WHERE clients.client_number = ?";

        List<PaymentDto> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setPaymentNumber(resultSet.getInt("payment_number"));
                paymentDto.setPolicyNumber(resultSet.getInt("policy_number"));
                paymentDto.setPaymentAmount(resultSet.getDouble("payment_amount"));
                paymentDto.setPaymentDate(new Date(resultSet.getDate("payment_date").getTime()));

                result.add(paymentDto);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось найти выплаты клиента. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }

        return result;
    }
}
