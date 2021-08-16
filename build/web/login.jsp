<%-- 
    Document   : home
    Created on : Jul 19, 2021, 9:55:13 AM
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
                        <div class="title top-content">Login Form</div>
                        <form class="bottom-content" action="login" method="post">
                            <table>
                                <tr>
                                    <td class="label">User Name:</td>
                                    <td class="txt"><input type="text" name="userName" value="${username}"/></td>
                                </tr>
                                <tr>
                                    <td class="label">Password:</td>

                                    <td class="txt"><input type="password" name="password"/></td>

                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <button type="submit">Sign in</button>
                                        <a href="register.jsp">Register</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <c:if test="${msg ne ''}">
                            <br/>
                            <div class="error-msg">${msg}</div>
                        </c:if>
                    </c:if>
                    <c:if test="${sessionScope.account!=null}">
                        <div class="top-content">Welcome <span class="title">${sessionScope.account.username}</span></div>
                        </c:if>

                </div>
            </div>
        </div>
    </body>
</html>
