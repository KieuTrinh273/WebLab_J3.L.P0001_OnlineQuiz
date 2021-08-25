/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.implement.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;

/**
 *
 * @author DELL
 */
public class ManageQuizController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageQuizController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageQuizController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            QuestionDAO qdao = new QuestionDAO();

            List<Question> questions = qdao.getAllQues();

            int size = questions.size();
            int pageSize = 3;
            int numOfPage = size / pageSize + (size % pageSize == 0 ? 0 : 1);

            String pageRaw = request.getParameter("page");
            int page;
            try {
                if (pageRaw == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(pageRaw);
                }
            } catch (Exception e) {
                page = numOfPage + 1;
            }
            List<Question> quesOnPage = qdao.pagingForQues(page, pageSize);

            request.setAttribute("questions", questions);
            request.setAttribute("page", page);
            request.setAttribute("quesOnPage", quesOnPage);
            request.setAttribute("numOfPage", numOfPage);
            request.getRequestDispatcher("manageQuiz.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageQuizController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("error.jsp");
        }
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
            int id = Integer.parseInt(request.getParameter("quesId"));
            QuestionDAO qdao = new QuestionDAO();
            qdao.deleteQuesByID(id);

            doGet(request, response);
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
