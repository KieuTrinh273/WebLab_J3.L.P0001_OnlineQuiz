<%-- 
    Document   : manageQuiz
    Created on : Jul 19, 2021, 8:05:23 PM
    Author     : DELL
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp" %>
            <div class="main">
                <div class="content">
                    <c:if test="${quesOnPage.size()<1}">
                        <h1 style="color: red">Page not found!</h1>
                    </c:if>
                        
                    <c:if test="${quesOnPage.size()>=1}">
                        
                    <!--get number from sessionScope-->
                    <div class="top-content">Number of question: <span>${questions.size()}</span></div>
                    <div class="bottom-content list">
                        <div class="each-row"> 
                            <div class="th left">Question</div>
                            <div class="th right">DateCreated</div>
                        </div>

                        <div class="all-question">
                            <c:forEach items="${quesOnPage}" var="ques">
                                <div class="each-row">
                                    <div class="left">${ques.question}</div>
                                    <div class="right">${ques.date}</div>
                                    <c:if test="${sessionScope.account.isTeacher}">
                                        <form onsubmit="if(!confirm('Do you really want to delete this question?')){return false;}"
                                            id="delete" action="manage-quiz" method="post">
                                            <input type="hidden" name="quesId" value="${ques.id}">
                                            <input type="submit" value="Delete">
                                        </form>

                                        <!--href="manager-quiz?quesId=${ques.id}">Delete</a>-->
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="page">
                            <c:forEach begin="1" end="${numOfPage}" var="i">
                                <a class="${i==page?"clicked":""}" 
                                   href="manage-quiz?page=${i}">${i}</a>
                            </c:forEach>
                        </div>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
        <script src="js/deleteQues.js" type="text/javascript"></script>
    </body>
</html>
