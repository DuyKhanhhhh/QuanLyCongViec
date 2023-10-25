package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.AdminDAO;

import com.example.projectqlcv.DAO.IAdminDAO;

import com.example.projectqlcv.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@WebServlet(name = "HomeAdminController", value = "/homeAdmin")
public class HomeAdminController extends HttpServlet {
    private IAdminDAO adminDAO;

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
            request.getRequestDispatcher("admin/homeAdmin.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        if (user == null) {
            user = "";
        }
        switch (user) {
            case "update":
                updateUser(request, response);
                break;
        }

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUd"));
        String name = request.getParameter("nameUd");
        String email = request.getParameter("emailUd");
        String phoneNumber = request.getParameter("phoneNumberUd");
        String password = request.getParameter("passwordUd");
        String address = request.getParameter("addressUd");
        String avatar = request.getParameter("avatarUd");
        User user = new User(name, email, phoneNumber, password, address, avatar);
        adminDAO.updateUser(id, user);
        try {
            response.sendRedirect("admin/homeAdmin.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        if (user == null) {
            user = "";
        }
        switch (user) {
            case "update":
                showUpDateForm(request, response);

                break;
            case "delete":
                deleteUser(request,response);
                break;
            default:
                showAllUser(request, response);
        }

    }

    private void showAllUser(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = adminDAO.selectAllUser();
        request.setAttribute("listUser", userList);
        try {
            request.getRequestDispatcher("admin/homeAdmin.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showUpDateForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = adminDAO.findById(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/update.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}