package astrac.appleandoranges

object Checkout {
  def checkout(cart: Cart, config: CheckoutConfig): Money =
    cart.items.foldLeft(Money(0)) { (acc, cur) =>
      acc + config.priceFn(cur)
    } - config.discountFn(cart.items)
}
