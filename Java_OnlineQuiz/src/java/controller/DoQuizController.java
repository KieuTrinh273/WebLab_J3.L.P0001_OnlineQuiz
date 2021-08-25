/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DBContext;
import dao.implement.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Question;

/**
 *
 * @author DELL
 */
public class DoQuizController extends HttpServlet {

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
            try {
                DBContext dBContext = new DBContext();
                int secsPerQ = Integer.parseInt(dBContext.getTimePerQ());
                
                //get total time
                HttpSession session = request.getSession();
                int numOfQues = (int) session.getAttribute("numOfQues");
                int secs = numOfQues * secsPerQ;
                
                long now = Calendar.getInstance().getTimeInMillis();
                long endTime = now + numOfQues*secsPerQ*1000;

                QuestionDAO qdao = new QuestionDAO();
                List<Question> questions;
                
                //get random numOfQues question from database
                questions = qdao.getRandomQuesion(numOfQues);

                //get correct answer
                List<String> correctAnswer = new ArrayList<>();
                for (Question question : questions) {
                    correctAnswer.add(question.getAns());
                    
                }

                session.setAttribute("correctAnswer", correctAnswer);
                request.setAttribute("secs", secs);
                request.setAttribute("questions", questions);
                session.setAttribute("endTime", endTime);
                request.getRequestDispatcher("doQuiz.jsp").forward(request, response);
            } catch (Exception ex) {
                request.getRequestDispatcher("error.jsp");
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
