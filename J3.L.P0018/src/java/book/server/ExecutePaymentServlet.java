/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import book.driver.BookDAO;
import book.driver.BookDTO;
import book.driver.OrderDAO;
import book.driver.OrderDTO;
import book.driver.TotalDTO;
import book.driver.UseDiscountDAO;
import book.driver.UseDiscountDTO;
import book.driver.UserDTO;
import book.util.PaymentServices;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
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
@WebServlet(name = "ExecutePaymentServlet", urlPatterns = {"/ExecutePaymentServlet"})
public class ExecutePaymentServlet extends HttpServlet {

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
            String paymentId = request.getParameter("paymentId");
            String payerId = request.getParameter("PayerID");

            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.executePayment(paymentId, payerId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);

            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);

            //Begin CheckBeforePayment
            HttpSession session = request.getSession();
            BookDAO bookDao = new BookDAO();
            TotalDTO total = (TotalDTO) session.getAttribute("TOTAL");
            List<BookDTO> listOrder = (List<BookDTO>) session.getAttribute("BOOKCART");
            String discountCode = (String) session.getAttribute("DISCOUNTCODE");
            UseDiscountDAO useDiscountDao = new UseDiscountDAO();
            String Checkout_Message = "";
            UserDTO user = (UserDTO) session.getAttribute("USER");
            OrderDAO orderDao = new OrderDAO();
            //Check Quantity
            for (int i = 0; i < listOrder.size(); i++) {
                int id = listOrder.get(i).getBookID();
                int orderQuantity = listOrder.get(i).getBookQuantity();
                int daoQuantity = bookDao.getBookQuantity(id);
                int totalQuantity = daoQuantity - orderQuantity;
                bookDao.updateBookQuantity(id, totalQuantity);
                OrderDTO order = new OrderDTO(user.getUsername(), listOrder.get(i).getBookID(), orderQuantity, "");
                orderDao.insertOrder(order);
            }
            if (total.getDiscount() > 1) {
                UseDiscountDTO useDiscount = new UseDiscountDTO(user.getUsername(), discountCode, "");
                useDiscountDao.insertUseDiscount(useDiscount);
            }
            session.setAttribute("BOOKCART", null);
            session.setAttribute("TOTAL", null);
            Checkout_Message = "Thank for your order! we will delivery for you soon!";
            session.setAttribute("ORDERSUCCESS", Checkout_Message);

        } catch (Exception e) {
            log("Error at ExecutePaymentServlet: " + e.toString());
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
