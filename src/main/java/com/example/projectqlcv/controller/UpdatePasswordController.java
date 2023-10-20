package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.LoginUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdatePasswordController", value = "/updatePassword")
public class UpdatePasswordController extends HttpServlet {
    UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        if (login == null) {
            login = "";
        }
        switch (login) {
            case "updatePassword":
                changePassword(request, response);
                break;
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        try {
            LoginUser loginUser = userDAO.findPasswordByEmail(email, password);
            if (loginUser != null) {
                if (newPassword.equals(confirmPassword) && !password.equals(newPassword)) {
                    userDAO.editPassWordUser(email, newPassword);
                    request.setAttribute("message", "Update success !");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Erorr Password Not Synchronized !");
                    request.getRequestDispatcher("editPassword.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Wrong email or password !");
                request.getRequestDispatcher("editPassword.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}