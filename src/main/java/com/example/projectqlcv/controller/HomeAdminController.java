package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.AdminDAO;
import com.example.projectqlcv.DAO.IAdminDao;
import com.example.projectqlcv.DAO.IUserDAO;
import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeAdminController", value = "/admin")
public class HomeAdminController extends HttpServlet {
    private IUserDAO userDAO;
    private IAdminDao adminDao;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        adminDao = new AdminDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        if (user == null){
            user = "";
        }
        switch (user){
            default:
                showAllUser(request, response);
                break;
        }
    }

    private void showAllUser(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = adminDao.selectAllUser();
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
