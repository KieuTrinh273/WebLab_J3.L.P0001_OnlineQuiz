<%-- 
    Document   : error
    Created on : Jul 20, 2021, 2:35:29 AM
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
                <div class="content error-msg">
                    <c:if test="${requestScope.error!=null}">
                    <h1>${requestScope.error}</h1>
                    </c:if>
                    <c:if test="${requestScope.error==null}">
                    <h1>Error occurred!</h1>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
