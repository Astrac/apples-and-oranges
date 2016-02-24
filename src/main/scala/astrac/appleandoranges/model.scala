package astrac.appleandoranges

sealed trait Item
case object Apple extends Item
case object Orange extends Item

case class Cart(items: Seq[Item])

case class Money(pences: Int) extends AnyVal {
  def +(other: Money) = Money(pences + other.pences)
  def -(other: Money) = Money(pences - other.pences)
  def *(times: Int) = Money(pences * times)
  override def toString: String = s"£${pences / 100}.${pences % 100}"
}

case class CheckoutConfig(priceFn: Item => Money, discountFn: Seq[Item] => Money)

object DiscountFunctions {

  def applesTwoPerOne(items: Seq[Item], priceFn: Item => Money) = priceFn(Apple) * (items.count(_ == Apple) / 2)

  def orangesThreePerTwo(items: Seq[Item], priceFn: Item => Money) = priceFn(Orange) * (items.count(_ == Orange) / 3)

  def combined(items: Seq[Item], priceFn: Item => Money) = applesTwoPerOne(items, priceFn) + orangesThreePerTwo(items, priceFn)
}
