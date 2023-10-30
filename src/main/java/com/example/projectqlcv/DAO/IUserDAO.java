package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.Group;
import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;

import java.util.List;

public interface IUserDAO {
    User login(String userName, String password);

    void signUp(String email, String password, String name, String phoneNumber);

    User checkLoginUser(String email);
    User findPasswordByEmail(String email, String password);
    User findEmailById(int id);
    User selectAllUserId(int id);

    void editPassWordUser(String email, String rePassword);

    void addGroup(Group group);

    List<Group> selectGroupFromSQL();

    void addGroup(Table table);

    List<Table> selectAllTable();
    boolean editInformationUser(int id ,User user);
}
