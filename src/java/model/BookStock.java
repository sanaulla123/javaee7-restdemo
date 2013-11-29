/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author msanaull
 */
@XmlRootElement
public class BookStock {
  public Book book;
  public Integer totalUnitsInStock;
}
