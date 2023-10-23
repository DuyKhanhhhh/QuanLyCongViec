package com.example.projectqlcv.controller;

import com.example.projectqlcv.model.User;
import com.example.projectqlcv.DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginAndSignUpController", value = "/loginAndSignUp")
public class LoginAndSignUpController extends HttpServlet {
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
            case "signUp":
                signUpUser(request, response);
                break;
            case "login":
                loginUser(request, response);
                break;
        }
    }


    private void loginUser(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userDAO.login(email, password);
        try {
            if (user == null) {
                request.setAttribute("message", "Wrong email or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                response.sendRedirect("homeUser");
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void signUpUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String phoneNumber = request.getParameter("phoneNumber");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            User user = userDAO.checkLoginUser(email);
            if (user == null) {
                if (!password.equals(confirmPassword)) {
                    request.setAttribute("message","Passwords are not duplicates !");
                    request.getRequestDispatcher("signUp.jsp").forward(request,response);
                }else {
                    userDAO.sign_up(email, password, name, phoneNumber);
                    request.setAttribute("message", "Sign up success !");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Duplicate email please re-enter !");
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            }

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
