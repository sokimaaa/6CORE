package com._6core.platform.shopping.cart.app.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.lib.java.domain.model.cart.immutable.ImmutableShoppingCartV01Impl;
import com._6core.platform.shopping.cart.domain.dto.internal.CartId;
import com._6core.platform.shopping.cart.domain.dto.internal.CheckoutRequest;
import com._6core.platform.shopping.cart.domain.dto.internal.CheckoutResponse;
import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderRequest;
import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderResponse;
import com._6core.platform.shopping.cart.domain.dto.internal.EmptyCartResponse;
import com._6core.platform.shopping.cart.domain.dto.internal.ValidationResponse;
import com._6core.platform.shopping.cart.domain.persistent.out.CheckoutShoppingCartPort;
import com._6core.platform.shopping.cart.domain.persistent.out.CreateOrderGrcpPort;
import com._6core.platform.shopping.cart.domain.validator.Validator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CheckoutCartTest {
  @InjectMocks private CheckoutCart checkoutCart;
  @Mock private CheckoutShoppingCartPort checkoutShoppingCartPort;
  @Mock private CreateOrderGrcpPort grcpPort;
  @Mock private Validator<ShoppingCartV01> validator;

  @Test
  void checkout_validRequest_happyPath() {
    List<String> cartItems = List.of("item1", "item2");
    ImmutableShoppingCartV01Impl cart =
        ImmutableShoppingCartV01Impl.builder().addAllProductIds(cartItems).cartId("testId").build();
    CheckoutRequest inputRequest = new CheckoutRequest("testId");
    CartId id = new CartId(inputRequest.cartId());
    CreateOrderRequest createOrderRequest = new CreateOrderRequest(cartItems);
    CreateOrderResponse createOrderResponse = new CreateOrderResponse("testOrderId", true);
    ValidationResponse validationResponse = new ValidationResponse();
    validationResponse.setIsValid(true);
    when(checkoutShoppingCartPort.get(id)).thenReturn(Mono.just(cart));
    when(validator.validate(cart)).thenReturn(validationResponse);
    when(grcpPort.createOrder(createOrderRequest)).thenReturn(Mono.just(createOrderResponse));
    when(checkoutShoppingCartPort.clean(id)).thenReturn(Mono.just(new EmptyCartResponse()));

    Mono<CheckoutResponse> actual = checkoutCart.checkout(inputRequest);
    assertNotNull(actual);
    StepVerifier.create(actual)
        .consumeNextWith(
            r -> {
              assertEquals("testId", r.cartId());
              assertEquals("testOrderId", r.orderId());
              assertEquals(null, r.transactionId());
              assertEquals(true, r.ok());
            })
        .verifyComplete();
  }
}
