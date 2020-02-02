package com.controller.bbs;

import com.board.dao.BbsDAO;
import com.board.dto.BbsDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class writeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionID = (String) session.getAttribute("sessionID");
        if (sessionID == null) {
            sessionID = "비회원";
        }

        MultipartRequest multi = null;
        int sizeLimit = 10 * 1024 * 1024; // 10메가입니다.
        String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");    // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준
        try {
            multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());

            String bbsTitle = multi.getParameter("bbsTitle");
            String bbsContent = multi.getParameter("bbsContent");
            String bbsDate = multi.getParameter("bbsDate");
            String bbsFile = multi.getOriginalFileName("bbsFile");

            //String bbsTitle = request.getParameter("bbsTitle");
            //String bbsContent = request.getParameter("bbsContent");
            //String bbsDate = request.getParameter("bbsDate");

            BbsDAO bbsDAO = BbsDAO.getInstance();
            BbsDTO bbsDTO = new BbsDTO();
            bbsDTO.setBbsID(bbsDAO.nextval() + 1);
            bbsDTO.setBbsTitle(bbsTitle);
            bbsDTO.setBbsContent(bbsContent);
            bbsDTO.setBbsDate(bbsDate);
            bbsDTO.setUserID(sessionID);
            bbsDTO.setBbsFile(bbsFile);

            int wResult = bbsDAO.write(bbsDTO);
            response.sendRedirect("list.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/write.jsp");
        rd.forward(request, response);
    }
}
