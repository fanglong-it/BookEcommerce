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
public class UseDiscountDAO implements Serializable {

    public UseDiscountDTO checkUserInDiscount(String username, String discountCode) throws NamingException, SQLException {
        UseDiscountDTO useDiscount = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "select Username,DiscountCode,DiscountDate\n"
                        + "from tblUseDiscount\n"
                        + "where Username = ? and DiscountCode = ? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, discountCode);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String discountDate = rs.getString("DiscountDate");
                    useDiscount = new UseDiscountDTO(username, discountCode, discountDate);
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
        return useDiscount;
    }

    public boolean insertUseDiscount(UseDiscountDTO dto) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = ConnectDB.makeConnect();
            if (cn != null) {
                String sql = "insert into tblUseDiscount values(? , ? , ? )";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getUsername());
                pst.setString(2, dto.getDiscountCode());
                long milis = System.currentTimeMillis();
                Date date = new Date(milis);
                pst.setString(3, date.toString());
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
