package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.User;

import java.util.List;

public interface IAdminDAO {
    boolean deleteUser(int id);

    List<User> selectAllUser();
}
