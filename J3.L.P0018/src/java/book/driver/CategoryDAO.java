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
public class CategoryDAO implements Serializable {

    public List<CategoryDTO> getAllCategory() throws SQLException, NamingException {
        List<CategoryDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select CategoryId, CategoryName\n"
                        + "from tblCategory";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String categoryId = rs.getString("CategoryId");
                    String categoryName = rs.getString("CategoryName");
                    CategoryDTO cate = new CategoryDTO(categoryId, categoryName);
                    list.add(cate);
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
    
    public String getCategoryName(String CategoryId) throws SQLException, NamingException {
        String result = "";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select CategoryId, CategoryName\n"
                        + "from tblCategory \n"
                        + "Where categoryId like ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, CategoryId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("CategoryName");
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
        return result;
    }
}
