package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.Group;
import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private String connectUrl = "jdbc:mysql://localhost:3306/workManagement";
    private String userName = "root";
    private String passWord = "1";


    private static final String ADD_USER_TO_SQL = "INSERT INTO user(email, name, phoneNumber, password) VALUES(?, ?, ?, ?) ";
    private static final String LOGIN_USER_HOME = "SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String SELECT_USER_ID = "SELECT email FROM user WHERE id = ?";
    private static final String CHECK_USER_LOGIN = "select * from user where email = ?";
    private static final String UPDATE_PASSWORD_USER = "UPDATE user SET password = ? WHERE email = ? ";
    private static final String UPDATE_USER_ID = "UPDATE user SET name = ?, phoneNumber = ? , address = ? , avatar = ? WHERE id = ?";
    private static final String SELECT_PASSWORD_BY_EMAIL = "SELECT email,password FROM user WHERE email = ? AND password = ?";
    private static final String ADD_GROUP_TO_SQL = "INSERT INTO groupWork(name,groupType,permission,information) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_GROUP_WORK = "SELECT * FROM groupWork";
    private static final String ADD_TABLE_TO_SQL = "INSERT INTO tableWork(tableName, permission, groupDescribe) VALUES(?, ?, ?)";
    private static final String SELECT_ALL_TABLE = "SELECT * FROM tableWork";
    private static final String DELETE_GROUP_SQL = "delete from groupWork where id = ?";


    protected Connection connection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectUrl, userName, passWord);
        return connection;
    }

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
    public User findUserById(int id) {
        User user = null;
        try {
            Connection connection = connection();
PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                user = new User(email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    @Override
    public void editPassWordUser(User user) {
        Connection connection = null;
        try {
            connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_USER);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteGroup(int id) {
        boolean rowDeleted;
        try (Connection connection = connection();
             PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }


    public User login(String email, String password) {
        User user = null;
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER_HOME);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String emailDB = rs.getString("email");
                String passWord = rs.getString("password");
                user = new User(id, emailDB, passWord);
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
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_TO_SQL);) {
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
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN)) {
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
    public void addGroup(Group group) {
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_GROUP_TO_SQL);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getGroupType());
            preparedStatement.setString(3, group.getPermission());
            preparedStatement.setString(4, group.getInformation());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Group> selectGroupFromSQL() {
        List<Group> groups = new ArrayList<>();
        try {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_GROUP_WORK);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String groupType = resultSet.getString("groupType");
                String permission = resultSet.getString("permission");
                String information = resultSet.getString("information");
                groups.add(new Group(id, name, groupType, permission, information));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public void addGroup(Table table) {
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TABLE_TO_SQL);
            preparedStatement.setString(1, table.getName());
            preparedStatement.setString(2, table.getPermission());
            preparedStatement.setString(3, table.getGroup());
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
            while (rs.next()) {
                String name = rs.getString("tableName");
                String permission = rs.getString("permission");
                String group = rs.getString("groupDescribe");
                listTable.add(new Table(name, permission, group));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listTable;
    }

    @Override
    public boolean editInformationUser(int id, User user) {
        boolean rowUpdate;
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ID);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPhoneNumber());
            preparedStatement.setString(3,user.getAddress());
            preparedStatement.setString(4,user.getAvatar());
            preparedStatement.setInt(5,id);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdate;
    }
}