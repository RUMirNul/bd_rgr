package edu.ansvistunov.service;

import edu.ansvistunov.Util;
import edu.ansvistunov.dto.ClientDto;
import edu.ansvistunov.dto.PaymentDto;
import edu.ansvistunov.dto.PolicyDto;

import java.sql.*;

public class PolicyService {
    public static int addNewPolicy(PolicyDto policyDto) {
        Connection connection = DatabaseService.connect();

        String query = "INSERT INTO policies (client_number, insurance_code, company_kpp, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, policyDto.getClientNumber());
            preparedStatement.setInt(2, policyDto.getInsuranceCode());
            preparedStatement.setInt(3, policyDto.getCompanyKpp());
            preparedStatement.setDate(4, new Date(policyDto.getStartDate().getTime()));
            preparedStatement.setDate(5, new Date(policyDto.getEndDate().getTime()));
            preparedStatement.execute();

            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                return key.getInt("policy_number");
            } else {
                throw new SQLException("Ошибка при добавлении нового полиса.");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось добавить полис в базу данных. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
        return -1;
    }

    public static PolicyDto getPolicyById(int id) {
        Connection connection = DatabaseService.connect();

        String query = "SELECT policy_number, client_number, insurance_code, company_kpp, start_date, end_date " +
                "FROM policies WHERE policy_number = ?";

        PolicyDto policyDto = new PolicyDto();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int policyNumber = result.getInt("policy_number");
                int clientNumber = result.getInt("client_number");
                int insuranceCode = result.getInt("insurance_code");
                int companyKpp = result.getInt("company_kpp");
                Date startDate = result.getDate("start_date");
                Date endDate = result.getDate("end_date");

                policyDto.setPolicyNumber(policyNumber);
                policyDto.setClientNumber(clientNumber);
                policyDto.setInsuranceCode(insuranceCode);
                policyDto.setCompanyKpp(companyKpp);
                policyDto.setStartDate(Util.sqlDateToDate(startDate));
                policyDto.setEndDate(Util.sqlDateToDate(endDate));
            }

        } catch (SQLException e) {
            System.out.println("Не удалось получить полис с таким номером. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
        return policyDto;
    }

    public static void deletePolicyById(int policyId) {
        Connection connection = DatabaseService.connect();

        String query = "DELETE FROM policies WHERE policy_number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, policyId);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить полис. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
    }

}
