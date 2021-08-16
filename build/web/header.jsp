<%-- 
    Document   : header
    Created on : Jul 19, 2021, 9:56:32 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/header.css">
    </head>
    <body>

        <div class="preheader"></div>

        <div class="header-container">
                <div class="top-nav">
                    <div class= "nav-inner">
                        <ul>
                            <li><a href="${sessionScope.correctAnswer==null?"login.jsp":"#"}">Home</a></li>
                            <li><a href= "${sessionScope.correctAnswer==null?"takeQuiz.jsp":"#"}">Take Quiz</a></li>
                            <li><a href= "${sessionScope.correctAnswer==null?"makeQuiz.jsp":"#"}">Make Quiz</a></li>
                            <li><a href= "${sessionScope.correctAnswer==null?"manageQuiz.jsp":"#"}">Manage Quiz</a></li>
                            <c:if test="${sessionScope.account!=null}">
                                <li><a href="logout">Logout</a></li>
                            </c:if>
                        </ul>
                </div>
            </div>
        </div>

    </body>
</html>
