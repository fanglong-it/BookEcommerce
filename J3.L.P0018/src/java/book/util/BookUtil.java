/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.util;

import book.driver.BookDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BookUtil {
    
    public boolean checkExistInList(List<BookDTO> book, String id){
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getBookID() == Integer.parseInt(id)){
                return true;
            }
        }
        return false;
    }
    
    public int getBookQuantityInList(List<BookDTO> book, String id){
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getBookID() == Integer.parseInt(id)){
                return book.get(i).getBookQuantity();
            }
        }
        return 0;
    }
    
    public void updateBookInList(List<BookDTO> book, String id, int newQuantity){
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).getBookID() == Integer.parseInt(id)){
                book.get(i).setBookQuantity(newQuantity);
            }
        }
    }
}
