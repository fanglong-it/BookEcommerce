/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import book.driver.UserDAO;
import book.driver.UserDTO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
private static final String LOGIN_PAGE = "login.jsp";
private static final String REGISTER_PAGE = "register.jsp";
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
        String url = REGISTER_PAGE;
        try {
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String password = request.getParameter("password");
            String Repassword = request.getParameter("Repassword");
            String Dob = request.getParameter("dob");
            UserDAO dao = new UserDAO();
            String Error_message = "";
            Boolean flag = true;
            HttpSession session = request.getSession();
            if(dao.checkUserExist(username)){
                flag = false;
                Error_message = "The username have been taken, choose another!";
            }else if(!password.equals(Repassword)){
                flag = false;
                Error_message = "The Password and Repassword must match!";
            }
            if(flag){
                UserDTO user = new UserDTO(username, password, fullname, Dob, "US", "Active");
                if(dao.insertUser(user)){
                    url = LOGIN_PAGE;
                }else{
                    Error_message = "Can't insert into database!";
                    url = REGISTER_PAGE;
                }
            }
            session.setAttribute("ERROR_REGISTER", Error_message);
        } catch (Exception e) {
            log("Error at Register Servlet: " + e.toString());
        } finally {
            response.sendRedirect(url);
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
