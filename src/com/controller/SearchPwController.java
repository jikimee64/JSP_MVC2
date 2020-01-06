package com.controller;

import com.member.dao.UserDAO;
import com.member.dto.UserDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchPwController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");

        UserDAO userDAO = UserDAO.getInstance();
        UserDTO userDTO = userDAO.getMember(userID);

        if(userDTO == null || !userDTO.getUserName().equals(userName) || !userDTO.getUserEmail().equals(userEmail)) {
            request.setAttribute("searchPwResult", 0);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchPw.jsp");
            rd.forward(request, response);
        } else{
            request.setAttribute("searchPwResult", 1);
            request.setAttribute("dto", userDTO);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchPw.jsp");
        rd.forward(request, response);
    }
}
