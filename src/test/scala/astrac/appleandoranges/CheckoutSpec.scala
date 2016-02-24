package astrac.appleandoranges

import org.scalatest.{FlatSpec, Matchers}

class CheckoutSpec extends FlatSpec with Matchers {
  val priceMap = Map[Item, Price](
    Apple -> Price(10),
    Orange -> Price(5)
  )

  val config = CheckoutConfig(priceMap.apply _)

  "The Checkout" should "calculate the price for an empty list" in {
    Checkout.checkout(Cart(Seq.empty), config) should equal(Price(0))
  }

  it should "calculate the price for a simple cart" in {
    val cart = Cart(Seq(Apple, Apple, Orange, Apple, Orange))

    Checkout.checkout(cart, config) should equal(Price(40))
  }
}
