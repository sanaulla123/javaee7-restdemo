/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Represents the type of binding of the book.
 */
@XmlEnum(String.class)
public enum Binding {
  @XmlEnumValue("Paperback") PAPERBACK ("Paperback"), 
  @XmlEnumValue("Hardcover") HARDCOVER ("Hardcover");

  private Binding(String displayString) {
    this.displayString = displayString;
  }
  
  public String displayString;
}
