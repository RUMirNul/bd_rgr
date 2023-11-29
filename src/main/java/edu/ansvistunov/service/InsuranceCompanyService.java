package edu.ansvistunov.service;

import edu.ansvistunov.dto.CompanyDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsuranceCompanyService {

    public static void addCompany(CompanyDto companyDto) {

        Connection connection = DatabaseService.connect();

        String query = "INSERT INTO insurance_companies (company_kpp, name, address) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, companyDto.getCompanyKpp());
            preparedStatement.setString(2, companyDto.getName());
            preparedStatement.setString(3, companyDto.getAddress());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Не удалось добавить компанию в базу данных. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
    }

    public static CompanyDto getCompanyByKpp(int companyKpp) {
        Connection connection = DatabaseService.connect();

        String query = "SELECT company_kpp, name, address FROM insurance_companies WHERE company_kpp = ?";

        CompanyDto companyDto = new CompanyDto();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, companyKpp);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int kpp = result.getInt("company_kpp");
                String name = result.getString("name");
                String address = result.getString("address");

                companyDto.setCompanyKpp(kpp);
                companyDto.setName(name);
                companyDto.setAddress(address);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось получить компанию с таким КПП. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }
        return companyDto;
    }

    public static void deleteCompanyByKpp(int companyKpp) {
        Connection connection = DatabaseService.connect();

        String query = "DELETE FROM insurance_companies WHERE company_kpp = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, companyKpp);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить компанию. \nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }

    }

    public static List<CompanyDto> findAllByCompanyName(String companyName) {
        Connection connection = DatabaseService.connect();

        String query = "SELECT * FROM insurance_companies WHERE name = ?";

        List<CompanyDto> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, companyName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CompanyDto company = new CompanyDto();
                company.setCompanyKpp(rs.getInt("company_kpp"));
                company.setName(rs.getString("name"));
                company.setAddress(rs.getString("address"));
                result.add(company);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось найти компании с названием: " + companyName + "\nОшибка: " + e.getMessage());
        } finally {
            DatabaseService.disconnect();
        }

        return result;
    }
}
