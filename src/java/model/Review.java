/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents the user comment on some item. 
 * This is not annotated as @XMLRootElement as we dont expect this to be used
 * as a root of XML.
 */
public class Review {
  public String content;
  public Rating rating;
  public String author;
  private Date postedOn;

  public Review(String content, String author, Date postedOn, Rating rating) {
    this.content = content;
    this.rating = rating;
    this.author = author;
    this.postedOn = postedOn;
  }
  
  /**
   * Returns the date formatted using SimpleDateFormat and is used by JAXB
   * while generating the XML.
   */
  @XmlElement(name="postedOn")
  public String getPostedOn(){
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
    return sdf.format(postedOn);
  }
  
  /**
   * Reformats the date represented in String into a Date instance. This is used
   * while JAXB is unmarshalling the XML into Java objects.
   */
  public void setPostedOn(String posted) {
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
    try {
      this.postedOn = sdf.parse(posted);
    } catch (ParseException ex) {
      Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Review() {
  }
  
}
