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
import model.Account;

/**
 *
 * @author DELL
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
                String msg = "";
                
                //get user inputs
                String username = request.getParameter("userName");
                String password = request.getParameter("password");
                boolean role = request.getParameter("role").equals("student")?false:true;
                String email = request.getParameter("email");
                
                AccountDAO accountDAO = new AccountDAO();

                //validating
                if(username.isEmpty()){
                    msg+="username can not be empty!<br/>";
                }
                if(password.isEmpty()){
                    msg+="password can not be empty!<br/>";
                }
                if(email.isEmpty()){
                    msg+="email can not be empty!<br/>";
                }
                if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
                    if (username.contains(" ") || password.contains(" ")) {
                        msg += "Both username and password can not contain space<br/>";
                    }
                    if (username.length() > 30 || password.length() > 30) {
                        msg += "Username and/or password is invalid! Max length is 30 characters!<br/>";
                    }
                    if (accountDAO.checkExisted(username,email)) {
                        msg += "Username/email is already existed! Please choose another!<br/>";
                    }
                    if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        msg += "Email is invalid! Please re-check!";
                    }
                    if (msg.equals("")) {
                        Account account = new Account(username, password, role, email);
                        accountDAO.insertAccount(account);
                        msg = "Sucessfull";
                    }
                }
                request.setAttribute("msg", msg);
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("register.jsp").forward(request, response);
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
