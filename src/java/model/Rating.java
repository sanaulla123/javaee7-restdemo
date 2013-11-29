/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Represents the different levels of rating which a user can give to 
 * any item.
 */
@XmlEnum(Integer.class)
public enum Rating {
  @XmlEnumValue("1") ONE(1), 
  @XmlEnumValue("2") TWO(2), 
  @XmlEnumValue("3") THREE(3), 
  @XmlEnumValue("4") FOUR(4), 
  @XmlEnumValue("5") FIVE(5);
  
  private Rating(int code) {
    this.code = code;
  }
  public int code;
}
