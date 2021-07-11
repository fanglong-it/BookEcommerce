/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.util;

import book.driver.BookDTO;
import book.driver.TotalDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BookUtil {

    public boolean checkExistInList(List<BookDTO> book, String id) {
        for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getBookID() == Integer.parseInt(id)) {
                return true;
            }
        }
        return false;
    }

    public int getBookQuantityInList(List<BookDTO> book, String id) {
        for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getBookID() == Integer.parseInt(id)) {
                return book.get(i).getBookQuantity();
            }
        }
        return 0;
    }

    public void updateBookInList(List<BookDTO> book, String id, int newQuantity) {
        for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getBookID() == Integer.parseInt(id)) {
                book.get(i).setBookQuantity(newQuantity);
            }
        }
    }

    public void deleteBookInList(List<BookDTO> book, String id) {
        for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getBookID() == Integer.parseInt(id)) {
                book.remove(i);
            }
        }
    }

    public float getBookTotalInList(List<BookDTO> book) {
        float result = 0;
        for (int i = 0; i < book.size(); i++) {
            if (book.get(i).getBookQuantity() >= 1) {
                result += book.get(i).getBookQuantity() * book.get(i).getPrice();
            }
        }
        return result;
    }

    public float calculatorTotal(float subtotal, int discount) {
        if (discount > 1) {
            return subtotal - ((subtotal * discount) / 100);
        } else {
            return subtotal;
        }
    }

    public String checkQuantityEnough(List<BookDTO> order, List<BookDTO> database) {
        String result = "Enough";
        for (BookDTO bookOrder : order) {
            for (BookDTO bookDatabase : database) {
                if (bookOrder.getBookID() == bookDatabase.getBookID()) {
                    if (bookDatabase.getBookQuantity() <= bookOrder.getBookQuantity()) {
                        result = bookDatabase.getTitle();
                    }
                }
            }
        }
        return result;
    }
    
    public TotalDTO changeCurrencies(TotalDTO dto){
        TotalDTO newTotal = new TotalDTO(dto.getSubTotal()/23000, dto.getDiscount(),
                dto.getShipping()/23000, dto.getTotal()/23000);
        return newTotal;
    }

}
