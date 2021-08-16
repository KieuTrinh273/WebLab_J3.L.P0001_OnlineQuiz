<%-- 
    Document   : takeQuiz
    Created on : Jul 20, 2021, 6:21:55 AM
    Author     : DELL
--%>

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
                    <c:if test="${sessionScope.account==null}">
                        Please <a href="login">Login</a> to take quiz!
                    </c:if>
                    <c:if test="${sessionScope.account!=null}">
                        <div class="top-content">Welcome <span class="title">${sessionScope.account.username}</span></div>
                        <div class="bottom-content">
                            <form action="take-quiz" method="post">
                                Enter number of questions:<br/>
                                <input id="inputNumber" name="inputNumber" type="text"/>
                                <div class="button">
                                    <button type="submit">Start</button>
                                </div>
                            </form>
                        </div>
                        <c:if test="${msg!=null}">
                            <br/>
                            <div class="error-msg">${msg}</div>
                        </c:if>
                    </c:if>

                </div>
            </div>
        </div>
    </body>
</html>
