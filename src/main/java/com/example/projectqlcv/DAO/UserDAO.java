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
    private String passWord = "Duykhanh123@";

    private static final String ADD_USER_TO_SQL = "INSERT INTO user(email, name, phoneNumber, password) VALUES(?, ?, ?, ?) ";
    private static final String LOGIN_USER_HOME = "SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String SELECT_EMAIL_USER_ID = "SELECT email FROM user WHERE id = ?";
    private static final String SELECT_INFORMATION_USER_ID = "SELECT id,name,phoneNumber,address,avatar FROM user WHERE id = ?";
    private static final String CHECK_USER_LOGIN = "select * from user where email = ?";
    private static final String UPDATE_PASSWORD_USER = "UPDATE user SET password = ? WHERE email = ? ";
    private static final String UPDATE_USER_ID = "UPDATE user SET name = ?, phoneNumber = ? , address = ? , avatar = ? WHERE id = ?";
    private static final String SELECT_PASSWORD_BY_EMAIL = "SELECT email,password FROM user WHERE email = ? AND password = ?";
    private static final String ADD_GROUP_TO_SQL = "INSERT INTO groupWork(name,groupType,permission,information) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_GROUP_WORK = "SELECT * FROM groupWork";
    private static final String ADD_TABLE_TO_SQL = "INSERT INTO tableWork(tableName, permission, groupDescribe) VALUES(?, ?, ?)";
    private static final String SELECT_ALL_TABLE = "SELECT * FROM tableWork";
    private static final String UPDATE_GROUP = "UPDATE groupWork SET name = ?, groupType = ?, permission = ?, information = ? WHERE id = ?";
    private static final String SELECT_GROUP_BY_ID = "SELECT * FROM groupWork where id = ?";
    private static final String DELETE_GROUP_SQL = "DELETE FROM groupWork where id = ?";

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
    public User findEmailById(int id) {
        User user = null;
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_USER_ID);
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
    public User selectAllUserId(int id) {
        User user = null;
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INFORMATION_USER_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int iD= resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String avatar = resultSet.getString("avatar");
                user = new User(iD,name,phoneNumber,address,avatar);
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
    public void addTable(Table table) {
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

    public boolean updateGroup(int id, Group group) {
        boolean updateGroup;
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GROUP);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getGroupType());
            preparedStatement.setString(3, group.getPermission());
            preparedStatement.setString(4, group.getInformation());
            preparedStatement.setInt(5, id);
            updateGroup = preparedStatement.executeUpdate() > 0;
        }
        return updateGroup;
    }
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

    @Override
    public boolean deleteGroup(int id) {
        boolean rowDelete;
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP_SQL);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }

    @Override
    public Group findGroupById(int id) {
        Group listGroup = null;
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int iD = rs.getInt("id");
                String name = rs.getString("name");
                String group = rs.getString("groupType");
                String permission = rs.getString("permission");
                String information = rs.getString("information");
                listGroup = new Group(iD,name,group,permission,information);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listGroup;
    }
}