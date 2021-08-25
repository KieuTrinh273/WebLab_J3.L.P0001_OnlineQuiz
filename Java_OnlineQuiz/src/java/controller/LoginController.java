/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.implement.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author DELL
 */
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
            //get infomation from user
            String username = request.getParameter("userName");
            String password = request.getParameter("password");
            String msg = "";

            AccountDAO accountDAO = new AccountDAO();
            //check user name and password empty or not
            if (username.isEmpty()) {
                msg += "usename can not be empty!<br/>";
            }
            if (password.isEmpty()) {
                msg += "password can not be empty!<br/>";
            }
            
            //if not empty
            if (!username.isEmpty()&&!password.isEmpty()) {
                //username does not exist => show error message
                if (!accountDAO.checkExisted(username, "")) {
                    msg += "username does not exist! Please re-check!<br/>";
                } else {
                    //check if password is correct
                    Account account;
                    account = accountDAO.getAccByLoginInfor(username, password);
                    if (account == null) {
                        msg += "password is incorrect! Please re-check!";
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("account", account);
                    }
                }
            }
            request.setAttribute("msg", msg);
            request.setAttribute("username", username);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Error occurred");
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
