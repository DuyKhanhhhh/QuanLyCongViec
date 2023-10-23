package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.IUserDAO;
import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.Group;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

@WebServlet(name = "AddGroupController", value = "/groups")
public class GroupWorkController extends HttpServlet {
    private IUserDAO iUserDAO;

    @Override
    public void init() throws ServletException {
        iUserDAO = new UserDAO();
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
        }
    }

    private void addGroup(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String groupType = request.getParameter("groupType");
        String permission = request.getParameter("permission");
        String information = request.getParameter("information");
        Group group = new Group(name, groupType, permission, information);
        iUserDAO.addGroup(group);
        try {
            response.sendRedirect("/groups");
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
                    showNewFormGroup(request,response);
                    break;
                default:
                    selectGroupFromSql(request,response);
            }
    }
    private void showNewFormGroup(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("addGroup.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void selectGroupFromSql(HttpServletRequest request, HttpServletResponse response){
        List<Group> groups = iUserDAO.selectGroupFromSQL();
        request.setAttribute("groups",groups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homeUser.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}