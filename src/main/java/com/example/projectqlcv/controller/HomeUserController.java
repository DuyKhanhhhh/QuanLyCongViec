package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.IUserDAO;
import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.Group;
import com.example.projectqlcv.model.Table;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddGroupController", value = "/homeUser")
public class HomeUserController extends HttpServlet {
    private IUserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
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
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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
            default:
                selectGroupFromSql(request, response);
        }
    }

    private void showNewFromTable(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Table> tableList = userDAO.selectAllTable();
            request.setAttribute("listTable", tableList);
            request.getRequestDispatcher("homeUser.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewFormGroup(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addGroup.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void selectGroupFromSql(HttpServletRequest request, HttpServletResponse response) {
        List<Group> groups = userDAO.selectGroupFromSQL();
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
