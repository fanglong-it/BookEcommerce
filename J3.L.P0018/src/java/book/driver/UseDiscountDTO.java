/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.driver;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class UseDiscountDTO implements Serializable{
    private String username;
    private String discountCode;
    private String discountDate;

    public UseDiscountDTO(String username, String discountCode, String discountDate) {
        this.username = username;
        this.discountCode = discountCode;
        this.discountDate = discountDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(String discountDate) {
        this.discountDate = discountDate;
    }
    
}
