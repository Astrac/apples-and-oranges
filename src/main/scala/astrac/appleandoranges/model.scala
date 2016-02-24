package astrac.appleandoranges

sealed trait Item
case object Apple extends Item
case object Orange extends Item

case class Cart(items: Seq[Item])

case class Price(pences: Int) extends AnyVal {
  def +(other: Price) = Price(pences + other.pences)
  override def toString: String = s"£${pences / 100}.${pences % 100}"
}

case class CheckoutConfig(priceFn: Item => Price)
