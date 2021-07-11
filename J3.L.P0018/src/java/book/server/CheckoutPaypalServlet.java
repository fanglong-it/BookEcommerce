/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import book.driver.BookDAO;
import book.driver.BookDTO;
import book.driver.OrderDetail;
import book.driver.TotalDTO;
import book.util.BookUtil;
import book.util.PaymentServices;
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
@WebServlet(name = "CheckoutPaypalServlet", urlPatterns = {"/CheckoutPaypalServlet"})
public class CheckoutPaypalServlet extends HttpServlet {

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
            BookDAO bookDao = new BookDAO();
            BookUtil bookUtil = new BookUtil();
            HttpSession session = request.getSession();
            List<BookDTO> listCart = (List<BookDTO>) session.getAttribute("BOOKCART");
            TotalDTO totalDto = (TotalDTO) session.getAttribute("TOTAL");
            List<BookDTO> listDatabase = bookDao.getAllBook();
            String Checkout_Message = "";
            String checkQuantity = bookUtil.checkQuantityEnough(listCart, listDatabase);
            if (!checkQuantity.equals("Enough")) {
                Checkout_Message = "Quantity of " + checkQuantity + " in listDatabase is not Enough";
            } else {
                TotalDTO newTotalDto = bookUtil.changeCurrencies(totalDto);
                String product = "Order code:" + listCart.hashCode();
                String subtotal = String.format("%.02f", newTotalDto.getTotal());
                String shipping = String.format("%.02f", 0.000);
                float tax = 0;
                String total = String.format("%.02f", newTotalDto.getTotal());

                OrderDetail orderDetail = new OrderDetail(product, Float.parseFloat(subtotal),
                        Float.parseFloat(shipping), tax, Float.parseFloat(total));

                PaymentServices paymentServices = new PaymentServices();
                url = paymentServices.authorizePayment(orderDetail);
            }
            session.setAttribute("TOTAL", totalDto);
            session.setAttribute("ERROR_CHECKOUT", Checkout_Message);
        } catch (Exception e) {
            log("error at CheckoutPaypalServlet:" + e.toString());
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
