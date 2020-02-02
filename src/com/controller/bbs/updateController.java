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
import java.io.File;
import java.io.IOException;

public class updateController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MultipartRequest multi = null;
        int sizeLimit = 10 * 1024 * 1024; // 10메가입니다.
        String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");    // 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준
        try {
            multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());

            String bbsID = multi.getParameter("bbsID");
            String bbsTitle = multi.getParameter("bbsTitle");
            String bbsContent = multi.getParameter("bbsContent");

            BbsDAO bbsDAO = BbsDAO.getInstance();
            BbsDTO bbsDTO = new BbsDTO();
            bbsDTO.setBbsID(Integer.parseInt(bbsID));
            bbsDTO.setBbsTitle(bbsTitle);
            bbsDTO.setBbsContent(bbsContent);

            String bbsFile = "";
            File file = multi.getFile("bbsFile");
            if (file != null) { //파일이 새롭게 등록된 경우
                bbsFile = multi.getOriginalFileName("bbsFile");
                String prev = bbsDAO.getFile(bbsID);
                File prevFile = new File(savePath + "/" + prev);
                if(prevFile.exists()){
                    prevFile.delete();
                }
            } else { //기존 파일 유지
                bbsFile = bbsDAO.getFile(bbsID);
            }
            bbsDTO.setBbsFile(bbsFile);
            bbsDAO.update(bbsDTO);
            response.sendRedirect("list.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
