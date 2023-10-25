package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;

import java.util.List;

public interface IAdminDao {
    List<User> selectAllUser();
}
