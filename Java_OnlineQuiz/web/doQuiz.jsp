<%-- 
    Document   : doQuiz
    Created on : Jul 21, 2021, 1:15:09 AM
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
    <body onload="createTimer(${secs})">
        <div class="container">
            <%@include file="header.jsp" %>
            <div class="main">
                <div class="content">
                    <c:if test="${sessionScope.account==null}">
                        Please <a href="login">Login</a> to take quiz!
                    </c:if>
                    <c:if test="${sessionScope.account!=null and questions != null}">
                        
                        <div class="do-quiz">
                            <table>
                                <tr>
                                    <td class="left">
                                        <div class="top-content">Welcome <span class="title">${sessionScope.account.username}</span></div>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="left"></td>
                                    <td class="middle"> Time remaining: <span id="timer"></span>
                                    </td>
                                </tr>
                            </table>
                            <div style="margin-top: 24px">
                                <form id="doQuiz" action="result" method="post">
                                    <c:forEach items="${questions}" var="ques" varStatus="status">
                                        <div class="each-question">
                                            <div class="question-infor">
                                                <div class="question">${ques.question}</div>
                                                <div class="ans">
                                                    <input type="checkbox" id="op1" name="${status.count}Answer" value="1">
                                                    <label for="op1">${ques.option1}</label>
                                                </div>
                                                <div class="ans">
                                                    <input type="checkbox" id="op2" name="${status.count}Answer" value="2">
                                                    <label for="op2">${ques.option2}</label>
                                                </div>
                                                <div class="ans">
                                                    <input type="checkbox" id="op3" name="${status.count}Answer" value="3">
                                                    <label for="op3">${ques.option3}</label>
                                                </div>
                                                <div class="ans">
                                                    <input type="checkbox" id="op4" name="${status.count}Answer" value="4">
                                                    <label for="op4">${ques.option4}</label>
                                                </div>
                                            </div>
                                            <div class="right">
                                                <c:if test="${status.count!=questions.size()}">
                                                    <a onclick="plusQues(1)">Next</a>
                                                </c:if>
                                                <c:if test="${status.count==questions.size()}">
                                                    <button type="submit">Submit</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </form>
                            </div>
                        </div>

                    </c:if>

                </div>
            </div>
        </div>
        <script src="js/showQues.js" type="text/javascript"></script>
        <script src="js/timing.js" type="text/javascript"></script>
    </body>
</html>
