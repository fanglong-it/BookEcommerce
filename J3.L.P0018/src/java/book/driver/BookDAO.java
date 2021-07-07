/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.driver;

import book.connect.ConnectDB;
import java.io.Serializable;
import java.sql.Connection;
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
public class BookDAO implements Serializable {
    

    public List<BookDTO> getAllBook() throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    Float price = rs.getFloat("Price");
                    String author = rs.getString("Author");
                    String category = rs.getString("CategoryId");
                    String importDate = rs.getString("ImportDate");
                    String status = rs.getString("Status");
                    String photoCode = rs.getString("PhotoCode");
                    BookDTO book = new BookDTO(bookId, title, price, author, category, importDate, status, photoCode);
                    list.add(book);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<BookDTO> getBookByName(String Name) throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook \n"
                        + "WHERE Title like ? and Status = ? and BookQuantity > 0";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + Name + "%");
                pst.setString(2, "Active");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    Float price = rs.getFloat("Price");
                    String author = rs.getString("Author");
                    String category = rs.getString("CategoryId");
                    String importDate = rs.getString("ImportDate");
                    String status = rs.getString("Status");
                    String photoCode = rs.getString("PhotoCode");
                    BookDTO book = new BookDTO(bookId, title, price, author, category, importDate, status, photoCode);
                    list.add(book);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<BookDTO> getBookByPrice(Float Price) throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook \n"
                        + "Where Price Between 0 and ? and Status = ? and BookQuantity > 0";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, Price);
                pst.setString(2, "Active");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    Float price = rs.getFloat("Price");
                    String author = rs.getString("Author");
                    String category = rs.getString("CategoryId");
                    String importDate = rs.getString("ImportDate");
                    String status = rs.getString("Status");
                    String photoCode = rs.getString("PhotoCode");
                    BookDTO book = new BookDTO(bookId, title, price, author, category, importDate, status, photoCode);
                    list.add(book);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<BookDTO> getBookByCate(String cateID) throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook \n"
                        + "Where CategoryId like ? and Status = ? and BookQuantity > 0";
                pst = con.prepareStatement(sql);
                pst.setString(1, cateID);
                pst.setString(2, "Active");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    Float price = rs.getFloat("Price");
                    String author = rs.getString("Author");
                    String category = rs.getString("CategoryId");
                    String importDate = rs.getString("ImportDate");
                    String status = rs.getString("Status");
                    String photoCode = rs.getString("PhotoCode");
                    BookDTO book = new BookDTO(bookId, title, price, author, category, importDate, status, photoCode);
                    list.add(book);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public BookDTO getBook(String BookId) throws NamingException, SQLException {
        CategoryDAO cateDao = new CategoryDAO();
        BookDTO book = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode, Discription, BookQuantity\n"
                        + "from tblBook \n"
                        + "Where BookId = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, BookId);
                rs = pst.executeQuery();
                if(rs.next()){
                    int bookId = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    Float price = rs.getFloat("Price");
                    String author = rs.getString("Author");
                    String categoryId = cateDao.getCategoryName(rs.getString("CategoryId"));
                    String importDate = rs.getString("ImportDate");
                    String status = rs.getString("Status");
                    String photoCode = rs.getString("PhotoCode");
                    String discription = rs.getString("Discription");
                    int bookQuantity = rs.getInt("BookQuantity");
                    book = new BookDTO(bookId, title, price, author, categoryId, importDate, status, photoCode, discription, bookQuantity);
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
        return book;
    }
}
