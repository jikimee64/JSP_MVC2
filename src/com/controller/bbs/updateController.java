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

public class updateController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bbsID = request.getParameter("bbsID");
        String bbsTitle = request.getParameter("bbsTitle");
        String bbsContent = request.getParameter("bbsContent");

        BbsDAO bbsDAO = BbsDAO.getInstance();
        BbsDTO bbsDTO = new BbsDTO();
        bbsDTO.setBbsID(Integer.parseInt(bbsID));
        bbsDTO.setBbsTitle(bbsTitle);
        bbsDTO.setBbsContent(bbsContent);

        bbsDAO.update(bbsDTO);
        response.sendRedirect("list.do");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bbsID = request.getParameter("bbsID");
        BbsDAO bbsDAO = BbsDAO.getInstance();
        BbsDTO bbsDTO = new BbsDTO();
        bbsDTO = bbsDAO.selectbyID(bbsID);

        request.setAttribute("update", bbsDTO);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/update.jsp");
        rd.forward(request, response);
    }
}
