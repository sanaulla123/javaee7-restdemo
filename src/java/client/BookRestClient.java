/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import model.Book;
import model.Books;
import model.Review;

/**
 * Testing the Client API by invoking the Book RESTful Webservice.
 */
public class BookRestClient {

  public static void main(String[] args) {

    invokeAllBooksService();
  }

  private static void invokeAllBooksService() {
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

  private static String joinList(List<String> list) {
    StringBuilder builder = new StringBuilder();
    for (String str : list) {
      builder.append(str).append(",");
    }
    return builder.toString().substring(0, builder.length() - 1);
  }
}
