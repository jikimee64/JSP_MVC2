package com.controller.bbs;

import com.board.dao.BbsDAO;
import com.board.dto.BbsDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class writeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionID = (String) session.getAttribute("sessionID");
        if(sessionID == null){
            sessionID = "비회원";
        }

        String bbsTitle = request.getParameter("bbsTitle");
        String bbsContent = request.getParameter("bbsContent");
        String bbsDate = request.getParameter("bbsDate");

        BbsDAO bbsDAO = BbsDAO.getInstance();
        BbsDTO bbsDTO = new BbsDTO();
        bbsDTO.setBbsID(bbsDAO.nextval() + 1);
        bbsDTO.setBbsTitle(bbsTitle);
        bbsDTO.setBbsContent(bbsContent);
        bbsDTO.setBbsDate(bbsDate);
        bbsDTO.setUserID(sessionID);

        int wResult = bbsDAO.write(bbsDTO);
        System.out.println(wResult);
        response.sendRedirect("list.do");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/write.jsp");
        rd.forward(request, response);
    }
}
