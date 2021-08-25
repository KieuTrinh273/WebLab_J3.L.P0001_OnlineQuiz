<%-- 
    Document   : register
    Created on : Jul 19, 2021, 12:38:41 PM
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
                    <c:choose>
                        <c:when test="${msg eq 'Sucessfull'}">
                            <div class="top-content">
                                Register successfully! <a href="login">Login</a> to get started!
                            </div>
                        </c:when>
                        <c:when test="${msg ne 'Sucessfull'}">
                            <div class="title top-content">Registration Form</div>
                            
                            <form class="register bottom-content" action="register" method="post">
                                <table class="register">
                                    <tr>
                                        <td class="left">User Name:</td>
                                        <td><input type="text" name="userName" value="${username}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="left">Password:</td>
                                        <td><input type="password" name="password" /></td>
                                    </tr>
                                    <tr>
                                        <td class="left">User Type:</td>
                                        <td><select name="role">
                                                <option value="teacher">Teacher</option>
                                                <option value="student">Student</option>
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td class="left">Email:</td>
                                        <td><input type="text" name="email" value="${email}"/></td>
                                    </tr>
                                    <tr>
                                        <td class="left"></td>
                                        <td>
                                            <button type="submit">Register</button>
                                        </td>
                                    </tr>
                                </table>

                            </form>
                            <c:if test="${msg ne ''}">
                                <div class="error-msg">${msg}</div>
                            </c:if>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
