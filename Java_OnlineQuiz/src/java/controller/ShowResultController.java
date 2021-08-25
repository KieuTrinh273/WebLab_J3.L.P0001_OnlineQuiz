/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class ShowResultController extends HttpServlet {

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
            HttpSession session = request.getSession();

            long endTime = (long) session.getAttribute("endTime");
            Date today = Calendar.getInstance().getTime();
            Date expiredTime = new Date(endTime);

            //user click submit after entime => show error message and do not calculate mark
            if (today.after(expiredTime)) {
                String errorMessage = "time out!";
                session.removeAttribute("correctAnswer");
                session.removeAttribute("secs");               
                request.setAttribute("error", errorMessage);
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            ArrayList<String> correctAns = (ArrayList<String>) session.getAttribute("correctAnswer");
            ArrayList<String> userAnswer = new ArrayList<>();

            String ans = "";
            String[] userAnswer_raw;
            int correctPoint = 0;

            //get user's answer for each question
            //user skip question => value is 0
            //otherwise => value is chosen option
            for (int i = 1; i <= correctAns.size(); i++) {
                ans = "0";
                userAnswer_raw = request.getParameterValues(i + "Answer");
                if (userAnswer_raw != null) {
                    ans = "";
                    for (String string : userAnswer_raw) {
                        ans += string;
                    }
                }
                userAnswer.add(ans);
            }

            //compare correct answer with user answer
            for (int i = 0; i < userAnswer.size(); i++) {
                ans = correctAns.get(i);
                if (userAnswer.get(i).equals(ans)) {
                    correctPoint++;
                }
            }

            double point = correctPoint * 10.0 / correctAns.size();
            DecimalFormat formPoint = new DecimalFormat("#.#");
            String result = point >= 5 ? "Passed" : "Not passed";

            session.removeAttribute("correctAnswer");
            session.removeAttribute("secs");

            request.setAttribute("percent", Math.round(point * 10) + "%");
            request.setAttribute("point", formPoint.format(point));
            request.setAttribute("result", result);
            request.getRequestDispatcher("showResult.jsp").forward(request, response);
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
