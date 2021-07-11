/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import book.driver.BookDAO;
import book.driver.BookDTO;
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
@WebServlet(name = "UpdateBookAdminServlet", urlPatterns = {"/UpdateBookAdminServlet"})
public class UpdateBookAdminServlet extends HttpServlet {
    private static final String VIEWPRODUCT_PAGE = "indexForAdmin.jsp";
    
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
        String url = VIEWPRODUCT_PAGE;
        try {
            String bookId = request.getParameter("bookId");
            String title = request.getParameter("bookTitle");
            String price = request.getParameter("bookPrice");
            String author = request.getParameter("bookAuthor");
            String categoryId = request.getParameter("bookCategory");
            String importDate = request.getParameter("dateImport");
            String status = request.getParameter("bookStatus");
            String photoCode = request.getParameter("photoCode");
            String discription = request.getParameter("bookDescription");
            String bookQuantity = request.getParameter("bookQuantity");
            
            BookDTO book = new BookDTO(Integer.parseInt(bookId), title, Float.parseFloat(price), author, categoryId, importDate,
                    status, photoCode,discription, Integer.parseInt(bookQuantity));
            BookDAO bookDAO = new BookDAO();
            if(bookDAO.updateBookForAdmin(book)){
                url ="DispatchServlet?btnAction=ViewDetail&BookId="+bookId;
            }
            
        } catch (Exception e) {
            log("Error at UpdateBookAdminServlet: " + e.toString());
        }finally{
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
