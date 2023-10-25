package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.AdminDAO;


import com.example.projectqlcv.DAO.IUserDAO;

import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@WebServlet(name = "HomeAdminController", value = "/homeAdmin")
public class HomeAdminController extends HttpServlet {
    AdminDAO adminDAO;
    @Override
    public void init() {
        adminDAO = new AdminDAO();
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        adminDAO.deleteUser(id);
        List<User> list = adminDAO.selectAllUser();
        request.setAttribute("message","Delete success !");
        request.setAttribute("listUser",list);
        try {
            request.getRequestDispatcher("homeAdmin.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        if (user == null){
            user = "";
        }
        switch (user){
            case "delete":
                deleteUser(request, response);
                break;
            default:
                showAllUser(request, response);
                break;
        }
    }
    private void showAllUser(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = adminDAO.selectAllUser();
        request.setAttribute("listUser", userList);
        try {
            request.getRequestDispatcher("homeAdmin.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
