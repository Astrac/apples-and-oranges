package astrac.appleandoranges

import org.scalatest.{FlatSpec, Matchers}

class CheckoutSpec extends FlatSpec with Matchers {
  val priceMap = Map[Item, Money](
    Apple -> Money(10),
    Orange -> Money(5)
  )

  val noDiscountsConfig = CheckoutConfig(priceMap.apply, _ => Money(0))

  val orangeDiscountConfig = CheckoutConfig(priceMap.apply, DiscountFunctions.orangesThreePerTwo(_, priceMap.apply))
  val appleDiscountConfig = CheckoutConfig(priceMap.apply, DiscountFunctions.applesTwoPerOne(_, priceMap.apply))
  val combinedDiscountConfig = CheckoutConfig(priceMap.apply, DiscountFunctions.combined(_, priceMap.apply))

  "The Checkout" should "calculate the price for an empty list" in {
    Checkout.checkout(Cart(Seq.empty), noDiscountsConfig) should equal(Money(0))
  }

  it should "calculate the price for a simple cart" in {
    val cart = Cart(Seq(Apple, Apple, Orange, Apple, Orange))

    Checkout.checkout(cart, noDiscountsConfig) should equal(Money(40))
  }

  it should """apply the discount function "Apples two per one"""" in {
    val cart = Cart(Seq(Apple, Apple, Apple, Orange, Orange))

    Checkout.checkout(cart, appleDiscountConfig) should equal(Money(30))
  }

  it should """apply the discount function "Oranges three per two"""" in {
    val cart = Cart(Seq(Apple, Apple, Orange, Orange, Orange, Orange, Orange, Apple))

    Checkout.checkout(cart, orangeDiscountConfig) should equal(Money(50))
  }

  it should "apply the combined discount function" in {
    val cart = Cart(Seq(Apple, Apple, Orange, Orange, Orange, Orange, Orange, Apple))

    Checkout.checkout(cart, combinedDiscountConfig) should equal(Money(40))
  }
}
