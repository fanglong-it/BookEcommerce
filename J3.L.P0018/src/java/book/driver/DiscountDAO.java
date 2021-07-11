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
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class DiscountDAO implements Serializable {

    public int getDiscountValue(String discountCode) throws NamingException, SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "select DiscountCode,DiscountValue\n"
                        + "from tblDiscount \n"
                        + "where DiscountCode = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, discountCode);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("DiscountValue");
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

    public boolean insertDiscount(DiscountDTO discount) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "insert into tblDiscount values( ?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, discount.getDiscountCode());
                pst.setInt(2, discount.getDiscountValue());
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(3, date.toString());
                if (pst.execute()) {
                    return true;
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
        return false;
    }

}
