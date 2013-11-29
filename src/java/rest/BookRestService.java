package rest;

import db.BookDb;
import java.net.URISyntaxException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.Book;
import model.Books;

/**
 * RESTful Service for operations on Book like adding new book,
 * viewing the data for a book given its ISBN, listing all the books.
 */
@Path("/book")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class BookRestService {
   
  @Context
  UriInfo uriInfo;
  
  /**
   * Service method to return all the books present in the database in the form 
   * of XML. The conversion from Java object to XML is handled by JAXB.
   */
  @GET
  public Books getAllBooks(){
    Books books = BookDb.bookDb.getAllBooks();
    return books;
  }
  
  /**
   * Service method to return the details of the book represented by the ISBN
   * number. The conversion from Book java object to XML is handled by JAXB.
   */
  @GET
  @Path("{isbn}")
  public Book getBook(@PathParam("isbn") String isbn){
    Book book = BookDb.bookDb.getBookByIsbn(isbn);
    if ( book == null ){
      throw new NotFoundException("No book found with the matching ISBN: "+ isbn);
    }
    return book;
  }
  
  /**
   * Service method to add the details of the new book. The data for the details 
   * of the book is sent in the form of XML which is converted to Book object
   * by the JAXB. And return the the details page displaying the details 
   * of the new Book added.
   */
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  public Response createBook(Book book) throws URISyntaxException{
    if ( book == null){
      throw new BadRequestException();
    }
    
    BookDb.bookDb.addToBookDb(book);
    return Response.created(uriInfo.getAbsolutePathBuilder().path(book.isbn).build()).build();
  }
}
