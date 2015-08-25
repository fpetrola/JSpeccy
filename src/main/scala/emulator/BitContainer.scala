
package emulator

import scala.AnyValCompanion
import scala.beans.BeanProperty

object BitContainer {
  final val maxLocations = 20
  final var emptyLocations = new Array[Int](maxLocations * 16)
  final val mem = new Array[Int](65536 * 8 + 16)
  final val memBitContainers = new Array[BitContainer](65536)

  def init() {
    for (a <- 0 to mem.length - 1)
      BitContainer.mem(a) = a

    for (a <- 0 to memBitContainers.length - 1)
      memBitContainers(a) = new BitContainer(0, a)
  }

  init()
}
class BitContainer(value: Int, address: Int) {
 
  @BeanProperty
  var memorySource = address != 0
  final var locations = new Array[Int](BitContainer.emptyLocations.length)
  var _locationCounter = 0

  def locationCounter_=(_locationCounter: Int) = {
    this._locationCounter = _locationCounter
    val max = 10
    if (locationCounter > max - 1) {
      var newCounter = max / 2
      Array.copy(locations, newCounter * 8, locations, 0, (_locationCounter - newCounter) * 8)
      this._locationCounter = newCounter
      Array.copy(BitContainer.emptyLocations, 0, locations, newCounter * 8, locations.length - newCounter * 8)
    }
  }
  def locationCounter = this._locationCounter

  final var timestamp = Z80.decodedOpcodes

  def this(value: Int) {
    this(value, 0)
  }

  def this(value: Long) {
    this(value.intValue(), 0)
  }

  def this() {
    this(0)
  }

  def init(value: Int, bitContainer1: BitContainer, bitContainer2: BitContainer, bits: Int = 16) {
    intValue = value

    var start = locationCounter * 8

    if (bitContainer1 != this) {
      start = bitContainer1.locationCounter * 8
      Array.copy(bitContainer1.locations, 0, locations, 0, start)
      locationCounter += bitContainer1.locationCounter
    }

    Array.copy(bitContainer2.locations, 0, locations, start, bitContainer2.locationCounter * 8)
    locationCounter += bitContainer2.locationCounter

    timestamp = bitContainer1.timestamp
  }

  def init(value: Int, address: Int) {
    intValue = value
    locationCounter = if (address != 0 /*&& address > 32767*/ ) 1 else 0
    //    locationCounter = 2
    originalAddress = address
    //    clearLocations
    Array.copy(BitContainer.mem, address * 8, locations, 0, 16)
    //    timestamp = Z80.decodedOpcodes
  }

  def clearLocations {
    Array.copy(BitContainer.emptyLocations, 0, locations, 0, locations.length)
  }

  @BeanProperty
  final var intValue: Int = value
  @BeanProperty
  final var originalAddress: Int = address

  init(value, address)

  def toInt: Int = intValue

  def <(i: Int): Bit = new Bit(intValue < i, originalAddress)
  def >(i: Int): Bit = new Bit(intValue > i, originalAddress)
  //  def ==(i: Int): Bit = new Bit(intValue == i, originalAddress)
  //  def !=(i: Int): Bit = new Bit(intValue != i, originalAddress)

  def !=(i: Int): Boolean = intValue != i
  def ==(i: Int): Boolean = intValue == i

  def >>>=(i: Int) = intValue >>>= i
  def ^=(i: Int) = intValue ^= i
  def &=(i: Int) = intValue &= i
  def |=(i: Int) = intValue |= i
  def :=(i: BitContainer) = {
    originalAddress = i.originalAddress
    locationCounter = 0
    intValue = i.intValue
    init(i.intValue, this, i)
  }
  def :=(i: Int) = {
    intValue = i
    locationCounter = 0
    clearLocations
  }

  def +=(i: Int) = intValue += i
  def -=(i: Int) = intValue -= i

  def >>>=(i: BitContainer) = intValue >>>= i.intValue
  def &=(i: BitContainer) = {
    intValue &= i.intValue
    init(intValue, this, i)
  }
  def ^=(i: BitContainer) = intValue ^= i.intValue
  def |=(i: BitContainer) = intValue |= i.intValue
  def +=(i: BitContainer) = intValue += i.intValue
  def -=(i: BitContainer) = intValue -= i.intValue

  def &(i: BitContainer): BitContainer = BitContainerFactory.create(intValue & i.intValue, this, i)
  def |(i: BitContainer): BitContainer = BitContainerFactory.create(intValue | i.intValue, this, i)
  def +(i: BitContainer): BitContainer = BitContainerFactory.create(intValue + i.intValue, this, i)
  def -(i: BitContainer): BitContainer = BitContainerFactory.create(intValue - i.intValue, this, i)
  def ^(i: BitContainer): BitContainer = BitContainerFactory.create(intValue ^ i.intValue, this, i)
  def <<(i: BitContainer): BitContainer = BitContainerFactory.create(intValue << i.intValue, this, i)
  def >>>(i: BitContainer): BitContainer = BitContainerFactory.create(intValue >>> i.intValue, this, i)

  def peek() = {
    timestamp = Z80.decodedOpcodes
  }

  def poke8(i: BitContainer) = {
    //    if (originalAddress > 16384 && originalAddress < 16384 + 6912)
    //      locationCounter = 0
    //    else if (i.locationCounter > 0)
    //      i.locationCounter = 1

    timestamp = i.timestamp;
    locationCounter= 0

    init(i.intValue, this, i, 8)
  }

  def poke16(i: BitContainer) = {
    timestamp = i.timestamp;
    init(i.intValue, this, i)
  }
}


