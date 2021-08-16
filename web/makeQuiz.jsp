<%-- 
    Document   : makeQuiz
    Created on : Jul 19, 2021, 7:19:05 PM
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

                    <!--accessable for teacher only-->
                    <c:if test="${sessionScope.account.role eq 'teacher'}">
                        <c:if test="${msg ne ''}">
                            <div class="error-msg">${msg}</div>
                        </c:if>
                        <c:if test="${newQues!=null}">
                            Inserted successfully!
                        </c:if>
                        <form class="bottom-content" action="make-quiz" method="post">
                            <table>
                                <tr>
                                    <td class="tdlabel">
                                        Question:
                                    </td>
                                    <td>
                                        <textarea name="txtQuestion" rows="6" >${txtQuestion}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">
                                        Option 1:
                                    </td>
                                    <td>
                                        <textarea name="txtOption1" rows="3">${txtOption1}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">
                                        Option 2:
                                    </td>
                                    <td>
                                        <textarea name="txtOption2" rows="3">${txtOption2}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">
                                        Option 3:
                                    </td>
                                    <td>
                                        <textarea name="txtOption3" rows="3">${txtOption3}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">
                                        Option 4:
                                    </td>
                                    <td>
                                        <textarea name="txtOption4" rows="3">${txtOption4}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">
                                        Answer(s):
                                    </td>
                                    <td>
                                        <input type="checkbox" id="cbOption1" name="answer" value="1">
                                        <label for="cbOption1"> Option 1 </label>
                                        <input type="checkbox" id="cbOption2" name="answer" value="2">
                                        <label for="cbOption2"> Option 2 </label>
                                        <input type="checkbox" id="cbOption3" name="answer" value="3">
                                        <label for="cbOption3"> Option 3 </label>
                                        <input type="checkbox" id="cbOption4" name="answer" value="4">
                                        <label for="cbOption4"> Option 4 </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <button type="submit">Save</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.account.role ne 'teacher' || sessionScope.account==null}">
                        For teacher only!
                    </c:if>

                </div>
            </div>
        </div>
    </body>
</html>
