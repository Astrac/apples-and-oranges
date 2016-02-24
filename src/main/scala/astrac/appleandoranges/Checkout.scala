package astrac.appleandoranges

object Checkout {
  def checkout(cart: Cart, config: CheckoutConfig): Price = cart.items.foldLeft(Price(0)) { (acc, cur) =>
    acc + config.priceFn(cur)
  }
}
