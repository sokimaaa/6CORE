package com._6core.platform.shopping.cart.spec.rest.v1;

import com._6core.platform.shopping.cart.spec.rest.v1.request.CheckoutShoppingCartRequest;
import com._6core.platform.shopping.cart.spec.rest.v1.response.CheckoutShoppingCartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Validated
@Tag(name = "checkoutCart", description = "the reactive checkoutCart API")
public interface CheckoutShoppingCartApi {

  /**
   * POST /carts/{cartId} : Checkout Cart
   *
   * @param cartId - (required)
   * @param checkoutRequest - (required)
   * @param serverWebExchange - (optional)
   * @return Accepted (status code 202)
   */
  @Operation(
      operationId = "checkoutCart",
      summary = "Checkout the cart",
      tags = {"checkoutCart"},
      responses = {
        @ApiResponse(
            responseCode = "202",
            description = "Accepted",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CheckoutShoppingCartResponse.class))),
        @ApiResponse(responseCode = "400", description = "Incorrect checkout request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Cart not found")
      })
  @PostMapping(
      value = "carts/{cartId}",
      produces = "application/json",
      consumes = "application/json")
  default Mono<ResponseEntity<CheckoutShoppingCartResponse>> checkout(
      @Parameter(name = "cartId", required = true) @PathVariable("cartId") final String cartId,
      @Parameter(name = "cartCheckoutRequest", required = true) @Valid @RequestBody
          final CheckoutShoppingCartRequest checkoutRequest,
      @Parameter(hidden = true) final ServerWebExchange serverWebExchange) {
    final var cartCheckoutResponseMock =
        new CheckoutShoppingCartResponse("-1", "-1", "-1", Boolean.FALSE);
    return Mono.just(ResponseEntity.ok(cartCheckoutResponseMock));
  }
}
