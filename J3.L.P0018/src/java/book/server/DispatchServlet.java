/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private static final String HOME_PAGE = "index.jsp";
    private static final String LOGIN_PAGE ="login.jsp";
    private static final String REGISTER_PAGE = "register.jsp";
    private static final String REGISTER_SERVLET = "RegisterServlet";
    private static final String LOGOUT_SERVLET = "LogoutServlet";
    private static final String LOGIN_SERVLET ="LoginServlet";
    private static final String SEARCH_SERVLET = "SearchServlet";
    private static final String LOAD_SERVLET = "LoadServlet";
    private static final String VIEWDETAIL_SERVLET = "ViewDetailServlet";
    
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
        String url = HOME_PAGE;
        try {
            String button = request.getParameter("btnAction");
            if (button == null) {
                url = LOAD_SERVLET;
            } else if (button.equals("")) {
                url = LOAD_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("ViewDetail")) {
                url = VIEWDETAIL_SERVLET;
            }else if(button.equals("LoginPage")){
                url = LOGIN_PAGE;
            }else if(button.equals("Login")){
                url = LOGIN_SERVLET;
            }else if(button.equals("RegisterPage")){
                url = REGISTER_PAGE;
            }else if(button.equals("RegisterServlet")){
                url = REGISTER_SERVLET;
            }else if(button.equals("Logout")){
                url = LOGOUT_SERVLET;
            }

        } catch (Exception e) {
            log("Error at Dispatch Servlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
