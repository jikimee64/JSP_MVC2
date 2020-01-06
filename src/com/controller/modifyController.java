package com.controller;

import com.member.dao.UserDAO;
import com.member.dto.UserDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class modifyController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("userPassword");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");

        Pattern pwPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$");
        Matcher matcher1 = pwPattern.matcher(userPassword);
        Pattern emailPattern = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
        Matcher matcher2 = emailPattern.matcher(userEmail);
        if (!matcher1.matches()) {
            request.setAttribute("pwRole", 0);
            getSession(request, response);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/modify.jsp");
            rd.forward(request, response);
        }
        if (!matcher2.matches()) {
            request.setAttribute("emailRole", 0);
            getSession(request, response);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/modify.jsp");
            rd.forward(request, response);
        } else {
            UserDAO userDAO = UserDAO.getInstance();
            UserDTO userDTO = new UserDTO();
            userDTO.setUserPassword(userPassword);
            userDTO.setUserName(userName);
            userDTO.setUserEmail(userEmail);
            userDTO.setUserID(userID);

            int modifyResult = userDAO.modify(userDTO);

            if (modifyResult == 1) {
                request.setAttribute("modifyResult", modifyResult);
                HttpSession session = request.getSession();
                session.setAttribute("sessionID", userID);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("modifyResult", 0);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/signUp.jsp");
                rd.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getSession(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/modify.jsp");
        rd.forward(request, response);
    }

    private void getSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userID = (String)session.getAttribute("sessionID");
        UserDAO userDAO = UserDAO.getInstance();
        UserDTO userDTO = userDAO.getMember(userID);
        request.setAttribute("dto", userDTO);
    }
}
