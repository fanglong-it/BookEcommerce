/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.driver;

/**
 *
 * @author Admin
 */
public class TotalDTO {
    private float subTotal;
    private float discount;
    private float shipping;
    private float total;

    public TotalDTO() {
    }

    
    
    public TotalDTO(float subTotal, float discount, float shipping, float total) {
        this.subTotal = subTotal;
        this.discount = discount;
        this.shipping = shipping;
        this.total = total;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getShipping() {
        return shipping;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
