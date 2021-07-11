/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import book.driver.BookDTO;
import book.driver.TotalDTO;
import book.util.BookUtil;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "UpdateBookInCartServlet", urlPatterns = {"/UpdateBookInCartServlet"})
public class UpdateBookInCartServlet extends HttpServlet {
private static final String VIEWCART_PAGE = "viewCart.jsp";
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
        String url = VIEWCART_PAGE;
        try {
            String quantity = request.getParameter("quantity");
            String bookId = request.getParameter("BookId");
            BookUtil util = new BookUtil();
            HttpSession session = request.getSession();
            List<BookDTO> listBook = (List<BookDTO>) session.getAttribute("BOOKCART");
            util.updateBookInList(listBook, bookId, Integer.parseInt(quantity));
            session.setAttribute("BOOKCART", listBook);
            
            float subTotal = util.getBookTotalInList(listBook);
            TotalDTO total = new TotalDTO(subTotal, 1, 0, util.calculatorTotal(subTotal, 1));
            session.setAttribute("TOTAL", total);
            
        } catch (Exception e) {
            log("Error at UpdateBookInCartServlet: " +e.toString());
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
