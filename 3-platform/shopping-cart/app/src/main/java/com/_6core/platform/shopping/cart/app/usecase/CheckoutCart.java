package com._6core.platform.shopping.cart.app.usecase;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.dto.internal.CartId;
import com._6core.platform.shopping.cart.domain.dto.internal.CheckoutRequest;
import com._6core.platform.shopping.cart.domain.dto.internal.CheckoutResponse;
import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderResponse;
import com._6core.platform.shopping.cart.domain.mapper.CreateOrderResponse2CheckoutShoppingCartResponse;
import com._6core.platform.shopping.cart.domain.mapper.ShoppingCart2CreateOrderRequest;
import com._6core.platform.shopping.cart.domain.persistent.in.CheckoutUseCase;
import com._6core.platform.shopping.cart.domain.persistent.out.CheckoutShoppingCartPort;
import com._6core.platform.shopping.cart.domain.persistent.out.CreateOrderGrcpPort;
import com._6core.platform.shopping.cart.domain.validator.Validator;
import reactor.core.publisher.Mono;

public class CheckoutCart implements CheckoutUseCase {
  private final CheckoutShoppingCartPort checkoutShoppingCartPort;
  private final CreateOrderGrcpPort grcpPort;
  private final Validator<ShoppingCartV01> validator;

  public CheckoutCart(
      CheckoutShoppingCartPort checkoutShoppingCartPort,
      CreateOrderGrcpPort grcpPort,
      Validator<ShoppingCartV01> validator) {
    this.checkoutShoppingCartPort = checkoutShoppingCartPort;
    this.grcpPort = grcpPort;
    this.validator = validator;
  }

  @Override
  public Mono<CheckoutResponse> checkout(CheckoutRequest checkoutRequest) {
    CartId id = new CartId(checkoutRequest.cartId());
    Mono<CreateOrderResponse> orderResponse =
        checkoutShoppingCartPort
            .get(id)
            .filter(cart -> validator.validate(cart).getIsValid())
            .map(ShoppingCart2CreateOrderRequest.INCTANCE::map)
            .flatMap(order -> grcpPort.createOrder(order));
    checkoutShoppingCartPort.clean(id);
    return orderResponse.map(
        r -> CreateOrderResponse2CheckoutShoppingCartResponse.INCTANCE.map(r, id));
  }
}
