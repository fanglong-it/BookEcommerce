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
public class DiscountDTO implements Serializable{
    private String discountCode;
    private int discountValue;
    private String discountDate;

    public DiscountDTO(String discountCode, int discountValue, String discountDate) {
        this.discountCode = discountCode;
        this.discountValue = discountValue;
        this.discountDate = discountDate;
    }

    public String getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(String discountDate) {
        this.discountDate = discountDate;
    }
    
    

    public DiscountDTO(String discountCode, int discountValue) {
        this.discountCode = discountCode;
        this.discountValue = discountValue;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }
    
}
