package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.LoginUser;

import java.sql.*;

public class UserDAO implements IUserDAO{
    private String connectUrl = "jdbc:mysql://localhost:3306/workManagement";
    private String userName = "root";
    private String passWord = "1";

    private static final String ADD_USER_TO_SQL = "INSERT INTO signUpUser(email, name, phoneNumber, password) VALUES(?, ?, ?, ?) ";
    private static final String LOGIN_USER_HOME =  "SELECT * FROM signUpUser WHERE email = ? AND password = ?";
    private static final String CHECK_USER_LOGIN = "select * from signUpUser where email = ?";
    private final String UPDATE_PASSWORD_USER = "UPDATE signUpUser SET password = ? WHERE email = ? ";
    private final String SELECT_PASSWORD_BY_EMAIL = "SELECT email,password FROM signUpUser WHERE email = ? AND password = ?";
    @Override
    public LoginUser findPasswordByEmail(String email, String password) {
        LoginUser loginUser = null;
        Connection connection = null;
        try {
            connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD_BY_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String emailUser = resultSet.getString("email");
                String passwordUser = resultSet.getString("password");
                loginUser = new LoginUser(emailUser, passwordUser);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loginUser;
    }

    @Override
    public void editPassWordUser(String email, String rePassword) {
        Connection connection = null;
        try {
            connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_USER);
            preparedStatement.setString(1, rePassword);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection connection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectUrl, userName, passWord);
        return connection;
    }

    public LoginUser login(String email, String password) {
        LoginUser loginUser = null;
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER_HOME);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String emailDB = rs.getString("email");
                String passWord = rs.getString("password");
                loginUser = new LoginUser(id, emailDB, passWord);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loginUser;
    }

    @Override
    public void sign_up(String email, String password, String name, String phoneNumber) {
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
    public LoginUser checkLoginUser(String email) {
        LoginUser loginUser = null;
        try(Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN)) {
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String emailDB = rs.getString("email");
                String password = rs.getString("password");
                loginUser = new LoginUser(id,emailDB,password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loginUser;
    }
}