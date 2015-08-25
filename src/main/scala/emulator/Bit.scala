package emulator

import scala.AnyValCompanion
import scala.beans.BeanProperty

class Bit(value: Boolean, address: Int) {
  
   def this(value: Boolean) {
    this(value, 0)
  }

  var intValue: Boolean = value
  @BeanProperty
  var originalAddress: Int = address
  
  def ||(i: Bit): Bit = new Bit(intValue || i.intValue, originalAddress)
  
  def :=(i: Boolean) = intValue = i

}