package jaxb;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import db.BookDb;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Book;
import model.Books;
import model.Review;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 * Unit test to test the Marshalling and Unmarshalling of the 
 * XML using JAXB APIs.
 */
public class JaxbTest {
  
  static BookDb bookDb;
  public JaxbTest() {
  }
  
  @BeforeClass
  public static void setup(){
    bookDb = BookDb.bookDb;
  }
  
  /**
   * Marshalling one instance of Book class.
   */
  @Test public void marshalOne() throws JAXBException{
    JAXBContext context = JAXBContext.newInstance(Book.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    m.marshal(bookDb.getAllBooks().myBooks.get(0), System.out);
    

  }
  
  /**
   * Marshalling list of books i.e instance of Books class.
   */
  @Test public void marshalAll() throws JAXBException{
    JAXBContext context = JAXBContext.newInstance(Books.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    m.marshal(bookDb.getAllBooks(), System.out);
    
  }
  
  /**
   * Unmarshalling the XML file into an instance of Books class.
   */
  @Test public void unMarshalAll() throws JAXBException{
    JAXBContext context = JAXBContext.newInstance(Books.class);
    Unmarshaller um = context.createUnmarshaller();
    Books books = (Books)um.unmarshal(new File("D:\\dev\\my_books.xml"));
    for ( Book book : books.myBooks){
      System.out.println("Details for: "+book.title);
      System.out.println("Author: "+ joinList(book.authors));
      System.out.println("ISBN: "+ book.isbn);
      System.out.println("Publisher: "+ book.publisher);
      System.out.println("Cost: "+book.price);
      System.out.println("Publication year: "+book.year);
      System.out.println("Reviews: ");
      for ( Review review : book.reviews){
        System.out.println(review.content+" by "+ review.author+" posted on "+review.getPostedOn());
      }
    }
  }
  
  private String joinList(List<String> list){
    StringBuilder builder = new StringBuilder();
    for ( String str : list){
      builder.append(str).append(",");
    }
    return builder.toString().substring(0, builder.length()-1);
  }
}