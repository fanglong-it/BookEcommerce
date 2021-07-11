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
public class BookDAO implements Serializable {

    public List<BookDTO> getAllBook() throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode, Discription, BookQuantity\n"
                        + "from tblBook \n"
                        + "Where status = 'Active' and BookQuantity > 1";
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
                    String Discription = rs.getString("Discription");
                    int bookQuantity = rs.getInt("BookQuantity");
                    BookDTO book = new BookDTO(bookId, title, price, author,
                            category, importDate, status, photoCode, Discription, bookQuantity);
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

    public List<BookDTO> getAllBookForAdmin() throws SQLException, NamingException {
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

    public List<BookDTO> getBookByName(String name) throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook \n"
                        + "WHERE Title like ? and Status = ? and BookQuantity > 1";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
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

    public List<BookDTO> getBookByNameForAdmin(String name) throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook \n"
                        + "WHERE Title like ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
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

    public List<BookDTO> getBookByPrice(Float price) throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook \n"
                        + "Where Price Between 0 and ? and Status = ? and BookQuantity > 1";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, price);
                pst.setString(2, "Active");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    Float bookPrice = rs.getFloat("Price");
                    String author = rs.getString("Author");
                    String category = rs.getString("CategoryId");
                    String importDate = rs.getString("ImportDate");
                    String status = rs.getString("Status");
                    String photoCode = rs.getString("PhotoCode");
                    BookDTO book = new BookDTO(bookId, title, bookPrice, author, category, importDate, status, photoCode);
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

    public List<BookDTO> getBookByPriceForAdmin(Float price) throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook \n"
                        + "Where Price Between 0 and ?";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, price);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int bookId = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    Float bookPrice = rs.getFloat("Price");
                    String author = rs.getString("Author");
                    String category = rs.getString("CategoryId");
                    String importDate = rs.getString("ImportDate");
                    String status = rs.getString("Status");
                    String photoCode = rs.getString("PhotoCode");
                    BookDTO book = new BookDTO(bookId, title, bookPrice, author, category, importDate, status, photoCode);
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
                        + "Where CategoryId like ? and Status = ? and BookQuantity > 1";
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

    public List<BookDTO> getBookByCateForAdmin(String cateID) throws SQLException, NamingException {
        List<BookDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select BookId, Title, Price, Author, CategoryId, ImportDate, Status, PhotoCode\n"
                        + "from tblBook \n"
                        + "Where CategoryId like ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, cateID);
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

    public BookDTO getBook(String bookId) throws NamingException, SQLException {
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
                pst.setString(1, bookId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int bookID = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    Float price = rs.getFloat("Price");
                    String author = rs.getString("Author");
                    String categoryId = cateDao.getCategoryName(rs.getString("CategoryId"));
                    String importDate = rs.getString("ImportDate");
                    String status = rs.getString("Status");
                    String photoCode = rs.getString("PhotoCode");
                    String discription = rs.getString("Discription");
                    int bookQuantity = rs.getInt("BookQuantity");
                    book = new BookDTO(bookID, title, price, author, categoryId, importDate, status, photoCode, discription, bookQuantity);
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

    public boolean updateBookForAdmin(BookDTO bookDTO) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "update tblBook \n"
                        + "set Title = ?, Price = ?, Author = ?, CategoryId = ?, PhotoCode = ?, Discription = ?, BookQuantity = ?, Status = ? \n"
                        + "where BookId = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, bookDTO.getTitle());
                pst.setFloat(2, bookDTO.getPrice());
                pst.setString(3, bookDTO.getAuthor());
                pst.setString(4, bookDTO.getCategoryId());
                pst.setString(5, bookDTO.getPhotoCode());
                pst.setString(6, bookDTO.getDiscription());
                pst.setInt(7, bookDTO.getBookQuantity());
                pst.setString(8, bookDTO.getStatus());
                pst.setInt(9, bookDTO.getBookID());
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

    public boolean deleteBookForAdmin(int bookId) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "update tblBook \n"
                        + "set Status = 'InActive' \n"
                        + "where BookId = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, bookId);
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

    public boolean insertBookForAdmin(BookDTO dto) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "insert into tblBook values(?,?,?,?,?,?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getTitle());
                pst.setFloat(2, dto.getPrice());
                pst.setString(3, dto.getAuthor());
                pst.setString(4, dto.getCategoryId());

                //getToday
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(5, date.toString());

                pst.setString(6, "Active");
                pst.setString(7, dto.getPhotoCode());
                pst.setString(8, dto.getDiscription());
                pst.setInt(9, dto.getBookQuantity());

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

    public int getBookQuantity(int bookId) throws NamingException, SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "select BookQuantity\n"
                        + "from tblBook \n"
                        + "Where BookId = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, bookId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("BookQuantity");
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
        return result;
    }

    public boolean updateBookQuantity(int bookId, int quantity) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "update tblBook \n"
                        + "set BookQuantity = ? \n"
                        + "where BookId = ? ";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, quantity);
                pst.setInt(2, bookId);
                if (pst.execute()) {
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
}
