/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.driver;

import book.connect.ConnectDB;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class OrderDAO implements Serializable {

    public boolean insertOrder(OrderDTO order) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "insert into tblOrder values(?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, order.getUsername());
                pst.setInt(2, order.getBookId());
                pst.setInt(3, order.getQuantity());
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(4, date.toString());

                if (pst.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }

    public List<OrderDTO> getOrderHistory() throws NamingException, SQLException {
        List<OrderDTO> orderHistory = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BookDAO dao = null;

        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "select Username, BookId, Quantity, OrderDate\n"
                        + "from tblOrder";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    dao = new BookDAO();
                    String username = rs.getString("Username");
                    int bookId = rs.getInt("BookId");
                    BookDTO bookDTO = dao.getBook("" + bookId);
                    int quantity = rs.getInt("Quantity");
                    String orderDate = rs.getString("OrderDate");
                    OrderDTO order = new OrderDTO(username, bookDTO.getTitle(), quantity, orderDate);
                    orderHistory.add(order);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return orderHistory;
    }

    public List<OrderDTO> getOrderHistoryByDate(String Date) throws NamingException, SQLException {
        List<OrderDTO> orderHistory = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BookDAO dao = null;

        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "select Username, BookId, Quantity, OrderDate\n"
                        + "from tblOrder\n"
                        + "Where OrderDate between '2020-01-01' and ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, Date);
                rs = pst.executeQuery();
                while (rs.next()) {
                    dao = new BookDAO();
                    String username = rs.getString("Username");
                    int bookId = rs.getInt("BookId");
                    BookDTO bookDTO = dao.getBook("" + bookId);
                    int quantity = rs.getInt("Quantity");
                    String orderDate = rs.getString("OrderDate");
                    OrderDTO order = new OrderDTO(username, bookDTO.getTitle(), quantity, orderDate);
                    orderHistory.add(order);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return orderHistory;
    }

    public List<OrderDTO> getOrderHistoryByUsername(String Username) throws NamingException, SQLException {
        List<OrderDTO> orderHistory = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BookDAO dao = null;

        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "select Username, BookId, Quantity, OrderDate\n"
                        + "from tblOrder \n"
                        + "where Username Like ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + Username + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    dao = new BookDAO();
                    String username = rs.getString("Username");
                    int bookId = rs.getInt("BookId");
                    BookDTO bookDTO = dao.getBook("" + bookId);
                    int quantity = rs.getInt("Quantity");
                    String orderDate = rs.getString("OrderDate");
                    OrderDTO order = new OrderDTO(username, bookDTO.getTitle(), quantity, orderDate);
                    orderHistory.add(order);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return orderHistory;
    }

}
