package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.LoginUser;

public interface IUserDAO {
    public LoginUser login(String userName, String password);

    public void sign_up(String email, String password, String name, String phone);

    public LoginUser checkLoginUser(String email);

    public LoginUser findPasswordByEmail(String email, String password);

    public void editPassWordUser(String email, String rePassword);
}
