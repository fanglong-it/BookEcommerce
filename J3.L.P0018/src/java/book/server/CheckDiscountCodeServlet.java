/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import book.driver.DiscountDAO;
import book.driver.TotalDTO;
import book.driver.UseDiscountDAO;
import book.util.BookUtil;
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
@WebServlet(name = "CheckDiscountCodeServlet", urlPatterns = {"/CheckDiscountCodeServlet"})
public class CheckDiscountCodeServlet extends HttpServlet {

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
            BookUtil util = new BookUtil();

            HttpSession session = request.getSession();
            DiscountDAO discountDao = new DiscountDAO();
            UseDiscountDAO useDiscountDao = new UseDiscountDAO();
            String username = request.getParameter("username");
            String discountCode = request.getParameter("DiscountCode");
            String Message_Cart = "";
            if (useDiscountDao.checkUserInDiscount(username, discountCode) != null) {
                Message_Cart = "The code have been used!";
            } else {
                if (discountDao.getDiscountValue(discountCode) > 0) {
                    TotalDTO totalDTO = (TotalDTO) session.getAttribute("TOTAL");
                    totalDTO.setDiscount(discountDao.getDiscountValue(discountCode));
                    totalDTO.setTotal(util.calculatorTotal(totalDTO.getSubTotal(), discountDao.getDiscountValue(discountCode)));
                    session.setAttribute("TOTAL", totalDTO);
                    Message_Cart = "Success for discount Code!";
                } else {
                    Message_Cart = "The Discount not match!";
                }
            }
            session.setAttribute("DISCOUNTCODE", discountCode);
            request.setAttribute("ERROR_DISCOUNT", Message_Cart);

        } catch (Exception e) {
            log("Error at CheckDiscountCodeServlet:" + e.toString());
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
