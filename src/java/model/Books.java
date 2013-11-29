/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Container for multiple books
 */
@XmlRootElement
public class Books{
  
  @XmlElement(name="book")
  public List<Book> myBooks = new ArrayList<>();
  
  public void addToBooks(Book book){
    myBooks.add(book);
  }
  public void setBooks(List<Book> bks){
    myBooks.addAll(bks);
  }
}
