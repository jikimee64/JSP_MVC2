package com.controller.bbs;

import com.board.dao.BbsDAO;
import com.board.dto.BbsDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class viewController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bbsID = request.getParameter("bbsID");
        BbsDAO bbsDAO = BbsDAO.getInstance();
        BbsDTO bbsDTO = new BbsDTO();
        bbsDAO.hitUpdate(bbsID);
        bbsDTO = bbsDAO.selectbyID(bbsID);

        request.setAttribute("view", bbsDTO);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
        rd.forward(request, response);

    }
}
