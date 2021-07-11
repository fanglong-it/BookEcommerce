/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.server;

import book.driver.BookDAO;
import book.driver.BookDTO;
import book.driver.CategoryDAO;
import book.driver.CategoryDTO;
import book.driver.TotalDTO;
import book.driver.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "LoadServlet", urlPatterns = {"/LoadServlet"})
public class LoadServlet extends HttpServlet {

    private static final String HOME_PAGE = "index.jsp";

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
            CategoryDAO cateDao = new CategoryDAO();
            BookDAO bookDao = new BookDAO();
            HttpSession session = request.getSession();
            
            //LoadCategory
            List<CategoryDTO> listCate = cateDao.getAllCategory();
            session.setAttribute("CATE", listCate);
            
            //LoadListBook
            List<BookDTO> listBook = null;
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if(user == null){
            listBook = bookDao.getAllBook();
            }else if(user.getRole().equals("AD")){
                listBook = bookDao.getAllBookForAdmin();
            }else{
                listBook = bookDao.getAllBook();
            }
            session.setAttribute("LISTBOOK", listBook);

            //Set Load for Book in cart
            List<BookDTO> bookCart = null;
            if (session.getAttribute("BOOKCART") == null) {
                bookCart = new ArrayList<>();
            } else {
                bookCart = (List<BookDTO>) session.getAttribute("BOOKCART");
            }
            session.setAttribute("BOOKCART", bookCart);

            //Set Load for total price of book
            TotalDTO totalDTO = null;
            if (session.getAttribute("TOTAL") == null) {
                
            } else {
                totalDTO = (TotalDTO) session.getAttribute("TOTAL");
            }
            session.setAttribute("TOTAL", totalDTO);

        } catch (Exception e) {
            log("Error at load Servlet: " + e.toString());
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
