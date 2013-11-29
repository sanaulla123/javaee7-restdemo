/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author msanaull
 */
@XmlRootElement
@XmlSeeAlso(Review.class)
public class Reviews extends ArrayList<Review>{
  @XmlElementWrapper(name="reviews")
  @XmlElement(name="review")
  public List<Review> getReviews(){
    return this;
  }
  
  public void setReviews(List<Review> reviews){
    this.addAll(reviews);
  }
}
