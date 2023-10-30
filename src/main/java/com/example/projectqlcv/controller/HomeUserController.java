package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.IUserDAO;
import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.Group;
import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            case "updateGroup":
                updateGroup(request,response);
                break;
            case "addTable":
                addTable(request, response);
                break;
            case "editUser":
                editInformation(request,response);
                break;
        }
    }
    private void editInformation(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("idUd"));
        String name = request.getParameter("nameUd");
        String phoneNumber = request.getParameter("phoneNumberUd");
        String address = request.getParameter("addressUd");
        String avatar = request.getParameter("avatarUd");
        User user = new User(name,phoneNumber,address,avatar);
        userDAO.editInformationUser(id,user);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void updateGroup(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String group = request.getParameter("group");
        String permission = request.getParameter("permission");
        String information = request.getParameter("information");
        Group groups = new Group(name,group,permission,information);
        userDAO.updateGroup(id,groups);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addTable(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String permission = request.getParameter("permission");
        String group = request.getParameter("group");
        Table table = new Table(name, permission, group);
        userDAO.addTable(table);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addGroup":
                showNewFormGroup(request, response);
                break;
            case "updateGroup":
                showUpdateGroup(request,response);
                break;
            case "delete":
                deleteGroup(request,response);
                break;
            case "addTable":
                showNewFromTable(request, response);
                break;
            case "editUser":
                showEditFormUser(request,response);
                break;
            default:
                selectGroupFromSql(request, response);
        }
    }
    private void showEditFormUser(HttpServletRequest request ,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.selectAllUserId(id);
        request.setAttribute("user",user);
        try {
            request.getRequestDispatcher("home/editUser.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteGroup(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteGroup(id);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showUpdateGroup(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Group group = userDAO.findGroupById(id);
        request.setAttribute("listGroup", group);
        try {
            request.getRequestDispatcher("home/updateGroup.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
