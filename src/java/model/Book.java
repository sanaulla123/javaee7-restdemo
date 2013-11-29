/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents the data for a Book. Annotated for use with JAXB.
 */
@XmlRootElement
public class Book {
  
  public String title;
  
  @XmlElementWrapper(name="authors")
  @XmlElement(name="author")
  public List<String> authors;
  
  public String publisher;
  public Integer year;
  public Integer numberOfPages;
  public Double price;
  
  @XmlElementWrapper(name = "reviews")
  @XmlElement(name="review")
  public List<Review> reviews;
  
  public String isbn;
  public Binding binding;
  
  @Override
  public String toString(){
    return title;
  }
}
