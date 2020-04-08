package repository;

import entity.Parking;

import java.sql.*;

public class ParkingRepository {
    private Connection connection;

    public ParkingRepository() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/parking_lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong",
                    "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        try {
            Statement statement = this.connection.createStatement();
            String deleteSql = "DELETE FROM parking_lot";
            statement.execute(deleteSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByTicket(String lotNo, int spaceNo, String carNumber) {
        String querySql = "DELETE FROM parking_lot WHERE lot_no = ? AND space_no = ? AND car_number = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(querySql)) {
            preparedStatement.setString(1, lotNo);
            preparedStatement.setInt(2, spaceNo);
            preparedStatement.setString(3, carNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String lotNumber, int spaceNo) {
        String insertSql = "INSERT INTO parking_lot (lot_no, space_no) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(insertSql);
            preparedStatement.setString(1, lotNumber);
            preparedStatement.setInt(2, spaceNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Parking queryMinNumberParking() {
        try (Statement statement = this.connection.createStatement()) {
            String querySql = "SELECT lot_no, space_no FROM parking_lot WHERE car_number is NULL ORDER BY lot_no, space_no ASC LIMIT 1";
            ResultSet resultSet = statement.executeQuery(querySql);
            if (resultSet.next()) {
                return new Parking(resultSet.getString("lot_no"), resultSet.getInt("space_no"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean querybyTicket(String lotNo, int spaceNo, String carNumber) {
        String querySql = "SELECT * FROM parking_lot WHERE lot_no = ? AND space_no = ? And car_number = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(querySql)) {
            preparedStatement.setString(1, lotNo);
            preparedStatement.setInt(2, spaceNo);
            preparedStatement.setString(3, carNumber);
            if (preparedStatement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateCarNumber(String lotNo, int spaceNo, String carNumber) {
        String updateSql = "UPDATE parking_lot SET car_number = ? WHERE lot_no = ? AND space_no = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, carNumber);
            preparedStatement.setString(2, lotNo);
            preparedStatement.setInt(3, spaceNo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
