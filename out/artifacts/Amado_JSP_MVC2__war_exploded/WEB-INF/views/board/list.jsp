<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="com.board.dao.BbsDAO" %>
<%@ page import="com.board.dto.BbsDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

<%
    BbsDAO bbsDao = BbsDAO.getInstance();
    List<BbsDTO> list = bbsDao.selectList();
%>

<c:if test="${sessionID == null }">
    <script>
        alert("로그인을 해주세요.");
        location.href = "main.do";
    </script>
</c:if>

<div id="content-wrapper">
    <div class="container-fluid">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#">Board</a>
            </li>
            <li class="breadcrumb-item active">Tables</li>
        </ol>

        <!-- DataTables Example -->
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-table"></i>
                <button type="button" onclick="location.href='write.do' " class="btn btn-xs pull-right">Register
                    New Board
                </button>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>날짜</th>
                            <th>조회</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss");
                            int count = 0;
                            for (BbsDTO b : list) {
                        %>
                        <tr>
                            <td><%=b.getBbsID() %>
                            </td>
                            <td><b><a href="view.do?bbsID=<%=b.getBbsID() %> "><%=b.getBbsTitle() %>
                            </a></b></td>
                            <td><%=b.getUserID() %>
                            </td>
                            <td><%=b.getBbsDate() %>
                            </td>
                            <td><%=b.getBbsHit() %>
                            </td>
                        </tr>
                        </tbody>
                        <%
                                count++;
                            }
                            if (count == 0) {
                        %>
                        <tr>
                            <td colspan="7">작성한 게시글이 없습니다.</td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>

<%@include file="../includes/footer.jsp" %>