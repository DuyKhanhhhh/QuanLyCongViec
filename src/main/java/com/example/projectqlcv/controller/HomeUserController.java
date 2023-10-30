package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.IUserDAO;
import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.Group;
import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;
import com.sun.net.httpserver.HttpExchange;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "AddGroupController", value = "/homeUser")
public class HomeUserController extends HttpServlet {
    private IUserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addGroup":
                addGroup(request, response);
                break;
            case "addTable":
                addTable(request, response);
                break;
            case "updatePassword":
                changePassword(request, response);
                break;
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        User user = userDAO.findUserById(id);
        if (user != null) {
            if (newPassword.equals(confirmPassword) && !password.equals(newPassword)) {
                userDAO.editPassWordUser(user);
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

    }

    private void addTable(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String permission = request.getParameter("permission");
        String group = request.getParameter("group");
        Table table = new Table(name, permission, group);
        userDAO.addGroup(table);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addGroup(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String groupType = request.getParameter("groupType");
        String permission = request.getParameter("permission");
        String information = request.getParameter("information");
        Group group = new Group(name, groupType, permission, information);
        userDAO.addGroup(group);
        HttpSession session = request.getSession();
        session.setAttribute("message","Add group success !");
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addGroup":
                showNewFormGroup(request, response);
                break;
            case "addTable":
                showNewFromTable(request, response);
                break;
            case "deleteGroup":
                deleteGroup(request, response);
                break;
            case "informationGroup":
                showNewFormByIdOfGroup(request,response);
                break;
            default:
                selectGroupFromSql(request, response);
        }
    }

    private void deleteGroup(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteGroup(id);
        List<Group> list = userDAO.selectGroupFromSQL();
        HttpSession session = request.getSession();
        session.setAttribute("message", "Delete success !");
        request.setAttribute("groups", list);
        try {
            request.getRequestDispatcher("homeUser.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.findUserById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("editPassword.jsp").forward(request, response);
    }

    private void showNewFromTable(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Table> tableList = userDAO.selectAllTable();
            request.setAttribute("listTable", tableList);
            request.getRequestDispatcher("home/addTable.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewFormGroup(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("home/addGroup.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showNewFormByIdOfGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Group group = userDAO.selectGroupById(id);
        request.setAttribute("group",group);
        request.getRequestDispatcher("information.jsp").forward(request,response);

    }

    private void selectGroupFromSql(HttpServletRequest request, HttpServletResponse response) {
        List<Group> groups = userDAO.selectGroupFromSQL();
        Collections.sort(groups, new Comparator<Group>() {
            @Override
            public int compare(Group group1, Group group2) {
                return group1.getName().compareToIgnoreCase(group2.getName());
            }
        });
        request.setAttribute("groups", groups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homeUser.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
