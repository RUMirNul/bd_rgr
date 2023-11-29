package edu.ansvistunov.service;

import edu.ansvistunov.Util;
import edu.ansvistunov.dto.InsuranceTypeDto;
import edu.ansvistunov.dto.PaymentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    public static int addNewPayment(PaymentDto paymentDto) {
        Connection connection = DatabaseService.connect();

        String query = "INSERT INTO payments (policy_number, payment_amount, payment_date) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, paymentDto.getPolicyNumber());
            preparedStatement.setDouble(2, paymentDto.getPaymentAmount());
            preparedStatement.setDate(3, new Date(paymentDto.getPaymentDate().getTime()));
            preparedStatement.execute();

            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                return key.getInt("payment_number");
            } else {
                throw new SQLException("Ошибка при добавлении новой выплаты.");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось добавить выплату в базу данных. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
        return -1;
    }

    public static PaymentDto getPaymentById(int paymentId) {
        Connection connection = DatabaseService.connect();

        String query = "SELECT payment_number, policy_number, payment_amount, payment_date FROM payments WHERE payment_number = ?";

        PaymentDto paymentDto = new PaymentDto();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, paymentId);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int paymentNumber = result.getInt("payment_number");
                int policyNumber = result.getInt("policy_number");
                double paymentAmount = result.getDouble("payment_amount");
                java.util.Date paymentDate = Util.sqlDateToDate(result.getDate("payment_date"));

                paymentDto.setPaymentNumber(paymentNumber);
                paymentDto.setPolicyNumber(policyNumber);
                paymentDto.setPaymentAmount(paymentAmount);
                paymentDto.setPaymentDate(paymentDate);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось получить запись о выплате с таким id. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }

        return paymentDto;
    }

    public static void deletePaymentById(int paymentId) {
        Connection connection = DatabaseService.connect();

        String query = "DELETE FROM payments WHERE payment_number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, paymentId);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить запись о выплате. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
    }

    public static List<PaymentDto> getAllPaymentsByDate(java.util.Date date) {
        Connection connection = DatabaseService.connect();

        String query = "SELECT payment_number, policy_number, payment_amount, payment_date FROM payments WHERE payment_date = ?";

        List<PaymentDto> payments = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new Date(date.getTime()));

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int paymentNumber = result.getInt("payment_number");
                int policyNumber = result.getInt("policy_number");
                double paymentAmount = result.getDouble("payment_amount");
                java.util.Date paymentDate = Util.sqlDateToDate(result.getDate("payment_date"));

                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setPaymentNumber(paymentNumber);
                paymentDto.setPolicyNumber(policyNumber);
                paymentDto.setPaymentAmount(paymentAmount);
                paymentDto.setPaymentDate(paymentDate);

                payments.add(paymentDto);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось получить записи о выплатах по дате. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }

        return payments;
    }
}
