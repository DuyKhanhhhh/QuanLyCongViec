package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements IAdminDao{
    private static final String SELECT_ALL_USER = "SELECT * FROM user";
    private String connectUrl = "jdbc:mysql://localhost:3306/workManagement";
    private String userName = "root";
    private String passWord = "Duykhanh123@";
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
