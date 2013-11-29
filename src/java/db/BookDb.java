/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Binding;
import model.Book;
import model.BookStock;
import model.Books;
import model.Rating;
import model.Review;

/**
 * Class for populating test data.
 */
public class BookDb {
  
  Books books;
  Map<String, Book> bookIsbnMap;
  Map<Book, BookStock> bookStock;
  
  public static BookDb bookDb = new BookDb();
  
  private BookDb(){
    
    Calendar calendar = Calendar.getInstance();
    Date today = calendar.getTime();
    calendar.add(Calendar.DATE, -4);
    Date olderDay1  =  calendar.getTime();
    calendar.add(Calendar.DATE, -10);
    Date olderDay2 = calendar.getTime();
    
    books = new Books();
    bookIsbnMap = new HashMap<>();
    Book book = new Book();
    book.authors = Arrays.asList("Narasimha Karumanchi");
    book.binding = Binding.PAPERBACK;
    book.isbn = "9780615459813";
    book.numberOfPages = 429;
    book.price = 344.0;
    book.publisher = "CareerMonk Publications";
    book.title = "Data Structures and Algorithms Made Easy 2nd Edition";
    book.year = 2011;
    book.reviews = (Arrays.asList(new Review("Great book", "Abc Xyz", today ,Rating.FOUR), 
            new Review("Needs improvement", "Xyz Pqr", olderDay1, Rating.TWO)));
    
    books.addToBooks(book);
    bookIsbnMap.put(book.isbn, book);
    
    book = new Book();
    book.title = "Java : The Complete Reference 8 Edition";
    book.price = 438.0;
    book.authors = Arrays.asList("Herbert Schildt");
    book.binding = Binding.PAPERBACK;
    book.isbn = "9781259002465";
    book.numberOfPages = 1152;
    book.publisher = "Tata McGraw - Hill Education";
    book.year = 2011;
    book.reviews = (Arrays.asList(new Review("Great Book!!", "Pqr Wxy", today, Rating.FIVE),
            new Review("Must read for Java programmers", "Xyz Abc", olderDay1, Rating.FIVE)));
    
    books.addToBooks(book);
    bookIsbnMap.put(book.isbn, book);
    
  }
  
  /**
   * Adds the new instance of Book to the database i.e the List of books.
   */
  public boolean addToBookDb(Book book){
    books.addToBooks(book);
    bookIsbnMap.put(book.isbn, book);
    
    return true;
  }
  
  /**
   * API to obtain the book details given the ISBN number.
   */
  public Book getBookByIsbn(String isbn){
    return bookIsbnMap.get(isbn);
  }
  
  /**
   * Returns all the book details stored in the list.
   */
  public Books getAllBooks(){
    return books;
  }
}
