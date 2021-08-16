<%-- 
    Document   : showResult
    Created on : Jul 21, 2021, 9:07:25 PM
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
                    <div class="top-content">Your score <span class="title">${point} (${percent}) - ${result}</span></div>
                    <div class="bottom-content">
                        <form action="take-quiz">
                            Take another quiz  <button type="submit">Start</button>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
