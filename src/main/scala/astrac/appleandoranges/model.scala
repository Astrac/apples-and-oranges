package astrac.appleandoranges

sealed trait Item
case object Apple extends Item
case object Orange extends Item

case class Cart(items: Seq[Item])

case class Price(pences: Int) extends AnyVal

case class CheckoutConfig(priceFn: Item => Price)
