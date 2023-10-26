package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AdminDAO implements IAdminDAO {

    private String connectUrl = "jdbc:mysql://localhost:3306/workManagement";
    private String userName = "root";
    private String passWord = "giang";

    private static final String UPDATE_USER_ID = "UPDATE user SET name = ?, email = ? , phoneNumber = ? , password = ? , address = ? , avatar =  ? WHERE id = ? ";
    private static final String SELECT_ALL_USER_ID = "SELECT * FROM user WHERE id = ?";
    private static final String DELETE_USER_SQL = "delete from user where id = ?";
    private static final String SELECT_ALL_USER = "SELECT * FROM user";
    private static final String ADD_USER_TO_SQL = "INSERT INTO user(email, name, phoneNumber, password) VALUES(?, ?, ?, ?) ";
    private static final String CHECK_ADMIN_LOGIN = "select * from user where email = ?";
    protected Connection connection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectUrl, userName, passWord);
        return connection;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String phone = rs.getString("phoneNumber");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String avatar = rs.getString("avatar");
                users.add(new User(id, email, name, phone, password, address, avatar));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User checkLoginUser(String email) {
        User user = null;
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ADMIN_LOGIN)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String emailDB = rs.getString("email");
                String password = rs.getString("password");
                user = new User(id, emailDB, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean rowDeleted;
        try (Connection connection = connection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    @Override
    public void createUser(String email, String password, String name, String phoneNumber) {
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_TO_SQL);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUser(int id, User user) {
        boolean updateRow;
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ID);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getAvatar());
            preparedStatement.setInt(7, id);
            updateRow = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateRow;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int iD = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String avatar = resultSet.getString("avatar");
                user = new User(iD, name, email, phoneNumber, password, address, avatar);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
