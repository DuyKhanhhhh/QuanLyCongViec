package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.User;

import java.util.List;

public interface IAdminDAO {
    List<User> selectAllUser();

    boolean updateUser(int id, User user);

    User findById(int id);
    boolean deleteUser(int id);
}
