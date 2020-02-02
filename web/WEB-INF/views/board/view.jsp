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
                <button type="button" onclick="location.href='list.do' " class="btn btn-xs pull-right">View Board
                </button>
            </div>
            <article>
                <div class="container" role="main">
                    <div class="bg-white rounded shadow-sm">
                        <div class="mb-3">
                            <label for="bbsTitle">제목</label>
                            <input type="text" class="form-control" name="bbsTitle" readonly id="bbsTitle"
                                   value="<c:out value="${view.bbsTitle}"/>">
                        </div>
                        <div class="mb-3">
                            <label for="bbsContent">내용</label>
                            <textarea class="form-control" rows="5" name="bbsContent" readonly id="bbsContent">${view.bbsContent}</textarea>
                        </div>
                        <div class="mb-3">
                            <label for="bbsFile">파일 업로드</label>
                            <input type="text" id="bbsFile" class="form-control" readonly name="bbsFile" value="<c:out value="${view.bbsFile}"/>"><br/>
                        </div>
<%--                        <div class="board_title"><c:out value="${view.bbsTitle}"/></div>--%>
<%--                        <div class="board_content">${view.bbsContent}</div>--%>
<%--                        <div class="board_info_box">${view.bbsFile}</div>--%>
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
                                <c:if test="${ view.bbsFile != NULL}">
                                    <td>
                                        <a href="download.do?bbsID=${view.bbsID}">
                                            <button type="button" class="btn btn-sm btn-primary" id="btnDownload">다운
                                            </button>
                                        </a>
                                    </td>
                                </c:if>
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