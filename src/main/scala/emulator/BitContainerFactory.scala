package emulator

class BitContainerFactory
object BitContainerFactory {
  final var counter = 0
  final val instances = new Array[BitContainer](50)

  for (a <- 0 to instances.length - 1)
    instances(a) = new BitContainer()

  def create(value: Int, bitContainer1: BitContainer, bitContainer2: BitContainer): BitContainer =
    {
      val instance = instances(BitContainerFactory.counter)
      BitContainerFactory.counter += 1
      instance.locationCounter= 0
      instance.init(value, bitContainer1, bitContainer2)
      instance
    }

  def createFixed(value: Int): BitContainer =
    {
      new BitContainer(value)
    }

  def create(value: Int): BitContainer =
    {
      val instance = instances(BitContainerFactory.counter)
      BitContainerFactory.counter += 1
      instance.locationCounter= 0
      instance.init(value, 0)
      instance
    }

  def freeInstances = {
    counter = 0
  }
}
