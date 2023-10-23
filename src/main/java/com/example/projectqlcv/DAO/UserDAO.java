package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    private String connectUrl = "jdbc:mysql://localhost:3306/workManagement";
    private String userName = "root";
    private String passWord = "Duykhanh123@";

    private static final String ADD_USER_TO_SQL = "INSERT INTO user(email, name, phoneNumber, password) VALUES(?, ?, ?, ?) ";
    private static final String LOGIN_USER_HOME =  "SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String CHECK_USER_LOGIN = "select * from user where email = ?";
    private final String UPDATE_PASSWORD_USER = "UPDATE user SET password = ? WHERE email = ? ";
    private final String SELECT_PASSWORD_BY_EMAIL = "SELECT email,password FROM user WHERE email = ? AND password = ?";
    private static final String ADD_TABLE_TO_SQL = "INSERT INTO tableWork(tableName, permission, groupDescribe) VALUES(?, ?, ?)";
    private static final String SELECT_ALL_TABLE = "SELECT * FROM tableWork";
    @Override
    public User findPasswordByEmail(String email, String password) {
        User user = null;
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
                user = new User(emailUser, passwordUser);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
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

    @Override
    public void addGroup(Table table) {
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TABLE_TO_SQL);
            preparedStatement.setString(1,table.getName());
            preparedStatement.setString(2,table.getPermission());
            preparedStatement.setString(3,table.getGroup());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Table> selectAllTable() {
        List<Table> listTable = new ArrayList<>();
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TABLE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("tableName");
                String permission = rs.getString("permission");
                String group = rs.getString("groupDescribe");
                listTable.add(new Table(name,permission,group));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listTable;
    }

    protected Connection connection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectUrl, userName, passWord);
        return connection;
    }

    public User login(String email, String password) {
        User user = null;
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER_HOME);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String emailDB = rs.getString("email");
                String passWord = rs.getString("password");
                user= new User(id, emailDB, passWord);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void signUp(String email, String password, String name, String phoneNumber) {
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
    public User checkLoginUser(String email) {
        User user = null;
        try(Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN)) {
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String emailDB = rs.getString("email");
                String password = rs.getString("password");
                user = new User(id,emailDB,password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
