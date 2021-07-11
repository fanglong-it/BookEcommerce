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
public class OrderDTO implements Serializable{
    private String username;
    private int bookId;
    private String bookTitle;
    private int quantity;
    private String orderDate;

    public OrderDTO(String username, int bookId, int quantity, String orderDate) {
        this.username = username;
        this.bookId = bookId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public OrderDTO(String username, String bookTitle, int quantity, String orderDate) {
        this.username = username;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
}
