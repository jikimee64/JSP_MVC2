package com.controller.member;

import com.member.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("userPassword");

        UserDAO userDAO = UserDAO.getInstance();
        int loginResult = userDAO.login(userID, userPassword);

        if(loginResult == 1) {
            request.setAttribute("loginResult", loginResult);
            HttpSession session = request.getSession();
            session.setAttribute("sessionID", userID);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
            rd.forward(request, response);
        }else{
            request.setAttribute("loginResult", loginResult);
            request.getSession().setAttribute("messageType", "오류 메시지");
            request.getSession().setAttribute("messageContent", "아이디 혹은 비밀번호가 틀렸습니다.");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        rd.forward(request, response);
    }
}
