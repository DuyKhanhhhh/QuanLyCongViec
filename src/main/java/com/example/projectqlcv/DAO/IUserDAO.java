package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.User;

public interface IUserDAO {
    User login(String userName, String password);

    void signUp(String email, String password, String name, String phone);

    User checkLoginUser(String email);

    User findPasswordByEmail(String email, String password);

    void editPassWordUser(String email, String rePassword);


}
