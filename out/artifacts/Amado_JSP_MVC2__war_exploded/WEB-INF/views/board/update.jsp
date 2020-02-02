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
                <button type="button" class="btn btn-xs pull-right">Update Board
                </button>
            </div>
            <article>
                <div class="container" role="main">
                    <form action="update.do" method="post" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="bbsTitle">제목</label>
                            <input type="text" class="form-control" name="bbsTitle" required="required" id="bbsTitle"
                                   value="${update.bbsTitle}" placeholder="제목을 입력해 주세요"/>
                        </div>
                        <div class="mb-3">
                            <label for="bbsContent">내용</label>
                            <textarea class="form-control" rows="5" name="bbsContent" id="bbsContent"
                                      placeholder="내용을 입력해 주세요">${update.bbsContent}</textarea>
                        </div>
                        <div class="mb-3">
                            <label for="bbsFile">파일 업로드</label>
                            <input type="file" class="file" name="bbsFile"><br/>
                            <div class="input-group col-xs-12">
                                <span class="input-group-addon"></span>
                                <input type="text" class="form-control input-lg" disabled placeholder="${update.bbsFile}">
                                <span class="input-group-btn">
                            <button class="browse btn btn-primary input-lg" type="button">
                               파일찾기
                            </button>
                        </span>
                            </div>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-sm btn-primary" id="btnSave">저장</button>
                            <button type="button" onclick="location.href='list.do' " class="btn btn-sm btn-primary"
                                    id="btnList">목록
                            </button>
                        </div>
                        <div style="display: none;">
                            <input type="text" name="bbsID" value="${update.bbsID }"/>
                        </div>
                    </form>
                </div>
            </article>
        </div>

<%@include file="../includes/footer.jsp" %>