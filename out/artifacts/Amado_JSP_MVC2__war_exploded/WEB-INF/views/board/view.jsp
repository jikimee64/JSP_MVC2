<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

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
                <button type="button" class="btn btn-xs pull-right">View Board
                </button>
            </div>
            <article>
                <div class="container" role="main">
                    <div class="bg-white rounded shadow-sm">
                        <div class="board_title"><c:out value="${view.bbsTitle}"/></div>
                        <div class="board_content">${view.bbsContent}</div>
                    </div>

                    <div style="margin-top : 30px">
                        <c:choose>
                            <c:when test="${view.userID == sessionID || sessionID == 'admin' }">
                                <td>
                                    <a href="delete.do?bbsID=${view.bbsID}">
                                        <button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
                                    </a>
                                </td>
                                <td>
                                    <a href="update.do?bbsID=${view.bbsID}">
                                        <button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
                                    </a>
                                </td>
                            </c:when>
                        </c:choose>
                        <button type="button" onclick="location.href='list.do' " class="btn btn-sm btn-primary"
                                id="btnList">목록
                        </button>
                    </div>
                </div>
            </article>
        </div>

<%@include file="../includes/footer.jsp" %>