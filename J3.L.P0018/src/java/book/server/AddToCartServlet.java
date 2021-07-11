/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import book.driver.BookDAO;
import book.driver.BookDTO;
import book.driver.TotalDTO;
import book.driver.UserDTO;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private static final String VIEWDETAIL_PAGE = "viewProductDetail.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

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
        String url = VIEWDETAIL_PAGE;
        try {

            BookDAO bookDao = new BookDAO();
            BookUtil util = new BookUtil();
            HttpSession session = request.getSession();
            String bookId = request.getParameter("BookId");
            String quantity = request.getParameter("Quantity");
            List<BookDTO> listBook = (List<BookDTO>) session.getAttribute("BOOKCART");
            BookDTO book = bookDao.getBook(bookId);
            book.setBookQuantity(Integer.parseInt(quantity));
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null) {
                url = LOGIN_PAGE;
            } else {
                if (listBook.isEmpty()) {
                    listBook.add(book);
                } else {
                    if (util.checkExistInList(listBook, bookId)) {
                        util.updateBookInList(listBook, bookId, util.getBookQuantityInList(listBook, bookId) + Integer.parseInt(quantity));
                    } else {
                        listBook.add(book);
                    }
                }
            }
            float subTotal = util.getBookTotalInList(listBook);
            TotalDTO total = new TotalDTO(subTotal, 1, 0, util.calculatorTotal(subTotal, 1));
            session.setAttribute("TOTAL", total);
            session.setAttribute("BOOKCART", listBook);

        } catch (Exception e) {
            log("Error at AddToCartServlet: " + e.toString());
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
