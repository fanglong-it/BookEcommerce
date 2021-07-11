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
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String username, String password) throws NamingException, SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select Username, Password, Name, roleId, Status\n"
                        + "from tblUser \n"
                        + "where Username = ? and Password = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String passWord = "*******";
                    String name = rs.getString("Name");
                    String role = rs.getString("roleId");
                    String status = rs.getString("Status");
                    user = new UserDTO(username, passWord, name, "", role, status);
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
        return user;
    }

    public boolean checkUserExist(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "select Username, Password, Name, roleId, Status\n"
                        + "from tblUser \n"
                        + "where Username = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
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
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertUser(UserDTO user) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.makeConnect();
            if (con != null) {
                String sql = "insert into tblUser values(?,?,?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, user.getUsername());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getName());
                pst.setString(4, user.getDob());
                pst.setString(5, "US");
                pst.setString(6, "Active");
                if(pst.executeUpdate() > 0){
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
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
