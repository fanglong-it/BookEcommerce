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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateBookServlet", urlPatterns = {"/CreateBookServlet"})
public class CreateBookServlet extends HttpServlet {

    private static final String VIEWBOOK_PAGE = "indexForAdmin.jsp";
    private static final String CREATEBOOK_PAGE = "createBookPage.jsp";

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
        String url = VIEWBOOK_PAGE;
        try {
            String title = request.getParameter("bookTitle");
            String price = request.getParameter("bookPrice");
            String discription = request.getParameter("bookDescription");
            String author = request.getParameter("bookAuthor");
            String categoryId = request.getParameter("bookCategory");
            String bookQuantity = request.getParameter("bookQuantity");
            String photoCode = request.getParameter("photoCode");
            BookDAO dao = new BookDAO();
            String Create_Message = "";
            HttpSession session = request.getSession();
            
            BookDTO book = new BookDTO(title, Float.parseFloat(price),
                    author, categoryId, photoCode, discription, Integer.parseInt(bookQuantity));
            if(dao.insertBookForAdmin(book)){
                url = VIEWBOOK_PAGE;
            }else{
                Create_Message = "Can't insert Book";
                url = CREATEBOOK_PAGE;
            }
            session.setAttribute("CREATE_MESSAGE", Create_Message);

        } catch (Exception e) {
            log("Error at CreateBookServlet:" + e.toString());
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
