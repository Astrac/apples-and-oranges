package astrac.appleandoranges

import scala.io.StdIn

object Main extends App {

  val priceMap = Map[Item, Money](
    Apple -> Money(60),
    Orange -> Money(25)
  )

  val config = CheckoutConfig(priceMap.apply, DiscountFunctions.combined(_, priceMap.apply))

  println("""Enter "Apple" or "Orange" once per line; an empty line will calculate the total price""")

  val cart = Cart(Iterator
    .iterate(StdIn.readLine("-> "))(_ => StdIn.readLine("-> "))
    .takeWhile(_ != "")
    .flatMap {
      case "Apple" => Some(Apple)
      case "Orange" => Some(Orange)
      case invalid => println(s"Invalid input: $invalid"); None
    }
    .toSeq)

  val price = Checkout.checkout(cart, config)

  println(s"Total price: $price")
}
