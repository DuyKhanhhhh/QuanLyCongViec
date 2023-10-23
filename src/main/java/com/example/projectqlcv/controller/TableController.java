package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.IUserDAO;
import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.Table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TableController", urlPatterns = "/table")
public class TableController extends HttpServlet  {
    UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table = request.getParameter("table");

        switch (table){
            case "add":
                insertTable(request,response);
                break;
        }
    }

    private void insertTable(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String permission = request.getParameter("permission");
        String group = request.getParameter("group");
        Table table = new Table(name,permission,group);
        userDAO.addGroup(table);
        try {
            response.sendRedirect("homeUser.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Table> tableList = userDAO.selectAllTable();
        request.setAttribute("listTable",tableList);
        request.getRequestDispatcher("homeUser.jsp").forward(request,response);
    }
}

