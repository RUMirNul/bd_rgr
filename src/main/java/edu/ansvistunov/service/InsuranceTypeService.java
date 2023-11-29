package edu.ansvistunov.service;

import edu.ansvistunov.dto.CompanyDto;
import edu.ansvistunov.dto.InsuranceTypeDto;

import java.sql.*;

public class InsuranceTypeService {

    public static int addNewInsuranceType(InsuranceTypeDto insuranceTypeDto) {
        Connection connection = DatabaseService.connect();

        String query = "INSERT INTO insurance_types (name, description) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, insuranceTypeDto.getName());
            preparedStatement.setString(2, insuranceTypeDto.getDescription());
            preparedStatement.execute();

            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                return key.getInt("insurance_code");
            } else {
                throw new SQLException("Ошибка при добавлении нового вида страхования.");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось добавить вид страхования в базу данных. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
        return -1;
    }

    public static InsuranceTypeDto getInsuranceTypeByCode(int insuranceCode) {
        Connection connection = DatabaseService.connect();

        String query = "SELECT insurance_code, name, description FROM insurance_types WHERE insurance_code = ?";

        InsuranceTypeDto insuranceTypeDto = new InsuranceTypeDto();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, insuranceCode);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int code = result.getInt("insurance_code");
                String name = result.getString("name");
                String description = result.getString("description");

                insuranceTypeDto.setInsuranceCode(code);
                insuranceTypeDto.setName(name);
                insuranceTypeDto.setDescription(description);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось получить тип страхования с таким кодом. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
        return insuranceTypeDto;
    }

    public static void deleteInsuranceTypeByCode(int insuranceCode) {
        Connection connection = DatabaseService.connect();

        String query = "DELETE FROM insurance_types WHERE insurance_code = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, insuranceCode);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить тип страхования. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }

    }
}
