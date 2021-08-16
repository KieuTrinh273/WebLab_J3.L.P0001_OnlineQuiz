/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuestionDAO;
import entity.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class MakeQuizServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("makeQuiz.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            QuestionDAO qdao = new QuestionDAO();
            String msg = "";
            String question = null, option1 = null, option2 = null, option3 = null, option4 = null;
            if (request.getParameter("txtQuestion").isEmpty()) {
                msg += "Question can not be empty!<br/>";
            } else {
                question = request.getParameter("txtQuestion");
                request.setAttribute("txtQuestion", question);
            }
            if (request.getParameter("txtOption1").isEmpty()) {
                msg += "Option 1 can not be empty!<br/>";
            } else {
                option1 = request.getParameter("txtOption1");
                request.setAttribute("txtOption1", option1);
            }
            if (request.getParameter("txtOption2").isEmpty()) {
                msg += "Option 2 can not be empty!<br/>";
            } else {
                option2 = request.getParameter("txtOption2");
                request.setAttribute("txtOption2", option2);
            }
            if (request.getParameter("txtOption3").isEmpty()) {
                msg += "Option 3 can not be empty!<br/>";
            } else {
                option3 = request.getParameter("txtOption3");
                request.setAttribute("txtOption3", option3);
            }
            if (request.getParameter("txtOption4").isEmpty()) {
                msg += "Option 4 can not be empty!<br/>";
            } else {
                option4 = request.getParameter("txtOption4");
                request.setAttribute("txtOption4", option4);
            }

            String[] ans = request.getParameterValues("answer");
            if (ans == null) {
                msg += "Please choose answer!<br/>";
            } //if user choosed answer
            else {
                //get all answer(s)
                String answer = "";
                for (String an : ans) {
                    answer += an;
                }

                if (question != null && option1 != null && option2 != null
                        && option3 != null && option4 != null) {
                    Date now = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(now);

                    //create new question
                    Question newQues = new Question(question, option1, option2, option3, option4, answer, strDate);
                    if (qdao.checkExisted(newQues)) {
                        msg += "Insert failed! Question is exists!<br/>";
                    } else {
                        qdao.insertQuestion(newQues);
                        request.setAttribute("newQues", newQues);
                        request.removeAttribute("txtQuestion");
                        request.removeAttribute("txtOption1");
                        request.removeAttribute("txtOption2");
                        request.removeAttribute("txtOption3");
                        request.removeAttribute("txtOption4");
                    }
                }
            }

            request.setAttribute("msg", msg);
            request.getRequestDispatcher("makeQuiz.jsp").forward(request, response);
        } catch (Exception ex) {
            request.getRequestDispatcher("error.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
