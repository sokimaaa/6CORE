package com._6core.platform.shopping.cart.spec.rest.v1;

import com._6core.platform.shopping.cart.spec.rest.v1.response.CleanShoppingCartResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Validated
@Tag(name = "cleanCart", description = "the reactive cleanCart API")
public interface CleanShoppingCartApi {

  /**
   * DELETE /carts/{cartId}/items : Clean up the entire cart
   *
   * @param cartId - (required)
   * @param serverWebExchange - (optional)
   * @return OK (status code 204, 205)
   */
  @Operation(
      operationId = "cleanCart",
      summary = "Clean up the cart",
      tags = {"cleanCart"},
      responses = {
        @ApiResponse(
            responseCode = "204",
            description = "No content to reset",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CleanShoppingCartResponse.class))),
        @ApiResponse(
            responseCode = "205",
            description = "Reset cart content",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CleanShoppingCartResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Cart not found")
      })
  @DeleteMapping(
      value = "carts/{cartId}/items",
      produces = "application/json",
      consumes = "application/json")
  default Mono<ResponseEntity<CleanShoppingCartResponse>> clean(
      @Parameter(name = "cartId", required = true) @PathVariable("cartId") final String cartId,
      @Parameter(hidden = true) final ServerWebExchange serverWebExchange) {
    final var cleanResponse =
        new CleanShoppingCartResponse("-1", Collections.emptyList(), Boolean.FALSE);
    return Mono.just(ResponseEntity.ok(cleanResponse));
  }

  /**
   * DELETE /carts/{cartId}/items/{itemId} : Clean the item from the cart
   *
   * @param cartId - (required)
   * @param itemId - (required)
   * @param serverWebExchange - (optional)
   * @return OK (status code 204, 205)
   */
  @Operation(
      operationId = "cleanCartItem",
      summary = "Clean the item from cart",
      tags = {"cleanCartItem"},
      responses = {
        @ApiResponse(
            responseCode = "204",
            description = "No content to reset",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CleanShoppingCartResponse.class))),
        @ApiResponse(
            responseCode = "205",
            description = "Reset cart content",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CleanShoppingCartResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Cart or item not found")
      })
  @DeleteMapping(
      value = "carts/{cartId}/items/{itemId}",
      produces = "application/json",
      consumes = "application/json")
  default Mono<ResponseEntity<CleanShoppingCartResponse>> clean(
      @Parameter(name = "cartId", required = true) @PathVariable("cartId") final String cartId,
      @Parameter(name = "itemId", required = true) @PathVariable("itemId") final String itemId,
      @Parameter(hidden = true) final ServerWebExchange serverWebExchange) {
    final var cleanResponse =
        new CleanShoppingCartResponse("-1", Collections.singletonList(itemId), Boolean.FALSE);
    return Mono.just(ResponseEntity.ok(cleanResponse));
  }
}
