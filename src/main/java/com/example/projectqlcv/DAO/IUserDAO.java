package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.User;

public interface IUserDAO {
    public User login(String userName, String password);

    public void sign_up(String email, String password, String name, String phone);

    public User checkLoginUser(String email);

    public User findPasswordByEmail(String email, String password);

    public void editPassWordUser(String email, String rePassword);
}
