package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements IAdminDAO {
    private String connectUrl = "jdbc:mysql://localhost:3306/workManagement";
    private String userName = "root";
    private String passWord = "1";
    private static final String DELETE_USER_SQL = "delete from user where id = ?";
    private static final String SELECT_ALL_USER = "SELECT * FROM user";

    protected Connection connection() throws ClassNotFoundException, SQLException {


        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectUrl, userName, passWord);
        return connection;
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
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String phone = rs.getString("phoneNumber");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String avatar = rs.getString("avatar");
                users.add(new User(id,email,name,phone,password,address,avatar));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
