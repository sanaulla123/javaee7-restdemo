/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Binding;
import model.Book;
import model.Books;
import model.Rating;
import model.Review;

/**
 * Testing the Client API by invoking the Book RESTful Webservice.
 */
public class BookRestClient {

  public static void main(String[] args) {

    invokeAllBooksService();
    invokeGetBookService("9781259002465");
    invokeCreateBookService();
    //Trying to read the book details created by the above method.
    invokeGetBookService("9788126538058");
  }

  private static void invokeAllBooksService() {
    System.out.println("****Invoking the All Books Service****");
    //Obtaining the instance of Client which will be entry point to invoking REST Services.
    Client client = ClientBuilder.newClient();

    //Targeting the RESTful Webserivce we want to invoke by capturing it in WebTarget instance.
    WebTarget allBooksTarget = client.target("http://localhost:8080/RestfulDemo/rs/book");

    //Building the request i.e a GET request to the RESTful Webservice defined 
    //by the URI in the WebTarget instance.
    Invocation allBooksInvocation = allBooksTarget.request().buildGet();

    //Invoking the request to the RESTful API and capturing the Response.
    Response response = allBooksInvocation.invoke();

    //As we know that this RESTful Webserivce returns the XML data which can be unmarshalled
    //into the instance of Books by using JAXB.
    Books books = response.readEntity(Books.class);

    for (Book book : books.myBooks) {
      printBook(book);
    }
  }

  private static void invokeGetBookService(String isbn) {
    System.out.println("****Invoking the Get Book Service****");
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/RestfulDemo/rs/book/" + isbn);
    Response response = target.request().buildGet().invoke();
    Book book = response.readEntity(Book.class);
    printBook(book);
  }

  private static void invokeCreateBookService() {
    System.out.println("****Invoking the Create Book Service****");
    
    Calendar calendar = Calendar.getInstance();
    Date today = calendar.getTime();
    calendar.add(Calendar.DATE, -4);
    Date olderDay1  =  calendar.getTime();
    
    Book book = new Book();
    book.authors = Arrays.asList("Gayle Laakmann McDowell");
    book.binding = Binding.PAPERBACK;
    book.isbn = "9788126538058";
    book.numberOfPages = 280;
    book.price = 260.0;
    book.publisher = "Wiley/Goels Computer Hut";
    book.title = "The Google Resume: How to Prepare for a Career and land a Job at Apple, Microsoft, Google, or Any Top Tech Company";
    book.year = 2012;
    book.reviews = (Arrays.asList(new Review("Very Precise and handy", "Shameer", today, Rating.FOUR),
            new Review("Best guide for cover letters, resumes etc", "Harshith", olderDay1, Rating.FIVE)));
    
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/RestfulDemo/rs/book");
    Response response = target.request().buildPost(Entity.entity(book, MediaType.APPLICATION_XML)).invoke();
    System.out.println(response.getLocation().getPath());
  }

  private static String joinList(List<String> list) {
    StringBuilder builder = new StringBuilder();
    for (String str : list) {
      builder.append(str).append(",");
    }
    return builder.toString().substring(0, builder.length() - 1);
  }

  private static void printBook(Book book) {
    System.out.println("Details for: " + book.title);
    System.out.println("Author: " + joinList(book.authors));
    System.out.println("ISBN: " + book.isbn);
    System.out.println("Publisher: " + book.publisher);
    System.out.println("Cost: " + book.price);
    System.out.println("Publication year: " + book.year);
    System.out.println("Reviews: ");
    for (Review review : book.reviews) {
      System.out.println(review.content + " by " + review.author + " posted on " + review.getPostedOn());
    }
  }
}
