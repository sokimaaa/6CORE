package com._6core.platform.shopping.cart.spec.rest.v1;

import com._6core.platform.shopping.cart.spec.rest.v1.response.GetShoppingCartItemsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Validated
@Tag(name = "exploreCart", description = "the reactive exploreCart API")
public interface ExploreShoppingCartApi {

  /**
   * GET /carts/{cartId}/items : Entire Cart Items
   *
   * @param cartId - (required)
   * @param serverWebExchange - (optional)
   * @return OK (status code 200)
   */
  @Operation(
      operationId = "exploreCartItems",
      summary = "Exploring the cart items",
      tags = {"exploreCartItems", "exploreCart"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetShoppingCartItemsResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Cart not found")
      })
  @GetMapping(value = "carts/{cartId}/items", produces = "application/json")
  default Mono<ResponseEntity<GetShoppingCartItemsResponse>> show(
      @Parameter(name = "cartId", required = true) @PathVariable("cartId") final String cartId,
      @Parameter(hidden = true) final ServerWebExchange serverWebExchange) {
    final var getItemsResponse =
        new GetShoppingCartItemsResponse("-1", Collections.emptyList(), Boolean.FALSE);
    return Mono.just(ResponseEntity.ok(getItemsResponse));
  }
}
