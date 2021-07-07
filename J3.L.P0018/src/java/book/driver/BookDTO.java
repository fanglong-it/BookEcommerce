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
public class BookDTO implements Serializable{
    private int bookID;
    private String title;
    private float price;
    private String author;
    private String categoryId;
    private String importDate;
    private String status;
    private String photoCode;
    private String discription;
    private int bookQuantity;
    

    public BookDTO(int bookID, String title, float price, String author, String categoryId, String importDate, String status, String photoCode) {
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.author = author;
        this.categoryId = categoryId;
        this.importDate = importDate;
        this.status = status;
        this.photoCode = photoCode;
    }
    

    public BookDTO(int bookID, String title, float price, String author, String categoryId,
            String importDate, String status, String photoCode, String discription, int bookQuantity) {
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.author = author;
        this.categoryId = categoryId;
        this.importDate = importDate;
        this.status = status;
        this.photoCode = photoCode;
        this.discription = discription;
        this.bookQuantity = bookQuantity;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
    
    

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhotoCode() {
        return photoCode;
    }

    public void setPhotoCode(String photoCode) {
        this.photoCode = photoCode;
    }
    
    
}
